/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.coco.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.coco.client.model.ModelArtfulHelmet;

@EventBusSubscriber(Dist.CLIENT)
public class CocoModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelArtfulHelmet.LAYER_LOCATION, ModelArtfulHelmet::createBodyLayer);
	}
}