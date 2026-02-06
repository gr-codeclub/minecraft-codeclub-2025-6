# Core Blocks Library

This directory contains the core block definitions for the MCreator Procedures skill.

## Structure

- **core-blocks.json**: The 25 most commonly used procedure blocks, carefully selected to cover 80% of student use cases

## Block Selection Criteria

Blocks were chosen based on:
1. **Curriculum alignment**: Supports vein miner, RPG dice, extending blocks, and other planned projects
2. **Frequency of use**: Most common operations students will need
3. **Educational value**: Teaches fundamental programming concepts

## Block Categories

### Tier 1: Essential (Must-have)
- `block_add`, `block_remove` - Block manipulation
- `coord_x/y/z` - Coordinate access
- `math_number`, `math_dual_ops`, `math_random_int_between` - Math operations
- `controls_if`, `compare_operators` - Conditionals
- `entity_send_chat` - Player feedback
- `mcitem_allblocks` - Block selection

### Tier 2: Important (Common patterns)
- `spawn_gem`, `entity_add_item` - Item management
- `logic_boolean`, `logic_binary_ops` - Boolean logic
- `text`, `text_join` - String manipulation
- `entity_from_deps` - Event context
- `world_data_blockat` - Block checking
- `controls_repeat_ext` - Loops

### Tier 3: Useful (Quality of life)
- `entity_add_xp` - Experience rewards
- `entity_checkgamemode` - Permissions
- `world_data_isday` - Time checks
- `entity_haspotioneffect`, `entity_add_potion` - Status effects
- `logic_negate` - NOT operator

## Adding New Blocks

To add a new block to the core library:

1. Research the block definition in `C:\MCreator\plugins\mcreator-core\procedures\<block_name>.json`
2. Understand its structure (fields, inputs, outputs, statement type)
3. Add an entry to `core-blocks.json` with:
   - `type`: Block type ID
   - `description`: Clear, concise description
   - `category`: Logical grouping
   - `inputs`: Input value slots
   - `fields`: Dropdown/text field definitions
   - `output`: Return type (if expression block)
   - `statement`: true if action block
   - `examples`: 2-3 concrete use cases

4. Test the block with the skill

## Dynamic Block Loading

For blocks not in the core library, the skill can dynamically load definitions from:
```
C:\MCreator\plugins\mcreator-core\procedures\*.json
```

This keeps the core library tight while allowing access to all 500+ blocks when needed.
