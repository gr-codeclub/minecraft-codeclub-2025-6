package net.mcreator.bigboigoober.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.bigboigoober.entity.ShroomEntity;
import net.mcreator.bigboigoober.client.model.ModelShroom;

public class ShroomRenderer extends MobRenderer<ShroomEntity, LivingEntityRenderState, ModelShroom> {
	private ShroomEntity entity = null;

	public ShroomRenderer(EntityRendererProvider.Context context) {
		super(context, new ModelShroom(context.bakeLayer(ModelShroom.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}

	@Override
	public void extractRenderState(ShroomEntity entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		this.entity = entity;
	}

	@Override
	public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
		return ResourceLocation.parse("bigboigoober:textures/entities/mushroomhead.png");
	}
}