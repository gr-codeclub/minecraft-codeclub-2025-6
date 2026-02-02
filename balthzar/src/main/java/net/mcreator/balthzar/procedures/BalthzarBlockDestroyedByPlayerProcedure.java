package net.mcreator.balthzar.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BalthzarBlockDestroyedByPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		xPos = x;
		yPos = y;
		zPos = z;
		while ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == blockstate.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, xPos, xPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			xPos = xPos + 1;
		}
	}
}