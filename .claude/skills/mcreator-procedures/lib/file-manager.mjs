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
    this.workspaceFile = null; // Will be found automatically
  }

  /**
   * Find the .mcreator workspace file in the mod folder
   */
  async findWorkspaceFile() {
    if (this.workspaceFile) return this.workspaceFile;

    const files = await fs.readdir(this.modFolder);
    const mcreatorFile = files.find(f => f.endsWith('.mcreator'));

    if (!mcreatorFile) {
      throw new Error(`Could not find .mcreator workspace file in ${this.modFolder}`);
    }

    this.workspaceFile = path.join(this.modFolder, mcreatorFile);
    return this.workspaceFile;
  }

  /**
   * Read the MCreator workspace file
   */
  async readWorkspace() {
    const workspaceFile = await this.findWorkspaceFile();
    const content = await fs.readFile(workspaceFile, 'utf-8');
    return JSON.parse(content);
  }

  /**
   * Write the MCreator workspace file
   */
  async writeWorkspace(workspace) {
    const workspaceFile = await this.findWorkspaceFile();
    await fs.writeFile(workspaceFile, JSON.stringify(workspace, null, 2), 'utf-8');
  }

  /**
   * Register a procedure in the MCreator workspace
   */
  async registerProcedureInWorkspace(procedureName, dependencies = []) {
    const workspace = await this.readWorkspace();

    // Convert procedure name to registry name (snake_case)
    const registryName = procedureName
      .replace(/([A-Z])/g, '_$1')
      .toLowerCase()
      .replace(/^_/, '');

    // Check if already registered
    const existing = workspace.mod_elements.find(
      el => el.name === procedureName || el.registry_name === registryName
    );

    if (existing) {
      // Already registered, skip
      return;
    }

    // Create the mod element entry
    const modElement = {
      name: procedureName,
      type: 'procedure',
      compiles: true,
      locked_code: false,
      registry_name: registryName,
      metadata: {
        dependencies: dependencies,
        files: [
          `src/main/java/net/mcreator/${workspace.workspaceSettings.modid}/procedures/${procedureName}Procedure.java`
        ]
      }
    };

    // Add to mod_elements array
    workspace.mod_elements.push(modElement);

    // Write back to file
    await this.writeWorkspace(workspace);
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
   * Create a new procedure file and register it in the workspace
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

    // Write the .mod.json file
    await fs.writeFile(filePath, JSON.stringify(modJson, null, 2), 'utf-8');

    // Determine dependencies from the XML
    const dependencies = options.dependencies || this.extractDependencies(procedureXML);

    // Register in MCreator workspace
    await this.registerProcedureInWorkspace(procedureName, dependencies);

    return {
      success: true,
      filePath,
      procedureName,
      registered: true
    };
  }

  /**
   * Extract dependencies from procedure XML
   * (basic implementation - looks for common patterns)
   */
  extractDependencies(xml) {
    const deps = [];

    // Check for coordinate usage
    if (xml.includes('coord_x') || xml.includes('coord_y') || xml.includes('coord_z')) {
      deps.push(
        { name: 'x', type: 'number' },
        { name: 'y', type: 'number' },
        { name: 'z', type: 'number' },
        { name: 'world', type: 'world' }
      );
    }

    // Check for entity usage
    if (xml.includes('entity_from_deps') || xml.includes('entity_pos')) {
      if (!deps.find(d => d.name === 'entity')) {
        deps.push({ name: 'entity', type: 'entity' });
      }
    }

    return deps;
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
