/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.eleazar.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.eleazar.EleazarMod;

@EventBusSubscriber
public class EleazarModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EleazarMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ELEAZAR = REGISTRY.register("eleazar",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.eleazar.eleazar")).icon(() -> new ItemStack(Blocks.AMETHYST_BLOCK)).displayItems((parameters, tabData) -> {
				tabData.accept(EleazarModBlocks.KPOPSUCKS.get().asItem());
				tabData.accept(EleazarModBlocks.DICEBLOCK.get().asItem());
				tabData.accept(EleazarModItems.SKILL_STONE.get());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(EleazarModItems.SIXSEVEN.get());
			tabData.accept(EleazarModItems.SIXSEVENSIXSEVENSIXSEVEN.get());
		}
	}
}