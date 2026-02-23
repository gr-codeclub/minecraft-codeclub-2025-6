#!/usr/bin/env python3
"""
Generate Vein Miner Procedure

Creates a MCreator procedure that breaks matching blocks in a 3x3x3 area
and drops them at the player's feet.
"""

import json
import os
from pathlib import Path


def create_block(block_type, **kwargs):
    """Create a block XML snippet"""
    block = f'<block type="{block_type}">'

    # Add fields
    for field_name, field_value in kwargs.get('fields', {}).items():
        block += f'\n  <field name="{field_name}">{field_value}</field>'

    # Add values
    for value_name, value_block in kwargs.get('values', {}).items():
        block += f'\n  <value name="{value_name}">{value_block}</value>'

    # Add next
    if 'next' in kwargs:
        block += f'\n  <next>{kwargs["next"]}</next>'

    block += '\n</block>'
    return block


def coord(axis):
    """Create coordinate block"""
    return f'<block type="coord_{axis}"></block>'


def number(value):
    """Create number block"""
    return f'<block type="math_number"><field name="NUM">{value}</field></block>'


def math_add(a, b):
    """Create addition block"""
    return f'''<block type="math_dual_ops">
  <field name="OP">ADD</field>
  <value name="A">{a}</value>
  <value name="B">{b}</value>
</block>'''


def entity_from_deps():
    """Create entity from deps block"""
    return '<block type="entity_from_deps"></block>'


def entity_pos(axis):
    """Create entity position block"""
    return f'''<block type="entity_pos_{axis}">
  <value name="entity">{entity_from_deps()}</value>
</block>'''


def get_block_at(x, y, z):
    """Create world_data_blockat block"""
    return f'''<block type="world_data_blockat">
  <value name="x">{x}</value>
  <value name="y">{y}</value>
  <value name="z">{z}</value>
</block>'''


def compare_blocks(a, b):
    """Create compare_mcblocks block"""
    return f'''<block type="compare_mcblocks">
  <value name="a">{a}</value>
  <value name="b">{b}</value>
</block>'''


def remove_and_drop(x, y, z, x2, y2, z2):
    """Create block_remove_drop block"""
    return f'''<block type="block_remove_drop">
  <value name="x">{x}</value>
  <value name="y">{y}</value>
  <value name="z">{z}</value>
  <value name="x2">{x2}</value>
  <value name="y2">{y2}</value>
  <value name="z2">{z2}</value>
</block>'''


def if_statement(condition, then_block, next_block=None):
    """Create controls_if block"""
    block = f'''<block type="controls_if">
  <value name="IF0">{condition}</value>
  <statement name="DO0">{then_block}</statement>'''
    if next_block:
        block += f'\n  <next>{next_block}</next>'
    block += '\n</block>'
    return block


def generate_veinminer_xml():
    """Generate the complete vein miner procedure XML"""

    # Original broken block (at event position)
    broken_block = get_block_at(coord('x'), coord('y'), coord('z'))

    # Player position for dropping
    player_x = entity_pos('x')
    player_y = entity_pos('y')
    player_z = entity_pos('z')

    # Generate checks for 3x3x3 area
    checks = []

    for dx in range(-1, 2):
        for dy in range(-1, 2):
            for dz in range(-1, 2):
                # Skip center
                if dx == 0 and dy == 0 and dz == 0:
                    continue

                # Calculate check position
                check_x = math_add(coord('x'), number(dx)) if dx != 0 else coord('x')
                check_y = math_add(coord('y'), number(dy)) if dy != 0 else coord('y')
                check_z = math_add(coord('z'), number(dz)) if dz != 0 else coord('z')

                # Get block at this position
                block_at_pos = get_block_at(check_x, check_y, check_z)

                # Compare with broken block
                is_match = compare_blocks(block_at_pos, broken_block)

                # Remove and drop
                remove_drop = remove_and_drop(
                    check_x, check_y, check_z,
                    player_x, player_y, player_z
                )

                # Create if statement
                if_block = if_statement(is_match, remove_drop)
                checks.append(if_block)

    # Chain all checks together
    for i in range(len(checks) - 1):
        # Insert next block reference
        checks[i] = checks[i].replace('</block>', f'  <next>{checks[i+1]}</next>\n</block>')

    # Build complete XML
    xml = f'''<xml xmlns="https://developers.google.com/blockly/xml">
  <block type="event_trigger" deletable="false" x="40" y="40">
    <field name="trigger">no_ext_trigger</field>
    <next>
      {checks[0]}
    </next>
  </block>
</xml>'''

    return xml, len(checks)


def register_in_workspace(mod_folder, procedure_name):
    """Register the procedure in the MCreator workspace file"""
    # Find .mcreator file
    workspace_file = None
    for f in Path(mod_folder).iterdir():
        if f.suffix == '.mcreator':
            workspace_file = f
            break

    if not workspace_file:
        print("Warning: Could not find .mcreator workspace file")
        print("You'll need to manually import the procedure in MCreator")
        return False

    # Read workspace
    with open(workspace_file, 'r') as f:
        workspace = json.load(f)

    # Convert to registry name
    registry_name = ''.join(['_' + c.lower() if c.isupper() else c for c in procedure_name]).lstrip('_')

    # Check if already registered
    for element in workspace['mod_elements']:
        if element.get('name') == procedure_name or element.get('registry_name') == registry_name:
            print(f"Procedure already registered in workspace")
            return True

    # Create mod element entry
    mod_element = {
        "name": procedure_name,
        "type": "procedure",
        "compiles": True,
        "locked_code": False,
        "registry_name": registry_name,
        "metadata": {
            "dependencies": [
                {"name": "x", "type": "number"},
                {"name": "y", "type": "number"},
                {"name": "z", "type": "number"},
                {"name": "world", "type": "world"},
                {"name": "entity", "type": "entity"}
            ],
            "files": [
                f"src/main/java/net/mcreator/{workspace['workspaceSettings']['modid']}/procedures/{procedure_name}Procedure.java"
            ]
        }
    }

    # Add to workspace
    workspace['mod_elements'].append(mod_element)

    # Write back
    with open(workspace_file, 'w') as f:
        json.dump(workspace, f, indent=2)

    return True


def create_procedure_file(mod_folder, xml):
    """Create the .mod.json file and register in workspace"""
    elements_dir = Path(mod_folder) / 'elements'

    if not elements_dir.exists():
        raise FileNotFoundError(f"Elements directory not found: {elements_dir}")

    procedure_file = elements_dir / 'VeinMiner.mod.json'

    # Check if already exists
    if procedure_file.exists():
        print(f"Warning: {procedure_file} already exists!")
        response = input("Overwrite? (y/n): ")
        if response.lower() != 'y':
            print("Cancelled.")
            return None

    # Create .mod.json structure
    mod_json = {
        "_fv": 79,
        "_type": "procedure",
        "definition": {
            "procedurexml": xml,
            "skipDependencyNullCheck": False
        }
    }

    # Write file
    with open(procedure_file, 'w') as f:
        json.dump(mod_json, f, indent=2)

    # Register in workspace
    registered = register_in_workspace(mod_folder, 'VeinMiner')

    return procedure_file, registered


def main():
    print("Generating Vein Miner Procedure...")
    print("=" * 80)
    print()

    # Find mod folder
    mod_folders = ['dextermod', 'codeclub20231', 'codeclub20232']
    mod_folder = None

    for folder in mod_folders:
        if Path(folder).exists():
            mod_folder = folder
            break

    if not mod_folder:
        print("Error: Could not find mod folder.")
        print(f"Looked for: {', '.join(mod_folders)}")
        print()
        print("Please run this script from the repository root, or specify:")
        mod_folder = input("Enter mod folder path: ").strip()

        if not Path(mod_folder).exists():
            print(f"Error: {mod_folder} does not exist")
            return

    print(f"Using mod folder: {mod_folder}")
    print()

    # Generate XML
    xml, num_checks = generate_veinminer_xml()

    print(f"Generated XML with {num_checks} block checks (3x3x3 area)")
    print()

    # Create file
    try:
        result = create_procedure_file(mod_folder, xml)

        if result:
            procedure_file, registered = result
            print("[OK] Vein Miner procedure created successfully!")
            print(f"  File: {procedure_file}")
            print(f"  Scan area: 3x3x3 blocks (26 positions)")
            if registered:
                print(f"  Registered: YES - MCreator will recognize it immediately")
            else:
                print(f"  Registered: NO - You may need to import manually")
            print()
            print("Next steps:")
            if registered:
                print("  1. Open MCreator (or restart if already open)")
            else:
                print("  1. Open MCreator and manually import the procedure")
            print("  2. Find 'VeinMiner' in the Procedures list")
            print("  3. Create or edit a tool item (pickaxe, axe, shovel)")
            print("  4. In item triggers, set:")
            print("     'When block destroyed with tool' -> VeinMiner")
            print("  5. Build and test: cd dextermod && ./gradlew runClient")
            print()
            print("How it works:")
            print("  - Breaks all matching blocks in a 3x3x3 cube")
            print("  - Drops all items at your feet")
            print("  - Perfect for mining ore veins!")
            print()
            print("Note: For larger veins, you'd need recursive checking")
            print("      (coming in Phase 3 with variables support)")

    except Exception as e:
        print(f"Error: {e}")


if __name__ == '__main__':
    main()
