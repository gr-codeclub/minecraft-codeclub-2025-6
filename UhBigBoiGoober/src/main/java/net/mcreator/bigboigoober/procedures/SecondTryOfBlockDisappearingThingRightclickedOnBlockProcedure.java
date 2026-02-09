package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class SecondTryOfBlockDisappearingThingRightclickedOnBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double zPos = 0;
		double yPos = 0;
		double xMax = 0;
		double zMax = 0;
		double yMax = 0;
		double limit = 0;
		double xMin = 0;
		double MinedCount = 0;
		double zOffset = 0;
		boolean DeletedTsRow = false;
		boolean FinishedZ = false;
		limit = 250;
		zOffset = 1;
		xPos = x + 1;
		xMin = xPos;
		xMax = xPos;
		yPos = y;
		zPos = z;
		FinishedZ = false;
		DeletedTsRow = true;
		while (DeletedTsRow) {
			DeletedTsRow = false;
			while (xPos <= xMax || (world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				if ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
					{
						BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
						world.destroyBlock(_pos, false);
					}
					DeletedTsRow = true;
					MinedCount = MinedCount + 1;
					xPos = Math.max(xPos, xMax);
				}
				xPos = xPos + 1;
				if (xPos >= limit) {
					break;
				}
			}
			xPos = x - 1;
			while (xPos >= xMin || (world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				if ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
					{
						BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
						world.destroyBlock(_pos, false);
					}
					DeletedTsRow = true;
					MinedCount = MinedCount + 1;
					xPos = Math.min(xPos, xMin);
				}
				xPos = xPos + 1;
				if (xPos >= limit) {
					break;
				}
			}
		}
	}
}