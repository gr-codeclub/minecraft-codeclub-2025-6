# Lesson 11: Spell of Fortune — Variables and Math

**Theme:** Java Superpowers — calculating things and storing results
**Duration:** ~60 minutes
**New Concept:** Declaring variables and doing arithmetic in Java
**Builds On:** Lesson 6-10 (all previous Java concepts), Lesson 2 (warrior skill scoreboard)

---

## What We're Building

The **Spell of Fortune** — gives the player diamonds, but the NUMBER of diamonds depends on their warrior skill level from the RPG system (Lesson 2). Stronger warriors get more loot!

| Warrior Score | Diamonds |
|--------------|----------|
| 0-4 (Peasant) | 1 |
| 5-9 (Fighter) | 2 |
| 10-14 (Warrior) | 3 |
| 15-19 | 4 |
| 20+ (Champion) | 5+ |

---

## Teacher: Physical Demo (8 min)

### Part 1 — Variables Are Named Jars

**Bring:** a jar (or cup) and some coins (or counters, sweets, anything countable). Stick a label on the jar: **`bonus`**.

Put 3 coins in the jar.

> *"This jar is a **variable**. It has a name — `bonus`. It has contents — 3. In Java, I'd write this as:"*

Write on the whiteboard:

```
int bonus = 3;
```

| Piece | What it means |
|-------|---------------|
| `int` | "This jar holds a whole number" (short for **integer**) |
| `bonus` | The name of the jar |
| `=` | "Put this inside" |
| `3` | The value |
| `;` | End of sentence |

### Part 2 — Math With Variables

Add 2 more coins to the jar.

> *"Now bonus is 5. In Java:"*

```
bonus = bonus + 2;
```

> *"Read it as: bonus BECOMES bonus PLUS 2. The old value was 3, plus 2 is 5."*

Remove all coins. Put in a calculated amount:

> *"What if your warrior skill is 12? How many diamonds should you get?"*

Write on the board:

```
int bonus = (12 / 5) + 1;
```

> *"12 divided by 5 is... well, in Java, dividing whole numbers throws away the remainder. 12 / 5 = 2 (not 2.4). Then plus 1 = 3. So a warrior with skill 12 gets 3 diamonds."*

Try a few more:
- Skill 0: (0/5)+1 = 1 diamond
- Skill 5: (5/5)+1 = 2 diamonds
- Skill 20: (20/5)+1 = 5 diamonds

> *"The formula scales! Better warriors automatically get more loot. That's the power of math in code."*

### Part 3 — Blockly Variables vs Java Variables

> *"You've used variables in Blockly before — remember the `roll` variable in the dice block? In Blockly, you dragged a 'set variable' block. In Java, you TYPE `int bonus = 3;`. Same idea, different way of writing it."*

> *"One important difference: in Java, you have to say WHAT TYPE of thing the jar holds. `int` means whole numbers. You can't put text in an `int` jar. Blockly didn't make you worry about types — Java does."*

---

## Step-by-Step: MCreator

### Step 1 — Set Up the Scoreboard and Read It With Blockly (5 min)

First, make sure the warrior scoreboard exists. In your Minecraft client, run these commands:

```
/scoreboard objectives add skill_warrior dummy
/scoreboard players set @s skill_warrior 10
```

This gives you a warrior score of 10 to test with. (If you already have `skill_warrior` from Lesson 2, just set it to 10.)

Now, in your `SpellbookCast` procedure:

1. Drag in a **"Set number variable"** block (from the **Variables** category)
2. Name the variable `warriorScore`
3. For the value, drag in a **"Get scoreboard score"** block (from **World procedures --> Scoreboard**)
4. Set it to get the `skill_warrior` objective for the **"Event/target entity"**
5. Connect this block ABOVE your custom code snippets — it needs to run first

This reads the player's warrior skill into a Blockly variable called `warriorScore`.

> **The Blockly-Java bridge:** When MCreator builds your mod, it turns Blockly's `warriorScore` into a Java variable called `warriorScore`. Your custom code can use this variable directly — that's the bridge between Blockly and Java. Blockly reads the scoreboard (because it's good at that), Java does the math (because it's good at that). They work together!

---

### Step 2 — Set Up the Import (3 min)

We need `ItemStack` and `Items` for giving diamonds.

1. Drag in a **"Give item to player"** block from **Player procedures --> Actions**
2. Leave it disconnected — it's just triggering the imports

> **Alternative fully-qualified names:**
> - `net.minecraft.world.item.ItemStack` instead of `ItemStack`
> - `net.minecraft.world.item.Items` instead of `Items`

---

### Step 3 — Calculate the Bonus in Java (10 min)

Add a new **"Custom code snippet"** block. Type:

```java
int bonus = (int)(warriorScore / 5) + 1;
```

Let's break it down:

| Piece | What it means |
|-------|---------------|
| `int` | This variable holds a whole number |
| `bonus` | The variable's name |
| `=` | "Make it equal to..." |
| `(int)` | Convert to a whole number (because Blockly numbers can be decimals) |
| `warriorScore / 5` | Divide the warrior score by 5 |
| `+ 1` | Add 1 (so even beginners get at least 1 diamond) |
| `;` | End of sentence |

> **What's the `(int)` for?** Blockly stores all numbers as decimals (called `double` in Java). But `ItemStack` needs a whole number for the count. `(int)` is like a cookie cutter — it chops off the decimal tail. `2.4` becomes `2`. `9.9` becomes `9`. Always rounds down.

---

### Step 4 — Give the Diamonds (8 min)

Add another **"Custom code snippet"** block below the bonus calculation. Type in two parts:

**Part A — The player check:**

```java
if (entity instanceof Player _p)
```

**Part B — Give the diamonds:**

```java
_p.getInventory().add(new ItemStack(Items.DIAMOND, bonus));
```

Let's break down Part B:

| Piece | What it means |
|-------|---------------|
| `_p.getInventory()` | Get the player's inventory |
| `.add(...)` | Add something to it |
| `new ItemStack(...)` | Create a new stack of items |
| `Items.DIAMOND` | The item type — diamonds! |
| `bonus` | How many — our calculated variable! |

Your complete code in this snippet:

```java
if (entity instanceof Player _p)
_p.getInventory().add(new ItemStack(Items.DIAMOND, bonus));
```

---

### Step 5 — Build and Test! (10 min)

Build and run. Before testing, give yourself some warrior XP:

```
/scoreboard objectives add skill_warrior dummy
/scoreboard players set @s skill_warrior 12
```

Now right-click your Spellbook. Check your inventory — you should get **3 diamonds** (because 12/5 + 1 = 3).

Try different warrior scores:

| Command | Score | Expected Diamonds |
|---------|-------|------------------|
| `/scoreboard players set @s skill_warrior 0` | 0 | 1 |
| `/scoreboard players set @s skill_warrior 5` | 5 | 2 |
| `/scoreboard players set @s skill_warrior 10` | 10 | 3 |
| `/scoreboard players set @s skill_warrior 20` | 20 | 5 |
| `/scoreboard players set @s skill_warrior 50` | 50 | 11 |

> *"Your code did all that math automatically! One formula handles every possible warrior level."*

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `bonus` is always 1 | `warriorScore` variable not set | Make sure the Blockly "set variable" block is ABOVE the custom code and connected to the procedure flow |
| `ItemStack` not found | Missing import | Add the Blockly "Give item" block (Step 2), or use `net.minecraft.world.item.ItemStack` |
| `Items` not found | Missing import | Same as above — the give-item block triggers both |
| Weird decimal numbers | Didn't cast to `int` | Make sure you have `(int)` before the math: `(int)(warriorScore / 5)` |
| "cannot find symbol: bonus" | Custom code snippets are in wrong order | The snippet that declares `int bonus = ...` must come BEFORE the snippet that uses `bonus` |
| Get 0 diamonds | Integer division went wrong | Check your parentheses: `(int)(warriorScore / 5) + 1` — the `+ 1` must be OUTSIDE the `(int)` cast |
| Error about `int` | Wrote `int(warriorScore / 5)` like a function call | The `int` needs its own brackets: `(int)(warriorScore / 5)` — cast syntax, not function syntax |

---

## Extension 1: Random Loot

Add a luck factor! Replace the bonus calculation with:

```java
int bonus = (int)(warriorScore / 5) + 1 + (int)(Math.random() * 3);
```

`Math.random() * 3` gives a random decimal between 0 and 3. `(int)` chops it to 0, 1, or 2. So the player gets their skill-based bonus PLUS 0-2 extra random diamonds.

> *"Even with the same warrior skill, you might get different amounts each time. Sound familiar? It's like the dice block — randomness keeps it exciting!"*

---

## Extension 2: Announce the Loot

Add a chat message that tells you how many diamonds you got. Put this in a new custom code snippet AFTER the give-items snippet:

```java
if (entity instanceof Player _p)
_p.displayClientMessage(Component.literal("Fortune smiles! +" + bonus + " diamonds!"), false);
```

The `+` operator joins text and numbers together. `"+" + bonus + " diamonds!"` becomes something like `"+3 diamonds!"`.

---

## Extension 3: Different Items for Different Ranks

Instead of always giving diamonds, use an if/else chain (coming next lesson for Java, but you can do it in Blockly):

- **Peasant:** Coal
- **Fighter:** Iron ingots
- **Warrior:** Gold ingots
- **Champion:** Diamonds

Replace `Items.DIAMOND` with `Items.COAL`, `Items.IRON_INGOT`, `Items.GOLD_INGOT`, etc.

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `ItemStack` | `net.minecraft.world.item.ItemStack` | Blockly "Give item to player" block |
| `Items` | `net.minecraft.world.item.Items` | Blockly "Give item to player" block |
| `Player` | `net.minecraft.world.entity.player.Player` | Blockly "Send chat message" block (from Lesson 6) |

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **Variable declaration** | `int bonus = 3;` — creating a named number box |
| **int** | A type that holds whole numbers |
| **Arithmetic** | `+`, `-`, `*`, `/` work on numbers in Java |
| **Integer division** | `12 / 5 = 2` (not 2.4) — dividing whole numbers drops the remainder |
| **Type casting** | `(int)` converts a decimal to a whole number (rounds down) |
| **new** | `new ItemStack(...)` creates a new object — like building a Lego set from its box |
| **Method chaining** | `_p.getInventory().add(...)` — calling a method on the RESULT of another method |

---

## Next Lesson Preview

> *"Your Spellbook is getting powerful. Messages, glowing, healing, particles, sounds, loot — all with Java. But every spell happens once. What if you want something to happen TEN times? Or a HUNDRED times? Next lesson, we'll learn the for-loop — Java's way of saying 'do this again and again, but slightly different each time.' We'll build a pillar of light that rises into the sky, one block at a time."*
