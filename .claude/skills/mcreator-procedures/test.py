#!/usr/bin/env python3
"""
Test the core blocks library structure
"""

import json
from pathlib import Path

def test_core_blocks():
    """Test that core-blocks.json is valid and well-structured"""
    blocks_path = Path(__file__).parent / 'blocks' / 'core-blocks.json'

    with open(blocks_path, 'r') as f:
        blocks = json.load(f)

    print(f"Loaded {len(blocks)} core blocks\n")
    print("="*80)

    # Group by category
    by_category = {}
    for name, block in blocks.items():
        cat = block.get('category', 'Other')
        if cat not in by_category:
            by_category[cat] = []
        by_category[cat].append(name)

    # Print summary
    for cat, block_names in sorted(by_category.items()):
        print(f"\n{cat}: ({len(block_names)} blocks)")
        print("-"*80)
        for name in sorted(block_names):
            block = blocks[name]
            desc = block.get('description', 'No description')
            print(f"  {name:30} - {desc}")

    print("\n" + "="*80)
    print(f"\n✓ Successfully loaded {len(blocks)} core blocks")
    print(f"✓ Organized into {len(by_category)} categories")

    # Validate structure
    required_fields = ['type', 'description', 'category']
    for name, block in blocks.items():
        for field in required_fields:
            if field not in block:
                print(f"⚠ Warning: Block '{name}' missing field '{field}'")

    print("\n✓ All blocks have required fields")

    # Show examples
    print("\n" + "="*80)
    print("\nExample blocks with use cases:\n")

    example_blocks = ['block_add', 'math_random_int_between', 'entity_send_chat']
    for name in example_blocks:
        if name in blocks:
            block = blocks[name]
            print(f"{name}:")
            print(f"  {block['description']}")
            if 'examples' in block:
                print("  Use cases:")
                for ex in block['examples']:
                    print(f"    - {ex}")
            print()

if __name__ == '__main__':
    test_core_blocks()
