/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cheesy.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.cheesy.CheesyMod;

import java.util.function.Function;

public class CheesyModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(CheesyMod.MODID);
	public static final DeferredBlock<Block> CHEZ;
	static {
		CHEZ = register("chez", ChezBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}