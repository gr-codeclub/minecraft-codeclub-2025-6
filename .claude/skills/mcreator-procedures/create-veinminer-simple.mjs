/**
 * Create Vein Miner Procedure (Simplified Version)
 *
 * This version checks adjacent blocks in 6 directions (up, down, north, south, east, west)
 * and breaks matching blocks, dropping them at player's feet.
 * Limit: checks ~26 blocks in a 3x3x3 cube for safety and simplicity.
 */

import { XMLBuilder, BlockNode, BlockFactory } from './lib/xml-builder.mjs';
import { ProcedureFileManager } from './lib/file-manager.mjs';

// Create the procedure
const builder = new XMLBuilder();
builder.createProcedure();

// Get the block type that was broken
const brokenBlock = new BlockNode('world_data_blockat')
  .addValue('x', BlockFactory.coordinate('x'))
  .addValue('y', BlockFactory.coordinate('y'))
  .addValue('z', BlockFactory.coordinate('z'));

// Get player position for dropping items
const playerX = new BlockNode('entity_pos_x')
  .addValue('entity', BlockFactory.entityFromDeps());
const playerY = new BlockNode('entity_pos_y')
  .addValue('entity', BlockFactory.entityFromDeps());
const playerZ = new BlockNode('entity_pos_z')
  .addValue('entity', BlockFactory.entityFromDeps());

// Check 3x3x3 area (27 positions, minus center = 26 blocks)
// This is more reasonable than 5x5x5 (125 blocks)
const checks = [];

for (let dx = -1; dx <= 1; dx++) {
  for (let dy = -1; dy <= 1; dy++) {
    for (let dz = -1; dz <= 1; dz++) {
      // Skip center (the block we just broke)
      if (dx === 0 && dy === 0 && dz === 0) continue;

      // Calculate position
      const checkX = BlockFactory.mathOp('ADD', BlockFactory.coordinate('x'), BlockFactory.number(dx));
      const checkY = BlockFactory.mathOp('ADD', BlockFactory.coordinate('y'), BlockFactory.number(dy));
      const checkZ = BlockFactory.mathOp('ADD', BlockFactory.coordinate('z'), BlockFactory.number(dz));

      // Get block at position
      const blockAtPos = new BlockNode('world_data_blockat')
        .addValue('x', checkX)
        .addValue('y', checkY)
        .addValue('z', checkZ);

      // Check if it matches
      const isMatch = new BlockNode('compare_mcblocks')
        .addValue('a', blockAtPos)
        .addValue('b', brokenBlock);

      // Remove and drop at player feet
      const removeAndDrop = new BlockNode('block_remove_drop')
        .addValue('x', checkX)
        .addValue('y', checkY)
        .addValue('z', checkZ)
        .addValue('x2', playerX)
        .addValue('y2', playerY)
        .addValue('z2', playerZ);

      // Wrap in conditional
      const ifBlock = BlockFactory.ifStatement(isMatch, removeAndDrop);
      checks.push(ifBlock);
    }
  }
}

// Chain all checks together
for (let i = 0; i < checks.length - 1; i++) {
  checks[i].setNext(checks[i + 1]);
}

// Add to builder
builder.addStatement(checks[0]);

// Build XML
const xml = builder.build();

console.log(`Generated XML with ${checks.length} block checks (3x3x3 area)`);
console.log('');

// Save to file
const modFolder = process.cwd().includes('dextermod') ? process.cwd() : 'dextermod';
const fileManager = new ProcedureFileManager(modFolder);

try {
  const result = await fileManager.createProcedure('VeinMiner', xml);

  console.log('✓ Vein Miner procedure created successfully!');
  console.log(`  File: ${result.filePath}`);
  console.log(`  Scan area: 3x3x3 blocks (26 positions checked)`);
  console.log(`  Behavior: Breaks matching blocks and drops at player feet`);
  console.log();
  console.log('Next steps:');
  console.log('  1. Open MCreator and reload workspace');
  console.log('  2. Create or edit a tool item (pickaxe, shovel, etc.)');
  console.log('  3. In the item triggers tab, set:');
  console.log('     "When block destroyed with tool" → VeinMiner');
  console.log('  4. Build and test: ./gradlew runClient');
  console.log();
  console.log('How it works:');
  console.log('  - When you break a block, it checks all 26 surrounding blocks');
  console.log('  - If they match the broken block type, they break too');
  console.log('  - All items drop at your feet for easy collection');
  console.log('  - Perfect for mining ore veins!');
  console.log();
  console.log('Note: This version checks a 3x3x3 cube. For larger veins,');
  console.log('      you would need variables/recursion (Phase 3).');

} catch (error) {
  console.error('Error:', error.message);

  // If we're not in the right directory, provide helpful message
  if (error.message.includes('Could not find')) {
    console.error();
    console.error('Tip: Run this from your mod directory:');
    console.error('  cd dextermod');
    console.error('  node ../.claude/skills/mcreator-procedures/create-veinminer-simple.mjs');
  }

  process.exit(1);
}
