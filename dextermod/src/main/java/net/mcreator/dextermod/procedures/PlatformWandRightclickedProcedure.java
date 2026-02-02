package net.mcreator.dextermod.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.dextermod.init.DextermodModBlocks;

public class PlatformWandRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double xPos = 0;
		double zPos = 0;
		xPos = x - 1;
		zPos = z - 1;
		for (int index0 = 0; index0 < 3; index0++) {
			for (int index1 = 0; index1 < 3; index1++) {
				world.setBlock(BlockPos.containing(x + xPos, y, z + zPos), DextermodModBlocks.DEXTER_BLOCK.get().defaultBlockState(), 3);
				zPos = zPos + 1;
			}
			xPos = xPos + 1;
			zPos = z - 1;
		}
	}
}