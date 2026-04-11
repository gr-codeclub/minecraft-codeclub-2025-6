# Lesson 8: The Spell Selector — Choosing Your Magic

**Theme:** Java Superpowers — making decisions with numbers
**Duration:** ~60 minutes
**New Concept:** Java `if` with `==` comparison — checking a number to decide what to do
**Builds On:** Lesson 6 (Greeting spell), Lesson 7 (Radiance spell), Lesson 2 (scoreboards)

---

## What We're Building

Right now, right-clicking the Spellbook fires ALL your spells at once. That's not how a spellbook works! Today we fix it:

1. **Sneak + right-click** → opens a spell selection menu (GUI)
2. **Click a spell button** → remembers your choice (saves to scoreboard)
3. **Regular right-click** → casts ONLY the selected spell

---

## Teacher: Physical Demo (8 min)

### Part 1 — The Restaurant

> *"Imagine you go to a restaurant. The waiter doesn't bring you every dish on the menu at once, right? You LOOK at the menu, CHOOSE a meal, and from then on the kitchen makes THAT meal for you. If you want something different later, you look at the menu again."*

Write on the whiteboard:

```
Sneak + right-click  →  Look at the menu
Click a button       →  Tell the waiter what you want
Right-click          →  The kitchen makes your order
```

### Part 2 — How Does the Kitchen Know?

> *"The waiter writes your order on a piece of paper — let's say a number. Meal 1, meal 2, meal 3. The kitchen reads the number and makes the matching dish."*

Write:

```
Order: 1  →  Spell of Greeting
Order: 2  →  Spell of Radiance
```

> *"In our mod, the 'piece of paper' is a scoreboard. The 'reading the number' is a Java `if` check."*

Write the Java:

```java
if ((int) selectedSpell == 1)
    // cast Greeting
if ((int) selectedSpell == 2)
    // cast Radiance
```

> *"This is a new Java pattern: `if (number == value)`. The double equals `==` means 'is this equal to?' It's different from single `=` which means 'put this inside'. Double equals ASKS a question. Single equals GIVES an answer."*

### Part 3 — `==` vs `=`

Draw big on the board:

```
=    means "set this to" (assignment)
==   means "is this equal to?" (comparison)
```

> *"In Blockly, you used the comparison block with `=` between two slots. In Java, comparison uses TWO equals signs: `==`."*

---

## Step-by-Step: MCreator

### Step 1 — Create the Scoreboard (2 min)

In your Minecraft client (or at the top of your procedure), create the scoreboard that will remember the selected spell:

```
/scoreboard objectives add selected_spell dummy
/scoreboard players set @s selected_spell 1
```

This sets the default to spell 1 (Greeting) so there's always something selected.

---

### Step 2 — Create the Spell Selector GUI (10 min)

1. In MCreator, click **+ New Element --> GUI**
2. Name it `SpellSelector`
3. In the **GUI type** dropdown, choose the basic (non-inventory) GUI option
4. The visual editor opens — you'll see a dark canvas with a toolbar along the top (icons for label, button, image, etc.)

**Add a label:**
1. Click the **Add label** button in the toolbar
2. Place it near the top of the canvas
3. Set the text to `"Choose Your Spell:"`
4. Set the color to white

**Add the first button:**
1. Click **Add button** in the toolbar
2. Place it below the label
3. Set the text to `"Spell of Greeting"`
4. In the button editor, next to **"On button clicked"**, create a new procedure called `SelectGreeting`

**Add the second button:**
1. Click **Add button** again
2. Place it below the first button
3. Set the text to `"Spell of Radiance"`
4. Create a new procedure called `SelectRadiance`

Save the GUI.

---

### Step 3 — Create the Button Procedures (5 min)

**SelectGreeting procedure:**
1. Open the `SelectGreeting` procedure
2. Drag in a **"Set score to on the scoreboard of"** block (from **World procedures --> Scoreboard**)
3. Set it to: set score `selected_spell` to `1` on the scoreboard of **"Event/target entity"**
4. Below that, drag in a **"Close any GUI open for"** block (from **World procedures --> Actions**)
5. Set it to close the GUI for **"Event/target entity"**

**SelectRadiance procedure:**
1. Open the `SelectRadiance` procedure
2. Same as above, but set the score to `2` instead of `1`
3. Add the close GUI block

> Both procedures are pure Blockly — no custom Java needed here. The buttons just set a number and close the menu.

---

### Step 4 — Rewrite the SpellbookCast Procedure (15 min)

This is where the magic happens. Open your `SpellbookCast` procedure. We're going to reorganize it.

> **BEFORE YOU CHANGE ANYTHING:** Your procedure has working code from Lessons 6 and 7. We're not throwing it away — the Java lines will come back in Step 5 inside `if` blocks. Here's what to do:
> - **KEEP** the Blockly "send chat message" block — we still need it for imports
> - **REMOVE** the Custom code snippets from Lessons 6 and 7 (we'll re-add them inside if-blocks in Step 5)
> - If your procedure has ghost mode code from Lesson 7's extension, remove those custom code snippets too

Now clear out the custom code snippets (but keep the Blockly blocks).

**Part A — The sneak check (Blockly):**

1. Drag in an **"If / else"** block from the **Logic** category
2. For the condition, drag in **"Is entity sneaking"** (from **Entity procedures** category)
3. Set the entity to **"Event/target entity"**

**Inside the "if" branch (sneaking = open the menu):**

4. Drag in an **"Open GUI"** block (from **World procedures --> Actions**)
5. Set it to open `SpellSelector` for **"Event/target entity"** at the player's x, y, z coordinates

**Inside the "else" branch (not sneaking = cast the selected spell):**

6. Drag in a **"Set number variable"** block and name it `selectedSpell`
7. For the value, drag in a **"Get scoreboard score"** block
8. Set it to get `selected_spell` for **"Event/target entity"**

Now add the Java dispatch code:

---

### Step 5 — The Java Dispatch (10 min)

This is the new Java concept! Inside the "else" branch, after the Blockly scoreboard read, add custom code snippets that check which spell was selected.

**Custom code snippet 1 — Greeting (from Lesson 6):**

```java
if ((int) selectedSpell == 1)
if (entity instanceof Player _p) _p.displayClientMessage(Component.literal("I cast the Spell of Greeting!"), false);
```

**Custom code snippet 2 — Radiance (from Lesson 7):**

```java
if ((int) selectedSpell == 2)
entity.setGlowingTag(true);
```

Let's break down the new pattern:

| Piece | What it means |
|-------|---------------|
| `(int) selectedSpell` | Convert the Blockly number to a whole number (it's stored as a decimal) |
| `==` | "Is this equal to?" — the comparison operator |
| `1` | The number that means "Greeting" |

> **Why `(int)`?** Blockly stores all numbers as decimals (like `1.0` instead of `1`). The `(int)` strips the decimal part so the comparison works cleanly. We'll use this pattern again in later lessons.

**Check the dispatch line carefully:**

- `if` — lowercase
- Space, then `(`
- `(int)` — parentheses around the word `int`
- Space
- `selectedSpell` — capital S, capital P in the middle (camelCase!)
- Space
- `==` — **TWO** equals signs, not one!
- Space
- `1` — the spell number
- `)` — closes the if

> **Why separate `if` blocks instead of `if / else if`?** Each spell is its own code snippet. When you add new spells in later lessons, you just add a new snippet with the next number. Clean and modular.

Your procedure should now look like:

```
[Blockly: if "Is entity sneaking"]
    [Blockly: Open GUI "SpellSelector" for entity]
[Blockly: else]
    [Blockly: set selectedSpell = get scoreboard "selected_spell"]
    [Custom code: if ((int) selectedSpell == 1) → Greeting]
    [Custom code: if ((int) selectedSpell == 2) → Radiance]
```

---

### Step 6 — Build and Test! (10 min)

Build and run. Give yourself the Spellbook.

**Test 1 — Open the menu:**
1. Hold the Spellbook
2. **Sneak** (hold shift) and **right-click**
3. The spell selector GUI should open with two buttons

**Test 2 — Select a spell:**
1. Click "Spell of Greeting"
2. The GUI should close

**Test 3 — Cast the selected spell:**
1. **Regular right-click** (don't sneak)
2. You should see your greeting message in chat — and ONLY the greeting, not the glow!

**Test 4 — Change spell:**
1. Sneak + right-click to open the menu again
2. Click "Spell of Radiance"
3. Regular right-click
4. You should glow — and NOT see the greeting message!

**Test 5 — Persistence (optional — skip if short on time):**
1. Disconnect and rejoin the world
2. Right-click the Spellbook (without opening the menu)
3. It should still cast whatever you selected last — scoreboards survive!

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| GUI doesn't open | Sneak check not working | Make sure the "Is entity sneaking" block uses "Event/target entity", not just "entity" |
| Both spells fire | Old procedure code wasn't cleared | Remove the old code from Steps 4. The spells should ONLY be inside the custom code `if` blocks |
| Nothing happens on right-click | `selectedSpell` not being read | Check that the Blockly "set number variable" and "get scoreboard score" blocks are connected inside the "else" branch |
| Wrong spell fires | Scoreboard numbers don't match | Button procedure sets score to 1 or 2; custom code checks `== 1` or `== 2`. Make sure they match |
| `=` instead of `==` | Used assignment instead of comparison | `=` puts a value in a variable. `==` asks "is it equal?" Use `==` in `if` statements |
| GUI opens but buttons do nothing | Button procedures not linked | Go back to the GUI editor, click each button, and make sure the "On button clicked" procedure is set |

---

## Extension 1: "No Spell Selected" Message

What if the player right-clicks before choosing a spell? Add a new custom code snippet after the others:

```java
if ((int) selectedSpell == 0)
if (entity instanceof Player _p) _p.displayClientMessage(Component.literal("No spell selected! Sneak + right-click to choose."), false);
```

Set the default scoreboard value to `0` instead of `1`, and now players get a helpful message until they pick a spell.

---

## Extension 2: Spell Name Display

When you cast a spell, show which one you're casting. Add this as the FIRST custom code snippet in the else branch:

```java
if (entity instanceof Player _p)
_p.displayClientMessage(Component.literal("Casting spell #" + (int) selectedSpell + "..."), false);
```

This uses string concatenation (the `+` operator) to join text and the spell number.

---

## Extension 3: More Buttons

Add a third button to the GUI for a spell you haven't learned yet — maybe "Spell of Healing" with number 3. The button and scoreboard will work, but the cast procedure won't do anything yet because there's no `if ((int) selectedSpell == 3)` block. You'll add that in Lesson 9!

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `Component` | `net.minecraft.network.chat.Component` | Blockly "Send chat message" block |
| `Player` | `net.minecraft.world.entity.player.Player` | Blockly "Send chat message" block |

No new imports needed — this lesson reuses the spells from Lessons 6 and 7.

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **`==` comparison** | Double equals asks "is this equal to?" — different from `=` which assigns |
| **`if` with numeric condition** | `if ((int) selectedSpell == 1)` — check a number and act on it |
| **Code dispatch** | Using multiple `if` blocks to choose which code runs based on a stored value |
| **State management** | Storing a choice (scoreboard) so it persists across right-clicks and game sessions |

---

## Next Lesson Preview

> *"Your Spellbook is organized now — you pick a spell and it sticks. But we only have two spells! Next lesson, we'll add the Spell of Healing. Here's the problem: you can't just write `entity.heal(4)` because not everything can be healed. Can you heal an arrow? A dropped item? No. We'll learn how to CHECK what type of thing you're dealing with before trying to heal it."*
