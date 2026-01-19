/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.bigboigoober.BigboigooberMod;

public class BigboigooberModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BigboigooberMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BIGBOIGOOBER = REGISTRY.register("bigboigoober",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.bigboigoober.bigboigoober")).icon(() -> new ItemStack(Items.POISONOUS_POTATO)).displayItems((parameters, tabData) -> {
				tabData.accept(BigboigooberModBlocks.BLOCK_THINGY.get().asItem());
				tabData.accept(BigboigooberModItems.FAKE_WATER_BUCKET.get());
			}).withSearchBar().build());
}