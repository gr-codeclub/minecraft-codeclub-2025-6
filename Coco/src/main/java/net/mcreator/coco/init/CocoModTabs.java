/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.coco.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.coco.CocoMod;

public class CocoModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CocoMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> COCO = REGISTRY.register("coco",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.coco.coco")).icon(() -> new ItemStack(Items.SALMON)).displayItems((parameters, tabData) -> {
				tabData.accept(CocoModBlocks.QUICK_COCO.get().asItem());
			}).build());
}