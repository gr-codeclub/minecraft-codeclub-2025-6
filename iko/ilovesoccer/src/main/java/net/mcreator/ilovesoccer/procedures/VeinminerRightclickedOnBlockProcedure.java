package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class VeinminerRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		xPos = x;
		yPos = y;
		zPos = z;
		while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			xPos = xPos + 1;
			xPos = x;
			xPos = xPos - 1;
			while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				zPos = zPos + 1;
				{
					BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
					Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
					world.destroyBlock(_pos, false);
				}
				zPos = x;
				zPos = zPos - 1;
			}
			while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				zPos = zPos - 1;
				{
					BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
					Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
					world.destroyBlock(_pos, false);
				}
			}
			zPos = z;
			xPos = xPos + 1;
		}
	}
}