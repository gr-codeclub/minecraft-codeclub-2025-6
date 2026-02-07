package net.mcreator.dextermod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

public class VeinMinerBlockDestroyedWithToolProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		boolean deletedThisRow = false;
		boolean finishedZ = false;
		double zPos = 0;
		double yPos = 0;
		double xPos = 0;
		double xMin = 0;
		double xMax = 0;
		double minedCount = 0;
		double limit = 0;
		double zOffset = 0;
		limit = 100;
		zOffset = 1;
		xPos = x + 1;
		xMin = xPos;
		xMax = xPos;
		yPos = y;
		zPos = z;
		minedCount = 1;
		finishedZ = false;
		deletedThisRow = true;
		while (deletedThisRow) {
			deletedThisRow = false;
			while (xPos <= xMax || (world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
				if ((world.getBlockState(BlockPos.containing(xPos, yPos, zPos))).getBlock() == blockstate.getBlock()) {
					{
						BlockPos _pos = BlockPos.containing(xPos, yPos, zPos);
						Block.dropResources(world.getBlockState(_pos), world, BlockPos.containing(x, y, z), null);
						world.destroyBlock(_pos, false);
					}
					deletedThisRow = true;
					minedCount = minedCount + 1;
					xMax = Math.max(xPos, xMax);
				}
				xPos = xPos + 1;
				if (minedCount >= limit) {
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
					deletedThisRow = true;
					minedCount = minedCount + 1;
					xMin = Math.min(xPos, xMin);
				}
				xPos = xPos - 1;
				if (minedCount >= limit) {
					break;
				}
			}
			if (!finishedZ && !deletedThisRow) {
				deletedThisRow = true;
				finishedZ = true;
				zPos = z;
				zOffset = -1;
			}
			if (minedCount >= limit) {
				break;
			}
			xPos = x;
			zPos = zPos + zOffset;
		}
		if (world instanceof ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Mined" + new java.text.DecimalFormat(" ## ").format(minedCount) + BuiltInRegistries.BLOCK.getKey(blockstate.getBlock()).toString())).withColor(0x0066cc),
					false);
		}
	}
}