# Lesson 14: Custom Commands — Writing Java Outside MCreator

**Theme:** Stepping outside MCreator to write our own Java file
**Duration:** ~50 minutes
**New Concept:** Creating a Java file that MCreator doesn't manage; annotations; the Brigadier command framework
**Builds On:** Lesson 13 (the switch expression returns!)

---

## What We're Building

A custom `/spellmode <0-5>` command that swaps Storm spell shapes instantly. Instead of typing this every time:

```
/scoreboard players set @s spell_mode 3
```

…you'll type just this:

```
/spellmode 3
```

Same effect, **much** less typing. And we'll learn how Minecraft commands actually work under the hood.

---

## Teacher: Physical Demo (8 min)

### Part 1 — Why Make Our Own Command?

> *"Last lesson, every time we wanted to change the Storm shape we typed 30 characters of `/scoreboard players set @s spell_mode 3`. Was that fun? No. Real Minecraft has shortcuts — `/give`, `/tp`, `/time`. They're all SHORT. We're going to make our own."*

Write on the whiteboard:

```
/scoreboard players set @s spell_mode 3     ← 40 keystrokes
/spellmode 3                                 ← 13 keystrokes
```

### Part 2 — The Vending Machine

> *"A vending machine has buttons. You press 3, you get item 3. You don't have to walk into the factory and ask them to make item 3. The button is a **shortcut** that knows what to do."*

> *"A command is a button. `/spellmode 3` is the button. We're going to wire it up so when someone presses it, it sets the scoreboard for us."*

### Part 3 — The "@" Symbol on the Board

Write on the whiteboard:

```java
@EventBusSubscriber
@SubscribeEvent
```

> *"You'll see these on the new file. The `@` symbol is called an **annotation**. Think of it like a sticker or a name tag. The game looks for stickers and knows what to do with the class wearing them."*

> *"`@EventBusSubscriber` is like wearing a 'I handle events' name tag. `@SubscribeEvent` on a method is like saying 'THIS method handles them.' The game finds them and calls them at the right moment."*

### Part 4 — Outside MCreator's Garden

> *"Until today, every Java file we touched was made by MCreator. MCreator drew the outline; we coloured inside the lines. This time, **we draw the whole picture ourselves.** MCreator doesn't know our new file exists — but the build still compiles it, because Java doesn't care who wrote it."*

---

## Step-by-Step

### Step 1 — Where MCreator Stops (5 min)

Open IntelliJ (not MCreator). Navigate to `init/DextermodModBlocks.java`. Look at the **top** of the file:

```java
// MCreator note: This file will be REGENERATED on each build
```

> **This is MCreator's territory.** Any file with that warning will be **wiped and rewritten** every time you hit the build hammer. Editing one of these is pointless — your edits vanish.

Now open `CastSpellProcedure.java` (the Storm spell file from Lesson 12/13). No warning at the top — but look at line 1:

```java
package net.mcreator.dextermod.procedures;
```

It lives in a package MCreator owns (`procedures`). MCreator made the file from a Blockly procedure, but the **user code blocks** inside are ours. Half MCreator, half us.

**Our new file will be neither.** It'll live in a brand new package called `commands`, and MCreator will never have heard of it.

---

### Step 2 — Create the File (5 min)

In IntelliJ:

1. **Right-click** the `net.mcreator.dextermod` package in the Project panel
2. **New → Package** → type `commands` → Enter
3. **Right-click** the new `commands` package
4. **New → Java Class** → type `SpellModeCommand` → Enter

You should now have an empty file at:

```
src/main/java/net/mcreator/dextermod/commands/SpellModeCommand.java
```

---

### Step 3 — Paste the Code (5 min)

Delete whatever IntelliJ put in the file and paste this:

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

> **That's a lot.** Don't worry — we'll only **explain the interesting parts**. Most of it is **boilerplate** (code you copy-paste every time you make a command). Once you've seen one Brigadier command, you've seen them all.

---

### Step 4 — What's Actually Going On (15 min)

#### The Annotations (the stickers)

```java
@EventBusSubscriber(modid = DextermodMod.MODID)
public class SpellModeCommand {

	@SubscribeEvent
	public static void register(RegisterCommandsEvent event) {
```

| Sticker | What it does |
|---------|--------------|
| `@EventBusSubscriber(modid = DextermodMod.MODID)` | "Hey NeoForge — look at this class for event handlers." The `modid` says which mod we belong to. |
| `@SubscribeEvent` | "THIS method handles an event." NeoForge finds it and calls it at the right moment. |
| The parameter `RegisterCommandsEvent` | The TYPE of event we want. NeoForge sees this and only calls us when commands are being registered. |

You **never call `register()` yourself.** NeoForge calls it for you when Minecraft starts up. Annotations are how the game finds the methods that want to be called.

#### The Command Tree (Brigadier)

```java
event.getDispatcher().register(
        Commands.literal("spellmode")
                .then(Commands.argument("mode", IntegerArgumentType.integer(0, 5))
                        .executes(SpellModeCommand::setMode)));
```

Read it like a sentence:

| Piece | What it means |
|-------|---------------|
| `Commands.literal("spellmode")` | The command starts with the word `spellmode` |
| `.then(Commands.argument("mode", IntegerArgumentType.integer(0, 5)))` | Followed by a number between 0 and 5 |
| `.executes(SpellModeCommand::setMode)` | When the user types it, run our `setMode` method |

> **Cool fact:** because we used `IntegerArgumentType.integer(0, 5)`, Brigadier **automatically rejects** `/spellmode 9`. The game refuses to run it and tells the user why. We didn't write any validation — Brigadier did it for us.

#### The Method Reference

```java
.executes(SpellModeCommand::setMode)
```

The `::` is a **method reference**. It means "here's a method, but don't call it yet — Brigadier, you call it when you need to." It's a tidy way to hand off a method without `()`.

#### The Handler (where the work happens)

```java
private static int setMode(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
    int mode = IntegerArgumentType.getInteger(ctx, "mode");
    ServerPlayer player = ctx.getSource().getPlayerOrException();
    // ... find or make the scoreboard ...
    // ... set the player's score ...
    // ... send a confirmation message ...
    return 1;
}
```

Reading top-to-bottom:
1. **Get the number** the player typed (`IntegerArgumentType.getInteger(ctx, "mode")`)
2. **Get the player** who ran the command
3. **Find the `spell_mode` scoreboard** — and create it if it doesn't exist yet (so the kids don't need the `/scoreboard objectives add` step any more!)
4. **Set the player's score** to the number
5. **Look up the shape name** with… (drum roll) **a switch expression**. Yes — the *exact same pattern* from Lesson 13, but returning a `String` instead of a `Vec3`. **Once you know the pattern, you can use it for any type.**
6. **Send a chat message** confirming what just happened
7. **Return 1** to tell Brigadier "the command succeeded"

> **The switch is the secret.** Everything else in this file is plumbing — annotations, imports, Brigadier syntax — that you copy-paste. The actual decision-making is **the switch you already know how to write.**

---

### Step 5 — Build and Test (10 min)

Save the file. Hit the build hammer in MCreator (or run `./gradlew build`). Launch Minecraft.

In chat:

```
/spellmode 0
/spellmode 1
/spellmode 2
/spellmode 3
/spellmode 4
/spellmode 5
```

Each one prints **"Spell mode set to: <name>"** in chat. Right-click your Spellbook between each to see the shape change.

Try `/spellmode 9`. Brigadier blocks you with a helpful error — **we never wrote that check**, the framework did it for us.

Try `/spellmode` with no number. Brigadier tells you it expected an argument — again, free validation.

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `Unknown command: spellmode` | Build didn't pick up the new class | Rebuild, then restart the client |
| `package net.mcreator.dextermod.commands does not exist` | Made the file in the wrong place | It must live in `src/main/java/net/mcreator/dextermod/commands/` |
| `cannot find symbol: Commands` | Missing import | Make sure `import net.minecraft.commands.Commands;` is at the top |
| `cannot find symbol: EventBusSubscriber` | Missing import | `import net.neoforged.fml.common.EventBusSubscriber;` |
| `cannot find symbol: RegisterCommandsEvent` | Missing import | `import net.neoforged.neoforge.event.RegisterCommandsEvent;` |
| Command compiles but does nothing | Class isn't registered for events | Check `@EventBusSubscriber(modid = DextermodMod.MODID)` is **on the class**, not on a method |
| Got the chat message but spell didn't change | `setMode` set a different scoreboard | The name must be exactly `"spell_mode"` in BOTH this file and `CastSpellProcedure.java` |

---

## Experiment!

**Change the command name.** Replace `"spellmode"` with `"shape"` — now you type `/shape 3`. Rebuild.

**Add a permission level.** Above `.then(...)`, insert `.requires(source -> source.hasPermission(2))` — now only ops can run the command.

**Make a new command from scratch.** Copy `SpellModeCommand.java` to `HelloCommand.java`. Inside, change the literal to `"hello"`, drop the argument, and have `setMode` (rename it `say`) just send a chat message saying "Hello!". You now have a `/hello` command.

**Wire up the Spell of Glow.** Make a `/glow` command that toggles `entity.setGlowingTag(true)` on the caller. (Hint: use `ctx.getSource().getPlayerOrException().setGlowingTag(...)`.)

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **A whole new Java file** | Code that lives **outside** MCreator's procedures — in your own package |
| **`package` declaration** | The first line of every Java file — says what folder/namespace the class lives in |
| **Annotations (`@`)** | Stickers on classes and methods that tell other code "treat me this way" |
| **`@EventBusSubscriber`** | Tells NeoForge to scan this class for event handlers |
| **`@SubscribeEvent`** | Marks a method as an event handler |
| **Method reference (`::`)** | Hand off a method without calling it: `ClassName::methodName` |
| **Brigadier command framework** | `Commands.literal(...).then(...).executes(...)` — Minecraft's command builder |
| **Argument validation** | `IntegerArgumentType.integer(0, 5)` rejects out-of-range values for free |

---

## How MCreator Sees This

MCreator scans your `.mod.json` files in `elements/` to know what exists. Our new file has **no matching `.mod.json`** — MCreator has no idea it exists. That's fine. Gradle (the build tool) doesn't care; it compiles **every** `.java` file under `src/main/java/`, MCreator-aware or not.

What this means in practice:
- **Safe to edit any time** — MCreator won't overwrite it.
- **Won't show up in MCreator's element list** — if you want to find it, you go through IntelliJ, not the MCreator workspace.
- **Compiles into the same mod jar** — players don't know or care which tool wrote the code.

This is how **all** real mod developers work. MCreator is a great accelerator for the boilerplate (blocks, items, GUIs), and once you outgrow what it can do, you drop into pure Java exactly like we did here.

---

## The Full Spellbook

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
[Java: custom command class]               ← Lesson 14
```

You've now written code in **two completely different ways**:
- **Inside MCreator procedures** (lessons 6–13) — adding Java to what MCreator generated
- **As your own Java file** (lesson 14) — writing the whole class yourself

This is the same split real Java developers experience between **generated/framework code** and **their own code**. You're doing professional Java now.

---

## Where To Go From Here

You can write commands! That unlocks a lot:
- `/heal` — full heal the player
- `/fly` — toggle flight in survival (`player.getAbilities().mayfly = true`)
- `/skill` — read or set a scoreboard skill from Lesson 2
- `/loadout` — give the player a starter kit of items
- A command that triggers ANY existing procedure (`CastSpellProcedure.execute(...)`) — your custom commands and your spells can talk to each other

The next time you build a feature and think *"how do I trigger this from chat?"* — you know the answer now. Make a command class.
