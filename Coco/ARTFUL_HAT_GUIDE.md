# Turning the Artful Hat into a real worn helmet

Target: **NeoForge 1.21.8** (this project), via **MCreator**. This documents the setup that
actually works, including every gotcha we hit getting there.

## Why a plain item doesn't work as a helmet

`Coco/models/artfulhat.json` is an **item display model**: a freestanding hat (cubes
*Bottom / Middle / Top*) with a `head` *display* transform and three textures
(`Hat.png`, `HatMiddle.png`, `HatTop.png`). Fine for an item in hand or an item frame, but the
armor renderer can't wear it, for two reasons:

1. **No humanoid skeleton.** Worn armor is rendered by grafting bones (`Head`, `Body`, arms,
   legs) onto the player's humanoid model. The freestanding hat has no `Head` bone to attach to.
2. **Three textures.** A worn armor model is drawn with a **single** texture; three separate
   PNGs can't all bind at once.

**MCreator supports custom 3D armor models natively** — you do *not* hand-write Java. Once you
give it a humanoid-structured model with the hat in a `Head` bone and one texture, its Armor
element generates the `getHumanoidArmorModel` override for you.

---

## The working recipe

### Step 1 — One merged texture

The hat must use **one** texture. In Blockbench (`ArtfulHat.bbmodel`):

- Switch the cubes to **Box UV** and use **UV → Auto-Layout** to pack all three onto a single
  sheet, then paint/copy your `Hat` / `HatMiddle` / `HatTop` pixels into place.
- Export one image (we used `artfulhelmet.png`, 128×128). Note its pixel size — it must match
  the model's `LayerDefinition.create(md, W, H)`.

### Step 2 — Rebuild the hat inside a `Head` bone

The hat geometry must live in a bone literally named **`Head`** at the player's head pivot.

**Coordinate reference** (Blockbench Y, feet on ground = 0):

```
Y=32  ━━━━━ TOP of the player's head   ← hat rests around here, builds upward
Y=24  ━━━━━ head bone PIVOT (= 0,24,0) = neck / bottom of head
```

- Head bone pivot stays at `(0, 24, 0)` — **don't move the pivot, move the cubes.**
- The head cube spans Y 24→32, so a hat sitting on top starts around **Y 30–32** and goes up.
- Anything below ~Y24 ends up at neck/chest level (a common mistake).
- In `addBox(...)` (entity space) the axis is **flipped**: head top is `y = -8`, more negative =
  higher. Conversion: `addBox_y = 24 − blockbenchY`.

**Previewing on a player in Blockbench:** a Modded Entity project is just your hat in empty
space. Install the **CEM Template Loader** plugin (File → Plugins), load the **Player**
template, drag your hat cubes into its `head` bone, and position against the reference head.

> ⚠️ **Before exporting, delete the template's player cubes** (head/body/arms/legs) but **keep
> the bone hierarchy and pivots**, leaving only your hat in `Head`. The armor renderer grafts the
> *entire* `Head` bone, so a leftover reference head cube renders as a **second blocky head** over
> the player. Tip: keep one `.bbmodel` *with* the reference for tweaking, export from a copy with
> the reference cubes stripped.

A ready reference with the cubes already converted into a `Head` bone is in
[`Assets/ModelArtfulHat.java`](Assets/ModelArtfulHat.java); the live working model is
[`models/mojmap-1.21.x/ModelArtfulHelmet.java`](models/mojmap-1.21.x/ModelArtfulHelmet.java).

### Step 3 — Export the model (mind the version!)

Export from Blockbench as **Java → Modded Entity**, and **pick a recent Minecraft version
(1.21.x)** in the export dialog.

> ⚠️ **Export-format gotcha (this bit us).** Blockbench 5.1.4's "Exported for Minecraft 1.17 or
> later" preset emits the old model API: `setupAnim(<EntityName> entity, float …)` and a
> non-generic class bound. On 1.21.8 the signature must be `setupAnim(LivingEntityRenderState
> state)` and the class `<T extends Entity>`. If you get `cannot find symbol class ArtfulHelmet`,
> that's this. Fix it in the **stored model source**
> [`models/mojmap-1.21.x/ModelArtfulHelmet.java`](models/mojmap-1.21.x/ModelArtfulHelmet.java)
> (change the class to `<T extends Entity>` and `setupAnim(T entity, …)`), **not** just the
> generated `src/` copy — MCreator regenerates `src/` from that stored source on every build, so
> a `src/`-only fix gets reverted. Exporting with a 1.21.x target avoids the problem entirely.

### Step 4 — Import the texture as an ENTITY texture

In MCreator → **Import textures → Import entity texture(s)…** and pick your merged sheet.

> ⚠️ **Do NOT use "Import armor texture."** That dialog wants two files (`*_layer_1.png` /
> `*_layer_2.png`) and is for the *vanilla flat-armor* system, not custom models. Custom-model
> armor reads its texture from `textures/entities/`, exactly where the entity-texture import
> puts it.

### Step 5 — Create the Armor element and bind the model

**Mod Elements → New → Armor.** In the helmet section:

- ✅ **Enable helmet** (leave chestplate / leggings / boots off).
- **Model:** select your imported Java model (e.g. `ModelArtfulHelmet`) and set the bone to
  **`Head`**. This is the switch that makes MCreator render the custom model instead of the flat
  vanilla head.
- **Armor texture:** your imported entity texture (`artfulhelmet`).
- **Item model:** the item display model (`artfulhat`) — this is the inventory icon (see the
  inventory gotcha below).

> ⚠️ **Mandatory "Armor layer texture" field.** The Armor element **won't save** without an
> element-level *armor layer texture* (the two-file `layer_1`/`layer_2` kind) — even though your
> custom model overrides it via `getArmorTexture` and it's never shown on the helmet. Just pick
> any existing one, or create a **fully transparent** placeholder. If you ever see a faint
> default-helmet "shell" over the hat in-game, switch this to a transparent texture.

Give it an armor material (protection / durability / enchantability) and a creative tab, then
build & run. Equip the helmet — the hat renders on the head and turns with it.

---

## Inventory icon shows up blank

Separate from the worn model, the **inventory icon** uses the *item* model (`artfulhat`) and its
own display transforms. Ours rendered blank because:

- The item model had **only a `head` display transform — no `gui` transform**, and
- the geometry sits at coordinates X 9–29 / Y 16–25 / Z 7–26: huge and far off-center (center
  ≈ `(19, 20.5, 16.5)`, not `(8,8,8)`).

In the inventory Minecraft uses the `gui` transform and rotates around the block center
`(8,8,8)`, so an oversized, off-center model with no `gui` transform renders **outside the 16×16
icon frame** → blank slot (textures are fine).

**Fix:** in Blockbench → **Display** tab. Easiest is to **select all cubes and recenter them
around (8,8,8)** in the item model, then set the **GUI** slot (plus Ground / Fixed / hand slots,
or a *Block* preset) so the hat sits in the item-frame preview. Re-export. (The item model's
`head` transform is irrelevant to the worn helmet — that uses the entity model — so you can
recenter the item geometry freely.)

---

## How the generated code works (for debugging)

MCreator generates an abstract `ArmorItem` whose
`registerItemExtensions(RegisterClientExtensionsEvent)` returns a `HumanoidModel` with **only the
`Head` bone** grafted from your model. The 1.21.8 signatures (different from older tutorials):

```java
public HumanoidModel getHumanoidArmorModel(ItemStack stack, EquipmentClientInfo.LayerType layerType, Model original) {
    return new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of(
        "head", new ModelPart(Collections.emptyList(), Map.of(
            "head", new ModelArtfulHelmet(Minecraft.getInstance().getEntityModels()
                    .bakeLayer(ModelArtfulHelmet.LAYER_LOCATION)).Head,
            "hat",  new ModelPart(Collections.emptyList(), Collections.emptyMap()))),
        "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
        "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
        "left_arm",  new ModelPart(Collections.emptyList(), Collections.emptyMap()),
        "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
        "left_leg",  new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
}

public ResourceLocation getArmorTexture(ItemStack stack, EquipmentClientInfo.LayerType type,
                                        EquipmentClientInfo.Layer layer, ResourceLocation _default) {
    return ResourceLocation.parse("coco:textures/entities/artfulhelmet.png");
}
```

The model layer is registered (by MCreator) in an `EntityRenderersEvent.RegisterLayerDefinitions`
handler: `event.registerLayerDefinition(ModelArtfulHelmet.LAYER_LOCATION, ModelArtfulHelmet::createBodyLayer)`.

---

## Tuning / troubleshooting checklist

- **`cannot find symbol class ArtfulHelmet`** → old export format; fix `setupAnim`/class bound in
  the stored `models/mojmap-1.21.x/…` source (Step 3), not just `src/`.
- **Hat floats / clips into head** → nudge the `addBox` Y offsets (head top is `y = -8`; more
  negative = higher).
- **Texture scrambled** → `texOffs` don't match the merged sheet; re-export so UVs are baked, and
  ensure `LayerDefinition.create(md, W, H)` equals the texture size.
- **Hat doesn't turn with the head** → cubes aren't inside the `Head` bone.
- **Second blocky head on the player** → leftover CEM reference cube in the `Head` bone; delete it.
- **Ghost flat-helmet shell** → element-level armor layer texture is visible; use a transparent one.
- **Blank inventory icon** → item model needs a `gui` display transform / recentered geometry.
- **Only a flat helmet, no hat** → the Armor element isn't pointing at the custom model / `Head`
  bone, or `getArmorTexture` isn't returning your entity texture.
