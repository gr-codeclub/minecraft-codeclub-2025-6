/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.dextermod.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.dextermod.DextermodMod;

public class DextermodModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DextermodMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEXTER_MOD = REGISTRY.register("dexter_mod",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.dextermod.dexter_mod")).icon(() -> new ItemStack(Items.LAPIS_LAZULI)).displayItems((parameters, tabData) -> {
				tabData.accept(DextermodModBlocks.DEXTER_BLOCK.get().asItem());
			}).withSearchBar().build());
}