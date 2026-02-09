package net.mcreator.ilovefootball.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class FootRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double zPos = 0;
		double yPos = 0;
		xPos = x;
		yPos = y;
		zPos = z - 5;
		for (int index0 = 0; index0 < 10; index0++) {
			while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				{
					BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
					Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
					world.destroyBlock(_pos, false);
				}
				xPos = xPos + 1;
			}
			xPos = x - 1;
			while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				{
					BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
					Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
					world.destroyBlock(_pos, false);
				}
				xPos = xPos - 1;
			}
			zPos = zPos + 1;
			xPos = x;
		}
	}
}