# Lesson 9: Spell of Healing — Checking Types with instanceof

**Theme:** Java Superpowers — making sure code is safe to run
**Duration:** ~60 minutes
**New Concept:** `instanceof` — checking what type an object is before using type-specific methods
**Builds On:** Lesson 6 (used `instanceof Player` without explanation), Lesson 7 (dot notation), Lesson 8 (spell selector)

---

## What We're Building

The **Spell of Healing** — restores 2 hearts (4 health points) when you right-click the Spellbook.

But here's the twist: we need to CHECK that the entity is a `LivingEntity` first. You can't heal an arrow. You can't heal a dropped item. Only living things have health.

---

## Teacher: Physical Demo (10 min)

**Bring three things:** a ball, a book, and a water bottle.

### Part 1 — Not Everything Can Do Everything

Hold up the ball.

> *"Can I drink from this?"*

(No.)

Hold up the book.

> *"Can I drink from this?"*

(No.)

Hold up the water bottle.

> *"Can I drink from this?"*

(Yes — because it's the right TYPE of thing.)

> *"In Minecraft, everything is an Entity. Players, zombies, arrows, dropped items — all entities. But only LIVING entities have health and can be healed. An arrow doesn't have hearts. A dropped diamond doesn't have hearts. Only living things do."*

### Part 2 — The Question Before the Action

Write on the whiteboard:

```
Can I heal this thing?
 → Is it a LivingEntity?
    → YES: heal it!
    → NO:  skip it, don't even try
```

> *"In Java, we ask this question with `instanceof`:"*

Write:

```
if (entity instanceof LivingEntity _le) _le.heal(4.0f);
```

Break it down:

| Piece | What it means |
|-------|---------------|
| `if` | Ask a question... |
| `entity instanceof LivingEntity` | "Is this entity a living thing?" |
| `_le` | If yes, give it the nickname `_le` (short for "living entity") |
| `_le.heal(4.0f)` | Then heal it by 4 health points (= 2 hearts) |
| `f` | Tells Java this is a decimal number (a "float") |

### Part 3 — You Already Know This!

> *"Remember Lesson 6? You typed `if (entity instanceof Player _player)`. That was the SAME pattern! You were checking 'is this entity a Player?' before sending a chat message. Today we're checking 'is this entity a LivingEntity?' before healing."*

Draw the hierarchy on the board:

```
Entity (everything)
 ├── LivingEntity (things with health)
 │    ├── Player (you!)
 │    ├── Zombie
 │    ├── Villager
 │    └── Chicken
 ├── Arrow (no health)
 ├── ItemEntity (dropped items, no health)
 └── FallingBlock (no health)
```

> *"`Player` is MORE specific than `LivingEntity`. If you check for `Player`, only players pass. If you check for `LivingEntity`, players AND zombies AND villagers all pass."*

---

## Step-by-Step: MCreator

### Step 1 — Set Up the Import (3 min)

We need MCreator to know about `LivingEntity`. The trick from Lesson 6: add a Blockly block that uses it.

1. In your `SpellbookCast` procedure, drag in a **"Heal entity"** block from the **Entity procedures --> Actions** category
2. Leave it floating off to the side, **not connected** to anything. This is fine! Think of it like putting a dictionary on your desk — you're not reading it yet, but MCreator needs it nearby so it knows the word `LivingEntity` exists
3. Set the entity to **"Event/target entity"** and the amount to `1` (the values don't matter — this block is just here for the import)

> **Don't worry about the floating block.** Your procedure isn't broken. The block is just there to help MCreator. You can delete it later once you're comfortable with fully-qualified names.

> **Alternative — fully-qualified name:** If you'd rather not add the Blockly block, use `net.minecraft.world.entity.LivingEntity` instead of `LivingEntity` in your code. The full address always works.

---

### Step 2 — Add the Spell of Healing (10 min)

Add a new **"Custom code snippet"** block to your procedure (from the **Advanced** category). Connect it after your existing code from Lessons 6 and 7.

Type this in two parts, just like Lesson 6:

**Part A — The type check:**

```java
if (entity instanceof LivingEntity _le)
```

- `LivingEntity` — capital L, capital E
- `_le` — the nickname, underscore then lowercase L and E

**Part B — The heal:**

```java
_le.heal(4.0f);
```

- `_le` — must match the nickname above
- `.heal(` — the method
- `4.0f` — four health points (2 hearts). The `f` means "float" (decimal number)
- `);` — close and semicolon

Your complete code in this snippet:

```java
if (entity instanceof LivingEntity _le)
_le.heal(4.0f);
```

> **Predicted mistake:** You might be tempted to write `entity.heal(4.0f);` without the instanceof check — after all, in Lesson 7 you wrote `entity.setGlowingTag(true);` without one! But `heal()` only exists on `LivingEntity`, not on `Entity`. Java won't let you call `.heal()` on something that might be an arrow. That's what the `instanceof` check solves — it proves to Java that the entity is alive.
>
> Think of it this way: **you know** you're a living entity. But Java doesn't. The computer sees `entity` and thinks "this could be anything." The `instanceof` check is you telling Java: "trust me, this is a living thing, so let me use living-thing abilities."

---

### Step 3 — Build and Test! (10 min)

Build and run. To test healing:

1. Jump off something high to take damage (you'll see your hearts go down)
2. Right-click your Spellbook
3. Watch your hearts go back up!

> *"How many hearts did you get back?"*
>
> 2 hearts. Each heart in Minecraft is 2 health points. We healed 4 health points = 2 hearts.

Try changing `4.0f` to different numbers:
- `2.0f` = 1 heart
- `10.0f` = 5 hearts
- `40.0f` = full heal (max health is 20, or 10 hearts)

---

### Step 4 — Why LivingEntity and Not Just Player? (5 min)

Ask the class:

> *"We checked for `LivingEntity`, not `Player`. Why not just check for Player?"*

Answer: Because `LivingEntity` is broader. If we ever wanted our spell to heal zombies, villagers, or chickens, the same code would work — any living thing can be healed. We used `Player` in Lesson 6 because `displayClientMessage()` only works on players. We used `LivingEntity` here because `heal()` works on anything alive.

> *"Choosing the right type is like choosing the right key. A master key (LivingEntity) opens more doors. A specific key (Player) only opens one. Use the broadest type that has the method you need."*

---

## Cheat Sheet — The instanceof Patterns You Know

Print this out or write it on the board. Students should keep it for future lessons.

**The pattern is always the same: check the type, give it a nickname, use the nickname.**

| Pattern | What it checks | What you can do after |
|---------|---------------|----------------------|
| `if (entity instanceof Player _player)` | Is it a player? | `_player.displayClientMessage(..., false)`, `_player.getInventory()` |
| `if (entity instanceof LivingEntity _le)` | Is it alive? | `_le.heal(...)`, `_le.getHealth()`, `_le.setHealth(...)` |

> We'll add a third row to this cheat sheet in Lesson 10 when we learn about `ServerLevel`!

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `LivingEntity` not found | Missing import | Add the Blockly "Heal entity" block (Step 1), or use `net.minecraft.world.entity.LivingEntity` |
| `heal` not found on entity | Used `entity.heal()` instead of `_le.heal()` | The heal method is on `LivingEntity`, not `Entity`. You need the `instanceof` check to get `_le` |
| Nothing happens | You're at full health! | Take damage first (jump off something), then cast |
| `4.0` works but gives a warning | Missing the `f` | Use `4.0f` not `4.0` — the `f` tells Java it's a float |
| `_le` doesn't work | Nickname doesn't match | Check that `_le` is exactly the same in both the `instanceof` line and the `.heal()` line |

---

## Extension 1: Spell of Harm

Healing is nice, but what about damage?

```java
if (entity instanceof LivingEntity _le)
_le.hurt(entity.damageSources().magic(), 4.0f);
```

This deals 4 damage (2 hearts) using "magic" damage. The death message will say "Player was killed by magic"!

> **Warning:** This hurts YOU when you right-click. Maybe save this for enemies...

---

## Extension 2: Health Display (Challenge / Teacher Demo)

> **This one is tricky!** It uses two instanceof checks on the same entity. Try it if you're feeling brave, or watch the teacher do it on the projector.

Add a custom code snippet that tells you your current health:

```java
if (entity instanceof LivingEntity _le)
if (entity instanceof Player _p) _p.displayClientMessage(Component.literal("Health: " + _le.getHealth() + "/" + _le.getMaxHealth()), false);
```

This shows something like `"Health: 14.0/20.0"` in chat. Notice:
- We check `LivingEntity` to access `getHealth()`
- We ALSO check `Player` to access `displayClientMessage()` (only players have chat messages)
- The `+` joins text and numbers together (this is called **string concatenation**)

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `LivingEntity` | `net.minecraft.world.entity.LivingEntity` | Blockly "Heal entity" block (even if disconnected) |

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **instanceof** | Check what type an object is before using type-specific methods |
| **Type hierarchy** | `Player` is a kind of `LivingEntity`, which is a kind of `Entity` |
| **Pattern variable** | The `_le` after `instanceof LivingEntity` — a nickname for the checked object |
| **float** | The `f` in `4.0f` — tells Java the number is a decimal |

---

## Next Lesson Preview

> *"So far, our spells have been quiet — a chat message, a glow, some healing. Next lesson, we're going to make some NOISE. Hearts bursting out of you. Sounds playing. Firework particles. And you'll learn that the arguments — the numbers in the brackets — control everything about what happens."*
