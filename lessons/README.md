# RPG Minecraft Mod — Lesson Arcs

Lesson plans for GR Code Club. Students build an RPG system using MCreator 2025.3 + NeoForge.

---

## Arc 1: RPG Foundations (Blockly)

5 lessons using MCreator's visual Blockly system. Students build a dice-based RPG system from scratch.

| # | Lesson | New Concept | What's Built |
|---|--------|-------------|-------------|
| 1 | [The Dice Block](lesson1.md) | Random numbers, else-if chains | Right-click block --> roll d6 --> 6 different effects |
| 2 | [Skills & Stats](lesson2.md) | Scoreboards (persistent data) | Warrior skill + Skill Stone item + DC check |
| 3 | [Block Memory](lesson3.md) | NBT data on blocks | 60-second cooldown on the dice |
| 4 | [Conditional Textures](lesson4.md) | Blockstate properties | Die face shows last roll visually |
| 5 | [The Encounter System](lesson5.md) | Systems composition | Full RPG loop: roll --> skill check --> outcome --> grow |

---

## Arc 2: Java Superpowers (Custom Code)

7 lessons introducing real Java using MCreator's "Custom code snippet" block. Students build a Spellbook item, adding one new spell per lesson. Each spell teaches one Java concept. **No code block has more than 3 lines.**

| # | Lesson | New Java Concept | What's Built |
|---|--------|-----------------|-------------|
| 6 | [Spell of Greeting](lesson6.md) | Typing a Java statement (strings, semicolons) | Spellbook item + chat message |
| 7 | [Spell of Radiance](lesson7.md) | Dot notation (calling methods on objects) | Entity glowing + ghost mode |
| 8 | [The Spell Selector](lesson8.md) | `if` with `==` comparison | GUI spell picker + scoreboard persistence |
| 9 | [Spell of Healing](lesson9.md) | `instanceof` type checks | Heal self (LivingEntity check) |
| 10 | [Spell of Fireworks](lesson10.md) | Method arguments + server/client | Particles + sounds |
| 11 | [Spell of Fortune](lesson11.md) | Variables and math in Java | Loot calculator based on warrior skill |
| 12 | [Spell of the Storm](lesson12.md) | For loops with changing counter | Particle pillar / fire ring / spiral |

---

## Concepts Introduced Over Both Arcs

### Arc 1 (Blockly)

| Concept | When | Mental Model |
|---------|------|-------------|
| Random numbers | L1 | Rolling a physical die |
| else-if chains | L1 | Reading a list top to bottom, stop at first match |
| Variables | L1 | Saying a number out loud (gone when you stop talking) |
| Scoreboards | L2 | Writing on paper (survives when you stop) |
| NBT data | L3 | Sticky note attached to a specific block |
| Blockstate properties | L4 | The block's "outfit" -- visible to the world |
| Composing systems | L5 | Die + skill check = encounter loop |

### Arc 2 (Java)

| Concept | When | Mental Model |
|---------|------|-------------|
| Java statements | L6 | Writing a sentence (semicolon = full stop) |
| Strings | L6 | Text inside double quotes |
| Dot notation / methods | L7 | torch.turnOn() -- tell an object to do something |
| camelCase | L7 | Humps like a camel: setGlowingTag |
| Booleans | L7 | true/false -- on/off switches |
| `==` comparison | L8 | "Is this equal to?" (double equals = question) |
| Code dispatch | L8 | Check a number, run the matching code |
| GUI + state | L8 | Restaurant menu: pick once, kitchen remembers |
| instanceof | L9 | Can you drink from a ball? Check the type first |
| Type hierarchy | L9 | Entity > LivingEntity > Player (Russian dolls) |
| Method arguments | L10 | "Jump" vs "Jump 3 times" -- details change the result |
| Server vs Client | L10 | The boss vs the artist (even in single-player) |
| Constants | L10 | ParticleTypes.HEART -- named values in ALL_CAPS |
| Variable declaration | L11 | A jar with a label and coins inside |
| int and type casting | L11 | (int) is a cookie cutter -- chops off the decimal |
| Integer division | L11 | 12 / 5 = 2 (not 2.4) |
| For loops | L12 | "Clap 5 times, each time higher" |
| Curly braces | L12 | Fence posts -- everything between them is the loop body |
| Counter variable (i) | L12 | y+i makes each repetition different -- the superpower |

---

## Pacing

Each session is 60 minutes:
- **~10-12 min:** Physical demo + whiteboard teaching
- **~30-35 min:** Building in MCreator
- **~10 min:** Testing in-game + sharing

### Arc 2 Specific Notes
- Lessons 6-7 are deliberately easy to build confidence with typing Java
- Lesson 8 is the most MCreator-heavy lesson (GUI builder + Blockly + Java dispatch)
- Lesson 9 is the steepest conceptual jump (instanceof) -- provide a printed cheat sheet
- Lesson 10 has the longest lines -- kids should type in chunks and count commas
- Lesson 12 is the longest code block (4 lines) -- go slow

---

## Teacher Prep Checklist

**Before Lesson 1:**
- [ ] Bring a real die to class
- [ ] Have MCreator workspace open with a working mod folder

**Before Lesson 4:**
- [ ] Pre-make 6 die face PNG textures (16x16 pixels)
- [ ] Pre-make 6 block model JSON files
- [ ] Have VS Code or Notepad++ available for JSON editing

**Before Lesson 6:**
- [ ] Practice typing the Lesson 6 Java line yourself -- know the common errors
- [ ] Have the line written on the board or a handout for students to copy from
- [ ] Bring Lego instructions (or picture of them) + blank paper for the demo

**Before Lesson 8:**
- [ ] Test MCreator's GUI builder yourself -- know how to add buttons and link procedures
- [ ] Have the restaurant menu analogy ready
- [ ] Write `=` vs `==` on the board in big letters

**Before Lesson 9:**
- [ ] Print the instanceof cheat sheet (or write it on the board)
- [ ] Bring a ball, book, and water bottle for the demo

**Before Lesson 10:**
- [ ] Write the particle types and sound events swap-in list on the board
- [ ] Pre-draw the server/client diagram

**Before Lesson 11:**
- [ ] Bring a jar/cup, label, and coins for the variable demo
- [ ] Ensure skill_warrior scoreboard exists in the test world

**Before Lesson 12:**
- [ ] Pre-type the Extension 2 and 3 code (sin/cos) to show on projector

---

## Planning Documents

- [Arc 1 Planning Notes](arc-plan.md)
- [MCreator Block Name Verification Guide](CLAUDE-NOTES.md)
