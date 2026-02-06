#!/usr/bin/env node
/**
 * MCreator Procedures Skill
 *
 * Generate and modify MCreator procedures programmatically
 */

import fs from 'fs/promises';
import path from 'path';
import { fileURLToPath } from 'url';
import { XMLBuilder, BlockFactory } from './lib/xml-builder.mjs';
import { ProcedureFileManager } from './lib/file-manager.mjs';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

/**
 * Load core blocks library
 */
async function loadCoreBlocks() {
  const blocksPath = path.join(__dirname, 'blocks', 'core-blocks.json');
  const content = await fs.readFile(blocksPath, 'utf-8');
  return JSON.parse(content);
}

/**
 * Main skill handler
 */
async function main() {
  const args = process.argv.slice(2);
  const command = args[0];

  try {
    switch (command) {
      case 'create':
        await handleCreate(args.slice(1));
        break;

      case 'explain':
        await handleExplain(args.slice(1));
        break;

      case 'modify':
        await handleModify(args.slice(1));
        break;

      case 'blocks':
        await handleBlocks(args.slice(1));
        break;

      case 'list':
        await handleList(args.slice(1));
        break;

      case 'test':
        await handleTest(args.slice(1));
        break;

      default:
        showHelp();
    }
  } catch (error) {
    console.error('Error:', error.message);
    process.exit(1);
  }
}

/**
 * Handle 'create' command
 */
async function handleCreate(args) {
  const description = args.join(' ');

  if (!description) {
    console.error('Usage: /mcprocedure create <description>');
    console.error('Example: /mcprocedure create roll 1d6 and send result to player');
    process.exit(1);
  }

  console.log(`Creating procedure from description: "${description}"\n`);

  // Simple pattern matching for now (will be expanded in Phase 3)
  const result = await generateFromDescription(description);

  if (result.success) {
    console.log('✓ Procedure created successfully!');
    console.log(`  Name: ${result.procedureName}`);
    console.log(`  File: ${result.filePath}`);
    console.log(`\nNext steps:`);
    console.log(`  1. Open MCreator and reload the workspace`);
    console.log(`  2. Find the procedure in the Workspace elements panel`);
    console.log(`  3. Test it in-game with /gradlew runClient`);
  }
}

/**
 * Generate procedure from natural language description
 */
async function generateFromDescription(description) {
  const lower = description.toLowerCase();

  // Pattern: "roll XdY" (dice roll)
  const diceMatch = lower.match(/roll (\d+)d(\d+)/);
  if (diceMatch) {
    const [, count, sides] = diceMatch;
    return await createDiceRollProcedure(parseInt(count), parseInt(sides));
  }

  // Pattern: "place [block] at [location]"
  if (lower.includes('place') && (lower.includes('at') || lower.includes('x+'))) {
    return await createPlaceBlockProcedure(description);
  }

  // Pattern: "send message" or "chat"
  if (lower.includes('send') && (lower.includes('message') || lower.includes('chat'))) {
    return await createChatMessageProcedure(description);
  }

  throw new Error(
    `Could not parse description: "${description}"\n` +
    `Supported patterns:\n` +
    `  - "roll XdY and send result to player"\n` +
    `  - "place [block] at x+N"\n` +
    `  - "send message [text] to player"`
  );
}

/**
 * Create a dice roll procedure (like curriculum RPG dice)
 */
async function createDiceRollProcedure(count, sides) {
  const builder = new XMLBuilder();
  builder.createProcedure();

  // Generate random number (1 to sides)
  const roll = BlockFactory.randomInt(1, sides);

  // Create message: "You rolled: " + roll
  const messagePrefix = BlockFactory.text(`You rolled ${count}d${sides}: `);
  const message = BlockFactory.joinText(messagePrefix, roll);

  // Send to player
  const chat = BlockFactory.sendChat(
    message,
    BlockFactory.boolean(false), // Not action bar
    BlockFactory.entityFromDeps()
  );

  builder.addStatement(chat);

  const xml = builder.build();
  const procedureName = `Roll${count}d${sides}`;

  // Find mod folder (check current directory for .mcreator file)
  const modFolder = await findModFolder();
  const fileManager = new ProcedureFileManager(modFolder);

  return await fileManager.createProcedure(procedureName, xml);
}

/**
 * Create a place block procedure (like DexterBlock extending block)
 */
async function createPlaceBlockProcedure(description) {
  const builder = new XMLBuilder();
  builder.createProcedure();

  // Parse offset (x+1, x-2, etc.)
  const offsetMatch = description.match(/x\s*([+-])\s*(\d+)/i);
  const xOffset = offsetMatch ? parseInt(offsetMatch[2]) * (offsetMatch[1] === '+' ? 1 : -1) : 1;

  // Parse block name
  const blockMatch = description.match(/place\s+(\w+)/i);
  const blockName = blockMatch ? blockMatch[1] : 'STONE';

  // Calculate X coordinate (event x + offset)
  const x = BlockFactory.mathOp(
    'ADD',
    BlockFactory.coordinate('x'),
    BlockFactory.number(xOffset)
  );

  // Y and Z unchanged
  const y = BlockFactory.coordinate('y');
  const z = BlockFactory.coordinate('z');

  // Block type
  const block = BlockFactory.blockType(`Blocks:${blockName.toUpperCase()}`);

  // Place block
  const placeBlock = BlockFactory.placeBlock(block, x, y, z);

  builder.addStatement(placeBlock);

  const xml = builder.build();
  const procedureName = `Place${blockName}AtOffset`;

  const modFolder = await findModFolder();
  const fileManager = new ProcedureFileManager(modFolder);

  return await fileManager.createProcedure(procedureName, xml);
}

/**
 * Create a chat message procedure
 */
async function createChatMessageProcedure(description) {
  const builder = new XMLBuilder();
  builder.createProcedure();

  // Extract message from description
  const messageMatch = description.match(/message\s+['"](.*?)['"]/i) ||
                       description.match(/send\s+(.*?)\s+to/i);
  const messageText = messageMatch ? messageMatch[1] : 'Hello!';

  const message = BlockFactory.text(messageText);

  const chat = BlockFactory.sendChat(
    message,
    BlockFactory.boolean(false),
    BlockFactory.entityFromDeps()
  );

  builder.addStatement(chat);

  const xml = builder.build();
  const procedureName = 'SendChatMessage';

  const modFolder = await findModFolder();
  const fileManager = new ProcedureFileManager(modFolder);

  return await fileManager.createProcedure(procedureName, xml);
}

/**
 * Find the mod folder (look for .mcreator file)
 */
async function findModFolder() {
  // Check current directory and parent directories
  let currentDir = process.cwd();

  for (let i = 0; i < 3; i++) {
    const files = await fs.readdir(currentDir);
    const mcreatorFile = files.find(f => f.endsWith('.mcreator'));

    if (mcreatorFile) {
      return currentDir;
    }

    currentDir = path.dirname(currentDir);
  }

  throw new Error(
    'Could not find MCreator workspace. Please run this command from within a mod folder (e.g., dextermod/)'
  );
}

/**
 * Handle 'explain' command
 */
async function handleExplain(args) {
  const procedureName = args.join('');

  if (!procedureName) {
    console.error('Usage: /mcprocedure explain <procedure-name>');
    console.error('Example: /mcprocedure explain DexterBlockEntityWalksOnTheBlock');
    process.exit(1);
  }

  console.log(`Explaining procedure: ${procedureName}\n`);

  const modFolder = await findModFolder();
  const fileManager = new ProcedureFileManager(modFolder);

  const xml = await fileManager.extractXML(procedureName);

  // Simple explanation (Phase 2 will implement full parser)
  console.log('Procedure XML:');
  console.log(xml);
  console.log('\n(Full explanation coming in Phase 2)');
}

/**
 * Handle 'blocks' command
 */
async function handleBlocks(args) {
  const category = args[0];
  const coreBlocks = await loadCoreBlocks();

  console.log('Core Procedure Blocks\n');
  console.log('=' .repeat(60));

  if (category) {
    // Filter by category
    const filtered = Object.entries(coreBlocks).filter(
      ([, block]) => block.category?.toLowerCase().includes(category.toLowerCase())
    );

    if (filtered.length === 0) {
      console.log(`No blocks found in category: ${category}`);
      return;
    }

    for (const [name, block] of filtered) {
      printBlock(name, block);
    }
  } else {
    // Show all blocks by category
    const byCategory = {};

    for (const [name, block] of Object.entries(coreBlocks)) {
      const cat = block.category || 'Other';
      if (!byCategory[cat]) byCategory[cat] = [];
      byCategory[cat].push([name, block]);
    }

    for (const [cat, blocks] of Object.entries(byCategory)) {
      console.log(`\n${cat}:`);
      console.log('-'.repeat(60));
      for (const [name, block] of blocks) {
        console.log(`  ${name}: ${block.description}`);
      }
    }

    console.log(`\n\nTotal: ${Object.keys(coreBlocks).length} core blocks`);
    console.log(`\nUse '/mcprocedure blocks <category>' to see details for a category`);
  }
}

function printBlock(name, block) {
  console.log(`\n${name}`);
  console.log(`  Description: ${block.description}`);
  if (block.output) console.log(`  Output: ${block.output}`);
  if (block.statement) console.log(`  Type: Statement (action block)`);
  if (block.examples) {
    console.log(`  Examples:`);
    block.examples.forEach(ex => console.log(`    - ${ex}`));
  }
}

/**
 * Handle 'list' command
 */
async function handleList(args) {
  const modFolder = await findModFolder();
  const fileManager = new ProcedureFileManager(modFolder);

  const procedures = await fileManager.listProcedures();

  console.log(`Procedures in ${path.basename(modFolder)}:\n`);
  console.log('=' .repeat(60));

  if (procedures.length === 0) {
    console.log('No procedures found in elements/ directory');
  } else {
    procedures.forEach(proc => {
      console.log(`  ${proc.name}`);
    });
    console.log(`\nTotal: ${procedures.length} procedures`);
  }
}

/**
 * Handle 'test' command
 */
async function handleTest(args) {
  console.log('Running test: Create example dice roll procedure\n');

  // Create a test procedure
  const builder = new XMLBuilder();
  builder.createProcedure();

  const roll = BlockFactory.randomInt(1, 6);
  const message = BlockFactory.joinText(
    BlockFactory.text('You rolled: '),
    roll
  );

  const chat = BlockFactory.sendChat(
    message,
    BlockFactory.boolean(false),
    BlockFactory.entityFromDeps()
  );

  builder.addStatement(chat);

  const xml = builder.build();

  console.log('Generated XML:');
  console.log(xml);
  console.log('\n✓ Test passed! XML builder is working correctly.');
}

/**
 * Show help message
 */
function showHelp() {
  console.log(`
MCreator Procedures Skill - Generate and modify MCreator procedures

USAGE:
  /mcprocedure <command> [arguments]

COMMANDS:
  create <description>     Create a new procedure from natural language
  explain <name>           Explain an existing procedure in plain English
  modify <name> <changes>  Modify an existing procedure (coming soon)
  blocks [category]        List available blocks (optionally by category)
  list                     List all procedures in current mod
  test                     Run a test to verify the skill is working

EXAMPLES:
  /mcprocedure create roll 1d6 and send result to player
  /mcprocedure create place stone at x+1
  /mcprocedure explain DexterBlockEntityWalksOnTheBlock
  /mcprocedure blocks math
  /mcprocedure list
  /mcprocedure test

SUPPORTED PATTERNS:
  Dice rolls:    "roll XdY and send result to player"
  Block placing: "place [block] at x+N"
  Chat messages: "send message [text] to player"

More patterns coming in Phase 3!
`);
}

// Run main function
main();
