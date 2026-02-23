# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Minecraft mod development repository for GR Code Club, using MCreator 2025.3 and NeoForge for Minecraft 1.21.8. This is a **multi-student learning environment** where multiple students work on individual mods within the same repository.

## Repository Structure

This repository contains multiple student mods, each in its own directory:

```
minecraft-codeclub-2025-6/
├── README.md
├── .gitignore
├── CLAUDE.md
├── dextermod/              # Example: One student's mod workspace
│   ├── dextermod.mcreator  # MCreator project file
│   ├── build.gradle
│   ├── src/
│   └── ...
├── codeclub20231/          # Another student's mod workspace
├── codeclub20232/          # Another student's mod workspace
└── ...
```

**Key points:**
- Each student creates their own mod folder (named after their username or machine number)
- Each student works on a corresponding git branch with the same name
- The current working branch is `dexter-mod` with mod folder `dextermod`
- When working with a specific student's mod, navigate to their directory (e.g., `cd dextermod`)
- Commands below reference `dextermod` as an example - substitute the appropriate folder name for other students

## Build System

The project uses Gradle with NeoForge toolchain and requires Java 21 (Eclipse Temurin).

### Essential Commands

From the student's mod directory (e.g., `dextermod`, `codeclub20231`, etc.):

**Build the mod:**
```bash
cd <mod-folder>
./gradlew build
```

**Run Minecraft client with the mod:**
```bash
cd <mod-folder>
./gradlew runClient
```

**Run Minecraft server:**
```bash
cd <mod-folder>
./gradlew runServer
```

**Clean build artifacts:**
```bash
cd <mod-folder>
./gradlew clean
```

**Important**: Replace `<mod-folder>` with the actual folder name (e.g., `dextermod`). All gradle commands must be run from the student's mod subdirectory, not the repository root.

## MCreator Integration

This project uses MCreator as the primary development tool. Key files in each student's mod folder:

- **`<modname>.mcreator`**: Main MCreator workspace configuration defining all mod elements (blocks, items, tabs, procedures)
- **`elements/*.mod.json`**: Individual mod element definitions that MCreator uses to generate code
- **`mcreator.gradle`**: Additional Gradle configuration applied by MCreator

Example: For the `dextermod` folder, the workspace file is `dextermod.mcreator`.

### MCreator Code Generation

MCreator automatically regenerates certain files marked with `MCreator note: This file will be REGENERATED on each build`. These files include:
- `init/DextermodModBlocks.java`
- `init/DextermodModItems.java`
- `init/DextermodModTabs.java`

**Important**: User code blocks are preserved:
- In Java files: Between `// Start of user code block` and `// End of user code block`
- In TOML files: Between `# Start of user code block` and `# End of user code block`

Any manual edits outside these blocks will be overwritten on next build.

## Code Architecture

### Mod Structure

Each student's mod follows MCreator's standard NeoForge structure. Example for `dextermod`:

```
net.mcreator.dextermod/
├── DextermodMod.java          # Main mod class with event handling and networking
├── block/                      # Custom block implementations
│   └── DexterBlockBlock.java
├── init/                       # Registration classes (auto-generated)
│   ├── DextermodModBlocks.java
│   ├── DextermodModItems.java
│   └── DextermodModTabs.java
└── procedures/                 # Event handlers and game logic
    └── DexterBlockEntityWalksOnTheBlockProcedure.java
```

**Note**: Each student's mod has its own package name following the pattern `net.mcreator.<modname>` (e.g., `net.mcreator.codeclub20231`).

### Main Mod Class (`DextermodMod.java`)

The main mod class handles:
- Deferred registry setup for blocks, items, and creative tabs
- Network message registration via `addNetworkMessage()`
- Server-side work queue for scheduled tasks via `queueServerWork()`
- Client-side player access via reflection with `clientPlayer()`

### Block System

Blocks are defined in two places:
1. **MCreator element** (`elements/DexterBlock.mod.json`): Properties like hardness, textures, behaviors
2. **Java implementation** (`block/DexterBlockBlock.java`): Custom logic overriding block methods

Example block features:
- Speed/jump factor modifiers
- Step-on event triggers calling procedures
- Path node types for mob navigation
- Flammability and light properties

### Procedures

Procedures are event handlers generated from MCreator's visual programming:
- Located in `procedures/` package
- Called by block/item events
- Take dependencies as parameters (world, position, entity, etc.)
- Example: `DexterBlockEntityWalksOnTheBlockProcedure` places blocks when stepped on

### Resource Files

- **Blockstates** (`assets/dextermod/blockstates/`): Define block model variants
- **Models** (`assets/dextermod/models/`): JSON models for blocks and items
- **Textures** (`assets/dextermod/textures/`): PNG texture files
- **Language files** (`assets/dextermod/lang/`): Translations (e.g., `en_us.json`)
- **Loot tables** (`data/dextermod/loot_table/`): Block drop definitions

## Development Workflow

### Branching Strategy

Each student works on their own branch named after their mod folder:
- Branch name matches the mod folder name (e.g., `dextermod`, `codeclub20231`)
- Student commits only to their own branch
- Each branch contains changes only to that student's mod folder
- The `main` branch is used for integration and shared files (README, .gitignore)
- **Important**: When working in this repository, always check which branch is active and ensure commands target the correct mod folder

### Making Changes

1. **For visual/property changes**: Use MCreator interface to modify mod elements
2. **For custom logic**: Edit Java files in user code blocks
3. **For resource changes**: Edit JSON/PNG files directly or through MCreator

### Testing

Run the client with `./gradlew runClient` from the student's mod directory. The mod will be loaded in a test Minecraft instance where you can:
- Enable cheats and creative mode
- Test new blocks/items in-game
- Check console logs for errors

**Note**: Each student's mod is independent - running the client only loads that specific student's mod.

### Version Configuration

The mod targets:
- Minecraft: 1.21.8
- NeoForge: 21.8.31
- MCreator: 2025.3 (build 202500345720)

These versions are defined in `gradle.properties`, `build.gradle`, and `neoforge.mods.toml`. Ensure version consistency across all files when updating.

## IntelliJ IDEA Setup

Each student's mod can be imported into IntelliJ as a separate Gradle project:
1. Open the student's mod directory (e.g., `dextermod`) as a Gradle project
2. Use Java 21 (Temurin) JDK
3. Run `runClient` task from Gradle sidebar under `Tasks/forgegradle runs`

**Note**: Students work on one mod at a time - open only the relevant mod folder in IntelliJ, not the entire repository root.

## Registry System

The mod uses NeoForge's DeferredRegister system:
- Blocks registered in `DextermodModBlocks.REGISTRY`
- Items registered in `DextermodModItems.REGISTRY`
- Creative tabs registered in `DextermodModTabs.REGISTRY`

All registries are bound to the mod event bus in `DextermodMod` constructor.

## Networking

The mod includes a network messaging system:
- Messages defined with `CustomPacketPayload.Type`
- Registered via `addNetworkMessage()` before mod initialization
- Handlers support bidirectional play phase communication
- Registration locked after `RegisterPayloadHandlersEvent` fires
