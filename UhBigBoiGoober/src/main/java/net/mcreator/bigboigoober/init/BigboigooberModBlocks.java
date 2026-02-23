/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.bigboigoober.block.FakeWaterBlock;
import net.mcreator.bigboigoober.block.DiceThingBlock;
import net.mcreator.bigboigoober.block.BlockThingyBlock;
import net.mcreator.bigboigoober.BigboigooberMod;

import java.util.function.Function;

public class BigboigooberModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(BigboigooberMod.MODID);
	public static final DeferredBlock<Block> BLOCK_THINGY;
	public static final DeferredBlock<Block> FAKE_WATER;
	public static final DeferredBlock<Block> DICE_THING;
	static {
		BLOCK_THINGY = register("block_thingy", BlockThingyBlock::new);
		FAKE_WATER = register("fake_water", FakeWaterBlock::new);
		DICE_THING = register("dice_thing", DiceThingBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}