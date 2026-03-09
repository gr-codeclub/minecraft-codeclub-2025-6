/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.eleazar.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.eleazar.block.KpopsucksBlock;
import net.mcreator.eleazar.block.DiceblockBlock;
import net.mcreator.eleazar.EleazarMod;

import java.util.function.Function;

public class EleazarModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(EleazarMod.MODID);
	public static final DeferredBlock<Block> KPOPSUCKS;
	public static final DeferredBlock<Block> DICEBLOCK;
	static {
		KPOPSUCKS = register("kpopsucks", KpopsucksBlock::new);
		DICEBLOCK = register("diceblock", DiceblockBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}