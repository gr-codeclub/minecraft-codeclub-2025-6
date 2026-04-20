/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovefootball.init;

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

import net.mcreator.ilovefootball.IlovefootballMod;

@EventBusSubscriber
public class IlovefootballModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IlovefootballMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ILOVEFOOTBALL = REGISTRY.register("ilovefootball",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.ilovefootball.ilovefootball")).icon(() -> new ItemStack(Blocks.DIAMOND_ORE)).displayItems((parameters, tabData) -> {
				tabData.accept(IlovefootballModBlocks.FOOTBALLBLOCK.get().asItem());
				tabData.accept(IlovefootballModItems.FOOTBALLSOMETHING.get());
				tabData.accept(IlovefootballModItems.FOOT.get());
				tabData.accept(IlovefootballModBlocks.DICEBLOCK.get().asItem());
				tabData.accept(IlovefootballModItems.SKILLSTONE.get());
				tabData.accept(IlovefootballModItems.SPELLBOOK.get());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(IlovefootballModItems.FOOT.get());
		}
	}
}