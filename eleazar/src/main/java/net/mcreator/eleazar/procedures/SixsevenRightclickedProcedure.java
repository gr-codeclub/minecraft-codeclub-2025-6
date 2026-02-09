package net.mcreator.eleazar.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.eleazar.init.EleazarModBlocks;

public class SixsevenRightclickedProcedure {
	public static void execute(LevelAccessor world, double y, double z) {
		boolean deletedthisrow = false;
		double xoffset = 0;
		double zoffset = 0;
		double minedcount = 0;
		double xMax = 0;
		double xPos = 0;
		double max = 0;
		xoffset = -1;
		zoffset = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(y - 2, 0, z), EleazarModBlocks.KPOPSUCKS.get().defaultBlockState(), 3);
				xoffset = xoffset + 1;
			}
			xoffset = -1;
			xoffset = xoffset + 1;
		}
		while (deletedthisrow) {
			minedcount = minedcount + 1;
			xMax = xPos + max + xMax;
		}
	}
}