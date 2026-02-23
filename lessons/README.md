# RPG Minecraft Mod — Lesson Arc

5-session plan for GR Code Club. Students build an RPG system using MCreator 2025.3 + NeoForge.

---

## The Arc

Each lesson builds on the last. The dice block from lesson 1 is still running in lesson 5 — nothing is throwaway.

| # | Lesson | New Concept | What's Built |
|---|--------|-------------|-------------|
| 1 | [The Dice Block](lesson1.md) | Random numbers, else-if chains | Right-click block → roll d6 → 6 different effects |
| 2 | [Skills & Stats](lesson2.md) | Scoreboards (persistent data) | Warrior skill + Skill Stone item + DC check |
| 3 | [Block Memory](lesson3.md) | NBT data on blocks | 60-second cooldown on the dice |
| 4 | [Conditional Textures](lesson4.md) | Blockstate properties | Die face shows last roll visually |
| 5 | [The Encounter System](lesson5.md) | Systems composition | Full RPG loop: roll → skill check → outcome → grow |

---

## Concepts Introduced Over the Arc

| Concept | When | Mental Model Used |
|---------|------|-------------------|
| Random numbers | L1 | Rolling a physical die |
| else-if chains | L1 | Reading a list top to bottom, stop at first match |
| Variables | L1 | Saying a number out loud (gone when you stop talking) |
| Scoreboards | L2 | Writing on paper (survives when you stop) |
| NBT data | L3 | Sticky note attached to a specific block |
| Blockstate properties | L4 | The block's "outfit" — visible to the world |
| Composing systems | L5 | Die + skill check = encounter loop |

---

## Pacing

Each session is 60 minutes:
- **~10–12 min:** Physical demo + whiteboard teaching
- **~35 min:** Building in MCreator
- **~10 min:** Testing in-game + sharing

If a session runs over, Lesson 3's "seconds remaining" display and Lesson 5's milestone announcements are the safest cuts — they're polish, not core mechanics.

---

## Teacher Prep Checklist

**Before Lesson 1:**
- [ ] Bring a real die to class
- [ ] Have MCreator workspace open with a working mod folder

**Before Lesson 4:**
- [ ] Pre-make 6 die face PNG textures (16×16 pixels)
- [ ] Pre-make 6 block model JSON files (copy/modify existing dice_block.json)
- [ ] Have VS Code or Notepad++ available for JSON editing

**Before Lesson 5:**
- [ ] Optionally pre-make a "magic crystal" texture for the Encounter Stone
- [ ] Optionally pre-build and test the Encounter Stone so you can demo it

---

## Planning Document

See [arc-plan.md](arc-plan.md) for the full planning notes including concept rationale, pacing analysis, and alternatives considered.
