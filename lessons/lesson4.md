# Lesson 4: The Die That Shows Its Face — Conditional Textures

**Theme:** Blocks that look different depending on their state
**Duration:** ~60 minutes
**New Concept:** Blockstate properties — a block's "outfit" that changes in the world
**Builds On:** NBT (L3), else-if chains (L1)

---

## What We're Building

The Dice Block will now **show which number was rolled** — like a real die. Roll a 3, and the top face displays three dots. Roll a 6, six dots appear.

This uses Minecraft's **blockstate** system — the same mechanism that makes doors open and close, logs face different directions, or candles show how many are on a cake.

---

## Teacher: First — Show the Goal (5 min)

**Before any teaching, open Minecraft and demo the finished block** (pre-build if possible, or show a video).

> *Watch it roll a 1 — the face shows one dot. Roll a 6 — six dots. Roll again — changes instantly.*

Visual learners need to see the destination before the journey makes sense. Show them the "wow moment" first, then build backward to it.

---

## Teacher: Physical Demo (8 min)

Hold up a real die.

> *"Every face of this die looks different. How does Minecraft know which face to show?"*

Explain: a blockstate **property** is like the block's outfit. It's a small piece of public information about the block — unlike NBT (which is private notes hidden inside), a property is visible to the game world. Everyone can see "this door is open." Nobody has to open the door to check.

Draw the comparison:

```
NBT (from last lesson)          Blockstate Property (today)
─────────────────────           ─────────────────────────
Private data                    Public data
Stored inside the block         Stored as part of the world
"lastRolledTime = 1234567"      "face = 3"
Used for logic/memory           Used for appearance
Can store big numbers           Limited to small values / booleans
```

> *"Think of NBT as a secret notebook inside the block. Think of a blockstate property as the outfit the block is wearing — anyone can see it just by looking."*

---

## Teacher Prep (Do Before Class)

**Make 6 die face textures.** Students can customize them later, but give them working ones to start.

Each texture is a **16×16 PNG**. Use any pixel art tool (Pixilator, Aseprite, Paint.NET). Place them in your mod's texture folder:

```
src/main/resources/assets/dextermod/textures/block/
  dice_face_1.png   ← one dot in the center
  dice_face_2.png   ← two dots diagonal
  dice_face_3.png   ← three dots diagonal
  dice_face_4.png   ← four dots corners
  dice_face_5.png   ← four corners + center
  dice_face_6.png   ← six dots in two columns
```

A white background with dark dots works perfectly. Keep dots 2–3px wide. Zoom in to 800% in your editor to place pixels precisely.

> **Shortcut:** Draw `dice_face_1.png` in class with a student as a demo of pixel art. Then hand out the pre-made set for 2–6. This gives the experience of making a texture without eating 30 minutes.

---

## The 0-Index Trap

Blockstate integer properties start at **0**, but our dice rolls go 1–6.

This means:

| Roll result | Property value | Why |
|-------------|---------------|-----|
| 1 | 0 | 1 - 1 = 0 |
| 2 | 1 | 2 - 1 = 1 |
| 3 | 2 | 3 - 1 = 2 |
| 4 | 3 | 4 - 1 = 3 |
| 5 | 4 | 5 - 1 = 4 |
| 6 | 5 | 6 - 1 = 5 |

**Write this table on the board before students touch MCreator.** They'll refer to it constantly. This is normal — programmers use 0-indexed lists all the time, but it always feels a bit weird at first.

> *"Why doesn't it start at 1?"* — Honest answer: computers have counted from 0 for 50 years and we're all stuck with it. The minus-one subtraction is a one-time cost; you'll get used to it.

---

## Step-by-Step: MCreator

---

### Step 1 — Add the Face Property to the Block (10 min)

1. Open your **Dice Block** element in MCreator
2. Find the **Properties** section (may be under "Block properties" or similar)
3. Add a new **integer property**:
   - Name: `face`
   - Minimum value: `0`
   - Maximum value: `5`
4. Make sure `face` has a **default value** of `0`
5. Save

MCreator will now regenerate the block to include this property. Each placed Dice Block in the world will remember its `face` value (0–5).

---

### Step 2 — Set Up Texture Variants (10 min)

MCreator needs to know which texture to use for each value of `face`. This is done in the **blockstates JSON file**.

**Teacher prep — do this before class.** Create the full blockstates JSON and all 6 model files ahead of time. During the lesson, show the file on the projector, explain the structure, then let students copy the pre-made version into their mod. This eliminates the highest-risk failure mode (JSON syntax errors producing a silent pink-and-black error block with no useful message).

The completed **blockstates file** lives at:
```
src/main/resources/assets/dextermod/blockstates/dice_block.json
```

And looks like this — show this on the projector:

```json
{
  "variants": {
    "face=0": { "model": "dextermod:block/dice_face_1" },
    "face=1": { "model": "dextermod:block/dice_face_2" },
    "face=2": { "model": "dextermod:block/dice_face_3" },
    "face=3": { "model": "dextermod:block/dice_face_4" },
    "face=4": { "model": "dextermod:block/dice_face_5" },
    "face=5": { "model": "dextermod:block/dice_face_6" }
  }
}
```

Walk through it with the class: *"face=0 means the block is showing its first texture, dice_face_1. Face=5 means dice_face_6. That minus-one offset is why the table on the board is so important."*

You'll also need a **model file** for each face — copy your existing `dice_block.json` model file five more times, changing only the texture reference. Prep these files before class:

```
src/main/resources/assets/dextermod/models/block/
  dice_face_1.json   ← texture: "dextermod:block/dice_face_1"
  dice_face_2.json   ← texture: "dextermod:block/dice_face_2"
  ...etc
```

> **If MCreator overwrites the blockstates JSON on rebuild:** Check if MCreator has a "Block states" tab in the Dice Block element editor — if so, configure your variants there instead of editing the file directly. MCreator's built-in blockstate editor (if present) is regeneration-safe.

> **Students' creative moment:** One texture students CAN customise during class is the **face=5 (roll 6)** texture — make it gold, glowing, or with a special pattern. This is the jackpot face and it only takes changing one file. Give them 5 minutes for this after everything is working.

---

### Step 3 — Update the Procedure (10 min)

Open `DiceBlockRightClicked`. Find the line where you set the variable `roll` (the random number 1–6).

After that line (but before the else-if chain), add two things:

**A — Update the face property:**

```
[Set block property "face" at block's x, y, z] = roll - 1
```

In MCreator — from **Block procedures → Actions**:

*"Set integer property [face] of block at x: [x] y: [y] z: [z] to [roll - 1]"*

- Property name field: `face`
- Value slot: a **Math → subtract** block with `roll` minus `1`
- x/y/z slots: drag `x`, `y`, `z` from **Minecraft Components** — these are the block's own coordinates

**B — Add a visual pop (one extra block):**

Directly after the property update, add a particle from **World procedures → Actions**:

*"Spawn [3] server-side particles at x: [x] y: [y+1] z: [z] in area dx:0 dy:0 dz:0 with speed 0.1 type: SMOKE_NORMAL"*

This gives a visible "puff" when the face changes. For roll 6, swap `SMOKE_NORMAL` for `TOTEM_OF_UNDYING` inside the `else` branch to make jackpots visually distinct.

That's it. When the dice rolls, the procedure updates the face property, and Minecraft automatically swaps the texture.

---

### Step 4 — Test! (10 min)

Build and run.

1. Place the Dice Block
2. Right-click — the block's face should instantly change to show the rolled number
3. Roll several times and watch the face update
4. Right-click before the 60-second cooldown — the face should stay showing the last roll

> *"What roll is showing on your dice right now?"* — Ask the class. This is the moment they've built toward.

---

## Why Blockstates Instead of NBT?

Both NBT and blockstate properties can store data attached to a block. Why use a property for this?

| | Blockstate Property | NBT |
|---|---|---|
| Speed | Instantly visible | Requires reading data |
| Limit | Small numbers, booleans | Anything |
| Triggers | Can be used in blockstates JSON | Only accessible from code |
| Best for | Visual state, model variants | Logic, cooldowns, complex data |

The face of the die needs to change the **texture** — and texture mappings live in blockstates JSON, which can only read blockstate properties, not NBT. That's why we need both: NBT for the cooldown timer, a property for the visual face.

---

## Common Problems

| Problem | Likely Cause |
|---------|-------------|
| Block shows the same texture for all rolls | blockstates JSON has a syntax error, or model files aren't named correctly |
| JSON error on build | Missing comma between variants, or extra comma on the last line |
| Face shows wrong number | Off-by-one — remember: roll 1 → face 0. Check the table on the board. |
| Textures all show as purple/black checkerboard | Texture files missing or wrong path — check the filename matches the JSON exactly |
| MCreator overwrites the blockstates JSON on rebuild | You may need to put the blockstates edit in MCreator's blockstates editor if it has one, or mark it as user code |

> **Note on MCreator regenerating files:** If MCreator overwrites your blockstates JSON when you rebuild, you may need to add the blockstates configuration inside MCreator's blockstate editor rather than editing the file directly. Check if MCreator has a "block states" tab in the block element editor — if it does, configure your variants there.

---

## Extension Challenges

- **Custom artwork:** Redesign one of the die face textures to match your mod's theme. A dragon scale die? A magic rune die?
- **Golden six:** Make the 6-face texture have a gold/yellow background — a special "jackpot" look. Requires one extra texture + model file.
- **Glowing six:** In the Dice Block settings, can you make the block emit light *only when* showing face 5 (the six)? This requires a conditional light level — explore the block properties to see if this is possible.
- **Four-sided die:** Change the property range to 0–3 and the random roll to 1–4. Make 4 die face textures. You now have a d4!

---

## Next Lesson Preview

We have all the pieces:
- A dice that rolls and shows its result
- A skill that grows as you fight
- A cooldown so the dice is precious

Next lesson is **The Encounter System** — we put it all together into a real RPG mechanic. An encounter stone you carry into battle. It rolls the dice, checks your skills, decides your fate, and makes you stronger either way. We're building a complete game loop.
