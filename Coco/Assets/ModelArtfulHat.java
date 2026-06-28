package net.mcreator.coco.client.model;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

/**
 * Reference armor model for the Artful Hat, hand-converted from Coco/models/artfulhat.json
 * into a humanoid "Head" bone so it can be worn as a HELMET on NeoForge 1.21.8.
 *
 * This matches the exact shape MCreator generates for custom armor models (extends
 * EntityModel<LivingEntityRenderState>, a LAYER_LOCATION, createBodyLayer, public ModelPart
 * fields per bone). Only the Head bone is needed because a helmet only grafts the Head part
 * onto the player's humanoid model.
 *
 * IMPORTANT — two things still need YOUR input (see ARTFUL_HAT_GUIDE.md):
 *   1. TEXTURE: armor renders with ONE texture. Hat.png + HatMiddle.png + HatTop.png must be
 *      merged into a single sheet, and the texOffs(...) values below set to where each cube's
 *      box-UV net lands on that sheet. The placeholders below give the correct SHAPE but the
 *      texture will look scrambled until you fix the UVs (easiest: re-export from Blockbench
 *      after merging, which fills these in automatically).
 *   2. The LayerDefinition.create(md, W, H) width/height MUST equal your merged texture size.
 *
 * The cube positions are in head-relative entity space: the player's head box spans Y -8..0,
 * so the hat sits stacked on top (Y values from -8 upward/negative). Nudge the addBox offsets
 * in-game if it sits too high/low/forward.
 */
public class ModelArtfulHat extends EntityModel<LivingEntityRenderState> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("coco", "modelartful_hat"), "main");

	public final ModelPart Head;

	public ModelArtfulHat(ModelPart root) {
		super(root);
		this.Head = root.getChild("Head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Head bone sits at the player's head pivot. The three hat cubes are stacked on top of it.
		// texOffs(...) are PLACEHOLDERS — set these to match your merged texture atlas.
		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create()
				// Bottom brim  (artfulhat.json cube 0: from[10,16,8] to[28,18,25], texture Hat.png)
				.texOffs(0, 0).addBox(-9.0F, -10.0F, -8.5F, 18.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
				// Middle band  (cube 1: from[11,18,10] to[27,23,24], texture HatMiddle.png)
				.texOffs(0, 40).addBox(-8.0F, -15.0F, -6.5F, 16.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
				// Top          (cube 2: from[9,23,7] to[29,25,26], texture HatTop.png)
				.texOffs(0, 80).addBox(-10.0F, -17.0F, -9.5F, 20.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		// W/H must equal your merged texture's pixel size (see note above).
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
		// Helmet follows the head automatically once grafted onto the humanoid head slot; nothing to animate.
	}
}
