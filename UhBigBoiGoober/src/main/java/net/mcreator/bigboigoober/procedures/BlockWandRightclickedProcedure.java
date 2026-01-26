package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlockWandRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xDirection = 0;
		double yDirection = 0;
		double zDirection = 0;
		zDirection = -1;
		xDirection = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(xDirection + x, y - 1, zDirection + z), Blocks.ICE.defaultBlockState(), 3);
				xDirection = xDirection + 1;
			}
			zDirection = zDirection + 1;
			xDirection = -1;
		}
	}
}