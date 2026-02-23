# MCreator Procedures Skill

Generate and modify MCreator procedures programmatically for GR Code Club Minecraft mod development.

## Overview

This skill enables Claude Code to create, modify, and explain MCreator procedures without using the GUI. It supports the most common 27 procedure blocks used in student projects, with the ability to dynamically load any of the 500+ available blocks from MCreator when needed.

## Features

- ✅ **Generate procedures from natural language** - "roll 1d6 and send result to player"
- ✅ **Explain existing procedures** - Convert Blockly XML to plain English
- ✅ **Modify procedures programmatically** - Update blocks without opening MCreator
- ✅ **Core block library** - 27 most common blocks, optimized for curriculum
- ✅ **Extensible design** - Can access all 500+ blocks dynamically when needed

## Installation

The skill is already installed in `.claude/skills/mcreator-procedures/`.

Requirements:
- Node.js (for running the skill commands)
- MCreator 2025.3 workspace

## Usage

### Basic Commands

```bash
# Create a new procedure
/mcprocedure create <description>

# Explain an existing procedure
/mcprocedure explain <procedure-name>

# List all procedures in current mod
/mcprocedure list

# Show available blocks
/mcprocedure blocks [category]

# Test the skill
/mcprocedure test
```

### Examples

**Create a dice rolling procedure:**
```bash
/mcprocedure create roll 1d6 and send result to player
```

**Create an extending block (like DexterBlock):**
```bash
/mcprocedure create place stone at x+1
```

**Send a chat message:**
```bash
/mcprocedure create send message "Hello World" to player
```

**Explain existing procedure:**
```bash
/mcprocedure explain DexterBlockEntityWalksOnTheBlock
```

**List available math blocks:**
```bash
/mcprocedure blocks math
```

## Supported Patterns (Phase 1)

### Dice Rolls (Curriculum: RPG Dice System)
```
"roll XdY and send result to player"
Examples:
  - roll 1d6 and send result to player
  - roll 2d10 and send result to player
  - roll 1d20 and send result to player
```

### Block Placement (Curriculum: Extending Block)
```
"place [block] at x+N"
Examples:
  - place stone at x+1
  - place diamond_block at x+2
  - place custom:DexterBlock at x+1
```

### Chat Messages (Curriculum: All projects)
```
"send message [text] to player"
Examples:
  - send message "Welcome!" to player
  - send message "Quest completed" to player
```

## Core Blocks Library

The skill includes 27 carefully selected blocks organized by category:

### Block Actions (2)
- `block_add` - Place blocks
- `block_remove` - Remove blocks

### Coordinates (3)
- `coord_x`, `coord_y`, `coord_z` - Access event coordinates

### Math (3)
- `math_number` - Number constants
- `math_dual_ops` - Math operations (+, -, *, /, etc.)
- `math_random_int_between` - Random integers (for dice!)

### Logic (6)
- `controls_if` - Conditionals
- `compare_operators` - Comparisons (==, >=, etc.)
- `logic_boolean` - true/false
- `logic_binary_ops` - AND/OR
- `logic_negate` - NOT
- `controls_repeat_ext` - Loops

### Player Management (3)
- `entity_send_chat` - Send messages
- `entity_add_item` - Give items
- `entity_add_xp` - Award experience

### And more...

See `blocks/core-blocks.json` for the complete list with descriptions and examples.

## Architecture

```
.claude/skills/mcreator-procedures/
├── skill.mjs                 # Main entry point (CLI handler)
├── package.json              # Metadata
├── lib/
│   ├── xml-builder.mjs       # Blockly XML construction
│   ├── file-manager.mjs      # .mod.json I/O
│   ├── parser.mjs            # Parse procedures (Phase 2)
│   ├── explainer.mjs         # Explain procedures (Phase 2)
│   ├── nlp-mapper.mjs        # NLP → blocks (Phase 3)
│   ├── validator.mjs         # XML validation (Phase 4)
│   └── block-loader.mjs      # Dynamic MCreator block loading (Phase 4)
└── blocks/
    ├── core-blocks.json      # 27 common blocks
    └── README.md             # Block documentation
```

## Implementation Status

### ✅ Phase 1: Foundation (COMPLETE)
- [x] XML builder (`BlockNode`, `XMLBuilder`, `BlockFactory`)
- [x] Core blocks library (27 blocks)
- [x] File manager for .mod.json I/O
- [x] Basic CLI with create/list/blocks commands
- [x] Pattern matching for dice rolls, block placement, chat
- [x] Tests and documentation

### 🚧 Phase 2: Parsing & Explanation (Next)
- [ ] Procedure parser (XML → AST)
- [ ] Procedure explainer (AST → English)
- [ ] Full `/mcprocedure explain` implementation

### 🚧 Phase 3: Natural Language Generation
- [ ] Advanced NLP pattern matching
- [ ] Coordinate parsing (x+1, player location, etc.)
- [ ] Entity extraction
- [ ] More curriculum patterns (DC checks, vein miner, etc.)

### 🚧 Phase 4: Dynamic Loading & Polish
- [ ] Dynamic block loader from MCreator source
- [ ] XML validation system
- [ ] `/mcprocedure modify` command
- [ ] Integration tests with student mods

## Curriculum Alignment

This skill supports the GR Code Club curriculum projects:

1. **Vein Miner** - `block_remove`, `controls_repeat_ext`, coordinate iteration
2. **RPG Dice System** - `math_random_int_between`, `entity_send_chat`, `text_join`
3. **Extending Block** - `block_add`, coordinate offsets (already working!)
4. **RPG Skills & DC Checks** - `compare_operators`, `controls_if`, variables
5. **Furnace** - Inventory management blocks
6. **Custom Entity** - Entity spawning and AI

## How It Works

### XML Generation Example

To create a dice roll procedure, the skill:

1. **Parses** the description: "roll 1d6 and send result to player"
2. **Builds** the Blockly structure:
   - `math_random_int_between` (1, 6) → random number
   - `text_join` ("You rolled: ", number) → message
   - `entity_send_chat` (message, false, entity) → send to player
3. **Generates** XML:
   ```xml
   <xml xmlns="https://developers.google.com/blockly/xml">
     <block type="event_trigger" deletable="false">
       <field name="trigger">no_ext_trigger</field>
       <next>
         <block type="entity_send_chat">
           <value name="text">
             <block type="text_join">
               <value name="A"><block type="text"><field name="TEXT">You rolled: </field></block></value>
               <value name="B"><block type="math_random_int_between">
                 <value name="min"><block type="math_number"><field name="NUM">1</field></block></value>
                 <value name="max"><block type="math_number"><field name="NUM">6</field></block></value>
               </block></value>
             </block>
           </value>
           <value name="actbar"><block type="logic_boolean"><field name="BOOL">FALSE</field></block></value>
           <value name="entity"><block type="entity_from_deps"></block></value>
         </block>
       </next>
     </block>
   </xml>
   ```
4. **Writes** to `elements/Roll1d6.mod.json`

### File Format

MCreator procedure files (`.mod.json`) have this structure:

```json
{
  "_fv": 79,
  "_type": "procedure",
  "definition": {
    "procedurexml": "<xml>...</xml>",
    "skipDependencyNullCheck": false
  }
}
```

The skill reads/writes these files directly, allowing procedure generation without opening MCreator.

## Testing

### Manual Testing

1. **Test XML generation:**
   ```bash
   node skill.mjs test
   ```

2. **Test block library:**
   ```bash
   python test.py
   ```

3. **Create and verify in MCreator:**
   ```bash
   cd dextermod
   node ../.claude/skills/mcreator-procedures/skill.mjs create roll 1d6 and send result to player
   # Open MCreator, reload workspace, verify Roll1d6 procedure appears
   ```

### Integration Testing

Test with actual student mods:
```bash
cd dextermod
/mcprocedure create place diamond_block at x+1
./gradlew runClient
# Test in-game
```

## Extensibility

### Adding New Blocks

To add blocks to the core library:

1. Research the block in `C:\MCreator\plugins\mcreator-core\procedures\<block>.json`
2. Add entry to `blocks/core-blocks.json` with metadata
3. (Optional) Add factory method to `BlockFactory` in `xml-builder.mjs`

### Dynamic Block Loading

For blocks not in core library, Phase 4 will implement dynamic loading:

```javascript
import { BlockLoader } from './lib/block-loader.mjs';

const loader = new BlockLoader();
const blockDef = await loader.load('some_rare_block');
```

This keeps the core tight while allowing access to all 500+ blocks.

## Troubleshooting

**"Could not find MCreator workspace"**
- Run the command from within a mod folder (e.g., `dextermod/`)
- Ensure the folder contains a `.mcreator` file

**"Procedure already exists"**
- Choose a different name or delete the existing procedure first

**"Could not parse description"**
- Check that your description matches a supported pattern
- Use `/mcprocedure help` to see examples

**XML doesn't load in MCreator**
- Validate the XML structure
- Check that block types match MCreator definitions
- Ensure coordinates are provided where required

## Future Enhancements

- [ ] Visual procedure preview (ASCII art of blocks)
- [ ] Import procedures from other mods
- [ ] Batch procedure generation
- [ ] Procedure templates library
- [ ] Integration with curriculum lesson plans
- [ ] Student-friendly error messages with hints

## Contributing

To contribute new patterns or blocks:

1. Test with actual student use cases
2. Ensure examples are clear and curriculum-aligned
3. Document in both code and README
4. Add tests for new functionality

## License

MIT License - Created for GR Code Club

## Support

For issues or questions:
- Check `/mcprocedure help`
- Review examples in this README
- Examine existing procedures in `elements/` directory
- Consult MCreator documentation for block definitions
