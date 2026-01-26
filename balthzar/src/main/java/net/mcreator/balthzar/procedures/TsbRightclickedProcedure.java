package net.mcreator.balthzar.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.balthzar.init.BalthzarModBlocks;

public class TsbRightclickedProcedure {
	public static void execute(LevelAccessor world, double y) {
		double xoffset = 0;
		double zoffset = 0;
		xoffset = -1;
		zoffset = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(xoffset + 1, y + 2, zoffset), BalthzarModBlocks.BALTHZAR.get().defaultBlockState(), 3);
				zoffset = xoffset + 1;
			}
			xoffset = xoffset + 1;
			zoffset = zoffset + 1;
		}
	}
}