/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.eleazar.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.eleazar.item.SkillStoneItem;
import net.mcreator.eleazar.item.SixsevensixsevensixsevenItem;
import net.mcreator.eleazar.item.SixsevenItem;
import net.mcreator.eleazar.EleazarMod;

import java.util.function.Function;

public class EleazarModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(EleazarMod.MODID);
	public static final DeferredItem<Item> KPOPSUCKS;
	public static final DeferredItem<Item> SIXSEVEN;
	public static final DeferredItem<Item> SIXSEVENSIXSEVENSIXSEVEN;
	public static final DeferredItem<Item> DICEBLOCK;
	public static final DeferredItem<Item> SKILL_STONE;
	static {
		KPOPSUCKS = block(EleazarModBlocks.KPOPSUCKS);
		SIXSEVEN = register("sixseven", SixsevenItem::new);
		SIXSEVENSIXSEVENSIXSEVEN = register("sixsevensixsevensixseven", SixsevensixsevensixsevenItem::new);
		DICEBLOCK = block(EleazarModBlocks.DICEBLOCK);
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