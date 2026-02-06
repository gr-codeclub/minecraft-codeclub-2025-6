# Bugfix: Automatic Workspace Registration

**Date:** 2026-02-06
**Issue:** Procedures not showing up in MCreator after creation
**Status:** FIXED ✓

## Problem

When creating procedures programmatically by only writing the `.mod.json` file to the `elements/` directory, MCreator wouldn't recognize them. This is because MCreator uses a workspace file (`.mcreator`) that maintains a registry of all mod elements.

**Example:**
```
dextermod/
├── dextermod.mcreator          ← Workspace file (registry)
└── elements/
    ├── VeinMiner.mod.json      ← Procedure file exists
    └── ...
```

The `VeinMiner.mod.json` file existed, but MCreator didn't know about it because it wasn't listed in the `mod_elements` array in `dextermod.mcreator`.

## Solution

Updated `ProcedureFileManager` to automatically register procedures in the workspace file when creating them.

### Changes Made

**1. Added workspace management methods to `file-manager.mjs`:**

```javascript
// Find the .mcreator workspace file
async findWorkspaceFile()

// Read the workspace JSON
async readWorkspace()

// Write the workspace JSON
async writeWorkspace(workspace)

// Register a procedure in the workspace
async registerProcedureInWorkspace(procedureName, dependencies)

// Extract dependencies from XML
extractDependencies(xml)
```

**2. Updated `createProcedure()` method:**

Now automatically:
1. Creates the `.mod.json` file in `elements/`
2. Extracts dependencies from the XML
3. Registers the procedure in the workspace file
4. Returns success status with `registered: true`

**3. Updated Python script `generate-veinminer.py`:**

Added `register_in_workspace()` function that:
1. Finds the `.mcreator` workspace file
2. Reads the workspace JSON
3. Creates a mod element entry
4. Adds it to the `mod_elements` array
5. Writes the workspace file back

## Workspace File Format

Procedures are registered in the `.mcreator` file like this:

```json
{
  "mod_elements": [
    {
      "name": "VeinMiner",
      "type": "procedure",
      "compiles": true,
      "locked_code": false,
      "registry_name": "vein_miner",
      "metadata": {
        "dependencies": [
          {"name": "x", "type": "number"},
          {"name": "y", "type": "number"},
          {"name": "z", "type": "number"},
          {"name": "world", "type": "world"},
          {"name": "entity", "type": "entity"}
        ],
        "files": [
          "src/main/java/net/mcreator/dextermod/procedures/VeinMinerProcedure.java"
        ]
      }
    }
  ]
}
```

## Dependencies Detection

The skill automatically detects required dependencies from the XML:

- **Coordinates**: If XML contains `coord_x/y/z`, adds `x`, `y`, `z`, `world` dependencies
- **Entity**: If XML contains `entity_from_deps` or `entity_pos`, adds `entity` dependency

This ensures MCreator knows what context the procedure needs to run.

## Testing

**Before fix:**
1. Create procedure → File created
2. Open MCreator → Procedure not visible
3. Manual fix required: Edit `.mcreator` file

**After fix:**
1. Create procedure → File created AND registered
2. Open MCreator → Procedure immediately visible ✓
3. No manual intervention needed ✓

## Impact

- **User Experience:** Procedures now work immediately after creation
- **Skill Completeness:** Matches MCreator's internal behavior
- **Documentation:** Updated to reflect automatic registration

## Files Modified

1. `.claude/skills/mcreator-procedures/lib/file-manager.mjs`
   - Added workspace management methods
   - Updated `createProcedure()` to auto-register

2. `.claude/skills/mcreator-procedures/generate-veinminer.py`
   - Added `register_in_workspace()` function
   - Updated to show registration status

3. `C:\Users\TrainingGR17\.claude\projects\C--minecraft-codeclub-2025-6\memory\MEMORY.md`
   - Added lesson about workspace registration requirement

## Lessons Learned

**MCreator Architecture:**
- `.mod.json` files in `elements/` define the content
- `.mcreator` workspace file acts as a registry/index
- Both must be in sync for MCreator to recognize elements

**Future Considerations:**
- Phase 2: Implement `deleteProcedure()` to also unregister from workspace
- Phase 3: Handle element renaming (update both files)
- Phase 4: Validate workspace consistency before operations

## Backward Compatibility

Existing procedures created with the old version (without registration) will continue to work but won't be visible in MCreator until manually registered. The fix only affects newly created procedures.

To fix old procedures:
1. Delete the `.mod.json` file
2. Recreate using the updated skill
3. OR: Manually add entry to `.mcreator` file

## Related Issues

This same pattern applies to ALL MCreator elements:
- Blocks
- Items
- Tools
- Entities
- Recipes
- etc.

Each requires both:
1. Element definition file (`.mod.json`)
2. Workspace registry entry (`.mcreator`)

The skill now follows this pattern correctly.
