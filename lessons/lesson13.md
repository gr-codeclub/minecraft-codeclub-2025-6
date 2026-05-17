# Lesson 13: Storm Shapes — Helpers and Switch

**Theme:** Java Superpowers — naming code so we can swap pieces in and out
**Duration:** ~60 minutes
**New Concept:** Private helper methods + the `switch` expression
**Builds On:** Lesson 2 (scoreboards), Lesson 8 (spell dispatch), Lesson 11 (variables), Lesson 12 (for loops, the Storm spell)

---

## What We're Building

Right now the Storm spell fires lightning in a **straight line** in front of you. By the end of this lesson you'll have **six different shapes** — line, cone, ring, wall — and you'll be able to switch between them mid-game by typing one command:

```
/scoreboard players set @s spell_mode 2
```

No rebuilding. No rerunning Minecraft. Just type a number and the lightning changes shape.

---

## Teacher: Physical Demo (10 min)

### Part 1 — The Recipe Book

**Bring:** any recipe book, or just draw one on the board.

> *"Imagine I'm making chocolate cake. Step 4 says 'make the sponge'. But the sponge recipe is six lines long. The cake book doesn't write it out — it says 'see page 12 for sponge.' Page 12 is a separate recipe with its own name. Why?"*

Take answers. Lead them to:

> *"Because if I want a vanilla cake, I don't rewrite the whole cake — I just go 'see page 13 for vanilla sponge' instead of page 12. **Same cake, different sponge.** That's a helper. We're going to do this in code."*

### Part 2 — The Restaurant Menu

> *"Now imagine you're at a restaurant. There are five pizzas on the menu. You don't cook them yourself — you just say 'I'll have number 3' and the kitchen does the right thing. That's a `switch`. You give it a number, it picks which recipe to use."*

### Part 3 — Helpers and Switch on the Board

Write this on the whiteboard:

```java
String result = switch (choice) {
    case 1 -> doThingOne();
    case 2 -> doThingTwo();
    default -> doThingThree();
};
```

Walk through it:

| Piece | What it means |
|-------|---------------|
| `switch (choice)` | Look at the value of `choice` |
| `case 1 ->` | If it's 1, run this |
| `case 2 ->` | If it's 2, run this |
| `default ->` | If it's anything else, run this |
| `String result =` | Catch what came back and put it in a variable |

> *"The arrow `->` is new — it means 'go do this'. The whole `switch` block hands back ONE value, which we stash in `result`. The helpers do the actual work."*

### Part 4 — Why Bother?

> *"Right now our lightning code is jammed in the middle of the for-loop. To change shapes we'd have to delete code and write new code each time. With helpers, we just swap one line. With a switch, we swap zero lines — the game picks at runtime!"*

---

## Step-by-Step: The Refactor

### Step 1 — Look At What We Have (3 min)

Open `CastSpellProcedure.java`. Find the Storm case (`case 3:`). Inside the `if (r.nextBoolean())` is this beast:

```java
entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(
        x + entity.getDirection().getStepX() * r.nextInt(1, i + 2),
        y,
        z + entity.getDirection().getStepZ() * r.nextInt(1, i + 2))));
```

That's the formula for **where the lightning hits**. It's tangled into the middle of the loop. We can't reuse it. We can't read it. Let's pull it out.

---

### Step 2 — Write Your First Helper (10 min)

Scroll **below the `execute` method** but **inside the class** (above the final `}` of the file). Add this method:

```java
private static Vec3 straightLineBolt(Entity entity, double x, double y, double z, int i, Random r) {
    return new Vec3(
            x + entity.getDirection().getStepX() * r.nextInt(1, i + 2),
            y,
            z + entity.getDirection().getStepZ() * r.nextInt(1, i + 2));
}
```

**Break it down — this is the most important block of the lesson:**

| Piece | What it means |
|-------|---------------|
| `private static` | Modifiers — just type them, we'll explain another day |
| `Vec3` | What this helper **gives back** (a position) |
| `straightLineBolt` | The helper's name |
| `(Entity entity, double x, ...)` | Its **inputs** — what it needs to do its job |
| `return new Vec3(...)` | Hand a position back to whoever called us |

> **The big new idea:** until now, our methods just *did* things. This one **returns** a value. It's like asking a friend "what's 2+2?" — they don't just shout, they hand you back "4". You can put that "4" in a variable.

Now go back to the loop and replace that big tangled `snapTo` line with **two clean lines**:

```java
Vec3 target = straightLineBolt(entity, x, y, z, i, r);
entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(target)));
```

`Vec3 target =` catches what the helper returned, just like catching a ball.

Build and test. **It should look identical to before** — same straight line of lightning. That's the point: we changed *how the code is organised* without changing *what it does*. (Real developers call this **refactoring**.)

---

### Step 3 — Paste the Other Five Shapes (10 min)

Now the fun part. Below `straightLineBolt`, paste these five helpers. **Don't worry about the maths** — `Math.cos`, `Math.sin`, `yRot` etc. are "magic shape maths". Trust that each one returns a different shape.

```java
// Straight line, but follows where you LOOK (not just N/S/E/W).
private static Vec3 smoothDirectionBolt(Entity entity, double x, double y, double z, int i, Random r) {
    Vec3 forward = horizontalLook(entity);
    double dist = r.nextInt(1, i + 2);
    return new Vec3(x + forward.x * dist, y, z + forward.z * dist);
}

// 360° ring of lightning around you.
private static Vec3 ringBolt(Entity entity, double x, double y, double z, int i, Random r) {
    double angle = r.nextDouble() * Math.PI * 2;
    double dist = r.nextInt(1, i + 2);
    return new Vec3(x + Math.cos(angle) * dist, y, z + Math.sin(angle) * dist);
}

// Cone in front of you.
private static Vec3 coneBolt(Entity entity, double x, double y, double z, int i, Random r) {
    Vec3 look = entity.getLookAngle();
    double baseAngle = Math.atan2(look.z, look.x);
    double spread = Math.toRadians(45);
    double angle = baseAngle + (r.nextDouble() - 0.5) * 2 * spread;
    double dist = r.nextInt(1, i + 2);
    return new Vec3(x + Math.cos(angle) * dist, y, z + Math.sin(angle) * dist);
}

// 30x30 square of lightning in front of you (N/S/E/W only).
private static Vec3 squareBolt(Entity entity, double x, double y, double z, int i, Random r) {
    Direction facing = entity.getDirection();
    Direction sideways = facing.getClockWise();
    int forward = r.nextInt(2, 31);
    int side = r.nextInt(-15, 16);
    return new Vec3(
            x + facing.getStepX() * forward + sideways.getStepX() * side,
            y,
            z + facing.getStepZ() * forward + sideways.getStepZ() * side);
}

// Same square but follows where you LOOK.
private static Vec3 smoothSquareBolt(Entity entity, double x, double y, double z, int i, Random r) {
    Vec3 forward = horizontalLook(entity);
    Vec3 sideways = forward.yRot((float) (Math.PI / 2));
    int forwardDist = r.nextInt(2, 31);
    int sideDist = r.nextInt(-15, 16);
    return new Vec3(
            x + forward.x * forwardDist + sideways.x * sideDist,
            y,
            z + forward.z * forwardDist + sideways.z * sideDist);
}

// A helper used by the smooth helpers above. Helpers can call other helpers!
private static Vec3 horizontalLook(Entity entity) {
    Vec3 look = entity.getLookAngle();
    return new Vec3(look.x, 0, look.z).normalize();
}
```

You'll also need to add this new import at the top of the file (next to the other `import` lines):

```java
import net.minecraft.core.Direction;
```

> **Notice something cool:** the smooth helpers call `horizontalLook(entity)`. **Helpers can use other helpers.** Just like the chocolate cake recipe pointing to the sponge recipe, which points to the "cream the butter" recipe, which points to "soften the butter". Tiny named pieces snap together.

> **What's `.normalize()`?** It takes an arrow (a `Vec3`) and resizes it so it's **exactly 1 block long**, keeping the same direction. Think: *"point the same way, but be exactly 1 long."* We need it because when we flatten the look vector with `new Vec3(look.x, 0, look.z)`, the arrow gets shorter if the player was looking up or down (we threw away the vertical part of its length). `normalize()` stretches it back to length 1 — so the spell goes the same distance whether you're staring at the sky or the floor.

Build and test. **Still a straight line!** None of the new helpers are being used yet. We've stocked the pantry — now we need to pick a recipe.

---

### Step 4 — The Switch (10 min)

This is where the lesson clicks. Just above the for-loop, read a new scoreboard:

```java
int spellMode = getEntityScore("spell_mode", entity);
```

Then **inside** the `if (r.nextBoolean())`, replace this line:

```java
Vec3 target = straightLineBolt(entity, x, y, z, i, r);
```

…with this whole block:

```java
Vec3 target = switch (spellMode) {
    case 1 -> smoothDirectionBolt(entity, x, y, z, i, r);
    case 2 -> ringBolt(entity, x, y, z, i, r);
    case 3 -> coneBolt(entity, x, y, z, i, r);
    case 4 -> squareBolt(entity, x, y, z, i, r);
    case 5 -> smoothSquareBolt(entity, x, y, z, i, r);
    default -> straightLineBolt(entity, x, y, z, i, r);
};
```

> **What just happened?** Java looks at `spellMode`, picks ONE of the lines, runs that helper, and hands the answer back into `target`. Whichever helper wins, the rest of the loop spawns lightning at the position it returned. **One line decides the shape of your spell.**

> **Watch the punctuation:** the whole switch block ends with `};` — that's a closing curly brace AND a semicolon, because the switch is part of a `Vec3 target = ...;` statement.

---

### Step 5 — Stepping Outside MCreator: A Custom Command (10 min)

So far every Java file we've touched was something **MCreator generated for us** — `CastSpellProcedure.java` was made from a Blockly procedure. We typed Java *inside* what MCreator already wrote.

This time we're going to make a **brand new file** that MCreator knows nothing about. Why? Because we want our own custom command (`/spellmode`) and MCreator doesn't have a "command" element in this workspace. So we're writing pure Java the way professional mod developers do.

> **Will MCreator wipe my file?** No. Open any file in `init/` — the first line says `// MCreator note: This file will be REGENERATED on each build`. **Only files with that marker get regenerated.** Our new file won't have it, so MCreator leaves it alone. But Gradle still compiles every `.java` file under `src/main/java/`, so our class works in the build.

**Open IntelliJ** (not MCreator) and create a new file at this path:

```
src/main/java/net/mcreator/dextermod/commands/SpellModeCommand.java
```

(In IntelliJ: right-click the `net.mcreator.dextermod` package → New → Package → name it `commands` → right-click that → New → Java Class → name it `SpellModeCommand`.)

**Paste this in:**

```java
package net.mcreator.dextermod.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import net.mcreator.dextermod.DextermodMod;

@EventBusSubscriber(modid = DextermodMod.MODID)
public class SpellModeCommand {

	@SubscribeEvent
	public static void register(RegisterCommandsEvent event) {
		event.getDispatcher().register(
				Commands.literal("spellmode")
						.then(Commands.argument("mode", IntegerArgumentType.integer(0, 5))
								.executes(SpellModeCommand::setMode)));
	}

	private static int setMode(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		int mode = IntegerArgumentType.getInteger(ctx, "mode");
		ServerPlayer player = ctx.getSource().getPlayerOrException();

		Scoreboard scoreboard = player.level().getScoreboard();
		Objective obj = scoreboard.getObjective("spell_mode");
		if (obj == null) {
			obj = scoreboard.addObjective(
					"spell_mode",
					ObjectiveCriteria.DUMMY,
					Component.literal("Spell Mode"),
					ObjectiveCriteria.RenderType.INTEGER,
					false,
					null);
		}
		scoreboard.getOrCreatePlayerScore(
				ScoreHolder.forNameOnly(player.getScoreboardName()), obj).set(mode);

		String shapeName = switch (mode) {
			case 1 -> "Smooth Direction";
			case 2 -> "Ring";
			case 3 -> "Cone";
			case 4 -> "Square";
			case 5 -> "Smooth Square";
			default -> "Straight Line";
		};
		ctx.getSource().sendSuccess(
				() -> Component.literal("Spell mode set to: " + shapeName),
				false);
		return 1;
	}
}
```

**What's actually going on:**

| Bit | What it does |
|-----|--------------|
| `@EventBusSubscriber(modid = ...)` | Tells NeoForge: "look at this class for event handlers" |
| `@SubscribeEvent` on `register` | Hooks into the moment commands are being set up |
| `Commands.literal("spellmode")` | The command name — what you type after the slash |
| `.then(Commands.argument("mode", IntegerArgumentType.integer(0, 5)))` | An integer 0–5 argument. Brigadier auto-rejects anything outside that range! |
| `.executes(SpellModeCommand::setMode)` | When the command runs, call our `setMode` method |
| Inside `setMode` | Get the number, find or create the scoreboard, set it, send a confirmation |
| The `switch` near the bottom | **Same pattern as our helpers** — but this time it returns a `String` (the label), not a `Vec3` |

> **Don't worry about memorising the syntax.** Most of this is **boilerplate** — code you copy-paste whenever you make a command. The interesting bit is at the bottom: that switch is the *same idea* as the one in the Storm spell, just returning a different type. Once you see that, you've seen the pattern.

**One more cool thing:** `setMode` is `static` and uses `SpellModeCommand::setMode` as a method reference. That's another way to point at a method without calling it — Brigadier will call it whenever someone types the command.

---

### Step 6 — Test It! (10 min)

Build and run. In chat:

```
/spellmode 0   ← straight line (default)
/spellmode 1   ← straight line, follows your look
/spellmode 2   ← 360° ring
/spellmode 3   ← cone in front
/spellmode 4   ← square in front (N/S/E/W)
/spellmode 5   ← square in front, follows your look
```

Right-click your spellbook between each. The lightning changes shape **without rebuilding the mod**. Run around, stand on a hill, try mode 2 in a village (sorry villagers).

> **The old `/scoreboard players set @s spell_mode 3` command still works** — `/spellmode 3` is just shorter to type *and* auto-creates the scoreboard objective if it doesn't exist yet.

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `cannot find symbol: method straightLineBolt` | Helper is inside `execute` instead of beside it | Helpers go **below** `execute`'s closing `}`, but **above** the class's closing `}` |
| `';' expected` after the switch | Forgot the `;` on the closing `};` | The whole switch is part of an assignment — needs a semicolon at the end |
| `cannot find symbol: Vec3` | Missing import | `import net.minecraft.world.phys.Vec3;` at the top |
| `cannot find symbol: Direction` | Missing import | `import net.minecraft.core.Direction;` at the top |
| Lightning still always straight | Forgot to read `spellMode`, or set the wrong scoreboard | Check `int spellMode = getEntityScore("spell_mode", entity);` and the `/scoreboard` command name |
| `Unknown command: spellmode` | Build didn't pick up the new command class | Hit the build hammer, then restart the client |
| All modes look the same | Build didn't take | Hit the build hammer again, or quit and rerun the client |

---

## Experiment!

**Change the cone width.** In `coneBolt`, find `Math.toRadians(45)` — try `15` for a tight beam, `90` for a half-circle.

**Change the square size.** In `squareBolt`, the line `int side = r.nextInt(-15, 16);` controls the width. Try `-5, 6` for a narrow corridor, or `-30, 31` for an enormous storm field.

**Make a thin wall instead of a filled square.** In `squareBolt`, replace `int forward = r.nextInt(2, 31);` with `int forward = 15;`. The bolts will only strike along one line out in front.

**Invent a new shape!** Copy one of the helpers, rename it (e.g. `tinyRingBolt`), tweak the maths, and add a new `case 6 ->` to the switch.

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **Helper method** | `private static Vec3 doSomething(...) { ... }` — your own named code block |
| **Return value** | A method can hand a value back with `return`. Catch it with `Type name = method();` |
| **Method parameters** | The `(Entity entity, double x, ...)` list — what the helper needs as inputs |
| **switch expression** | `switch (value) { case N -> ...; default -> ...; }` — pick one of many options |
| **The `->` arrow** | The modern switch's "go do this" symbol |
| **Default case** | `default ->` runs when no `case` matched — the safety net |
| **Refactoring** | Reorganising code without changing what it does |

---

## The Full Spellbook

Your Spellbook procedure has grown over the lessons:

```
[Blockly: send chat message]               ← Lesson 6
[Java: instanceof Player → greeting]       ← Lesson 6
[Java: entity.setGlowingTag(true)]         ← Lesson 7
[Java: if selectedSpell == N → dispatch]   ← Lesson 8
[Java: instanceof LivingEntity → heal]     ← Lesson 9
[Java: ServerLevel → particles]            ← Lesson 10
[Java: playSound]                          ← Lesson 10
[Blockly: set warriorScore]                ← Lesson 11
[Java: int bonus = calculation]            ← Lesson 11
[Java: instanceof Player → give items]     ← Lesson 11
[Java: for loop → particle pillar]         ← Lesson 12
[Java: storm + lightning bolts]            ← Lesson 12 extension
[Java: helper methods + switch shapes]     ← Lesson 13
```

You've gone from typing single-line Blockly imports to writing your own named methods, returning values, and dispatching between six of them at runtime. That's a **massive jump**.

---

## Where To Go From Here

You now know how to:
- Pull a chunk of code out and give it a name (**helper method**)
- Get a value back from a method (**return**)
- Pick one of many options cleanly (**switch expression**)
- Drive game behaviour from a scoreboard at runtime

Ideas for what to build next:
- Make every spell in your Spellbook use the switch pattern — each spell gets its own helper
- Add a `damageMode` scoreboard that controls how powerful the spells are
- Make a spell that picks a *random* shape each cast (hint: `r.nextInt(0, 6)` instead of reading the scoreboard)
- Make a "combo" spell that runs *two* shapes back-to-back (call two helpers in the loop)

The Spellbook is yours. The pantry is full of named recipes now — go bake.
