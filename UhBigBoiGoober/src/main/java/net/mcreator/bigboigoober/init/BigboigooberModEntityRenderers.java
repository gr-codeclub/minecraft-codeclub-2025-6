/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.bigboigoober.client.renderer.ShroomRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class BigboigooberModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BigboigooberModEntities.SHROOM.get(), ShroomRenderer::new);
	}
}