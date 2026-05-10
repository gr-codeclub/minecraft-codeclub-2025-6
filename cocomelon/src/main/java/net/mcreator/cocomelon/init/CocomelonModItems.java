/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cocomelon.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.cocomelon.item.CheeseItem;
import net.mcreator.cocomelon.item.COCOMELON676676767Item;
import net.mcreator.cocomelon.item.COCOMELON3Item;
import net.mcreator.cocomelon.CocomelonMod;

import java.util.function.Function;

public class CocomelonModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(CocomelonMod.MODID);
	public static final DeferredItem<Item> COCOMELON_2;
	public static final DeferredItem<Item> COCOMELON_3;
	public static final DeferredItem<Item> COCOMELON_676676767;
	public static final DeferredItem<Item> COCOMELONWEPON_1;
	public static final DeferredItem<Item> HICOCOMELON;
	public static final DeferredItem<Item> CHEESE;
	static {
		COCOMELON_2 = block(CocomelonModBlocks.COCOMELON_2);
		COCOMELON_3 = register("cocomelon_3", COCOMELON3Item::new);
		COCOMELON_676676767 = register("cocomelon_676676767", COCOMELON676676767Item::new);
		COCOMELONWEPON_1 = block(CocomelonModBlocks.COCOMELONWEPON_1);
		HICOCOMELON = block(CocomelonModBlocks.HICOCOMELON);
		CHEESE = register("cheese", CheeseItem::new);
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