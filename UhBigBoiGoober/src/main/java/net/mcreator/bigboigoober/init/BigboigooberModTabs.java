/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

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

import net.mcreator.bigboigoober.BigboigooberMod;

@EventBusSubscriber
public class BigboigooberModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BigboigooberMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BIGBOIGOOBER = REGISTRY.register("bigboigoober",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.bigboigoober.bigboigoober")).icon(() -> new ItemStack(Items.POISONOUS_POTATO)).displayItems((parameters, tabData) -> {
				tabData.accept(BigboigooberModBlocks.BLOCK_THINGY.get().asItem());
				tabData.accept(BigboigooberModItems.FAKE_WATER_BUCKET.get());
				tabData.accept(BigboigooberModItems.SECOND_TRY_OF_BLOCK_DISAPPEARING_THING.get());
				tabData.accept(BigboigooberModBlocks.DICE_THING.get().asItem());
				tabData.accept(BigboigooberModBlocks.SKILL_STONE_IDK.get().asItem());
			}).withSearchBar().build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(BigboigooberModItems.SECOND_TRY_OF_BLOCK_DISAPPEARING_THING.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COLORED_BLOCKS) {
			tabData.accept(BigboigooberModBlocks.DICE_THING.get().asItem());
		}
	}
}