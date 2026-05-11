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
				tabData.accept(IlovesoccerModBlocks.DICE_BLOCK.get().asItem());
				tabData.accept(IlovesoccerModItems.SKILLSTONE.get());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(IlovesoccerModBlocks.IDK_LOG.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_WOOD.get().asItem());
			tabData.accept(IlovesoccerModBlocks.STRIPPED_IDK_LOG.get().asItem());
			tabData.accept(IlovesoccerModBlocks.STRIPPED_IDK_WOOD.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_PLANKS.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_STAIRS.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_SLAB.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_FENCE.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_FENCE_GATE.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_DOOR.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_TRAPDOOR.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_PRESSURE_PLATE.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_BUTTON.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			tabData.accept(IlovesoccerModBlocks.IDK_LEAVES.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(IlovesoccerModBlocks.IDK_SIGN.get().asItem());
			tabData.accept(IlovesoccerModBlocks.IDK_HANGING_SIGN.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(IlovesoccerModItems.IDK_BOAT.get());
			tabData.accept(IlovesoccerModItems.IDK_CHEST_BOAT.get());
		}
	}
}