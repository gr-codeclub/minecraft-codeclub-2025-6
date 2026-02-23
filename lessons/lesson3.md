# Lesson 3: Block Memory — NBT

**Theme:** Blocks that remember things
**Duration:** ~60 minutes
**New Concept:** NBT data — data attached to a specific block instance
**Builds On:** Variables (L1), scoreboards (L2)

---

## What We're Building

We're giving the Dice Block a **memory**. First, a roll counter — the block remembers how many times it's been rolled and announces it. Then, as a bonus if time allows, a 60-second cooldown using the same idea.

This is your first encounter with **NBT** — Minecraft's way of attaching private data to specific objects in the world. It's how chests remember what's inside them, how furnaces remember what they're smelting, and how we'll remember our roll count.

> **Scoreboard (L2) = attached to the PLAYER** — follows them everywhere
> **NBT (today) = attached to ONE SPECIFIC BLOCK** — stays with that block in the world

---

## Teacher: Physical Demo (8 min)

**You need:** a small box or toy block, some sticky notes, and a pen.

Hold up the block. Stick a Post-it on it. Write `"Rolled: 0 times"` on the note.

> *"This sticky note is the block's memory. Watch what happens when I roll it."*

Mime right-clicking. Peel off the note. Cross out the 0, write 1. Stick it back on.

> *"The block now knows it's been rolled once."*

Right-click again. Update to 2. Again. 3.

Now place a second toy block with no note.

> *"If I right-click this second block — does it share the count?"*

No. Each block has its own note. The count on the first block stays at 3. The second block starts at 0.

> *"That's the difference between NBT and the scoreboard. The scoreboard is like a chart at school — your warrior score follows YOU wherever you go. NBT is a sticky note on THIS specific block — it only belongs to that one block in the world."*

---

## The "Smart Block" Upgrade

To give a block NBT memory, we have to upgrade it in MCreator. Normal blocks are just textures with properties. To hold data, a block needs a little storage compartment attached to it — Minecraft calls this a **block entity** (sometimes "tile entity").

Think of it like: a plain wooden crate vs a chest. Same basic shape, but the chest has a special internal mechanism that remembers its contents. We're upgrading the Dice Block from "plain crate" to "chest."

**In MCreator, this is one checkbox.** The rest happens automatically.

> Avoid saying "tile entity" to students — call it a "smart block" or "memory block" throughout. The technical term can be in a footnote.

---

## Step-by-Step: MCreator

> **Quick reference card** for today:
> - Enable block memory → Block element settings → **"Has block entity?"** checkbox
> - Read NBT number → **Block procedures → Actions** → *"Get NBT number tag [name] of block at x y z if it has block entity"*
> - Write NBT number → **Block procedures → Actions** → *"Set NBT number tag [name] of block at x y z to [value] if it has block entity"*
> - Get world tick time → **World procedures → Actions** → *"Get current world time"*
> - Block coordinates → use `x`, `y`, `z` from the procedure context — these are the block's coordinates when triggered from the block's right-click event

---

### Step 1 — Upgrade the Dice Block (5 min)

1. Open your **Dice Block** element in MCreator
2. Find the **"Has block entity"** setting (may be under Advanced, or just on the main settings page)
3. Check it
4. Save

MCreator will regenerate some files — that's fine. Your procedure from Lesson 1 is separate and won't be changed.

> *"We just gave the block pockets. Now it can carry sticky notes."*

---

### Step 2 — Add a Roll Counter (20 min)

Open `DiceBlockRightClicked`. We're adding code at the **very top**, before the random roll.

**Part A — Read the current count:**

From **Block procedures → Actions**, drag:

*"Get NBT number tag [name] of block at x: [x] y: [y] z: [z] if it has block entity"*

- Tag name field: type `rollCount`
- x/y/z slots: drag `x`, `y`, `z` coordinate blocks from **Minecraft Components**
- Store the whole block result as a new variable: `rollCount`

> If the tag has never been set, it returns 0. Perfect for a fresh block.

**Part B — Increment the count:**

```
Set variable rollCount = rollCount + 1
```

Use a **Math → add** block: `rollCount` + `1`.

**Part C — Save it back:**

From **Block procedures → Actions**, drag:

*"Set NBT number tag [name] of block at x: [x] y: [y] z: [z] to [value] if it has block entity"*

- Tag name field: type `rollCount`
- x/y/z slots: drag `x`, `y`, `z` from **Minecraft Components**
- Value slot: drag in the `rollCount` variable

**Part D — Tell the player:**

Add a chat message **before** the existing random roll message:

```
send chat "Roll #" + rollCount + "!"
```

So the chat will show: `"Roll #3!"` then `"You rolled a 5!"` — giving a running count.

---

### Step 3 — Test the Counter (10 min)

Build and run.

1. Place a Dice Block — right-click it several times. Chat should say Roll #1, Roll #2, Roll #3...
2. Place a **second** Dice Block. Right-click it.
3. It should say **Roll #1** — its own fresh counter, not sharing with the first block

Ask: *"What happens if you break the first block and place it again?"*

Let them test it. The count resets — the NBT lives on the placed block, not the block item. Break the block, the sticky note is gone.

---

### Step 4 — The Cooldown (15 min, core or extension depending on pace)

Now that the counter works, we know how to read/write NBT. The cooldown uses the same idea but stores **time** instead of a count.

> **The key maths — write this on the board:**
> ```
> 20 ticks = 1 second
> 60 seconds = 60 × 20 = 1200 ticks
> ```

Add a **second** NBT value to the procedure. Insert this at the very top (before the roll counter code):

```
Set variable lastTime = [Block procedures → Actions →
    "Get NBT number tag 'lastRolledTime' of block at x y z if it has block entity"]

Set variable nowTime  = [World procedures → Actions → "Get current world time"]

Set variable elapsed  = nowTime - lastTime

if elapsed < 1200
    → send chat "This dice needs more time! Try again soon."
    → STOP (Logic/Loops → "break out of loop" or use "return" if available)

else
    → [Block procedures → Actions →
       "Set NBT number tag 'lastRolledTime' of block at x y z to [nowTime] if it has block entity"]
    → (continue with the rest of the procedure below)
```

> **Note on block type:** MCreator's NBT number tag blocks store a Java `double` internally, which is fine for both our counter (small integer) and the world time (large integer — world time fits comfortably in a double for game durations). Just use the same "NBT number" block for both.

> **The stop block:** In MCreator, look for a **"return"** or **"exit procedure"** block in the Procedures/Control category. This makes the procedure stop immediately — nothing below it runs.

---

### Step 5 — Test the Cooldown (5 min)

Build and run.

1. Right-click — it rolls. ✓
2. Right-click again immediately — `"This dice needs more time!"` ✓
3. Place a second dice block — it rolls fine (its own timer) ✓
4. Wait 60 seconds on the first block — it rolls again ✓

---

## Key Concepts Side-by-Side

```
                 Scoreboard           NBT
                 ──────────           ───
Attached to:     The player           A specific block
Follows you?     Yes                  No (stays in the world)
Other blocks?    Shared               Each block has its own
Good for:        Skills, XP, stats    Cooldowns, counters, state
Analogy:         Your character sheet  The block's sticky note
```

---

## Common Problems

| Problem | Likely Cause |
|---------|-------------|
| Roll count is always 0 | "Has block entity" checkbox not enabled, OR using player coordinates instead of block coordinates |
| Count doesn't save between sessions | Using `set variable` only — forgot to write NBT back with "set NBT tag" |
| Two dice blocks share the same count | Using a scoreboard or global variable instead of NBT — check the block category vs player category |
| Cooldown doesn't work | Used integer instead of long for game time — swap to long |
| Build error after enabling block entity | Try MCreator → Build → Clean, then rebuild |

---

## What the Code Actually Looks Like

```java
public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
    BlockPos pos = BlockPos.containing(x, y, z);
    BlockEntity be = world.getBlockEntity(pos);

    // Read roll count from NBT
    int rollCount = 0;
    long lastTime = 0;
    if (be != null) {
        rollCount = be.getPersistentData().getInt("rollCount");
        lastTime  = be.getPersistentData().getLong("lastRolledTime");
    }

    // Cooldown check
    long now = world instanceof Level l ? l.getGameTime() : 0;
    if (now - lastTime < 1200) {
        if (entity instanceof ServerPlayer p)
            p.sendSystemMessage(Component.literal("This dice needs more time! Try again soon."));
        return;  // ← the "stop" block
    }

    // Update NBT
    rollCount++;
    if (be != null) {
        be.getPersistentData().putInt("rollCount", rollCount);
        be.getPersistentData().putLong("lastRolledTime", now);
        be.setChanged();
    }

    // Tell the player
    if (entity instanceof ServerPlayer p)
        p.sendSystemMessage(Component.literal("Roll #" + rollCount + "!"));

    // ... rest of the dice roll ...
}
```

---

## Extension Challenges

- **Seconds remaining:** Instead of "Try again soon," calculate exactly how many seconds are left:
  `secondsLeft = (1200 - elapsed) / 20`
  Print `"Come back in " + secondsLeft + " seconds!"`
- **Variable cooldown:** Champions (warrior >= 20) only wait 10 seconds (200 ticks), Peasants wait 60 (1200). Read the scoreboard inside the cooldown check.
- **Lifetime stats:** Store the all-time jackpot count (how many times roll 6 has come up) in a second NBT integer `jackpotCount`. Print it when a 6 is rolled: `"JACKPOT! You've hit jackpot " + jackpotCount + " times!"`

---

## Next Lesson Preview

The dice block works and remembers. But it always looks the same — a plain block no matter what you rolled. In Lesson 4 we'll make it show the actual die face that came up: roll a 6, see six dots on the block. This uses Minecraft's blockstate system — the same trick that makes doors look open or closed, or lets logs face in different directions.
