/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.coco.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.coco.item.WandItem;
import net.mcreator.coco.item.SkillStoneItem;
import net.mcreator.coco.item.BlockDissapearererItem;
import net.mcreator.coco.CocoMod;

import java.util.function.Function;

public class CocoModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(CocoMod.MODID);
	public static final DeferredItem<Item> QUICK_COCO;
	public static final DeferredItem<Item> WAND;
	public static final DeferredItem<Item> BLOCK_DISSAPEARERER;
	public static final DeferredItem<Item> RANDOM_DICE_THING;
	public static final DeferredItem<Item> SKILL_STONE;
	static {
		QUICK_COCO = block(CocoModBlocks.QUICK_COCO);
		WAND = register("wand", WandItem::new);
		BLOCK_DISSAPEARERER = register("block_dissapearerer", BlockDissapearererItem::new);
		RANDOM_DICE_THING = block(CocoModBlocks.RANDOM_DICE_THING);
		SKILL_STONE = register("skill_stone", SkillStoneItem::new);
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