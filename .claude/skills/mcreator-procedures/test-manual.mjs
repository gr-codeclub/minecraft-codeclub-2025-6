/**
 * Manual test of XML builder
 */

import { XMLBuilder, BlockFactory } from './lib/xml-builder.mjs';

// Test 1: Recreate DexterBlock procedure
console.log('Test 1: Recreate DexterBlock extending block procedure\n');
console.log('Expected from DexterBlockEntityWalksOnTheBlock.mod.json:');
console.log('<xml xmlns="https://developers.google.com/blockly/xml"><block type="event_trigger" deletable="false" x="457" y="34"><field name="trigger">no_ext_trigger</field><next><block type="block_add"><value name="block"><block type="mcitem_allblocks"><field name="value">CUSTOM:DexterBlock</field></block></value><value name="x"><block type="math_dual_ops"><field name="OP">ADD</field><value name="A"><block type="coord_x"></block></value><value name="B"><block type="math_number"><field name="NUM">1</field></block></value></block></value><value name="y"><block type="coord_y"></block></value><value name="z"><block type="coord_z"></block></value></block></next></block></xml>');

console.log('\n\nGenerated:');

const builder = new XMLBuilder();
builder.createProcedure();

// Calculate X = event x + 1
const x = BlockFactory.mathOp(
  'ADD',
  BlockFactory.coordinate('x'),
  BlockFactory.number(1)
);

// Y and Z unchanged
const y = BlockFactory.coordinate('y');
const z = BlockFactory.coordinate('z');

// Block type
const block = BlockFactory.blockType('CUSTOM:DexterBlock');

// Place block
const placeBlock = BlockFactory.placeBlock(block, x, y, z);

builder.addStatement(placeBlock);

const xml = builder.build();
console.log(xml);

// Test 2: Dice roll procedure
console.log('\n\n' + '='.repeat(80));
console.log('\nTest 2: Dice roll (1d6) procedure\n');

const builder2 = new XMLBuilder();
builder2.createProcedure();

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

builder2.addStatement(chat);

const xml2 = builder2.build();
console.log('Generated dice roll procedure:');
console.log(xml2);

console.log('\n✓ Manual tests complete!');
