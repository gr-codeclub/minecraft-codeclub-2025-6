/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.balthzar.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.balthzar.block.BalthzarBlock;
import net.mcreator.balthzar.BalthzarMod;

import java.util.function.Function;

public class BalthzarModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(BalthzarMod.MODID);
	public static final DeferredBlock<Block> BALTHZAR;
	public static final DeferredBlock<Block> BALTHAZAR_DICE;
	static {
		BALTHZAR = register("balthzar", BalthzarBlock::new);
		BALTHAZAR_DICE = register("balthazar_dice", BalthazarDiceBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}