# Lesson 2: Skills & Stats — The RPG Core

**Theme:** Character progression — getting better by doing things
**Duration:** ~60 minutes
**New Concept:** Persistent data — scoreboards that survive after the procedure ends
**Builds On:** else-if chains (Lesson 1), variables

---

## What We're Building

Three things that connect together:

1. **A warrior skill** — goes up by 1 every time you kill a mob
2. **A Skill Stone item** — right-click it to see your current rank
3. **A DC check on the dice** — your warrior skill can help you dodge the roll-1 lightning

| Warrior Score | Rank |
|--------------|------|
| 0–4 | Peasant |
| 5–9 | Fighter |
| 10–19 | Warrior |
| 20+ | Champion |

---

## Teacher: Physical Demo (10 min)

**You need:** a piece of paper, a marker, sticky notes (one per student), and your voice.

**Part 1 — Variables disappear:**

Say the number **7** out loud. Pause.

> *"What happened to the 7 I just said?"*

It's gone. Ask a student to hold a number in their head — say 42. Then mime "procedure ends" with a hand wave and ask if they still have it. They do (they're human) — point out computers don't. Variables in a procedure are forgotten the instant it finishes.

**Part 2 — Scoreboards persist:**

Draw two columns on the whiteboard. Label one **"Variable"** and one **"Scoreboard"**. Write `warrior = 5` in the Variable column.

> *"Procedure ends."*

Erase it. Now write `warrior = 5` in the Scoreboard column.

> *"Procedure ends."*

Don't erase it. Leave it there all lesson.

> *"That's the difference. The scoreboard is like Minecraft's character sheet — it writes the number down permanently, in a save file inside the world folder. It survives the procedure ending, quitting the game, even restarting the computer."*

Draw the full table:

```
WARRIOR SCOREBOARD
┌──────────────┬────────┐
│ Player Name  │ warrior│
├──────────────┼────────┤
│ Steve        │   12   │
│ Alex         │   4    │
│ You          │   0    │
└──────────────┴────────┘
```

**Part 3 — Give students sticky notes:**

Hand each student a sticky note. Have them write:
```
warrior: 0
```

> *"This is your character sheet. We'll pretend-update it as we talk about kills."*

During the lesson, whenever you mention a kill count going up, invite them to cross out and update their number. By the time they build in MCreator, they've already performed the same mental operation the scoreboard does.

> *"Does it show on screen like a leaderboard?"*

Only if we ask it to — it's stored silently in the background for now. We just read it with the Skill Stone.

---

## Step-by-Step: MCreator

> **Quick reference card** for today:
> - Scoreboard add → **Player** category → "add to scoreboard score"
> - Scoreboard read → **Player** category → "get scoreboard score"
> - Trigger: mob killed → **When a living entity is killed** (set trigger when creating procedure)

---

### Step 1 — OnMobKilled Procedure (12 min)

1. **+ New Element → Procedure**
2. Name it `OnMobKilled`
3. For the **trigger**, choose **"When a living entity is killed"**
4. Make sure `entity` (the killer/player) is a dependency

In the procedure canvas:

**First, filter to monsters only** — we don't want skill XP for killing a chicken.

Drag out an **if** block. Inside the condition: **"entity is of type: Monster"** (check Entity category for entity type checks). Put everything below inside this if.

**Inside the if:**

Drag out **"add to scoreboard score"**:
- Score name: `warrior`
- Amount: `1`
- For entity: the **player/entity** dependency

> **Dependency wiring — common sticking point:** The scoreboard block needs to know *which player* to add the score to. Look for a slot labelled "entity" or "player" on the block. You need to drag in the **"event/source entity"** dependency block (from the procedure's dependency list at the top) into that slot. Without this, MCreator doesn't know whose score to update. If you see an empty slot and nothing happens in-game, this is almost always why.

That's the whole procedure. Save it — MCreator automatically creates the `warrior` scoreboard objective the first time it's used.

> **The golden rule:** Any time you change a procedure, you must **rebuild the mod** before testing. If you right-click in-game and nothing has changed, check whether you built after your last edit.

---

### Step 2 — Skill Stone Item (5 min)

1. **+ New Element → Item**
2. Name it `Skill Stone`
3. Texture: use `emerald` or draw a gem
4. Under Events/Triggers → **"When item is right-clicked"** → create new procedure `CheckSkills`
5. Make sure **entity** (the player) is a dependency

---

### Step 3 — CheckSkills Procedure (15 min)

Open the `CheckSkills` procedure. This reads the score and prints the rank.

**Step A — Read the warrior score:**
- Drag **"get scoreboard score"** → name: `warrior`, for: entity
- Store the result in a new variable called `warriorScore`

**Step B — The else-if chain (discover the ordering rule, don't just tell it):**

Before building, ask students to write down the four ranks in the order they'd check them. Don't tell them the right answer yet — let them commit.

Most students will start from the bottom: *"If score < 5, Peasant. If score < 10, Fighter..."* This is also valid! Ask them to walk through it with a score of 25:

> *"Is 25 less than 5? No. Is 25 less than 10? No. Is 25 less than 20? No. Else — Champion."*

Works. Now try a score of 25 with the `>=` version but in the **wrong order** (lowest first):

> *"Is 25 >= 5? YES — Fighter! Stop."*

> *"The same person just became a Fighter instead of a Champion. That's the bug. Order matters because else-if STOPS at the first true match."*

Write this on the board and leave it up: **"else-if reads top to bottom and stops at the first true thing."**

Build the chain using highest-first:

```
Set variable warriorScore = [get scoreboard "warrior" for entity]

if warriorScore >= 20
    → send chat "You are a CHAMPION! Score: " + warriorScore

else if warriorScore >= 10
    → send chat "You are a WARRIOR! Score: " + warriorScore

else if warriorScore >= 5
    → send chat "You are a FIGHTER! Score: " + warriorScore

else
    → send chat "You are a humble Peasant. Score: " + warriorScore
```

Use the same **"create text with"** joining technique from Lesson 1 for the chat messages. The entity dependency goes into the "for player" slot of the scoreboard read block — same wiring as step 1.

---

### Step 4 — Test Skills (10 min)

Build and run. In game:
1. Use creative mode to find and kill some zombies (spawn eggs)
2. Right-click the Skill Stone
3. Watch your rank change as you kill more mobs

> Have students predict their rank before clicking. "I killed 8 mobs, what am I?" → Fighter!

> **Debugging tip — check the actual number:** If the Skill Stone shows an unexpected rank, check the real warrior score with a Minecraft command:
> ```
> /scoreboard players get @p warrior
> ```
> This bypasses your code entirely and reads straight from Minecraft's data. If this shows the wrong number, the problem is in `OnMobKilled`. If it shows the right number but your Skill Stone says the wrong rank, the problem is in `CheckSkills`. Divide and conquer!

> **The rebuild rule in action:** If nothing seems to be happening in-game, first ask: "Did I rebuild after my last change?" This is the most common cause of "it's not working" in every MCreator session.

---

### Step 5 — The DC Check on the Dice (10 min, bonus if time allows)

Now we connect the two systems. Open `DiceBlockRightClicked` from Lesson 1.

Find the **roll == 1** branch (the lightning). Currently it always strikes. We'll add a **dodge check** for skilled warriors.

Replace the lightning action with:

```
Set variable warriorLevel = [get scoreboard "warrior" for entity]

if warriorLevel >= 10
    → send chat "Your warrior skill deflects the strike!"
    → give player 10 XP  (reward the skilled player)

else
    → strike lightning at player x, y, z
```

This is a **Difficulty Check (DC)** — a concept straight from tabletop RPGs. Your skill level determines whether a bad roll hurts you or not.

> *"The dice rolled 1. The dice doesn't know who you are. But the procedure checks your warrior score and decides what happens based on BOTH."*

---

## Common Problems

| Problem | Likely Cause |
|---------|-------------|
| Skill Stone shows 0 every time | Scoreboard name typo — "warrior" must be spelled identically in both procedures |
| Killing chickens gives warrior XP | Forgot the monster-type filter in OnMobKilled |
| Champion players get called Fighter | else-if chain is in wrong order — check highest (>= 20) must come first |
| Warrior score disappears when I reopen the world | This shouldn't happen — scoreboards persist with the world save |
| Can't find "killer entity" vs "source entity" | Use "killing entity" — "source entity" can be ambiguous in MCreator |

---

## What the Code Actually Looks Like

```java
// OnMobKilled
public static void execute(Level world, Entity entity) {
    if (entity instanceof Player player) {
        Scoreboard sb = player.getScoreboard();
        Objective obj = sb.getOrCreateObjective("warrior", ...);
        sb.getOrCreatePlayerScore(player, obj).add(1);
    }
}

// CheckSkills
public static void execute(Level world, Entity entity) {
    if (entity instanceof Player player) {
        int score = sb.getOrCreatePlayerScore(player, obj).getScore();

        String rank;
        if      (score >= 20) rank = "CHAMPION";
        else if (score >= 10) rank = "WARRIOR";
        else if (score >= 5)  rank = "FIGHTER";
        else                  rank = "Peasant";

        player.sendSystemMessage(Component.literal("You are a " + rank + "! Score: " + score));
    }
}
```

---

## Extension Challenges

- Add a **second skill**: `miner` — goes up every time you break a stone block. Show both skills with the Skill Stone.
- Can you add a **rank-up announcement**? When warrior score hits exactly 10, broadcast to everyone: `"[PlayerName] has become a WARRIOR!"`
  - Hint: after adding 1, read the new score and check `if score == 10`
- Add a third tier to the DC check — Warriors (10+) dodge completely, Fighters (5–9) take half damage instead

---

## Next Lesson Preview

We need to stop the dice from being spammable. We'll give the block its own memory — it'll remember *exactly when it was last rolled* and refuse to roll again for 60 seconds. This uses something called **NBT**, which is Minecraft's way of attaching data to specific objects in the world.
