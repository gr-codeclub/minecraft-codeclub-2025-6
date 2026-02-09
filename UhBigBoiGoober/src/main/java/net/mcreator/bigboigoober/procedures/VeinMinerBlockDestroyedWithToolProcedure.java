package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

public class VeinMinerBlockDestroyedWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		double mined = 0;
		mined = 1;
		xPos = x + 1;
		yPos = y;
		zPos = z;
		while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			mined = mined + 1;
			xPos = xPos + 1;
			if (mined > 200) {
				break;
			}
		}
		xPos = x - 1;
		while ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
			{
				BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
				Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
				world.destroyBlock(_pos, false);
			}
			mined = mined + 1;
			xPos = xPos - 1;
			if (mined > 200) {
				break;
			}
		}
	}
}