/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.ilovesoccer.IlovesoccerMod;

@EventBusSubscriber
public class IlovesoccerModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IlovesoccerMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VOLDERMOLTSTOLEMYSHAMPOO = REGISTRY.register("voldermoltstolemyshampoo",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.ilovesoccer.voldermoltstolemyshampoo")).icon(() -> new ItemStack(IlovesoccerModBlocks.VOLDERMOLTHASA_BI_GNOSE.get())).displayItems((parameters, tabData) -> {
				tabData.accept(IlovesoccerModBlocks.VOLDERMOLTHASA_BI_GNOSE.get().asItem());
				tabData.accept(IlovesoccerModItems.VOLDERMOLTBWOAANBD.get());
				tabData.accept(IlovesoccerModItems.VEINMINER.get());
				tabData.accept(IlovesoccerModItems.SKILLSTONE.get());
				tabData.accept(IlovesoccerModItems.SPELLBOOK.get());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(IlovesoccerModItems.LAVACREEPER_SPAWN_EGG.get());
		}
	}
}