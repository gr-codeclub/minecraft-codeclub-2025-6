/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.ilovesoccer.block.VoldermolthasaBIGnoseBlock;
import net.mcreator.ilovesoccer.IlovesoccerMod;

import java.util.function.Function;

public class IlovesoccerModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(IlovesoccerMod.MODID);
	public static final DeferredBlock<Block> VOLDERMOLTHASA_BI_GNOSE;
	static {
		VOLDERMOLTHASA_BI_GNOSE = register("voldermolthasa_bi_gnose", VoldermolthasaBIGnoseBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}