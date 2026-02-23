# Lesson 5: The Encounter System — Putting It All Together

**Theme:** Systems talking to each other — building a complete RPG loop
**Duration:** ~60 minutes
**New Concept:** Composing small systems into bigger ones (no single new mechanic — the lesson is *architecture*)
**Builds On:** Everything from lessons 1–4

---

## What We're Building

The **Encounter Stone** — a magic item you carry into battle. When you right-click it:

1. A d6 is rolled (the "monster's attack")
2. Your warrior skill is checked
3. **If your skill beats the roll** → You prevail! XP reward + triumph message
4. **If the roll beats your skill** → You take damage + failure message
5. **Either way** → warrior skill goes up by 1 (you always learn)

This is a complete **RPG encounter loop**. Right out of Dungeons & Dragons.

---

## Teacher: The Standing Demo (5 min — timebox strictly)

**This is the highlight of the arc. Do it. But timebox to 5 minutes.**

Have everyone pick a warrior level — a number 1 to 4, kept secret on a piece of paper. Don't use row numbers or shoe sizes (avoids self-consciousness). Let them choose.

> *"You're a warrior. Your warrior level is your number. I'm a monster. I'm rolling my attack."*

Roll the die. Announce the result.

> *"If your warrior level is LESS than what I rolled, you've been defeated. Sit down — but now you're the monster's voice. Cheer when the roll is high!"*

This is critical: **give eliminated students a job immediately** (cheering for the monster). If they just sit quietly, you lose them. With the monster role, everyone stays engaged for the full demo.

Roll again. More sit (and cheer). Eventually only 1–2 students remain.

> *"Now — did the die know your warrior level?"* No.
> *"Did your warrior level know what the die was going to roll?"* No.
> *"But I — the referee — knew both. I looked at the die, looked at the warrior level, and made a decision. That's what the Encounter Stone does."*

Don't lecture further on architecture — let this idea surface through questions during the build. If a student asks "so the stone connects them?" or "the stone is like a translator?" — that's the moment. Confirm it and move on. The insight lands better when it comes from them.

---

## Step-by-Step: MCreator

---

### Step 1 — Create the Encounter Stone Item (5 min)

1. **+ New Element → Item**
2. Name it `Encounter Stone`
3. Texture: anything mysterious-looking. A gray stone works. A magic crystal would be amazing — pre-make this texture if you can. Students can always reskin it.
4. Events/Triggers → **"When item is right-clicked"** → create new procedure `EncounterStoneProcedure`
5. Add **entity** as a dependency

---

### Step 2 — Build the Encounter Procedure (30 min)

Open `EncounterStoneProcedure`. This is the main build for the session.

> **Prerequisite check:** Before building, confirm the `warrior` scoreboard exists from Lesson 2. In-game, run: `/scoreboard players get @p warrior`. If it says "no entity found" or errors, run `/scoreboard objectives add warrior dummy` to create it. A student whose warrior score silently returns 0 every encounter will be confused — this check prevents that.

#### Part A — Roll for the monster (5 min)

```
Set variable monsterRoll = [random integer from 1 to 6]
```

Same technique as Lesson 1. Name the variable `monsterRoll` so it's clear this is the monster's roll.

#### Part B — Read the player's warrior score (5 min)

```
Set variable playerWarrior = [get scoreboard "warrior" for entity]
```

Same as Lesson 2's CheckSkills procedure. Remember to wire the entity dependency into the scoreboard block's player slot.

#### Part C — The DC Check (10 min)

This is the heart of the encounter. In MCreator:

```
if playerWarrior >= monsterRoll
    → [give entity experience] 30 points
    → [spawn particle "TOTEM_OF_UNDYING"] at player x, y+1, z   ← victory burst!
    → [send chat message] "You prevailed! Warrior: " + playerWarrior + " vs Monster: " + monsterRoll

else
    → [damage entity] 2 hearts (use "deal damage to entity" block)
    → [spawn particle "SMOKE_NORMAL"] at player x, y+1, z        ← defeat puff
    → [send chat message] "The monster overwhelms you! Warrior: " + playerWarrior + " vs Monster: " + monsterRoll
```

> For the comparison block: use **"greater than or equal to" (>=)** from Logic. Left side: `playerWarrior`, right side: `monsterRoll`.

> For the message: use the **"create text with"** joining technique from Lesson 1. The message shows both numbers — always let the player see exactly what happened.

> **Why particles?** A chat message alone is invisible in the heat of the moment. Particles appear in the world — visible to everyone nearby, not just the player who clicked. The first time a student wins and sees the golden burst, they'll show everyone in the room. That's the moment.

#### Part D — Always grow (5 min)

**Outside and below the if/else block** (this runs regardless of outcome):

```
[add 1 to scoreboard "warrior" for entity]
```

> *"Why is this outside the if/else?"*

Because you always learn from a fight, win or lose. Losing to a monster teaches you something. Put it outside so it always runs.

#### Part E — Victory milestone (5 min, if time allows)

After the scoreboard add, check if the player just hit a milestone:

```
Set variable newScore = [get scoreboard "warrior" for entity]

if newScore == 10
    → [send chat message] "*** You have become a WARRIOR! ***"

else if newScore == 20
    → [send chat message] "*** CHAMPION! You are unstoppable! ***"
```

This gives players something to work toward and celebrates reaching each tier.

---

### Step 3 — Test! (10 min)

Build and run. In game:

1. Set your warrior score to test both outcomes:
   - `/scoreboard players set @p warrior 1` → you should lose most fights
   - `/scoreboard players set @p warrior 6` → you should win most fights
2. Right-click the Encounter Stone and watch the particles, damage, or XP
3. Reset to warrior 0: `/scoreboard players set @p warrior 0`
4. Now fight naturally — use it repeatedly and watch your warrior score climb
5. Once you're a Fighter (score 5), you'll notice you win more often

> **The moment to watch for:** the first time a student loses several times, grinds back up, and then beats a roll they would have lost at warrior 1. Don't announce this — let them notice it themselves. When they do, that's when the architecture question naturally surfaces: *"Wait, how does it know my score went up?"* Answer it then, not before.

> Challenge the class: *"Can you get to Champion rank (20) just by using the Encounter Stone?"* Let them estimate how many encounters that takes.

**Debugging:** If warrior always stays at 0 even after clicking many times, check:
- That the `warrior` scoreboard exists (`/scoreboard objectives list`)
- That the entity dependency is wired into the scoreboard blocks in both the add and read blocks
- That you rebuilt after the last edit

---

## The Full RPG Loop

Draw this on the board at the end of the lesson:

```
                    ┌────────────────────────────┐
                    │     ENCOUNTER STONE         │
                    └─────────────┬──────────────┘
                                  │ right-click
                    ┌─────────────▼──────────────┐
              ┌─────►    Roll d6 (monster power) │
              │     └─────────────┬──────────────┘
              │                   │
              │     ┌─────────────▼──────────────┐
              │     │  Read warrior scoreboard    │
              │     └─────────────┬──────────────┘
              │                   │
              │     ┌─────────────▼──────────────┐
              │     │   warrior >= monsterRoll?   │
              │     └──────┬──────────────┬───────┘
              │            │ YES           │ NO
              │     ┌──────▼──────┐ ┌──────▼──────┐
              │     │ XP + triumph│ │ Damage + sad │
              │     └──────┬──────┘ └──────┬───────┘
              │            └───────┬────────┘
              │     ┌─────────────▼──────────────┐
              │     │   warrior score += 1        │
              │     └─────────────┬──────────────┘
              │                   │
              └───────────────────┘
         (you come back stronger)
```

> *"That loop — try, outcome, grow, repeat — is the core of every RPG ever made. You just built it."*

---

## What the Code Actually Looks Like

```java
public static void execute(Level world, double x, double y, double z, Entity entity) {
    int monsterRoll = world.getRandom().nextInt(6) + 1;

    int playerWarrior = 0;
    if (entity instanceof ServerPlayer player) {
        Scoreboard sb = player.getScoreboard();
        Objective obj = sb.getObjective("warrior");
        if (obj != null) {
            playerWarrior = sb.getOrCreatePlayerScore(player, obj).getScore();
        }
    }

    if (playerWarrior >= monsterRoll) {
        // Success
        if (entity instanceof Player p) p.giveExperiencePoints(30);
        if (entity instanceof ServerPlayer p) {
            p.sendSystemMessage(Component.literal(
                "You prevailed! Warrior: " + playerWarrior + " vs Monster: " + monsterRoll
            ));
        }
        world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, x, y + 1, z, 0, 0.5, 0);
    } else {
        // Failure
        entity.hurt(world.damageSources().generic(), 4f); // 2 hearts
        if (entity instanceof ServerPlayer p) {
            p.sendSystemMessage(Component.literal(
                "The monster overwhelms you! Warrior: " + playerWarrior + " vs Monster: " + monsterRoll
            ));
        }
        world.addParticle(ParticleTypes.SMOKE, x, y + 1, z, 0, 0.2, 0);
    }

    // Always grow
    if (entity instanceof ServerPlayer player) {
        Objective obj = player.getScoreboard().getObjective("warrior");
        if (obj != null) player.getScoreboard().getOrCreatePlayerScore(player, obj).add(1);
    }
}
```

---

## Common Problems

| Problem | Likely Cause |
|---------|-------------|
| Warrior score is always 0 | Scoreboard name mismatch — "warrior" must match exactly across all procedures |
| Player always wins or always loses | Comparison is backwards — check `>=` direction. `playerWarrior >= monsterRoll`, not the reverse |
| Warrior score goes up on success but not failure | The score increment is inside the if/else instead of outside/below it |
| Damage doesn't happen | "deal damage" block may need a damage amount in half-hearts (4 = 2 hearts) |

---

## Extension Challenges

- **Loot table:** On a successful encounter, roll the dice AGAIN — a 6 also gives a diamond
- **Monster variety:** Make 3 different Encounter Stones — Skeleton (d4), Zombie (d6), Dragon (d12 using random 1–12)
- **Party system:** Find a way for two players to combine warrior scores against a bigger roll — how would you architect that?
- **Defeat recovery:** Add a 5-minute cooldown (6000 ticks) to the Encounter Stone itself using NBT, so you can't just spam it to level up instantly

---

## End of Arc: What You've Built

Over 5 lessons, the class built a complete RPG system from scratch:

| Lesson | Concept | What It Does |
|--------|---------|-------------|
| 1 | Random numbers + else-if | Dice block with 6 different outcomes |
| 2 | Persistent scoreboards | Warrior skill that grows as you fight |
| 3 | NBT block memory | 60-second cooldown on the dice |
| 4 | Blockstate properties | Die face that shows the last roll visually |
| 5 | Systems composition | Full encounter loop: roll → check → outcome → grow |

Every one of these concepts appears in professional game code. You didn't just learn to mod Minecraft — you learned how games are designed.
