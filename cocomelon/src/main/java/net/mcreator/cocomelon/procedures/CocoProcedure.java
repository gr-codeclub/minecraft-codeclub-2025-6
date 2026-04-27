package net.mcreator.cocomelon.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.cocomelon.init.CocomelonModBlocks;

public class CocoProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double XOFFSET = 0;
		double ZOFFSET = 0;
		XOFFSET = -1;
		ZOFFSET = -1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(XOFFSET + x, y - 2, ZOFFSET + z), CocomelonModBlocks.COCOMELON_2.get().defaultBlockState(), 3);
				XOFFSET = XOFFSET + 1;
			}
			ZOFFSET = XOFFSET + 1;
			ZOFFSET = ZOFFSET + 1;
		}
	}
}