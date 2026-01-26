/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cocomelon.init;

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

import net.mcreator.cocomelon.CocomelonMod;

@EventBusSubscriber
public class CocomelonModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CocomelonMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COCOMELON_1 = REGISTRY.register("cocomelon_1",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.cocomelon.cocomelon_1")).icon(() -> new ItemStack(Blocks.TNT)).displayItems((parameters, tabData) -> {
				tabData.accept(CocomelonModBlocks.COCOMELON_2.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(CocomelonModItems.COCOMELON_3.get());
			tabData.accept(CocomelonModItems.COCOMELON_676676767.get());
		}
	}
}