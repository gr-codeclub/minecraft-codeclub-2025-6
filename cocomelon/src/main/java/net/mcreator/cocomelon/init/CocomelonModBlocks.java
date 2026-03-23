/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.cocomelon.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import net.mcreator.cocomelon.block.HicocomelonBlock;
import net.mcreator.cocomelon.block.Cocomelonwepon1Block;
import net.mcreator.cocomelon.block.Cocomelon2Block;
import net.mcreator.cocomelon.CocomelonMod;

import java.util.function.Function;

public class CocomelonModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(CocomelonMod.MODID);
	public static final DeferredBlock<Block> COCOMELON_2;
	public static final DeferredBlock<Block> COCOMELONWEPON_1;
	public static final DeferredBlock<Block> HICOCOMELON;
	static {
		COCOMELON_2 = register("cocomelon_2", Cocomelon2Block::new);
		COCOMELONWEPON_1 = register("cocomelonwepon_1", Cocomelonwepon1Block::new);
		HICOCOMELON = register("hicocomelon", HicocomelonBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}