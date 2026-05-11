/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;

@EventBusSubscriber
public class IlovesoccerModDispenseBehaviors {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(IlovesoccerModItems.IDK_BOAT.get(), new BoatDispenseItemBehavior(IlovesoccerModEntities.IDK_BOAT.get()));
			DispenserBlock.registerBehavior(IlovesoccerModItems.IDK_CHEST_BOAT.get(), new BoatDispenseItemBehavior(IlovesoccerModEntities.IDK_CHEST_BOAT.get()));
		});
	}
}