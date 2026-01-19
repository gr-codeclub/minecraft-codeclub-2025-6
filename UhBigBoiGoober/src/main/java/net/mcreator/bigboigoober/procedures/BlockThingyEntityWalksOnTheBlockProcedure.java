package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.bigboigoober.init.BigboigooberModBlocks;

public class BlockThingyEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing((entity.getDirection()).getStepX() + x, y, (entity.getDirection()).getStepZ() + z), BigboigooberModBlocks.BLOCK_THINGY.get().defaultBlockState(), 3);
	}
}