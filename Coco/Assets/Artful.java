// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class Artful<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "artful"), "main");
	private final ModelPart Head;
	private final ModelPart Torso;
	private final ModelPart lLeg;
	private final ModelPart rLeg;
	private final ModelPart lArm;
	private final ModelPart rArm;
	private final ModelPart HatBottom;
	private final ModelPart HatTop;
	private final ModelPart HatVeryTop;

	public Artful(ModelPart root) {
		this.Head = root.getChild("Head");
		this.Torso = root.getChild("Torso");
		this.lLeg = root.getChild("lLeg");
		this.rLeg = root.getChild("rLeg");
		this.lArm = root.getChild("lArm");
		this.rArm = root.getChild("rArm");
		this.HatBottom = root.getChild("HatBottom");
		this.HatTop = root.getChild("HatTop");
		this.HatVeryTop = root.getChild("HatVeryTop");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Head = partdefinition.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(24, 10).addBox(-2.0F, -5.0F, -2.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 7.0F, 0.0F));

		PartDefinition Torso = partdefinition.addOrReplaceChild("Torso", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -3.0F, -2.0F, 7.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 10.0F, 0.0F));

		PartDefinition lLeg = partdefinition.addOrReplaceChild("lLeg", CubeListBuilder.create().texOffs(28, 28).addBox(-2.0F, -1.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 16.0F, 0.0F));

		PartDefinition rLeg = partdefinition.addOrReplaceChild("rLeg", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -1.0F, -1.0F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 16.0F, 0.0F));

		PartDefinition lArm = partdefinition.addOrReplaceChild("lArm", CubeListBuilder.create().texOffs(12, 31).addBox(-3.0F, -1.0F, -1.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 8.0F, 0.0F));

		PartDefinition rArm = partdefinition.addOrReplaceChild("rArm", CubeListBuilder.create().texOffs(24, 40).addBox(0.0F, -1.0F, -1.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 8.0F, 0.0F));

		PartDefinition HatBottom = partdefinition.addOrReplaceChild("HatBottom", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 9.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, 0.0F));

		PartDefinition HatTop = partdefinition.addOrReplaceChild("HatTop", CubeListBuilder.create().texOffs(28, 20).addBox(-2.0F, -2.0F, -2.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 1.0F, 0.0F));

		PartDefinition HatVeryTop = partdefinition.addOrReplaceChild("HatVeryTop", CubeListBuilder.create().texOffs(0, 23).addBox(-3.0F, -1.0F, -3.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Torso.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		lArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		HatBottom.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		HatTop.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		HatVeryTop.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}