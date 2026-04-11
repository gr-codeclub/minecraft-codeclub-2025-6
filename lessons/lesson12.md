# Lesson 12: Spell of the Storm — For Loops

**Theme:** Java Superpowers — repeating code with a changing counter
**Duration:** ~60 minutes
**New Concept:** The `for` loop — repeating code a specific number of times, where each repetition is slightly different
**Builds On:** Lesson 10 (particles, ServerLevel), Lesson 11 (variables, int)

---

## What We're Building

The **Spell of the Storm** — a pillar of glowing light that rises from the player into the sky, one block at a time. Each particle burst appears one block higher than the last.

This is something Blockly's "repeat" block **cannot do** — because Blockly's repeat doesn't give you a counter that changes each time. Java's for-loop does.

---

## Teacher: Physical Demo (10 min)

### Part 1 — Repetition Is Boring to Write

Stand up. Tell the class:

> *"Clap once."* (clap)
> *"Clap once."* (clap)
> *"Clap once."* (clap)
> *"Clap once."* (clap)
> *"Clap once."* (clap)

> *"That was annoying, right? I said the same thing five times. Better:"*

> *"Clap FIVE TIMES."*

(Everyone claps five times.)

> *"That's a loop. Instead of writing the same code five times, we write it ONCE and say 'do this 5 times.'"*

### Part 2 — The Counter Makes Each One Different

> *"But here's the SUPERPOWER. What if each clap should be different?"*

> *"Clap at your knees."* (clap low)
> *"Clap at your waist."* (clap middle)
> *"Clap at your chest."* (clap higher)
> *"Clap above your head."* (clap high)
> *"Clap on tiptoes!"* (clap highest)

> *"Each time, the HEIGHT changed. It went UP by one step. In Java, the for-loop gives you a counter that goes up by 1 each time. We can use that counter to control WHERE things happen."*

### Part 3 — The For Loop on the Board

Write on the whiteboard:

```
for (int i = 0; i < 5; i++) {
    // do something with i
}
```

Break it down:

| Piece | What it means |
|-------|---------------|
| `int i = 0` | Start counting from 0 |
| `i < 5` | Keep going while i is less than 5 |
| `i++` | After each loop, add 1 to i |
| `{ ... }` | The curly braces are like **fence posts** — everything between them runs each time |

> *"So i goes: 0, 1, 2, 3, 4 — that's 5 loops. If we use `i` inside the code, each loop is slightly different!"*

Show the particle example:

```
for (int i = 0; i < 10; i++) {
    spawn particles at y + i
}
```

> *"When i is 0, particles appear at y+0 (the player's feet). When i is 1, at y+1 (one block up). When i is 9, at y+9 (nine blocks up!). It builds a pillar!"*

Draw it on the board:

```
y+9  ✨
y+8  ✨
y+7  ✨
y+6  ✨
y+5  ✨
y+4  ✨
y+3  ✨
y+2  ✨
y+1  ✨
y+0  ✨ ← player is here
```

### Part 4 — Why Blockly's Repeat Can't Do This

> *"In Blockly, the 'repeat 10 times' block runs the same code 10 times. But there's no counter — no `i` that changes. Every loop is identical. That's fine for 'give 10 XP ten times' but useless for 'spawn particles at increasing heights.' Java's for-loop gives you the counter. That's the superpower."*

---

## Step-by-Step: MCreator

### Step 1 — Set Up the Imports (3 min)

We need `ServerLevel` and `ParticleTypes` again (from Lesson 10). If your procedure still has the disconnected Blockly "Spawn particles" block from Lesson 10, you're already set!

If not, drag one in from **World procedures --> Actions** and leave it floating.

> **Alternative fully-qualified names:**
> - `net.minecraft.server.level.ServerLevel`
> - `net.minecraft.core.particles.ParticleTypes`

---

### Step 2 — Type the Spell of the Storm (15 min)

Add a new **"Custom code snippet"** block to your procedure. **This is our longest block yet — 4 lines!** Take it one line at a time, and build-test after if you want to check your progress.

Type carefully:

```java
for (int i = 0; i < 10; i++) {
if (world instanceof ServerLevel _sl)
_sl.sendParticles(ParticleTypes.END_ROD, x, y+i, z, 3, 0.2, 0, 0.2, 0);
}
```

**Line by line:**

**Line 1 — The loop header:**
```java
for (int i = 0; i < 10; i++) {
```
- `for` — keyword that starts a loop
- `int i = 0` — counter starts at 0
- `i < 10` — keep going while i is less than 10
- `i++` — add 1 each time (this is shorthand for `i = i + 1`)
- `{` — opening curly brace (the left fence post — everything between the fences runs inside the loop)

**Line 2 — The server check (same pattern from Lesson 10):**
```java
if (world instanceof ServerLevel _sl)
```

**Line 3 — The particles (same as Lesson 10, but with `y+i`):**
```java
_sl.sendParticles(ParticleTypes.END_ROD, x, y+i, z, 3, 0.2, 0, 0.2, 0);
```
- The key difference: `y+i` instead of `y+1`
- When i=0: particles at y+0 (feet)
- When i=5: particles at y+5 (five blocks up)
- When i=9: particles at y+9 (nine blocks up!)

**Line 4 — Close the loop:**
```java
}
```
- `}` — closing curly brace (the right fence post — end of the loop body)

> **Brace check:** You need ONE `{` at the end of line 1 and ONE `}` on line 4. These are **curly braces** — they mark the start and end of the loop body. Make sure they match!

> **Common confusion: two kinds of brackets!**
> - `{ }` are **curly braces** (fence posts) — they wrap code that runs together (loop bodies, if bodies)
> - `( )` are **round brackets** — they wrap arguments and conditions
>
> They're different keys on the keyboard! Don't mix them up. If your error says `{ expected`, you probably typed `(` where you needed `{`.

---

### Step 3 — Build and Test! (10 min)

Build and run. Right-click your Spellbook.

You should see a **pillar of glowing END_ROD particles** rising 10 blocks into the sky from where you're standing. It appears all at once (because the loop runs instantly) as a column of light.

> Ask: *"Why does it appear all at once instead of building up slowly?"*
>
> Because the loop runs in a fraction of a second. All 10 iterations happen almost instantly. If we wanted it to build up slowly, we'd need timers — that's a future lesson.

Try standing on top of a hill and casting. The pillar shoots up into the sky!

---

### Step 4 — Experiment! (10 min)

**Change the height.** Replace `i < 10` with:
- `i < 3` — short pillar (3 blocks)
- `i < 20` — tall pillar (20 blocks!)
- `i < 50` — massive tower of light

**Change the particle type.** Try `ParticleTypes.FLAME` for a fire column, or `ParticleTypes.SOUL_FIRE_FLAME` for blue fire.

**Change the particle count.** Replace `3` (the 5th argument) with `10` for a denser pillar.

**Change the spread.** Replace `0.2, 0, 0.2` with `0, 0, 0` for a tight laser beam, or `1, 0, 1` for a wide column.

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `{ expected` error | Missing `{` at end of line 1 | Make sure the for line ends with `{` |
| `} expected` error | Missing `}` on line 3 | Add `}` as the last line |
| Particles all in one spot | Used `y+1` instead of `y+i` | The `i` is what changes each loop — make sure it's lowercase `i` |
| Only one burst of particles | Used `i < 1` or missing loop | Check your loop: `i = 0; i < 10; i++` |
| `i` not found | Typo in loop header | Make sure it's `int i = 0` with a semicolon after the 0 |
| Weird errors about semicolons | Semicolon placement in for header | The for header has exactly 2 semicolons: `for (int i = 0; i < 10; i++)` — no semicolon after `i++` |

---

## Extension 1: Ascending Sound Scale

Add a sound inside the loop! Put this in a SEPARATE custom code snippet right after the particle one (still inside the same procedure, but a new snippet):

```java
for (int i = 0; i < 8; i++) {
world.playSound(null, BlockPos.containing(x,y,z), SoundEvents.NOTE_BLOCK_PLING.value(), SoundSource.PLAYERS, 0.5f, 0.5f + i * 0.15f);
}
```

The pitch argument is `0.5f + i * 0.15f`:
- When i=0: pitch = 0.5 (low)
- When i=4: pitch = 1.1 (medium)
- When i=7: pitch = 1.55 (high)

It plays an ascending musical scale! Combined with the particle pillar, your spell now LOOKS and SOUNDS magical.

---

## Extension 2: Ring of Fire (Advanced — Teacher: type this on the projector for students to copy)

This one uses some maths to draw particles in a circle around the player. Don't worry about the `Math.sin` and `Math.cos` — it's "magic math that draws circles":

```java
for (int i = 0; i < 12; i++) {
if (world instanceof ServerLevel _sl) _sl.sendParticles(ParticleTypes.FLAME, x + Math.sin(i * 30 * Math.PI / 180) * 3, y + 0.5, z + Math.cos(i * 30 * Math.PI / 180) * 3, 1, 0, 0, 0, 0);
}
```

Don't worry about the `Math.sin` and `Math.cos` — it's "magic math that draws circles." Here's what you can change:
- `12` — number of fire points (more = smoother circle)
- `30` — angle between points (360/12 = 30)
- `3` — radius of the circle (try 5 for a bigger ring)
- `FLAME` — try `SOUL_FIRE_FLAME` for a blue ring, or `END_ROD` for a light ring

---

## Extension 3: Spiralling Pillar (Advanced — Teacher-assisted)

Combine the pillar AND the circle for a spiral. *(Teacher: type this on the projector — the line is too long for most students to type from description alone.)*

```java
for (int i = 0; i < 30; i++) {
if (world instanceof ServerLevel _sl) _sl.sendParticles(ParticleTypes.END_ROD, x + Math.sin(i * 24 * Math.PI / 180) * 2, y + i * 0.5, z + Math.cos(i * 24 * Math.PI / 180) * 2, 1, 0, 0, 0, 0);
}
```

The particles spiral upward because `y + i * 0.5` goes up AND `x + Math.sin(...)` goes in a circle. The result: a DNA-helix-style spiral of light.

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `ServerLevel` | `net.minecraft.server.level.ServerLevel` | Blockly "Spawn particles" block |
| `ParticleTypes` | `net.minecraft.core.particles.ParticleTypes` | Blockly "Spawn particles" block |
| `SoundEvents` | `net.minecraft.sounds.SoundEvents` | Blockly "Play sound" block |
| `SoundSource` | `net.minecraft.sounds.SoundSource` | Blockly "Play sound" block |
| `BlockPos` | `net.minecraft.core.BlockPos` | Any Blockly block using positions |
| `Math` | `java.lang.Math` | Always available — no import needed! |

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **for loop** | `for (int i = 0; i < 10; i++) { }` — repeat code with a changing counter |
| **Counter variable** | `i` starts at 0, goes up by 1 each loop, stops when the condition is false |
| **Curly braces** | `{ }` mark the start and end of a code block (loop body, if body) |
| **i++** | Shorthand for `i = i + 1` |
| **Using the counter** | `y + i` makes each iteration different — that's the superpower! |

---

## The Full Spellbook

Look at your procedure now! Over 6 lessons, you've built:

```
[Blockly: send chat message]           ← Lesson 6 import trick
[Java: instanceof Player → greeting]   ← Lesson 6
[Java: entity.setGlowingTag(true)]     ← Lesson 7
[Java: if selectedSpell == N → dispatch] ← Lesson 8
[Java: instanceof LivingEntity → heal] ← Lesson 9
[Java: ServerLevel → particles]        ← Lesson 10
[Java: playSound]                      ← Lesson 10
[Blockly: set warriorScore]            ← Lesson 11 bridge
[Java: int bonus = calculation]        ← Lesson 11
[Java: instanceof Player → give items] ← Lesson 11
[Java: for loop → particle pillar]     ← Lesson 12
```

A mix of Blockly blocks and custom Java code, working together. That's how real mod developers work — use the tools that make sense for each job.

---

## Where To Go From Here

You now know the building blocks of Java:
- **Strings** — text in double quotes
- **Methods** — object.doSomething()
- **instanceof** — checking types before acting
- **Arguments** — controlling methods with values
- **Variables** — storing and calculating numbers
- **For loops** — repeating code with a changing counter

These are the same concepts professional Java developers use every day. The only difference is they write MORE of them and combine them in bigger ways.

Some ideas for your next projects:
- A spell that spawns a protective dome of particles around you
- A spell that gives different effects based on time of day
- A wand that shoots fireballs (spawning a Fireball entity with velocity)
- A custom enchantment that triggers particle effects when you hit a mob
- A spell that builds structures (placing blocks in a loop)

The Spellbook is yours now. What will you add to it?
