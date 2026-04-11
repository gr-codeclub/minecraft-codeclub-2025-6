# Lesson 10: Spell of Fireworks — Arguments Change Everything

**Theme:** Java Superpowers — controlling what methods do with arguments
**Duration:** ~60 minutes
**New Concept:** Method arguments — the values inside parentheses change what the method does
**Builds On:** Lesson 7 (dot notation, methods), Lesson 9 (instanceof checks)

---

## What We're Building

The **Spell of Fireworks** — a burst of heart particles above the player, with a sound effect. Then students swap out particle types and sounds to create their own custom spell effects.

This is the lesson where things get LOUD and VISUAL.

---

## Teacher: Physical Demo (12 min)

This lesson has two parts to teach: **arguments** and **server vs client**.

### Part 1 — Arguments (5 min)

Stand up. Tell the class:

> *"Jump."*

Jump once.

> *"Jump THREE TIMES."*

Jump three times.

> *"Jump as HIGH AS YOU CAN."*

Jump high.

> *"The action was always the same — jump. But the DETAIL changed what happened. In Java, the detail goes inside the brackets. We call it an **argument**."*

Write on the whiteboard:

```
jump()          → jump once, default height
jump(3)         → jump three times
jump(HIGH)      → jump as high as possible
```

> *"The method stays the same. The arguments change the result."*

Now show a real example:

```
entity.setTicksFrozen(100)    → frozen for 5 seconds
entity.setTicksFrozen(200)    → frozen for 10 seconds
entity.setTicksFrozen(0)      → not frozen at all
```

> *"Same method, different arguments, completely different results. Today we'll use methods with LOTS of arguments — each one controls a different part of the effect."*

### Part 2 — Server and Client (5 min)

> **This is important.** Before we can spawn particles, we need to understand something about how Minecraft works.

Draw this on the whiteboard:

```
Your Minecraft
┌──────────────────────────────┐
│  ┌──────────┐  ┌──────────┐ │
│  │  SERVER   │  │  CLIENT  │ │
│  │ (the boss)│  │(the artist)│ │
│  │           │  │           │ │
│  │ Decides   │  │ Draws     │ │
│  │ what      │  │ what you  │ │
│  │ happens   │  │ see       │ │
│  └──────────┘  └──────────┘ │
└──────────────────────────────┘
```

> *"Even when you play single-player, Minecraft runs TWO copies of the world at the same time. The **server** is the boss — it decides what really happens. Blocks break, mobs spawn, damage is dealt — all on the server. The **client** is the artist — it draws everything you see on screen."*

> *"Particles need to be sent FROM the server. Why? Because in multiplayer, the server tells EVERY player's client 'show hearts here.' That way everyone sees the same thing."*

Draw:

```
[Server] ──"show hearts at x,y,z"──→ [Your Client]
                                  ──→ [Friend's Client]
                                  ──→ [Other Friend's Client]
```

> *"So before we spawn particles, we need to check: 'am I running on the server?' We do this with — you guessed it — `instanceof`!"*

Write:

```
if (world instanceof ServerLevel _sl)
    _sl.sendParticles(...)
```

> *"`world` is the game world. `ServerLevel` is the server's copy. If we're on the server, call it `_sl` and send particles. If we're on the client, skip it — the client will receive the particles automatically from the server."*

> **Rule of thumb:** *"If you're CHANGING the world or telling everyone about something, use `ServerLevel`."*

### Part 3 — Anatomy of sendParticles

Write on the board with labels:

```
_sl.sendParticles(ParticleTypes.HEART, x, y+1, z, 10, 0.5, 0.5, 0.5, 0)
                  ───────────────────  ─  ───  ─  ──  ───  ───  ───  ─
                  WHAT particle         WHERE       HOW    SPREAD      SPEED
                                                   MANY
```

> *"Nine arguments! Each one controls something different. Change any one of them and you get a different effect. This is the power of arguments."*

---

## Step-by-Step: MCreator

### Step 1 — Set Up the Import (3 min)

We need MCreator to know about `ServerLevel` and `ParticleTypes`.

1. In your `SpellbookCast` procedure, drag in a **"Spawn server-side particles"** block from **World procedures --> Actions**
2. Leave it disconnected (floating) — it's just here to trigger the imports
3. Also drag in a **"Play sound"** block from the same category — leave it disconnected too

> **Alternative — fully-qualified names:**
> - `net.minecraft.server.level.ServerLevel` instead of `ServerLevel`
> - `net.minecraft.core.particles.ParticleTypes` instead of `ParticleTypes`
> - `net.minecraft.sounds.SoundEvents` instead of `SoundEvents`
> - `net.minecraft.sounds.SoundSource` instead of `SoundSource`
> - `net.minecraft.core.BlockPos` instead of `BlockPos`

---

### Step 2 — The Spell of Fireworks: Particles (10 min)

Add a new **"Custom code snippet"** block to your procedure (from the **Advanced** category).

Type the code in two parts:

**Part A — The server check:**

```java
if (world instanceof ServerLevel _sl)
```

- `ServerLevel` — capital S, capital L
- `_sl` — nickname for "server level"

**Part B — Send the particles:**

```java
_sl.sendParticles(ParticleTypes.HEART, x, y+1, z, 10, 0.5, 0.5, 0.5, 0);
```

Let's break down each argument:

| Argument | What it controls | Our value | What it means |
|----------|-----------------|-----------|---------------|
| 1st | Particle type | `ParticleTypes.HEART` | Heart shapes |
| 2nd | X position | `x` | Player's X |
| 3rd | Y position | `y+1` | One block ABOVE the player |
| 4th | Z position | `z` | Player's Z |
| 5th | Count | `10` | Spawn 10 particles |
| 6th | X spread | `0.5` | How far they scatter sideways |
| 7th | Y spread | `0.5` | How far they scatter up/down |
| 8th | Z spread | `0.5` | How far they scatter forward/back |
| 9th | Speed | `0` | How fast they move outward |

Your complete code:

```java
if (world instanceof ServerLevel _sl)
_sl.sendParticles(ParticleTypes.HEART, x, y+1, z, 10, 0.5, 0.5, 0.5, 0);
```

> **Typing tip:** This is a long line. Type it in chunks if you like — type up to `HEART,`, then `x, y+1, z,`, then the rest. Count your commas when you're done — you need exactly **8 commas** separating 9 arguments.
>
> *(Teacher: before kids start typing, have them count the arguments out loud on the whiteboard: "Argument 1: HEART. Comma. Argument 2: x. Comma. Argument 3: y+1. Comma..." all the way to 9.)*

---

### Step 3 — Add a Sound Effect (8 min)

Add ANOTHER **"Custom code snippet"** block below the particles one. Type:

```java
world.playSound(null, BlockPos.containing(x, y, z), SoundEvents.NOTE_BLOCK_PLING.value(), SoundSource.PLAYERS, 1, 1);
```

The arguments:

| Argument | What it controls | Our value |
|----------|-----------------|-----------|
| 1st | Who NOT to play for | `null` — play for everyone |
| 2nd | Position | `BlockPos.containing(x, y, z)` — where the player is |
| 3rd | Which sound | `SoundEvents.NOTE_BLOCK_PLING.value()` — the pling! |
| 4th | Sound category | `SoundSource.PLAYERS` — counts as a player sound |
| 5th | Volume | `1` — normal volume |
| 6th | Pitch | `1` — normal pitch |

> **Note:** No `ServerLevel` check needed for `playSound`! Unlike particles, `playSound` already checks for you — it only plays on the server automatically. So you can call it directly on `world`.

---

### Step 4 — Build and Test! (8 min)

Build and run. Right-click your Spellbook. You should see:
- Hearts bursting above your head
- A pling sound

Now the fun part: **change the arguments!**

---

### Step 5 — Experiment! (10 min)

**Change the particle type.** Replace `ParticleTypes.HEART` with any of these:

| ParticleType | What it looks like |
|-------------|-------------------|
| `ParticleTypes.FLAME` | Fire sparks |
| `ParticleTypes.END_ROD` | Glowing white sparkles |
| `ParticleTypes.TOTEM_OF_UNDYING` | The totem explosion effect |
| `ParticleTypes.SMOKE` | Smoke puffs |
| `ParticleTypes.CHERRY_LEAVES` | Falling cherry blossom petals |
| `ParticleTypes.SNOWFLAKE` | Snow |
| `ParticleTypes.NOTE` | Musical notes |
| `ParticleTypes.SOUL_FIRE_FLAME` | Blue soul fire |
| `ParticleTypes.DRAGON_BREATH` | Purple dragon breath |

**Change the count.** Replace `10` with `50` for a massive burst, or `1` for a subtle effect.

**Change the sound.** Replace `SoundEvents.NOTE_BLOCK_PLING.value()` with:

| SoundEvent | What it sounds like |
|-----------|-------------------|
| `SoundEvents.WITHER_SPAWN.value()` | Terrifying wither sound |
| `SoundEvents.ENDER_DRAGON_GROWL.value()` | Dragon roar |
| `SoundEvents.TOTEM_USE.value()` | Totem of undying activation |
| `SoundEvents.AMETHYST_BLOCK_CHIME.value()` | Crystal chime |
| `SoundEvents.LIGHTNING_BOLT_THUNDER.value()` | Thunder crack |
| `SoundEvents.UI_TOAST_CHALLENGE_COMPLETE.value()` | Achievement sound |

**Change the pitch.** The last number in `playSound`. Try `2` for high-pitched or `0.5f` for deep/low.

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `ServerLevel` not found | Missing import | Add the Blockly "Spawn particles" block (Step 1), or use `net.minecraft.server.level.ServerLevel` |
| `ParticleTypes` not found | Missing import | Same — the Blockly particles block triggers this import |
| No particles appear | Missing `ServerLevel` check | Make sure your code starts with `if (world instanceof ServerLevel _sl)` |
| Wrong number of arguments | Missing or extra comma | `sendParticles` needs exactly 9 arguments separated by 8 commas |
| Sound doesn't play | Missing `.value()` | In NeoForge 1.21+, sound events need `.value()` at the end |
| `BlockPos` not found | Missing import | Add any Blockly block that uses block positions, or use `net.minecraft.core.BlockPos` |

---

## Extension: RPG Spell Effects

Combine particles with your RPG system from Lessons 1-5! Use a Blockly if/else to check warrior level, then different particle effects for each rank:

- **Peasant (0-4):** `SMOKE` particles + sad trombone sound
- **Fighter (5-9):** `FLAME` particles + pling sound
- **Warrior (10-19):** `END_ROD` particles + amethyst chime
- **Champion (20+):** `TOTEM_OF_UNDYING` particles + `UI_TOAST_CHALLENGE_COMPLETE` sound

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `ServerLevel` | `net.minecraft.server.level.ServerLevel` | Blockly "Spawn particles" block |
| `ParticleTypes` | `net.minecraft.core.particles.ParticleTypes` | Blockly "Spawn particles" block |
| `SoundEvents` | `net.minecraft.sounds.SoundEvents` | Blockly "Play sound" block |
| `SoundSource` | `net.minecraft.sounds.SoundSource` | Blockly "Play sound" block |
| `BlockPos` | `net.minecraft.core.BlockPos` | Any Blockly block using block positions |

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **Arguments** | Values inside `()` that control what a method does — change the arguments, change the result |
| **Multiple arguments** | Methods can take many arguments, separated by commas |
| **Server vs Client** | Minecraft runs two copies of the world; use `ServerLevel` for things that affect everyone |
| **null** | "Nothing" — used when an argument doesn't apply (like "don't exclude anyone from hearing the sound") |
| **Constants** | `ParticleTypes.HEART`, `SoundEvents.NOTE_BLOCK_PLING` — named values that never change, written in ALL_CAPS |

---

## Next Lesson Preview

> *"Your spells look and sound amazing. But they all do the same thing every time. Next lesson, we'll add MATHS. Your warrior skill from the RPG system will control how many diamonds you get. Stronger warrior = more loot. We'll write our first Java VARIABLE — a number box that remembers a calculation."*
