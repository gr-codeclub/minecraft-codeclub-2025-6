/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.BoatRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class IlovesoccerModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(IlovesoccerModEntities.IDK_BOAT.get(), context -> new BoatRenderer(context, IlovesoccerModModels.IDK_BOAT_LAYER_LOCATION));
		event.registerEntityRenderer(IlovesoccerModEntities.IDK_CHEST_BOAT.get(), context -> new BoatRenderer(context, IlovesoccerModModels.IDK_CHEST_BOAT_LAYER_LOCATION));
	}
}