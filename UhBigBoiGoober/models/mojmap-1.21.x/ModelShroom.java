// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class ModelShroom<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "shroom"), "main");
	private final ModelPart ll;
	private final ModelPart rl;
	private final ModelPart MushroomBody;
	private final ModelPart MushroomHead;
	private final ModelPart ra;
	private final ModelPart la;

	public ModelShroom(ModelPart root) {
		this.ll = root.getChild("ll");
		this.rl = root.getChild("rl");
		this.MushroomBody = root.getChild("MushroomBody");
		this.MushroomHead = root.getChild("MushroomHead");
		this.ra = root.getChild("ra");
		this.la = root.getChild("la");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ll = partdefinition.addOrReplaceChild("ll", CubeListBuilder.create().texOffs(36, 21).addBox(
				-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -2.0F));

		PartDefinition rl = partdefinition.addOrReplaceChild("rl", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F,
				-2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 2.0F));

		PartDefinition MushroomBody = partdefinition.addOrReplaceChild("MushroomBody",
				CubeListBuilder.create().texOffs(32, 26)
						.addBox(-6.0F, -4.6F, -4.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(24, 39)
						.addBox(-1.0F, -2.0F, 1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(32, 40)
						.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(3.0F, 20.6F, 0.0F));

		PartDefinition MushroomHead = partdefinition.addOrReplaceChild("MushroomHead",
				CubeListBuilder.create().texOffs(36, 0)
						.addBox(4.6F, -5.0F, -2.9F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 7)
						.addBox(7.2F, -5.0F, 1.4F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(36, 14)
						.addBox(1.2F, -5.0F, 0.4F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 26)
						.addBox(1.0F, -4.4F, -3.8F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 14)
						.addBox(0.0F, -3.4F, -3.8F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(16, 39)
						.addBox(8.6F, -1.8F, 1.2F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 39)
						.addBox(8.6F, -2.9F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
						.addBox(1.0F, -3.4F, -4.8F, 8.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(0, 43)
						.addBox(6.6F, -3.1F, -5.4F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(40, 40)
						.addBox(1.9F, -2.3F, -5.4F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 43)
						.addBox(6.4F, -2.6F, 4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(16, 43)
						.addBox(1.9F, -2.1F, 4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(24, 43)
						.addBox(-1.0F, -2.8F, 1.3F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(44, 0)
						.addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-5.0F, 17.4F, -0.2F));

		PartDefinition ra = partdefinition.addOrReplaceChild("ra", CubeListBuilder.create().texOffs(44, 4).addBox(-1.0F,
				-3.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 2.0F));

		PartDefinition la = partdefinition.addOrReplaceChild("la", CubeListBuilder.create().texOffs(44, 7).addBox(-1.0F,
				-3.0F, 2.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -7.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		ll.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		MushroomBody.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		MushroomHead.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		ra.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		la.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.ll.zRot = Mth.cos(limbSwing * 0.6662F) * limbSwingAmount;
		this.rl.zRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * limbSwingAmount;
	}
}