/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cheesy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.cheesy.item.ShhhItem;
import net.mcreator.cheesy.item.DaItem;
import net.mcreator.cheesy.item.CheeseHunkItem;
import net.mcreator.cheesy.CheesyMod;

import java.util.function.Function;

public class CheesyModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(CheesyMod.MODID);
	public static final DeferredItem<Item> CHEZ;
	public static final DeferredItem<Item> CHEESE_HUNK;
	public static final DeferredItem<Item> DA;
	public static final DeferredItem<Item> SHHH;
	public static final DeferredItem<Item> DICE_BLOX;
	static {
		CHEZ = block(CheesyModBlocks.CHEZ);
		CHEESE_HUNK = register("cheese_hunk", CheeseHunkItem::new);
		DA = register("da", DaItem::new);
		SHHH = register("shhh", ShhhItem::new);
		DICE_BLOX = block(CheesyModBlocks.DICE_BLOX);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}
}