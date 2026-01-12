package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.bigboigoober.init.BigboigooberModBlocks;

public class BlockThingyEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double y, Entity entity) {
		if (entity == null)
			return;
		if (true) {
			world.setBlock(BlockPos.containing(entity.getDeltaMovement().x() + 1, y, entity.getDeltaMovement().z() + 1), BigboigooberModBlocks.BLOCK_THINGY.get().defaultBlockState(), 3);
		}
	}
}