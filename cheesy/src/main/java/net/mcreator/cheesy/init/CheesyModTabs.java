/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cheesy.init;

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

import net.mcreator.cheesy.CheesyMod;

@EventBusSubscriber
public class CheesyModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CheesyMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CHEESE = REGISTRY.register("cheese",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.cheesy.cheese")).icon(() -> new ItemStack(Blocks.RAW_GOLD_BLOCK)).displayItems((parameters, tabData) -> {
				tabData.accept(CheesyModBlocks.CHEZ.get().asItem());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(CheesyModItems.DA.get());
		}
	}
}