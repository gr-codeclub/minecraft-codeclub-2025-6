package net.mcreator.cocomelon.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class COCOMELON676676767BlockDestroyedWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPOS = 0;
		double yPOS = 0;
		double zPOS = 0;
		xPOS = x;
		zPOS = z;
		yPOS = y;
		while ((world.getBlockState(BlockPos.containing(xPOS, yPOS, zPOS))).getBlock() == blockstate.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPOS, yPOS, zPOS);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			xPOS = xPOS + 1;
		}
	}
}