/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
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
				tabData.accept(IlovesoccerModItems.BURGER.get());
			}).withSearchBar().build());
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> VOLDERMOLTSTOLEMYSHAMPO = REGISTRY.register("voldermoltstolemyshampo",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.ilovesoccer.voldermoltstolemyshampo")).icon(() -> new ItemStack(Items.TORCH)).displayItems((parameters, tabData) -> {
				tabData.accept(IlovesoccerModItems.BURGER.get());
			}).withSearchBar().withTabsBefore(VOLDERMOLTSTOLEMYSHAMPOO.getId()).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(IlovesoccerModItems.LAVACREEPER_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(IlovesoccerModItems.BURGER.get());
		}
	}
}