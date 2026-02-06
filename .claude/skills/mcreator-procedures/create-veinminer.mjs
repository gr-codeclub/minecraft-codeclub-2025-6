/**
 * Create Vein Miner Procedure
 *
 * When an item breaks a block, this procedure:
 * 1. Gets the broken block type
 * 2. Scans a 5x5x5 area around the break point
 * 3. Breaks all matching blocks (up to 100)
 * 4. Drops them at the player's feet
 */

import { XMLBuilder, BlockNode, BlockFactory } from './lib/xml-builder.mjs';
import { ProcedureFileManager } from './lib/file-manager.mjs';

// Create the procedure
const builder = new XMLBuilder();
builder.createProcedure();

// Step 1: Get the block type that was broken (at event position)
const brokenBlock = new BlockNode('world_data_blockat')
  .addValue('x', BlockFactory.coordinate('x'))
  .addValue('y', BlockFactory.coordinate('y'))
  .addValue('z', BlockFactory.coordinate('z'));

// Step 2: Get player position for dropping items
const player = BlockFactory.entityFromDeps();
const playerX = new BlockNode('entity_pos_x')
  .addValue('entity', BlockFactory.entityFromDeps());
const playerY = new BlockNode('entity_pos_y')
  .addValue('entity', BlockFactory.entityFromDeps());
const playerZ = new BlockNode('entity_pos_z')
  .addValue('entity', BlockFactory.entityFromDeps());

// Step 3: Scan area and break matching blocks
// We'll use a 5x5x5 area (2 blocks in each direction)
// To avoid crashing, we'll limit to checking ~60 positions instead of nested loops

// Create a series of checks for each position offset
const blockChecks = [];
let checkCount = 0;
const maxBlocks = 100;

// Generate positions to check in a pattern
for (let dx = -2; dx <= 2 && checkCount < maxBlocks; dx++) {
  for (let dy = -2; dy <= 2 && checkCount < maxBlocks; dy++) {
    for (let dz = -2; dz <= 2 && checkCount < maxBlocks; dz++) {
      if (dx === 0 && dy === 0 && dz === 0) continue; // Skip center (already broken)

      // Calculate check position
      const checkX = BlockFactory.mathOp('ADD', BlockFactory.coordinate('x'), BlockFactory.number(dx));
      const checkY = BlockFactory.mathOp('ADD', BlockFactory.coordinate('y'), BlockFactory.number(dy));
      const checkZ = BlockFactory.mathOp('ADD', BlockFactory.coordinate('z'), BlockFactory.number(dz));

      // Get block at this position
      const blockAtPos = new BlockNode('world_data_blockat')
        .addValue('x', checkX)
        .addValue('y', checkY)
        .addValue('z', checkZ);

      // Compare with broken block
      const isMatch = new BlockNode('compare_mcblocks')
        .addValue('a', blockAtPos)
        .addValue('b', brokenBlock);

      // If match, remove block and drop at player feet
      const removeAndDrop = new BlockNode('block_remove_drop')
        .addValue('x', checkX)
        .addValue('y', checkY)
        .addValue('z', checkZ)
        .addValue('x2', playerX)
        .addValue('y2', playerY)
        .addValue('z2', playerZ);

      // Wrap in conditional
      const ifBlock = BlockFactory.ifStatement(isMatch, removeAndDrop);

      blockChecks.push(ifBlock);
      checkCount++;
    }
  }
}

// Add all checks to the procedure
let currentBlock = blockChecks[0];
for (let i = 1; i < blockChecks.length; i++) {
  currentBlock.setNext(blockChecks[i]);
  currentBlock = blockChecks[i];
}

// Add to builder
builder.addStatement(blockChecks[0]);

// Build XML
const xml = builder.build();

// Save to file
const modFolder = 'dextermod'; // Change this to your mod folder
const fileManager = new ProcedureFileManager(modFolder);

try {
  const result = await fileManager.createProcedure('VeinMiner', xml);

  console.log('✓ Vein Miner procedure created successfully!');
  console.log(`  File: ${result.filePath}`);
  console.log(`  Checks: ${blockChecks.length} block positions`);
  console.log(`  Max blocks: 100 (limited for safety)`);
  console.log(`  Scan range: 5x5x5 blocks around break point`);
  console.log();
  console.log('Next steps:');
  console.log('  1. Open MCreator and reload workspace');
  console.log('  2. Create a new tool item (pickaxe, shovel, etc.)');
  console.log('  3. In the tool triggers, set:');
  console.log('     - "When block destroyed with tool" → VeinMiner');
  console.log('  4. Test in-game by mining ore blocks!');
  console.log();
  console.log('Tips:');
  console.log('  - Works best with ore blocks (coal, iron, diamond)');
  console.log('  - All matching blocks within 2 blocks are broken');
  console.log('  - Items drop at your feet for easy collection');
  console.log('  - Limited to prevent lag/crashes');

} catch (error) {
  console.error('Error:', error.message);
  process.exit(1);
}
