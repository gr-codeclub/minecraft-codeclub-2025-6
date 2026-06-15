// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class model<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "model"), "main");
	private final ModelPart body;
	private final ModelPart r leg;
	private final ModelPart l leg;
	private final ModelPart r arm;
	private final ModelPart l arm;
	private final ModelPart head;

	public model(ModelPart root) {
		this.body = root.getChild("body");
		this.r leg = root.getChild("r leg");
		this.l leg = root.getChild("l leg");
		this.r arm = root.getChild("r arm");
		this.l arm = root.getChild("l arm");
		this.head = root.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -1.0F, 9.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition r leg = partdefinition.addOrReplaceChild("r leg", CubeListBuilder.create().texOffs(14, 13).addBox(-2.0F, -3.0F, -3.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 16.0F, 0.0F));

		PartDefinition l leg = partdefinition.addOrReplaceChild("l leg", CubeListBuilder.create().texOffs(0, 13).addBox(0.5F, -2.5F, -2.0F, 3.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 16.0F, 0.0F));

		PartDefinition r arm = partdefinition.addOrReplaceChild("r arm", CubeListBuilder.create().texOffs(0, 28).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 0.0F));

		PartDefinition l arm = partdefinition.addOrReplaceChild("l arm", CubeListBuilder.create().texOffs(28, 9).addBox(0.0F, -2.0F, -1.0F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 8.0F, 0.0F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		r leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		l leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		r arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		l arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}