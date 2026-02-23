/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovefootball.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.ilovefootball.block.FootballblockBlock;
import net.mcreator.ilovefootball.IlovefootballMod;

import java.util.function.Function;

public class IlovefootballModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(IlovefootballMod.MODID);
	public static final DeferredBlock<Block> FOOTBALLBLOCK;
	public static final DeferredBlock<Block> DICEBLOCK;
	static {
		FOOTBALLBLOCK = register("footballblock", FootballblockBlock::new);
		DICEBLOCK = register("diceblock", DiceblockBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}