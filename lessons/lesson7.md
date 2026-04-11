# Lesson 7: Spell of Radiance — The Dot

**Theme:** Java Superpowers — making things happen to objects
**Duration:** ~60 minutes
**New Concept:** The dot operator — calling methods on objects
**Builds On:** Lesson 6 (typing a Java statement, the `instanceof` guard)

---

## What We're Building

Two new spells for your Spellbook:

1. **Spell of Radiance** — makes the player glow with a bright white outline
2. **Spell of Shadows** (extension) — makes the player invisible. Combine with glowing for **ghost mode**!

---

## Teacher: Physical Demo (8 min)

**Bring:** a torch, phone flashlight, or desk lamp — anything with an on/off switch.

### Part 1 — Objects Can Do Things

Hold up the torch. Don't turn it on yet.

> *"This torch is an OBJECT. Objects can do things. Watch:"*

Say it like you're giving a command:

> *"Torch. Turn on."*

Turn it on.

> *"Torch. Turn off."*

Turn it off.

Write on the whiteboard:

```
torch.turnOn()
torch.turnOff()
```

> *"In Java, the DOT is how you tell an object to do something. Object, DOT, action. The brackets at the end mean 'do it now.'"*

### Part 2 — Minecraft Entities Are Objects

> *"In Minecraft, the player is an object. A zombie is an object. A chicken is an object. They can all DO things."*

Write on the whiteboard:

```
entity.setGlowingTag(true)
entity.setGlowingTag(false)
```

> *"This tells the entity: start glowing. The `true` means ON. What do you think `false` does?"*

(They'll answer "turns it off" — right!)

> *"Notice: no `instanceof` check needed this time. EVERY entity can glow — players, zombies, chickens, everything. We only need `instanceof` when we want to do something that only SOME entities can do. Glowing? Everyone can do that."*

### Part 3 — The Vocabulary

Draw this on the board and label the parts:

```
entity  .  setGlowingTag  (  true  )  ;
  |     |       |         |    |   |  |
object dot   method      (  arg  ) semicolon
```

> *"**Object** — the thing. **Dot** — 'do this.' **Method** — what to do. **Argument** — the detail. **Semicolon** — end of sentence."*

---

## Step-by-Step: MCreator

### Step 1 — Open Your Spellbook Procedure (2 min)

Open the `SpellbookCast` procedure you made in Lesson 6. You should already have:
- A Blockly "send chat message" block
- A Custom code snippet with your greeting spell

We're going to add a SECOND custom code snippet below the first.

---

### Step 2 — Add the Spell of Radiance (10 min)

1. From the **Advanced** category, drag out a new **"Custom code snippet"** block
2. Connect it below your first custom code snippet
3. Type this — it's short!

```java
entity.setGlowingTag(true);
```

That's it. One line. Six "words."

**Check it:**
- `entity` — **lowercase e!** This is the variable from your procedure, not the word "Entity" with a capital E
- `.` — the dot
- `setGlowingTag` — capital G, capital T (this is called "camelCase" — humps like a camel!)
- `(true)` — lowercase `true`, with brackets
- `;` — semicolon at the end

> **camelCase:** Java programmers smush words together and capitalize each new word: `setGlowingTag`, `displayClientMessage`. The first word stays lowercase, every word after gets a capital letter — like the humps of a camel. *(Teacher: write `setGlowingTag` on the board and draw little camel humps over the G and T. Kids will remember the visual.)*

---

### Step 3 — Build and Test! (10 min)

Build the mod. Run the client. Give yourself the Spellbook and right-click.

You should:
1. See your greeting message in chat (from Lesson 6)
2. Start **glowing with a bright white outline**!

The glow effect is the spectral outline — the same one you see when you hit a mob with a spectral arrow. Try looking at yourself in third person (press F5) to see it clearly.

> *"Can I make OTHER players or mobs glow, not just me?"* Not today — `entity` is whoever right-clicked, which is you. Making other mobs glow requires finding them first, which is a future lesson.

> Ask: *"Does the glow turn off?"*
>
> It doesn't! Once you call `setGlowingTag(true)`, it stays on until something calls `setGlowingTag(false)`. We'll fix that in the extension.

---

### Step 4 — Experiment With Other Methods (10 min)

The power of the dot is that there are HUNDREDS of methods you can call on `entity`. Try replacing your code with any of these (one at a time — save, build, test each one):

| Code | What it does |
|------|-------------|
| `entity.setGlowingTag(true);` | White spectral glow |
| `entity.setInvisible(true);` | Makes you invisible |
| `entity.setShiftKeyDown(true);` | Forces sneaking pose |
| `entity.setSwimming(true);` | Swimming animation on land |
| `entity.setTicksFrozen(200);` | Frost overlay (like powdered snow) for 10 seconds |

> *"Notice the pattern? It's always `entity.doSomething(value);` — object, dot, method, argument, semicolon. Same shape every time."*

---

## Common Problems

| Problem | Likely Cause | Fix |
|---------|-------------|-----|
| `setGlowingTag` not recognized | Typo in the method name | Check capitals: `setGlowingTag` with capital G and capital T |
| `True` instead of `true` | Java booleans are lowercase | Change `True` to `true` (or `False` to `false`) |
| Glow doesn't appear | Might be hard to see in first person | Press F5 for third-person view |
| Nothing happens at all | Custom code snippet not connected | Make sure the block is snapped to the one above it in the procedure |
| Old greeting spell broke | You might have edited the wrong code snippet | Each Custom code snippet is separate — check you're editing the new one |

---

## Extension 1: Ghost Mode

Want to be a ghost? Add one more line to your existing custom code snippet, right below `entity.setGlowingTag(true);`:

```java
entity.setGlowingTag(true);
entity.setInvisible(true);
```

Now you're invisible AND glowing — just an outline floating around. Spooky!

**Making it temporary:** We need to wait, then undo both effects. The wait has to be a Blockly block (Java can't easily pause mid-procedure), so we need to split the code around it:

1. Your **first** custom code snippet has the "turn on" lines (glow + invisible)
2. A Blockly **"Wait 5 seconds"** block (from the **Flow control** category) goes after it
3. A **second** custom code snippet after the wait has the "turn off" lines:

```java
entity.setGlowingTag(false);
entity.setInvisible(false);
```

Your procedure should look like:

```
[Blockly: send chat message "Spellbook activated!"]  ← from Lesson 6
[Custom code: greeting from Lesson 6]                 ← from Lesson 6
[Custom code: setGlowingTag(true) + setInvisible(true)]  ← this lesson
[Blockly: wait 5 seconds]                              ← Blockly block
[Custom code: setGlowingTag(false) + setInvisible(false)] ← undo block
```

Ghost mode lasts 5 seconds, then you return to normal.

---

## Extension 2: Method Discovery

How do you find out what methods exist? Here's a challenge:

Try typing `entity.set` and then different endings. Can you figure out what these do by testing them?

- `entity.setNoGravity(true);`
- `entity.setSilent(true);`
- `entity.setCustomName(Component.literal("Wizard"));` (floating name tag above your head — like using a name tag item!)
- `entity.setInvulnerable(true);`

> *"Professional Java programmers use a tool called 'autocomplete' that shows you every method available. It's like a menu of every ability the object has. For now, we're exploring by guessing and testing — that's how you build intuition."*

---

## Import Reference

| Short Name | Full Address | How to Unlock Short Name |
|------------|-------------|--------------------------|
| (none needed) | All methods used in this lesson (`setGlowingTag`, `setInvisible`, etc.) are on the `Entity` class, which is already available because `entity` is a procedure dependency | N/A |

No import tricks needed for this lesson! Everything works on the base `entity` object.

---

## Java Concepts Introduced

| Concept | What They Learned |
|---------|------------------|
| **The dot operator** | `object.method()` — the dot tells an object to do something |
| **Methods** | Actions that objects can perform: `setGlowingTag`, `setInvisible` |
| **Arguments** | The value in brackets that controls the method: `true` or `false` |
| **camelCase** | How Java names things: `setGlowingTag`, `displayClientMessage` |
| **Booleans** | `true` and `false` — on and off switches (always lowercase in Java) |

---

## Next Lesson Preview

> *"We've got two spells now — Greeting and Radiance. But there's a problem: right-clicking the Spellbook fires BOTH of them every time. That's not a spellbook, that's a spell grenade! Next lesson, we'll build a proper spell selector — a menu pops up where you CHOOSE which spell to cast. And the cool part? The selection sticks — pick a spell once, and from then on, right-clicking just casts that spell."*
