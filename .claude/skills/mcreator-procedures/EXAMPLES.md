# MCreator Procedures Skill - Examples

This document shows concrete examples of procedures created with the skill.

## Example 1: Extending Block (DexterBlock Recreation)

**Goal:** Recreate the existing DexterBlock procedure that places a block at x+1 when you walk.

**Command:**
```bash
cd dextermod
/mcprocedure create place DexterBlock at x+1
```

**What it does:**
1. Gets event coordinates (x, y, z)
2. Adds 1 to the X coordinate
3. Places CUSTOM:DexterBlock at (x+1, y, z)

**Generated XML:**
```xml
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="event_trigger" deletable="false" x="40" y="40">
    <field name="trigger">no_ext_trigger</field>
    <next>
      <block type="block_add">
        <value name="block">
          <block type="mcitem_allblocks">
            <field name="value">CUSTOM:DexterBlock</field>
          </block>
        </value>
        <value name="x">
          <block type="math_dual_ops">
            <field name="OP">ADD</field>
            <value name="A">
              <block type="coord_x"></block>
            </value>
            <value name="B">
              <block type="math_number">
                <field name="NUM">1</field>
              </block>
            </value>
          </block>
        </value>
        <value name="y">
          <block type="coord_y"></block>
        </value>
        <value name="z">
          <block type="coord_z"></block>
        </value>
      </block>
    </next>
  </block>
</xml>
```

**Visual Blockly Representation:**
```
[Event Trigger: no_ext_trigger]
  └─> [Place Block]
       ├─ block: [DexterBlock]
       ├─ x: [coord_x] + [1]
       ├─ y: [coord_y]
       └─ z: [coord_z]
```

---

## Example 2: Dice Roll (1d6)

**Goal:** Create a /roll command for RPG gameplay.

**Command:**
```bash
/mcprocedure create roll 1d6 and send result to player
```

**What it does:**
1. Generates random integer between 1 and 6
2. Concatenates "You rolled 1d6: " with the number
3. Sends message to player via chat

**Generated XML:**
```xml
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="event_trigger" deletable="false" x="40" y="40">
    <field name="trigger">no_ext_trigger</field>
    <next>
      <block type="entity_send_chat">
        <value name="text">
          <block type="text_join">
            <value name="A">
              <block type="text">
                <field name="TEXT">You rolled 1d6: </field>
              </block>
            </value>
            <value name="B">
              <block type="math_random_int_between">
                <value name="min">
                  <block type="math_number">
                    <field name="NUM">1</field>
                  </block>
                </value>
                <value name="max">
                  <block type="math_number">
                    <field name="NUM">6</field>
                  </block>
                </value>
              </block>
            </value>
          </block>
        </value>
        <value name="actbar">
          <block type="logic_boolean">
            <field name="BOOL">FALSE</field>
          </block>
        </value>
        <value name="entity">
          <block type="entity_from_deps"></block>
        </value>
      </block>
    </next>
  </block>
</xml>
```

**Visual Blockly Representation:**
```
[Event Trigger: no_ext_trigger]
  └─> [Send Chat Message]
       ├─ text: ["You rolled 1d6: "] + [Random Int (1, 6)]
       ├─ actbar: [FALSE]
       └─ entity: [Entity from event]
```

**Usage in Minecraft:**
1. Attach to a command procedure or item right-click event
2. When triggered, player sees: "You rolled 1d6: 4"

---

## Example 3: DC Check (Difficulty Class)

**Goal:** Create a skill check system (coming in Phase 3).

**Desired Command:**
```bash
/mcprocedure create DC 15 strength check, if pass give diamond
```

**What it should do:**
1. Roll 1d20
2. Compare roll >= 15
3. If true, give diamond to player
4. Send success/failure message

**Expected Visual Blockly:**
```
[Event Trigger]
  └─> [Set Variable: roll] = [Random Int (1, 20)]
       └─> [If]
            ├─ condition: [roll] >= [15]
            └─ then:
                 ├─> [Send Chat] "Success! You passed the DC 15 check"
                 └─> [Give Item] diamond to [entity from event]
       └─> [Else]
            └─> [Send Chat] "Failed. You rolled {roll} (needed 15+)"
```

**Status:** Phase 3 (coming soon)

---

## Example 4: Vein Miner (Basic)

**Goal:** Break all connected ore blocks (coming in Phase 3).

**Desired Command:**
```bash
/mcprocedure create break same blocks in 3x3x3 area
```

**What it should do:**
1. Get the block type at event position
2. Loop through 3x3x3 area around event
3. For each position, check if block matches original type
4. If match, remove block

**Expected Visual Blockly:**
```
[Event Trigger]
  └─> [Set Variable: originalBlock] = [Get Block at (x, y, z)]
       └─> [Repeat] 27 times  (3x3x3)
            └─> [For each offset in -1, 0, 1]
                 └─> [Set: checkX] = x + offset
                      └─> [If] [Get Block at (checkX, y, z)] == [originalBlock]
                           └─> [Remove Block at (checkX, y, z)]
```

**Status:** Phase 3 (requires loops with variables)

---

## Example 5: Simple Chat Message

**Command:**
```bash
/mcprocedure create send message "Welcome to the server!" to player
```

**What it does:**
1. Sends a simple text message to the player

**Generated XML:**
```xml
<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="event_trigger" deletable="false" x="40" y="40">
    <field name="trigger">no_ext_trigger</field>
    <next>
      <block type="entity_send_chat">
        <value name="text">
          <block type="text">
            <field name="TEXT">Welcome to the server!</field>
          </block>
        </value>
        <value name="actbar">
          <block type="logic_boolean">
            <field name="BOOL">FALSE</field>
          </block>
        </value>
        <value name="entity">
          <block type="entity_from_deps"></block>
        </value>
      </block>
    </next>
  </block>
</xml>
```

**Visual Blockly:**
```
[Event Trigger]
  └─> [Send Chat]
       ├─ text: ["Welcome to the server!"]
       ├─ actbar: [FALSE]
       └─ entity: [Entity from event]
```

---

## Example 6: Reward XP

**Manual Code (Phase 1):**
```javascript
import { XMLBuilder, BlockFactory } from './lib/xml-builder.mjs';

const builder = new XMLBuilder();
builder.createProcedure();

// Give 100 XP to player
const giveXP = new BlockNode('entity_add_xp')
  .addValue('amount', BlockFactory.number(100))
  .addValue('entity', BlockFactory.entityFromDeps());

builder.addStatement(giveXP);

const xml = builder.build();
// Save to procedure file...
```

**Future NLP Command (Phase 3):**
```bash
/mcprocedure create give 100 xp to player
```

---

## Example 7: Time-Based Events

**Manual Code:**
```javascript
const builder = new XMLBuilder();
builder.createProcedure();

// Check if daytime
const isDayCondition = new BlockNode('world_data_isday');

// Send message only during day
const message = BlockFactory.text("Good morning!");
const chat = BlockFactory.sendChat(
  message,
  BlockFactory.boolean(false),
  BlockFactory.entityFromDeps()
);

const ifDay = BlockFactory.ifStatement(isDayCondition, chat);

builder.addStatement(ifDay);
```

**Visual Blockly:**
```
[Event Trigger]
  └─> [If] [Is Daytime?]
       └─> then: [Send Chat] "Good morning!"
```

---

## Example 8: Game Mode Check

**Manual Code:**
```javascript
const builder = new XMLBuilder();
builder.createProcedure();

// Check if player is in survival mode
const isSurvival = new BlockNode('entity_checkgamemode')
  .addField('gamemode', 'SURVIVAL')
  .addValue('entity', BlockFactory.entityFromDeps());

// Only do action in survival
const action = BlockFactory.sendChat(
  BlockFactory.text("This only works in survival mode!"),
  BlockFactory.boolean(false),
  BlockFactory.entityFromDeps()
);

const ifSurvival = BlockFactory.ifStatement(isSurvival, action);

builder.addStatement(ifSurvival);
```

---

## Block Usage Patterns

### Coordinates
```javascript
// Get event coordinates
BlockFactory.coordinate('x')  // Current X
BlockFactory.coordinate('y')  // Current Y
BlockFactory.coordinate('z')  // Current Z

// Offset coordinates
BlockFactory.mathOp('ADD', BlockFactory.coordinate('x'), BlockFactory.number(1))  // x+1
BlockFactory.mathOp('MINUS', BlockFactory.coordinate('y'), BlockFactory.number(2))  // y-2
```

### Math Operations
```javascript
// Addition
BlockFactory.mathOp('ADD', valueA, valueB)

// Subtraction
BlockFactory.mathOp('MINUS', valueA, valueB)

// Multiplication
BlockFactory.mathOp('MULTIPLY', valueA, valueB)

// Division
BlockFactory.mathOp('DIVIDE', valueA, valueB)

// Random numbers
BlockFactory.randomInt(1, 6)   // Dice roll
BlockFactory.randomInt(0, 100) // Percentage
```

### Conditionals
```javascript
// Simple if
BlockFactory.ifStatement(condition, thenBlock)

// Comparisons
BlockFactory.compare('EQ', a, b)   // a == b
BlockFactory.compare('NEQ', a, b)  // a != b
BlockFactory.compare('LT', a, b)   // a < b
BlockFactory.compare('LTE', a, b)  // a <= b
BlockFactory.compare('GT', a, b)   // a > b
BlockFactory.compare('GTE', a, b)  // a >= b
```

### Text Operations
```javascript
// Text constant
BlockFactory.text("Hello World")

// Concatenation
BlockFactory.joinText(textA, textB)

// Example: "You rolled: 6"
BlockFactory.joinText(
  BlockFactory.text("You rolled: "),
  rollVariable
)
```

### Block Operations
```javascript
// Place block
BlockFactory.placeBlock(
  BlockFactory.blockType('Blocks:DIAMOND_BLOCK'),
  xCoord,
  yCoord,
  zCoord
)

// Remove block
BlockFactory.removeBlock(xCoord, yCoord, zCoord)

// Get block type
new BlockNode('world_data_blockat')
  .addValue('x', xCoord)
  .addValue('y', yCoord)
  .addValue('z', zCoord)
```

---

## Testing Procedures

After creating a procedure:

1. **Open MCreator**
   - File → Open Recent → Your mod
   - Wait for workspace to load

2. **Find the Procedure**
   - Look in the Workspace elements panel
   - Should appear in the "Procedures" category

3. **Attach to Trigger**
   - Create a new block/item or edit existing
   - In the triggers tab, select your procedure
   - Example: "When entity walks on block" → Roll1d6

4. **Test In-Game**
   ```bash
   cd dextermod
   ./gradlew runClient
   ```
   - Enable cheats: `/gamemode creative`
   - Test the trigger action
   - Check chat for messages

5. **Debug**
   - Check MCreator console for errors
   - Verify procedure XML in elements/*.mod.json
   - Use MCreator's procedure editor to inspect blocks

---

## Next Steps

- **Phase 2:** Implement `/mcprocedure explain` to convert XML back to plain English
- **Phase 3:** Add more natural language patterns (DC checks, vein miner, etc.)
- **Phase 4:** Dynamic block loading for advanced procedures
- **Integration:** Add to curriculum lesson plans with step-by-step examples
