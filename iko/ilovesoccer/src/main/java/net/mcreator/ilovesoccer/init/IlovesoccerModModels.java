/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.BoatModel;

@EventBusSubscriber(Dist.CLIENT)
public class IlovesoccerModModels {
	public static final ModelLayerLocation IDK_BOAT_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.parse("ilovesoccer:boat/idk_boat"), "main");
	public static final ModelLayerLocation IDK_CHEST_BOAT_LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.parse("ilovesoccer:chest_boat/idk_chest_boat"), "main");

	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(IDK_BOAT_LAYER_LOCATION, BoatModel::createBoatModel);
		event.registerLayerDefinition(IDK_CHEST_BOAT_LAYER_LOCATION, BoatModel::createChestBoatModel);
	}
}