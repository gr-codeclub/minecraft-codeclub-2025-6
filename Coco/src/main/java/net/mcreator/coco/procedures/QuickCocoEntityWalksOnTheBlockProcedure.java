package net.mcreator.coco.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.coco.init.CocoModBlocks;

public class QuickCocoEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing(x + (entity.getDirection()).getStepX(), y + 0, z + (entity.getDirection()).getStepZ()), CocoModBlocks.QUICK_COCO.get().defaultBlockState(), 3);
	}
}