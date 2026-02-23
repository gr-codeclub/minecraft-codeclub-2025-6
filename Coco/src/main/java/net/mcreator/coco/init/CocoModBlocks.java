/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.coco.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.coco.block.RandomDiceThingBlock;
import net.mcreator.coco.block.QuickCocoBlock;
import net.mcreator.coco.CocoMod;

import java.util.function.Function;

public class CocoModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(CocoMod.MODID);
	public static final DeferredBlock<Block> QUICK_COCO;
	public static final DeferredBlock<Block> RANDOM_DICE_THING;
	static {
		QUICK_COCO = register("quick_coco", QuickCocoBlock::new);
		RANDOM_DICE_THING = register("random_dice_thing", RandomDiceThingBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}