/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.*;

import net.mcreator.ilovesoccer.item.VoldermoltbwoaanbdItem;
import net.mcreator.ilovesoccer.item.VeinminerItem;
import net.mcreator.ilovesoccer.item.SkillstoneItem;
import net.mcreator.ilovesoccer.IlovesoccerMod;

import java.util.function.Function;

public class IlovesoccerModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(IlovesoccerMod.MODID);
	public static final DeferredItem<Item> VOLDERMOLTHASA_BI_GNOSE;
	public static final DeferredItem<Item> VOLDERMOLTBWOAANBD;
	public static final DeferredItem<Item> VEINMINER;
	public static final DeferredItem<Item> DICE_BLOCK;
	public static final DeferredItem<Item> SKILLSTONE;
	public static final DeferredItem<Item> IDK_LOG;
	public static final DeferredItem<Item> IDK_WOOD;
	public static final DeferredItem<Item> STRIPPED_IDK_LOG;
	public static final DeferredItem<Item> STRIPPED_IDK_WOOD;
	public static final DeferredItem<Item> IDK_PLANKS;
	public static final DeferredItem<Item> IDK_LEAVES;
	public static final DeferredItem<Item> IDK_STAIRS;
	public static final DeferredItem<Item> IDK_SLAB;
	public static final DeferredItem<Item> IDK_FENCE;
	public static final DeferredItem<Item> IDK_FENCE_GATE;
	public static final DeferredItem<Item> IDK_DOOR;
	public static final DeferredItem<Item> IDK_TRAPDOOR;
	public static final DeferredItem<Item> IDK_PRESSURE_PLATE;
	public static final DeferredItem<Item> IDK_BUTTON;
	public static final DeferredItem<Item> IDK_SIGN;
	public static final DeferredItem<Item> IDK_HANGING_SIGN;
	public static final DeferredItem<Item> IDK_BOAT;
	public static final DeferredItem<Item> IDK_CHEST_BOAT;
	static {
		VOLDERMOLTHASA_BI_GNOSE = block(IlovesoccerModBlocks.VOLDERMOLTHASA_BI_GNOSE);
		VOLDERMOLTBWOAANBD = register("voldermoltbwoaanbd", VoldermoltbwoaanbdItem::new);
		VEINMINER = register("veinminer", VeinminerItem::new);
		DICE_BLOCK = block(IlovesoccerModBlocks.DICE_BLOCK);
		SKILLSTONE = register("skillstone", SkillstoneItem::new);
		IDK_LOG = block(IlovesoccerModBlocks.IDK_LOG);
		IDK_WOOD = block(IlovesoccerModBlocks.IDK_WOOD);
		STRIPPED_IDK_LOG = block(IlovesoccerModBlocks.STRIPPED_IDK_LOG);
		STRIPPED_IDK_WOOD = block(IlovesoccerModBlocks.STRIPPED_IDK_WOOD);
		IDK_PLANKS = block(IlovesoccerModBlocks.IDK_PLANKS);
		IDK_LEAVES = block(IlovesoccerModBlocks.IDK_LEAVES);
		IDK_STAIRS = block(IlovesoccerModBlocks.IDK_STAIRS);
		IDK_SLAB = block(IlovesoccerModBlocks.IDK_SLAB);
		IDK_FENCE = block(IlovesoccerModBlocks.IDK_FENCE);
		IDK_FENCE_GATE = block(IlovesoccerModBlocks.IDK_FENCE_GATE);
		IDK_DOOR = doubleBlock(IlovesoccerModBlocks.IDK_DOOR);
		IDK_TRAPDOOR = block(IlovesoccerModBlocks.IDK_TRAPDOOR);
		IDK_PRESSURE_PLATE = block(IlovesoccerModBlocks.IDK_PRESSURE_PLATE);
		IDK_BUTTON = block(IlovesoccerModBlocks.IDK_BUTTON);
		IDK_SIGN = signBlock(IlovesoccerModBlocks.IDK_SIGN, IlovesoccerModBlocks.IDK_WALL_SIGN, new Item.Properties().stacksTo(16));
		IDK_HANGING_SIGN = hangingSignBlock(IlovesoccerModBlocks.IDK_HANGING_SIGN, IlovesoccerModBlocks.IDK_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16));
		IDK_BOAT = register("idk_boat", properties -> new BoatItem(IlovesoccerModEntities.IDK_BOAT.get(), properties.stacksTo(1)));
		IDK_CHEST_BOAT = register("idk_chest_boat", properties -> new BoatItem(IlovesoccerModEntities.IDK_CHEST_BOAT.get(), properties.stacksTo(1)));
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

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block) {
		return doubleBlock(block, new Item.Properties());
	}

	private static DeferredItem<Item> doubleBlock(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new DoubleHighBlockItem(block.get(), prop), properties);
	}

	private static DeferredItem<Item> signBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock) {
		return signBlock(block, wallBlock, new Item.Properties());
	}

	private static DeferredItem<Item> signBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new SignItem(block.get(), wallBlock.get(), prop), properties);
	}

	private static DeferredItem<Item> hangingSignBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock) {
		return hangingSignBlock(block, wallBlock, new Item.Properties());
	}

	private static DeferredItem<Item> hangingSignBlock(DeferredHolder<Block, Block> block, DeferredHolder<Block, Block> wallBlock, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new HangingSignItem(block.get(), wallBlock.get(), prop), properties);
	}
}