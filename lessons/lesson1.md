# Lesson 1: The Dice Block — Rolling the Bones

**Theme:** RPG Foundations — randomness and decisions
**Duration:** ~60 minutes
**New Concept:** Random numbers & `else if` chains
**Builds On:** `if` statements you already know

---

## What We're Building

A **Dice Block**. Right-click it and it "rolls" a random number 1–6, doing something different for each result — just like a board game die.

| Roll | Effect |
|------|--------|
| 1 | Lightning strike on the player |
| 2 | A zombie spawns nearby |
| 3 | A healing pulse — regenerate 2 hearts |
| 4 | +20 XP |
| 5 | Player catches fire for 3 seconds |
| 6 | **JACKPOT** — XP rain! |

This dice block is the heart of our RPG system. Every lesson we'll add more to it.

---

## Teacher: Physical Demo (10 min)

**Bring a real die to class.**

Roll it on the desk. Ask:

> *"What's going to happen when I roll this?"*

Nobody knows. That's the point — **randomness**. Games would be boring if the result was always the same.

Then ask:

> *"If I roll a 6, the player gets treasure. If I roll a 1, lightning strikes. What kind of code do we need to handle all six possibilities?"*

Draw this on the whiteboard:

```
Roll the die → get a number from 1 to 6

if number is 1  → ⚡ lightning
else if it's 2  → 🧟 zombie
else if it's 3  → ❤️ heal 2 hearts
else if it's 4  → ✨ XP
else if it's 5  → 🔥 fire
else            → 🎉 jackpot! (must be 6)
```

**Key teaching point — else if reads top to bottom and STOPS at the first match.**

Draw a finger going down the list: "Is it 1? No. Is it 2? No. Is it 3? YES — do the thing, skip everything else."

Ask: *"Why don't I need to write `else if it's 6`?"*
Answer: *"If it wasn't 1, 2, 3, 4, or 5 — it must be 6. That's what `else` catches."*

---

## Step-by-Step: MCreator

> **Quick reference card** — where to find blocks in MCreator:
> - Random number → **Math** category
> - Set a variable → **Variables** category
> - Print to chat → **Player** category → "send chat message"
> - If/else if → **Logic** category
> - Lightning → **World** category → "strike lightning at position"
> - Spawn entity → **Entity** category → "summon entity"
> - Give XP → **Player** category → "give experience"
> - Set on fire → **Entity** category → "set entity on fire"

---

### Step 1 — Create the Dice Block (5 min)

1. Click **+ New Element → Block**
2. Name it `Dice Block`
3. Pick any texture for now (stone works as a placeholder — we'll make proper die faces in a later lesson!)
4. Set **Hardness** to `0.5`
5. Save the block — don't link a procedure yet

---

### Step 2 — Create the Procedure (5 min)

1. Click **+ New Element → Procedure**
2. Name it `DiceBlockRightClicked`
3. When prompted for dependencies, make sure **entity** (the player) is included — this lets the procedure know who right-clicked

> **Common mistake:** If your procedure can't access the player later, you forgot to add the entity dependency. Add it now.

---

### Step 3 — Roll the Dice (8 min)

**First — a quick word on variables** (explain before touching MCreator):

> *"A variable is a named box that holds a number. You can change what's in it, and you can look at it later. We're making a box called `roll` and putting whatever random number we got inside. When we write `if roll == 1` later, the game opens that box and checks what's inside."*

A good analogy: a chest with a name tag. The name is `roll`. The contents change every time we right-click.

In the procedure canvas:

1. Drag out a **Set variable** block. Name the variable `roll`.
2. Go to **Math** → drag out **random integer from [ ] to [ ]**
3. Snap it into the value slot of your set block
4. Enter `1` and `6` as the range

Then add a **send chat message** block directly below. The message should say:

```
"You rolled a " + roll + "!"
```

> **Joining text and variables ("strings"):** "String" is just the programmer word for text. In MCreator, use a **"create text with"** block (in the Text category). It has slots you can chain: one for `"You rolled a "` (with a trailing space!), one for the `roll` variable (drag a **get variable** block from Variables), one for `"!"`.

> **Set vs Get:** You *set* a variable to put something in the box. You *get* a variable to read what's inside. You'll need a "get variable" block when referencing `roll` inside conditions later.

**Test early!** Before building the whole else-if chain, build the mod now and test that chat shows `"You rolled a 3!"` (or whatever number). This confirms the random block and chat message work before you add more complexity. Fix any issues here rather than buried inside a 6-branch chain.

---

### Step 4 — Build the else-if Chain (20 min)

Drag out an **if** block from Logic.

> **The gear icon trick:** Click the small **gear icon** on the if block. A menu appears letting you add `else if` and `else` sections. Add five `else if` sections and one `else`.

Now fill in each branch. For the condition slot, drag a **comparison block** (the one with `=` between two slots) from Logic. Set left side to the `roll` variable, right side to the number.

| Branch | Condition | Action |
|--------|-----------|--------|
| if | roll == 1 | Strike lightning at player's x, y, z |
| else if | roll == 2 | Summon Zombie at x+2, y, z |
| else if | roll == 3 | Heal player (add 4 to health — that's 2 hearts) |
| else if | roll == 4 | Give player 20 experience points |
| else if | roll == 5 | Set entity on fire for 3 seconds |
| else | *(no condition)* | Repeat 10 times: give player 50 XP |

> **For lightning:** drag in the player's X, Y, Z coordinate blocks from the **Entity** category to feed into the position slots.

> **For healing:** look in the **Entity** category for "heal entity" or "set entity health to (current health + 4)". Each heart is 2 health points in Minecraft.

> **For the jackpot:** Put a **repeat 10 times** loop inside the `else`, with give-experience inside it.

> **Why no explicit roll-6 branch?** Because `else` catches everything not already matched above. If it wasn't 1, 2, 3, 4, or 5 — it must be 6. You never need to write `else if roll == 6`. The `else` IS your 6.

---

### Step 5 — Link the Procedure to the Block (5 min)

Go back to your **Dice Block** settings. Find the **Events/Triggers** tab. Find the **"When block is right-clicked by player"** event and choose `DiceBlockRightClicked` from the dropdown.

Save. Build the mod.

---

### Step 6 — Test! (10 min)

Run the client. Place your Dice Block. Right-click it repeatedly. You should see:
- Chat messages telling you what you rolled
- Lightning, zombies, XP, fire — randomness in action!

Ask students: *"Did anyone roll the same number twice in a row? Three times? Is that cheating?"*

Answer: *It's real randomness — you could technically roll 6 ones in a row and it would still be fair. Humans are bad at seeing randomness as fair.*

---

## What the Code Actually Looks Like

Show this to connect the scratch blocks to real code:

```java
// MCreator generates something like this:
int roll = world.getRandom().nextInt(6) + 1;  // 1 to 6

player.sendSystemMessage(Component.literal("You rolled a " + roll + "!"));

if (roll == 1) {
    // lightning strike at player position
} else if (roll == 2) {
    // spawn zombie nearby
} else if (roll == 3) {
    entity.heal(4.0f);  // 4 = 2 hearts (each heart = 2 health points)
} else if (roll == 4) {
    player.giveExperiencePoints(20);
} else if (roll == 5) {
    entity.setSecondsOnFire(3);
} else {
    // jackpot! must be 6 — else catches what fell through
    for (int i = 0; i < 10; i++) {
        player.giveExperiencePoints(50);
    }
}
```

Point out: `nextInt(6)` gives 0–5, then `+1` shifts it to 1–6. MCreator handles this automatically — but it's good to know it's just maths.

---

## Common Problems

| Problem | Likely Cause |
|---------|-------------|
| Right-clicking does nothing | Forgot to link the procedure to the block's right-click event |
| "roll" variable doesn't work | Wrong variable name in one of the branches — check spelling |
| All branches fire instead of just one | Used separate `if` blocks instead of one `if / else if / else` chain |
| Can't access the player for XP/fire | Entity dependency missing from the procedure — re-create with it included |

---

## Extension Challenges

- Make roll 3 say something spooky like `"You feel a chill..."` with a particle effect instead of truly nothing
- Change `random 1 to 6` to `random 1 to 10` — now only rolling a 10 gets the jackpot (rarer!)
- What happens if you try to right-click 20 times in a row? Can you spam the dice? (We'll fix this properly in Lesson 3)

---

## Next Lesson Preview

We'll give players a **Warrior Skill level** that goes up every time they kill a mob. And then we'll make the dice check that skill — a skilled warrior can survive a roll of 1 that would destroy a beginner!
