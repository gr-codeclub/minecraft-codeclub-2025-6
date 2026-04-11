# Lesson 6: Spell of Greeting — Your First Line of Java

**Theme:** Java Superpowers — typing real code for the first time
**Duration:** ~60 minutes
**New Concept:** Writing a Java statement (strings, semicolons, parentheses)
**Builds On:** All 5 previous MCreator Blockly lessons

---

## What We're Building

A **Spellbook** item. Right-click it to cast a spell — but this time, the spell is written in **real Java code**, not Blockly blocks.

Today's spell: the **Spell of Greeting** — it sends a message to your chat. Simple? Yes. But you're about to type your first ever line of Java, and that's a big deal.

---

## Teacher: Physical Demo (10 min)

**Bring two things:** a Lego instruction booklet (or just a picture of one) and a blank piece of paper with a pen.

### Part 1 — Blockly is like Lego

Hold up the Lego booklet.

> *"Blockly is like building with Lego. You snap blocks together. It works, it's easy, and you can build cool stuff. But you can only build what the blocks let you build."*

### Part 2 — Java is like a blank page

Hold up the paper and pen.

> *"Java is like this. You can write ANYTHING. Way more freedom. But you have to spell everything right, and there are no pictures to guide you."*

Write this on the whiteboard in big letters:

```
_player.displayClientMessage(Component.literal("Hello!"), false);
```

Point to each piece:

| Piece | What it means |
|-------|---------------|
| `_player` | The person who clicked the spellbook |
| `.` | The dot means "do something" |
| `displayClientMessage(..., false)` | Send a chat message (`false` means show in chat, not the action bar) |
| `Component.literal("Hello!")` | The message text — wrapped in a "Component" because Minecraft's chat system needs it |
| `;` | The semicolon — **every Java sentence ends with one**, like a full stop in English |

### Part 3 — The Guard

Now write the guard line above the spell:

```
if (entity instanceof Player _player)
    _player.displayClientMessage(Component.literal("Hello!"), false);
```

> *"What's the `if (entity instanceof Player _player)` bit? It's a safety check. The thing that right-clicked could be anything — a player, a zombie, who knows. This says: IF the entity IS a Player, THEN call it `_player` and run the code. We'll learn more about this in Lesson 8 — for now, just think of it as a safety wrapper that you type once and forget about."*

---

## Step-by-Step: MCreator

### Step 1 — Create the Spellbook Item (3 min)

You've made items before — this one is quick:

1. Click **+ New Element --> Item**
2. Name it `Spellbook`
3. Pick a texture — the enchanted book texture works great, or use any placeholder
4. In the item properties, set **Max stack size** to `1`
5. Save the item

---

### Step 2 — Create the Procedure and Link It (5 min)

1. Go back to your **Spellbook** item settings
2. Find the **Events/Triggers** tab
3. Next to **"When item is right-clicked in air"**, click the dropdown and select **"Create new..."**
4. Name the new procedure `SpellbookCast`
5. MCreator will create the procedure and automatically set up the right dependencies (including `entity` — the player who right-clicked)

> **Why this way?** When you create a procedure directly from an item's trigger, MCreator automatically gives it the right dependencies. No need to set them up manually.

---

### Step 3 — Tell MCreator Which Java Words We'll Need (3 min)

MCreator needs to know which Java tools our code will use. Think of it like telling a librarian which books to pull off the shelf before you start reading. If we don't tell MCreator, it won't know what `Component` or `Player` means.

The trick: **add a Blockly block that uses those same tools.** MCreator will see the Blockly block and pull the right books off the shelf for us.

1. In your procedure, find the **Player** category in the block palette
2. Drag in a **"Send chat message to player"** block
3. Connect it to the start of your procedure
4. Set the message to `"Spellbook activated!"`
5. Make sure the player slot is connected to the **"Event/target entity"** block (from the **Minecraft Components** category at the top)

> Later in the arc, when you're more comfortable with Java, we'll learn a different way to handle this. For now: **one Blockly block first, then your Java code after it.**

**Alternative — fully-qualified names (no Blockly block needed):**

If you'd rather skip the Blockly block, you can use the "full address" of each Java class directly in your code. We'll show this in the reference table at the end of the lesson.

---

### Step 4 — Type Your First Java Code (15 min)

This is the big moment! We're going to type this in **two parts** so it's easier to get right.

1. In the procedure editor, find the **Advanced** category in the block palette (it's near the bottom of the list)
2. Drag out a **"Custom code snippet"** block
3. Connect it below your Blockly chat message block

Now type the code in the text area. We'll do it in two parts:

**Part A — The safety guard (type this first):**

```java
if (entity instanceof Player _player)
```

Check it carefully:
- `if` — lowercase
- Space, then `(`
- `entity` — lowercase, this is the thing that right-clicked
- `instanceof` — one word, all lowercase
- `Player` — capital P! This is the type we're checking for
- `_player` — lowercase with an underscore at the start. This is the nickname we give it
- `)` to close

**Part B — The spell (type this on the next line, right after the guard):**

```java
_player.displayClientMessage(Component.literal("Hello!"), false);
```

Check it carefully:
- `_player` — must match exactly what you wrote above (underscore, lowercase)
- `.displayClientMessage(` — capital C, capital M! Two capital letters in the middle
- `Component.literal(` — capital C on Component, lowercase l on literal
- `"Hello!"` — your message inside double quotes
- `, false)` — the `false` means "show in chat" (not the action bar above your hotbar)
- `);` — close `displayClientMessage(` and end the statement

> **Bracket counting tip:** Count the opening brackets `(` in your line. You need the same number of `)` at the end. The spell line has TWO `(` so it needs TWO `)`.

Your complete code in the Custom code snippet should look like:

```java
if (entity instanceof Player _player)
_player.displayClientMessage(Component.literal("Hello!"), false);
```

---

### Step 5 — Build and Test! (10 min)

Before you build, here's what to expect if something goes wrong:

> **What a build error looks like:** If you have a typo, MCreator will show red text in the console. Don't panic! This is normal — even professional programmers get errors on their first try. The fix is almost always one of these:
> - A capital letter that should be lowercase (or vice versa)
> - A missing bracket `)` or semicolon `;`
> - A misspelled word
>
> **How to fix it:** Open your procedure, click the custom code snippet, and compare what you typed **letter by letter** against the code in this lesson. Find the difference. Fix it. Build again.

Build the mod. Run the client. Give yourself the Spellbook (substitute your mod name if it's different):

```
/give @s dextermod:spellbook
```

Right-click with it. You should see two messages in chat:
1. `"Spellbook activated!"` — from the Blockly block
2. `"Hello!"` — from YOUR Java code

**You just wrote Java.**

Now change `"Hello!"` to your own message. Rebuild and test again. How about `"I cast the Spell of Greeting!"`? Or your name? Or something silly?

> *"Which message came from Blockly? Which came from Java? Could you tell the difference just by looking at the chat?"*
>
> No — they look identical. Java and Blockly can do the same things. Java just gives you more control over HOW.

---

## What the Code Actually Looks Like

Open the generated Java file and show the class. The Blockly message and the custom code snippet are right next to each other in the generated `.java` file. They're the same language — MCreator was writing Java for you all along!

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| Red error when building | Typo in the custom code | Compare your code letter-by-letter against this lesson. Check capitals, brackets, semicolons |
| `Component` not found | MCreator doesn't know about this class | Make sure you added the Blockly "send chat message" block in Step 3. Or use the fully-qualified name: `net.minecraft.network.chat.Component` |
| `Player` not found | Same as above | The Blockly chat message block fixes this too. Or use: `net.minecraft.world.entity.player.Player` |
| Nothing happens on right-click | Procedure not linked to item | Go back to Spellbook item --> Events/Triggers --> check "When item is right-clicked in air" |
| Message appears twice | Code is running on both server and client | In your procedure, wrap everything inside a Blockly **"If on server side"** block (Logic category). Put both the Blockly chat block and the custom code snippet inside it |
| `_player` doesn't work | Spelled differently in guard vs spell | Check that `_player` is identical in both lines — same underscore, same capitalization |

---

## Extension 1: Color Codes — The Hacky Way

Finished early? Change your message to include Minecraft's old-school color codes. These use `\u00A7` (the "section sign" character) followed by a letter:

```java
if (entity instanceof Player _player)
_player.displayClientMessage(Component.literal("\u00A7cFIREBOLT!"), false);
```

Here are some codes to try — just put them before your text:

| Code | Color/Effect |
|------|-------------|
| `\u00A7a` | Green |
| `\u00A7b` | Aqua |
| `\u00A7c` | Red |
| `\u00A7d` | Pink |
| `\u00A7e` | Yellow |
| `\u00A76` | Gold |
| `\u00A7l` | **Bold** |
| `\u00A7o` | *Italic* |
| `\u00A7k` | Obfuscated (scrambled — try it!) |
| `\u00A7r` | Reset to normal |

You can combine them: `"\u00A7c\u00A7lFIREBOLT!"` = red + bold.

This works, but look at the code — `\u00A7c` doesn't exactly scream "red", does it? There's a better way...

---

## Extension 2: Teacher Demo — The Proper Java Way

> **Teacher:** Show this on the projector. Students who want to try it can, but it's long — this is more of a "look at what's possible" moment.

Replace the color codes with Java's proper formatting API:

```java
if (entity instanceof Player _player)
_player.displayClientMessage(Component.literal("FIREBOLT!").withStyle(net.minecraft.ChatFormatting.RED, net.minecraft.ChatFormatting.BOLD), false);
```

> We use `net.minecraft.ChatFormatting` (the full address) because there's no Blockly trick for this one.

Compare the two approaches:

| Hacky way | Proper Java way |
|-----------|----------------|
| `"\u00A7c\u00A7l"` | `.withStyle(ChatFormatting.RED, ChatFormatting.BOLD)` |
| Hard to read | Reads like English |
| Easy to mistype | Clear and obvious |
| Old Minecraft trick | Modern Java API |

> *"See how much nicer the Java version reads? `ChatFormatting.RED` tells you exactly what it does. `\u00A7c` is just... mysterious gibberish. This is why programmers build proper tools — so code is readable."*

Some styles that work with `withStyle()`:

`RED`, `GREEN`, `BLUE`, `AQUA`, `GOLD`, `YELLOW`, `BOLD`, `ITALIC`, `OBFUSCATED`, `UNDERLINE`

---

## Import Reference

Every class in Java has a "full address" (called a **fully-qualified name**). Here are the ones used in this lesson:

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| `Component` | `net.minecraft.network.chat.Component` | Blockly "send chat message" block |
| `Player` | `net.minecraft.world.entity.player.Player` | Blockly "send chat message" block |
| `ChatFormatting` | `net.minecraft.ChatFormatting` | No Blockly trick — use full address |

If MCreator complains it doesn't recognize a short name, replace it with the full address. The full address always works.

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **Semicolons** | Every Java statement ends with `;` — like a full stop |
| **Strings** | Text goes inside `"double quotes"` |
| **Method calls** | `object.method(argument)` — we'll explore this more in Lesson 7 |
| **instanceof** | A safety check — "is this thing a Player?" — we'll explore this more in Lesson 8 |

---

## Next Lesson Preview

> *"Today you typed your first Java and sent a message. Next time, we're going to make you GLOW. One line of Java, and you'll light up like a beacon. And then... we'll make you invisible. At the same time. Ghost mode."*
