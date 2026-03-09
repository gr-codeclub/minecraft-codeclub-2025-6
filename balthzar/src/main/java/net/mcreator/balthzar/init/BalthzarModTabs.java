/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.balthzar.init;

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

import net.mcreator.balthzar.BalthzarMod;

@EventBusSubscriber
public class BalthzarModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BalthzarMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> JOEMAMA = REGISTRY.register("joemama",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.balthzar.joemama")).icon(() -> new ItemStack(Blocks.CRIMSON_STEM)).displayItems((parameters, tabData) -> {
				tabData.accept(BalthzarModBlocks.BALTHZAR.get().asItem());
				tabData.accept(BalthzarModItems.SKILL_STONE.get());
				tabData.accept(BalthzarModBlocks.BALTHAZAR_DICE.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(BalthzarModItems.TSB.get());
			tabData.accept(BalthzarModItems.HERMAN.get());
		}
	}
}