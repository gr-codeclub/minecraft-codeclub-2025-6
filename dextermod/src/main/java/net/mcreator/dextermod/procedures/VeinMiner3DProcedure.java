package net.mcreator.dextermod.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;

import net.mcreator.dextermod.DextermodMod;

public class VeinMiner3DProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		double xPos = 0;
		double yPos = 0;
		double zPos = 0;
		BlockState blockState = Blocks.AIR.defaultBlockState();
		xPos = x;
		yPos = y;
		zPos = z;
		blockState = blockstate;// Access dependencies to force MCreator to include them
		double _x = xPos;
		double _y = yPos;
		double _z = zPos;
		net.minecraft.world.level.block.state.BlockState _blockstate = blockState;
		// 3D BFS Vein Miner Implementation
		final int BLOCK_LIMIT = 100;
		final net.minecraft.core.BlockPos dropLocation = net.minecraft.core.BlockPos.containing(_x, _y, _z);
		final net.minecraft.core.BlockPos startPos = dropLocation;
		final net.minecraft.world.level.block.Block targetBlock = _blockstate.getBlock();
		java.util.Queue<net.minecraft.core.BlockPos> queue = new java.util.ArrayDeque<>();
		java.util.Set<net.minecraft.core.BlockPos> visited = new java.util.HashSet<>();
		queue.add(startPos);
		visited.add(startPos);
		int minedCount = 1;
		net.minecraft.core.BlockPos[] directions = new net.minecraft.core.BlockPos[]{new net.minecraft.core.BlockPos(0, 1, 0), new net.minecraft.core.BlockPos(0, -1, 0), new net.minecraft.core.BlockPos(1, 0, 0),
				new net.minecraft.core.BlockPos(-1, 0, 0), new net.minecraft.core.BlockPos(0, 0, 1), new net.minecraft.core.BlockPos(0, 0, -1)};
		while (!queue.isEmpty() && minedCount < BLOCK_LIMIT) {
			net.minecraft.core.BlockPos current = queue.poll();
			for (net.minecraft.core.BlockPos dir : directions) {
				net.minecraft.core.BlockPos neighbor = current.offset(dir);
				if (visited.contains(neighbor)) {
					continue;
				}
				visited.add(neighbor);
				net.minecraft.world.level.block.state.BlockState neighborState = world.getBlockState(neighbor);
				if (neighborState.getBlock() == targetBlock) {
					net.minecraft.world.level.block.Block.dropResources(neighborState, world, dropLocation, null);
					world.destroyBlock(neighbor, false);
					queue.add(neighbor);
					minedCount++;
					if (minedCount >= BLOCK_LIMIT) {
						break;
					}
				}
			}
		}
		if (world instanceof net.minecraft.server.level.ServerLevel _level) {
			_level.getServer().getPlayerList().broadcastSystemMessage(
					net.minecraft.network.chat.Component.literal(("[3D BFS] Mined " + minedCount + " " + net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(_blockstate.getBlock()).toString())).withColor(0x00FF00), false);
		}
		DextermodMod.LOGGER.info((new java.text.DecimalFormat("##.##").format(world.dayTime())));
	}
}