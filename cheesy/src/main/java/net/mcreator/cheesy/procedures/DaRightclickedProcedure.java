package net.mcreator.cheesy.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class DaRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y) {
		double xoffset = 0;
		double zoffset = 0;
		xoffset = -1;
		zoffset = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(xoffset + x, y - 2, xoffset), Blocks.WARPED_HYPHAE.defaultBlockState(), 3);
			}
		}
	}
}