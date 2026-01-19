/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.testmod.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.testmod.TestmodMod;

public class TestmodModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestmodMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = REGISTRY.register("test_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.testmod.test_tab")).icon(() -> new ItemStack(Blocks.AMETHYST_CLUSTER)).displayItems((parameters, tabData) -> {
				tabData.accept(TestmodModBlocks.DEXTER_BLOCK.get().asItem());
			}).withSearchBar().build());
}