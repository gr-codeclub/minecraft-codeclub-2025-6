package net.mcreator.coco.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class WandRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xOffset = 0;
		double zOffset = 0;
		xOffset = -1;
		zOffset = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(xOffset + x, y - 2, xOffset + z), Blocks.FROSTED_ICE.defaultBlockState(), 3);
				xOffset = xOffset + 1;
			}
			xOffset = -1;
			xOffset = zOffset + 1;
		}
	}
}