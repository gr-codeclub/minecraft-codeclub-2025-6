# RPG Mod Lesson Arc — Planning Document

## Constraints
- 10-year-old students, ~1 hour per session
- Max 15 minutes teaching, rest is hands-on in MCreator
- MCreator scratch (Blockly) system — no raw Java
- Already know: if, repeat loop, while loop
- Platform: MCreator 2025.3, NeoForge, Minecraft 1.21.8
- Already built: Dexter Block (trail effect), Vein Miner tool

## Concept Introduction Schedule

| Lesson | New Concept | RPG Hook |
|--------|-------------|----------|
| 1 | Random numbers + else-if chains | Dice block (roll for effects) |
| 2 | Persistent data via scoreboards | Skill tracking (warrior/miner XP) |
| 3 | NBT on blocks + ticking | Dice cooldown + block "memory" |
| 4 | Conditional textures (blockstate) | Dice face changes to show last roll |
| 5 | Putting it together / DC checks | Full RPG encounter — skill vs dice |

## Lesson Summaries

### Lesson 1 — The Dice Block
**New concept:** random numbers, else-if chain
**Build:** A block you right-click to "roll" a d6. Each face does something different.
**Physical demo:** Roll a real die and map outcomes on the whiteboard before coding.
**Key block:** Random integer 1–6 → set of else-if checks.
**End state:** Working dice block with 6 distinct outcomes.

### Lesson 2 — Skills & Stats
**New concept:** Scoreboards as persistent storage (data that survives procedure end)
**Build:** Warrior skill that increments on mob kill. Skill Stone item that reads and displays current rank.
**Physical demo:** Paper "character sheet" on whiteboard — variables in code are like saying a number out loud (forgotten instantly), scoreboards are like writing it down.
**Key block:** Scoreboard add/read. Else-if chain ordered from high to low threshold.
**Connects to L1:** Dice roll 1 now checks warrior skill for a DC check (skilled player dodges lightning).
**End state:** Kill mobs → skill goes up → Skill Stone shows rank.

### Lesson 3 — Block Memory (NBT)
**New concept:** NBT data attached to block entities — blocks that remember things
**Build:** The Dice Block gets a cooldown — stores the world time when last rolled, refuses to roll again for 60 seconds.
**Physical demo:** Sticky note on a physical block = NBT. The note remembers when it was last used.
**Key block:** Get/set NBT long on block entity. Compare world time - lastUsed > cooldown threshold.
**Important:** Requires making the Dice Block a "tile entity" (block entity) in MCreator.
**End state:** Dice block has a 60-second cooldown per block placement.

### Lesson 4 — Faces on the Dice (Conditional Textures)
**New concept:** Blockstate properties + conditional textures (block looks different based on stored data)
**Build:** The Dice Block shows a texture matching the last roll (1-face through 6-face textures).
**Physical demo:** Hold up a real die — notice how each face looks different. How does Minecraft know which face to show? It checks a "property" stored in the blockstate.
**Key steps:**
  - Create 6 textures (die faces 1–6)
  - Add an integer property to blockstate (0–5)
  - In procedure: set blockstate property to (roll - 1) after rolling
  - In blockstates JSON: map property value → texture
**End state:** The dice block's face visually changes to show the last roll result.

### Lesson 5 — The Encounter System (Putting It Together)
**New concept:** Combining everything — no single new concept, but introduces the idea of "systems talking to each other"
**Build:** An "Encounter Stone" — when right-clicked, it:
  1. Rolls a d6 (from Lesson 1 dice logic)
  2. Reads warrior skill (from Lesson 2 scoreboard)
  3. Does a DC check: if skill >= roll → success (XP + chat message "You prevailed!")
  4. If skill < roll → failure (damage + chat message "The monster overwhelms you!")
  5. Either way, warrior skill goes up by 1 (you learn from encounters)
**Physical demo:** The whole class stands up. Teacher rolls a real die. Anyone whose "warrior level" (their seat row number + their shoe size, just for fun) beats the roll stays standing. Everyone sits eventually — but tougher warriors last longer!
**Key insight:** Systems talking to each other is how real games are built. The dice doesn't know about skills. Skills don't know about dice. But a procedure can read BOTH and combine them.
**End state:** A complete, playable encounter loop.

## Pacing Notes
- Each session: 15 min teach → 35 min build → 10 min test/share
- If students finish early: extension challenges at end of each lesson
- If students fall behind: Lesson 3 (NBT) is the most complex — can be split across 2 sessions
- Lesson 4 (textures) requires some file editing outside MCreator — prep texture PNGs in advance

## MCreator Blocks Reference (for teacher)
- Random integer: Math → random integer from X to Y
- Scoreboard add: Player → Scoreboard score (add)
- Scoreboard read: Player → Scoreboard score (get)
- NBT read/write: Block → NBT data (requires block entity)
- World time: World → get time of day / game time
- Set blockstate property: Block → set block property
- Print to chat: Player → show message in chat
- Give XP: Player → give experience
- Summon entity: World → spawn entity
- Set on fire: Entity → set on fire
