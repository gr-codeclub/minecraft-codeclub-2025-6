/**
 * XML Builder for MCreator Procedure Blockly XML
 *
 * Constructs valid Blockly XML for MCreator procedures.
 */

/**
 * Represents a single Blockly block node
 */
export class BlockNode {
  constructor(type, options = {}) {
    this.type = type;
    this.fields = options.fields || {};
    this.values = {}; // input_value children
    this.statements = {}; // statement children
    this.next = null; // next block in sequence
    this.comment = options.comment || null;
  }

  /**
   * Add a field (simple value like dropdown selection, text input)
   */
  addField(name, value) {
    this.fields[name] = value;
    return this;
  }

  /**
   * Add a value input (block that returns a value)
   */
  addValue(name, block) {
    this.values[name] = block;
    return this;
  }

  /**
   * Add a statement input (block sequence)
   */
  addStatement(name, block) {
    this.statements[name] = block;
    return this;
  }

  /**
   * Set the next block in the sequence
   */
  setNext(block) {
    this.next = block;
    return this;
  }

  /**
   * Add a comment to this block
   */
  addComment(text) {
    this.comment = text;
    return this;
  }

  /**
   * Convert this block to XML string
   */
  toXML(indent = 0) {
    const spaces = '  '.repeat(indent);
    let xml = `${spaces}<block type="${this.type}">`;

    // Add comment if present
    if (this.comment) {
      xml += `\n${spaces}  <comment>${escapeXML(this.comment)}</comment>`;
    }

    // Add fields
    for (const [name, value] of Object.entries(this.fields)) {
      xml += `\n${spaces}  <field name="${name}">${escapeXML(String(value))}</field>`;
    }

    // Add value inputs
    for (const [name, block] of Object.entries(this.values)) {
      xml += `\n${spaces}  <value name="${name}">`;
      if (block) {
        xml += '\n' + block.toXML(indent + 2);
        xml += `\n${spaces}  </value>`;
      } else {
        xml += '</value>';
      }
    }

    // Add statement inputs
    for (const [name, block] of Object.entries(this.statements)) {
      xml += `\n${spaces}  <statement name="${name}">`;
      if (block) {
        xml += '\n' + block.toXML(indent + 2);
        xml += `\n${spaces}  </statement>`;
      } else {
        xml += '</statement>';
      }
    }

    // Close block tag
    if (this.next) {
      xml += `\n${spaces}  <next>`;
      xml += '\n' + this.next.toXML(indent + 2);
      xml += `\n${spaces}  </next>`;
      xml += `\n${spaces}</block>`;
    } else {
      xml += `\n${spaces}</block>`;
    }

    return xml;
  }
}

/**
 * Helper to escape XML special characters
 */
function escapeXML(str) {
  return str
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&apos;');
}

/**
 * Main XML builder for complete procedures
 */
export class XMLBuilder {
  constructor() {
    this.rootBlock = null;
  }

  /**
   * Create a new procedure with event trigger
   */
  createProcedure(triggerType = 'no_ext_trigger') {
    this.rootBlock = new BlockNode('event_trigger')
      .addField('trigger', triggerType);
    // Make it undeletable as it's the root
    return this;
  }

  /**
   * Add a statement block to the procedure
   */
  addStatement(block) {
    if (!this.rootBlock) {
      throw new Error('Must call createProcedure() first');
    }

    // Find the last block in the chain
    let current = this.rootBlock;
    while (current.next) {
      current = current.next;
    }

    current.setNext(block);
    return this;
  }

  /**
   * Build the complete XML string
   */
  build() {
    if (!this.rootBlock) {
      throw new Error('No blocks added to builder');
    }

    const header = '<xml xmlns="https://developers.google.com/blockly/xml">';
    const footer = '</xml>';

    // Add deletable="false" attribute to root block
    let xml = this.rootBlock.toXML(0);
    xml = xml.replace('<block type="event_trigger">', '<block type="event_trigger" deletable="false" x="40" y="40">');

    return header + xml + footer;
  }
}

/**
 * Factory functions for common block patterns
 */
export class BlockFactory {
  /**
   * Create a number constant block
   */
  static number(value) {
    return new BlockNode('math_number')
      .addField('NUM', value);
  }

  /**
   * Create a text constant block
   */
  static text(value) {
    return new BlockNode('text')
      .addField('TEXT', value);
  }

  /**
   * Create a boolean constant block
   */
  static boolean(value) {
    return new BlockNode('logic_boolean')
      .addField('BOOL', value ? 'TRUE' : 'FALSE');
  }

  /**
   * Create a coordinate block (x, y, or z)
   */
  static coordinate(axis) {
    const validAxes = ['x', 'y', 'z'];
    if (!validAxes.includes(axis.toLowerCase())) {
      throw new Error(`Invalid axis: ${axis}. Must be x, y, or z`);
    }
    return new BlockNode(`coord_${axis.toLowerCase()}`);
  }

  /**
   * Create a math operation block (A op B)
   */
  static mathOp(operator, blockA, blockB) {
    return new BlockNode('math_dual_ops')
      .addField('OP', operator)
      .addValue('A', blockA)
      .addValue('B', blockB);
  }

  /**
   * Create a random integer block
   */
  static randomInt(min, max) {
    return new BlockNode('math_random_int_between')
      .addValue('min', BlockFactory.number(min))
      .addValue('max', BlockFactory.number(max));
  }

  /**
   * Create a block selector (mcitem_allblocks)
   */
  static blockType(blockName) {
    return new BlockNode('mcitem_allblocks')
      .addField('value', blockName);
  }

  /**
   * Create an entity from dependencies block
   */
  static entityFromDeps() {
    return new BlockNode('entity_from_deps');
  }

  /**
   * Create a block_add (place block) statement
   */
  static placeBlock(blockType, x, y, z) {
    return new BlockNode('block_add')
      .addValue('block', blockType)
      .addValue('x', x)
      .addValue('y', y)
      .addValue('z', z);
  }

  /**
   * Create a block_remove statement
   */
  static removeBlock(x, y, z) {
    return new BlockNode('block_remove')
      .addValue('x', x)
      .addValue('y', y)
      .addValue('z', z);
  }

  /**
   * Create an entity_send_chat statement
   */
  static sendChat(text, actionBar, entity) {
    return new BlockNode('entity_send_chat')
      .addValue('text', text)
      .addValue('actbar', actionBar)
      .addValue('entity', entity);
  }

  /**
   * Create a text_join (concatenation) block
   */
  static joinText(textA, textB) {
    return new BlockNode('text_join')
      .addValue('A', textA)
      .addValue('B', textB);
  }

  /**
   * Create a controls_if (conditional) statement
   */
  static ifStatement(condition, thenBlock) {
    const ifBlock = new BlockNode('controls_if')
      .addValue('IF0', condition)
      .addStatement('DO0', thenBlock);
    return ifBlock;
  }

  /**
   * Create a comparison block
   */
  static compare(operator, valueA, valueB) {
    return new BlockNode('compare_operators')
      .addField('OP', operator)
      .addValue('A', valueA)
      .addValue('B', valueB);
  }

  /**
   * Create a spawn_gem (spawn item) statement
   */
  static spawnItem(item, x, y, z) {
    return new BlockNode('spawn_gem')
      .addValue('gem', item)
      .addValue('x', x)
      .addValue('y', y)
      .addValue('z', z);
  }

  /**
   * Create an entity_add_item (give item) statement
   */
  static giveItem(entity, item) {
    return new BlockNode('entity_add_item')
      .addValue('entity', entity)
      .addValue('item', item);
  }

  /**
   * Create a controls_repeat_ext (loop) statement
   */
  static repeat(times, doBlock) {
    return new BlockNode('controls_repeat_ext')
      .addValue('TIMES', times)
      .addStatement('DO', doBlock);
  }
}
