/**
 * File Manager for MCreator Procedure .mod.json files
 *
 * Handles reading, writing, and updating procedure element files
 */

import fs from 'fs/promises';
import path from 'path';

export class ProcedureFileManager {
  constructor(modFolder) {
    this.modFolder = modFolder;
    this.elementsDir = path.join(modFolder, 'elements');
  }

  /**
   * Read an existing procedure file
   */
  async readProcedure(procedureName) {
    const filePath = path.join(this.elementsDir, `${procedureName}.mod.json`);

    try {
      const content = await fs.readFile(filePath, 'utf-8');
      return JSON.parse(content);
    } catch (error) {
      if (error.code === 'ENOENT') {
        throw new Error(`Procedure '${procedureName}' not found at ${filePath}`);
      }
      throw error;
    }
  }

  /**
   * Create a new procedure file
   */
  async createProcedure(procedureName, procedureXML, options = {}) {
    const filePath = path.join(this.elementsDir, `${procedureName}.mod.json`);

    // Check if file already exists
    try {
      await fs.access(filePath);
      throw new Error(`Procedure '${procedureName}' already exists at ${filePath}`);
    } catch (error) {
      if (error.code !== 'ENOENT') {
        throw error;
      }
    }

    // Build the .mod.json structure
    const modJson = {
      _fv: 79, // Format version for MCreator 2025.3
      _type: 'procedure',
      definition: {
        procedurexml: procedureXML,
        skipDependencyNullCheck: options.skipDependencyNullCheck || false
      }
    };

    // Write the file
    await fs.writeFile(filePath, JSON.stringify(modJson, null, 2), 'utf-8');

    return {
      success: true,
      filePath,
      procedureName
    };
  }

  /**
   * Update an existing procedure's XML
   */
  async updateProcedure(procedureName, newProcedureXML) {
    const filePath = path.join(this.elementsDir, `${procedureName}.mod.json`);

    // Read existing file
    const modJson = await this.readProcedure(procedureName);

    // Update the procedurexml
    modJson.definition.procedurexml = newProcedureXML;

    // Write back
    await fs.writeFile(filePath, JSON.stringify(modJson, null, 2), 'utf-8');

    return {
      success: true,
      filePath,
      procedureName
    };
  }

  /**
   * Delete a procedure file
   */
  async deleteProcedure(procedureName) {
    const filePath = path.join(this.elementsDir, `${procedureName}.mod.json`);

    await fs.unlink(filePath);

    return {
      success: true,
      deletedFile: filePath
    };
  }

  /**
   * List all procedure files in the elements directory
   */
  async listProcedures() {
    const files = await fs.readdir(this.elementsDir);

    const procedures = [];

    for (const file of files) {
      if (file.endsWith('.mod.json')) {
        const filePath = path.join(this.elementsDir, file);
        const content = await fs.readFile(filePath, 'utf-8');
        const modJson = JSON.parse(content);

        if (modJson._type === 'procedure') {
          procedures.push({
            name: file.replace('.mod.json', ''),
            path: filePath
          });
        }
      }
    }

    return procedures;
  }

  /**
   * Check if a procedure exists
   */
  async procedureExists(procedureName) {
    const filePath = path.join(this.elementsDir, `${procedureName}.mod.json`);

    try {
      await fs.access(filePath);
      return true;
    } catch {
      return false;
    }
  }

  /**
   * Extract procedure XML from a .mod.json file
   */
  async extractXML(procedureName) {
    const modJson = await this.readProcedure(procedureName);
    return modJson.definition.procedurexml;
  }
}
