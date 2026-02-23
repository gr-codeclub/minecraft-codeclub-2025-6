# Notes for Claude — MCreator Block Name Verification

## How to Find Real MCreator Block Names

**Before writing any MCreator procedure instruction, verify the exact block name. Do not guess or use web search results — check the source directly.**

### Step 1 — Find the human-readable label

The canonical source for UI label text is:

```
C:\MCreator\plugins\mcreator-localization\lang\texts.properties
```

Search this file for the block's internal ID to get the exact displayed text. Example:
```
blockly.block.entity_get_scoreboard_score=Get %2 scoreboard score for %1
blockly.block.entity_set_scoreboard_score=Set score %1 to %2 on the scoreboard of %3
blockly.block.entity_from_deps=Event/target entity
blockly.block.source_entity_from_deps=Source entity
blockly.block.block_set_integer_property=Set integer property %1 of block at x: %2 y: %3 z: %4 to %5
blockly.block.block_nbt_num_get=Get NBT number tag %4 of block at x: %1 y: %2 z: %3 if it has block entity
blockly.block.block_nbt_num_set=Set NBT number tag %4 of block at x: %1 y: %2 z: %3 to %5 if it has block entity %6
blockly.block.spawn_particle_multi=Spawn %8 server-side particles at x: %1 y: %2 z: %3 ...
blockly.block.world_data_current_time=Get current world time
trigger.entity_dies=Entity dies
```

### Step 2 — Find the toolbox category

Each procedure block JSON file is in:
```
C:\MCreator\plugins\mcreator-core\procedures\
```

Open the block's `.json` file and read the `toolbox_id` field. Then find the category's `$<toolbox_id>.json` file in the same folder and read its `parent_category`.

Category display names are also in `texts.properties`:
```
blockly.category.worldprocedures=World procedures
blockly.category.scoreboard=Scoreboard
blockly.category.blockprocedures=Block procedures
blockly.category.blockactions=Actions
blockly.category.worldmanagement=Actions
```

So the full path shown to the user is: parent category → sub-category → block name.

### Step 3 — Find trigger details

Trigger JSON files live in:
```
C:\MCreator\plugins\mcreator-core\triggers\
```

Read the JSON to find `dependencies_provided` — these list the exact variable names available (e.g. `entity`, `sourceentity`, `x`, `y`, `z`).

Trigger display names are in `texts.properties` as `trigger.<id>=<label>`.

### Quick Reference — Confirmed Block Locations (MCreator 2025.3)

| Block | Category path | Exact label |
|-------|--------------|-------------|
| Scoreboard get | World procedures → Scoreboard | "Get [entity] scoreboard score for [score]" |
| Scoreboard set | World procedures → Scoreboard | "Set score [name] to [value] on the scoreboard of [entity]" |
| Event/target entity | Minecraft Components (top) | "Event/target entity" |
| Source entity | Minecraft Components (top) | "Source entity" |
| NBT number read (block) | Block procedures → Actions | "Get NBT number tag [name] of block at x y z if it has block entity" |
| NBT number write (block) | Block procedures → Actions | "Set NBT number tag [name] of block at x y z to [value] if it has block entity" |
| Set integer blockstate | Block procedures → Actions | "Set integer property [name] of block at x y z to [value]" |
| Spawn particles | World procedures → Actions | "Spawn [N] server-side particles at x y z ..." |
| Get world time | World procedures → Actions | "Get current world time" |

### Kill trigger specifics

- **Trigger name:** "Entity dies" (file: `entity_dies.json`)
- **`entity`** = the mob/entity that died ("Event/target entity" block)
- **`sourceentity`** = what caused the death — the player ("Source entity" block)
- Filter for player kills: check `source entity` is a player AND `event/target entity` is a monster

### Reminder: Always check before writing

If you are about to write a lesson that says "find the X block in the Y category" — grep `texts.properties` first. A wrong category name will directly confuse a 10-year-old who can't find the block.
