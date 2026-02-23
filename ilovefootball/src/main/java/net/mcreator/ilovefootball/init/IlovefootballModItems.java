/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovefootball.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.ilovefootball.item.FootballsomethingItem;
import net.mcreator.ilovefootball.item.FootItem;
import net.mcreator.ilovefootball.IlovefootballMod;

import java.util.function.Function;

public class IlovefootballModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(IlovefootballMod.MODID);
	public static final DeferredItem<Item> FOOTBALLBLOCK;
	public static final DeferredItem<Item> FOOTBALLSOMETHING;
	public static final DeferredItem<Item> FOOT;
	public static final DeferredItem<Item> DICEBLOCK;
	static {
		FOOTBALLBLOCK = block(IlovefootballModBlocks.FOOTBALLBLOCK);
		FOOTBALLSOMETHING = register("footballsomething", FootballsomethingItem::new);
		FOOT = register("foot", FootItem::new);
		DICEBLOCK = block(IlovefootballModBlocks.DICEBLOCK);
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