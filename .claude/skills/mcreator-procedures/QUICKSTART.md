# MCreator Procedures Skill - Quick Start

Get started generating MCreator procedures in under 5 minutes!

## Installation Check

The skill is already installed at:
```
.claude/skills/mcreator-procedures/
```

## Basic Usage

### 1. Create Your First Procedure

**Generate a dice roll:**
```bash
cd dextermod
/mcprocedure create roll 1d6 and send result to player
```

**What this does:**
- Creates a new file: `dextermod/elements/Roll1d6.mod.json`
- Generates valid Blockly XML for the procedure
- Ready to use in MCreator!

### 2. View in MCreator

1. Open MCreator
2. Open your mod workspace (dextermod)
3. Look in the Workspace elements panel
4. Find "Roll1d6" in the Procedures category
5. Double-click to open in visual editor

### 3. Test In-Game

1. Attach the procedure to a trigger:
   - Create a new item
   - In "When right-clicked" trigger, select Roll1d6
   - Save

2. Build and run:
   ```bash
   cd dextermod
   ./gradlew runClient
   ```

3. In-game:
   - Give yourself the item: `/give @s dextermod:your_item`
   - Right-click the item
   - See message: "You rolled 1d6: 4"

## Common Commands

### Create Procedures

**Dice roll:**
```bash
/mcprocedure create roll 2d10 and send result to player
```

**Place blocks (extending block):**
```bash
/mcprocedure create place diamond_block at x+2
```

**Chat message:**
```bash
/mcprocedure create send message "Welcome!" to player
```

### List Procedures

```bash
cd dextermod
/mcprocedure list
```

Output:
```
Procedures in dextermod:

============================================================
  DexterBlockEntityWalksOnTheBlock
  Roll1d6
  Roll2d10

Total: 3 procedures
```

### View Available Blocks

**All blocks:**
```bash
/mcprocedure blocks
```

**By category:**
```bash
/mcprocedure blocks math
/mcprocedure blocks logic
/mcprocedure blocks player
```

### Explain a Procedure

```bash
/mcprocedure explain Roll1d6
```

(Shows the Blockly XML structure - full explanation coming in Phase 2)

## Supported Patterns

### 1. Dice Rolls (RPG System)

**Pattern:** `roll XdY and send result to player`

**Examples:**
- `roll 1d6 and send result to player` - Standard six-sided die
- `roll 1d20 and send result to player` - D&D style
- `roll 2d6 and send result to player` - Two dice (Phase 3)

**Generated blocks:**
- Random integer between 1 and sides
- Text concatenation: "You rolled: " + number
- Send chat message to player

### 2. Block Placement (Extending Block)

**Pattern:** `place [block] at x+N`

**Examples:**
- `place stone at x+1` - Place one block east
- `place diamond_block at x+2` - Place two blocks east
- `place DexterBlock at x+1` - Place custom block

**Generated blocks:**
- Get event coordinates
- Math operation: x + offset
- Place block at calculated position

### 3. Chat Messages (Feedback)

**Pattern:** `send message "text" to player`

**Examples:**
- `send message "Hello!" to player`
- `send message "Quest complete" to player`

**Generated blocks:**
- Text constant
- Send chat to player entity

## Examples by Project

### Extending Block (Like DexterBlock)

```bash
/mcprocedure create place stone at x+1
```

**Attach to:** Block "When entity walks on"

**Result:** Creates a line of blocks as you walk

### RPG Dice System

```bash
/mcprocedure create roll 1d20 and send result to player
```

**Attach to:** Item "When right-clicked"

**Result:** Roll dice when using item

### Simple Greeting

```bash
/mcprocedure create send message "Welcome to my mod!" to player
```

**Attach to:** Player "When player joins world"

**Result:** Greet players when they join

## Troubleshooting

### "Could not find MCreator workspace"

**Problem:** Not running from mod folder

**Solution:**
```bash
cd dextermod  # Navigate to your mod folder
/mcprocedure create ...  # Try again
```

### "Procedure already exists"

**Problem:** You already created this procedure

**Solutions:**
1. Use a different name
2. Delete the old procedure in MCreator first
3. Use `/mcprocedure modify` (coming in Phase 4)

### Procedure doesn't appear in MCreator

**Problem:** MCreator hasn't reloaded

**Solution:**
1. Close MCreator
2. Reopen your workspace
3. Or: Click "Reload workspace" in MCreator

### XML doesn't look right

**Problem:** Unsupported pattern

**Solution:**
1. Check supported patterns above
2. Use exact format: "roll 1d6 and send result to player"
3. See EXAMPLES.md for manual code generation

## Next Steps

### Learn More

- **Full documentation:** README.md
- **Code examples:** EXAMPLES.md
- **Block reference:** blocks/core-blocks.json
- **Implementation details:** PHASE1-COMPLETE.md

### Advanced Usage

**Manual code generation:**
```javascript
import { XMLBuilder, BlockFactory } from './lib/xml-builder.mjs';

const builder = new XMLBuilder();
builder.createProcedure();

// Your custom blocks here
const myBlock = BlockFactory.sendChat(
  BlockFactory.text("Custom message!"),
  BlockFactory.boolean(false),
  BlockFactory.entityFromDeps()
);

builder.addStatement(myBlock);

const xml = builder.build();
// Save with file-manager.mjs
```

See EXAMPLES.md for 8+ detailed examples.

### Customize Patterns

Edit `skill.mjs` to add new patterns:

```javascript
// Add to generateFromDescription() function
if (lower.includes('your pattern')) {
  return await createYourProcedure(description);
}
```

### Request Features

Have an idea for a new pattern? Want more blocks in the core library?

1. Check existing GitHub issues
2. Submit new issue with use case
3. Describe the curriculum project it supports

## Help

**Get help:**
```bash
/mcprocedure help
```

**Test the skill:**
```bash
/mcprocedure test
```

**Validate blocks library:**
```bash
python test.py
```

## Tips

1. **Start simple** - Use basic patterns first (dice rolls, chat messages)
2. **Test in MCreator** - Always verify generated XML opens correctly
3. **Attach to triggers** - Procedures need triggers to run (events, right-clicks, etc.)
4. **Check console** - MCreator console shows procedure errors
5. **Iterate** - Generate, test, refine, repeat

## What's Coming

### Phase 2 (1-2 weeks)
- Full `/mcprocedure explain` - Plain English descriptions
- Procedure parser - Understand existing procedures

### Phase 3 (2 weeks)
- DC checks: "DC 15 strength check, if pass give diamond"
- Vein miner: "break same blocks in 3x3x3 area"
- More curriculum patterns

### Phase 4 (1-2 weeks)
- Dynamic block loading - Access all 500+ blocks
- XML validation - Helpful error messages
- Modify existing procedures

## Success!

You're now ready to generate MCreator procedures programmatically!

Try it out:
```bash
cd dextermod
/mcprocedure create roll 1d6 and send result to player
```

Then open MCreator and see your new Roll1d6 procedure!

Happy modding! 🎲🎮
