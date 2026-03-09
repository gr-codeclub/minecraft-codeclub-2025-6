/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.bigboigoober.client.model.ModelShroom;

@EventBusSubscriber(Dist.CLIENT)
public class BigboigooberModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelShroom.LAYER_LOCATION, ModelShroom::createBodyLayer);
	}
}