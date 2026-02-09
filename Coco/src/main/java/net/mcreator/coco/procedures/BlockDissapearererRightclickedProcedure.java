package net.mcreator.coco.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class BlockDissapearererRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		boolean deletedThisRow = false;
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		double xMax = 0;
		xPos = x;
		yPos = y;
		zPos = z;
		while (blockstate.getBlock() == (world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			xPos = xPos + 1;
		}
		xPos = xPos - 1;
		while (blockstate.getBlock() == (world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			xPos = xPos - 1;
		}
	}
}