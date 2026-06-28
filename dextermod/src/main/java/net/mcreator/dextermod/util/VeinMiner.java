package net.mcreator.dextermod.util;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Vein miner helper.
 *
 * Given a starting block, walks outward to find every connected block of the same
 * type and (optionally) breaks them all.
 *
 * Strategy: BFS (Breadth-First Search). Think of spreading ink from the start
 * block — each step, the ink leaks into neighbouring blocks of the same type.
 * We keep a queue of blocks to visit and a set of blocks we've already seen,
 * so we never loop forever.
 *
 * If diagonals = false, neighbours are the 6 face-adjacent blocks (up, down,
 * N, S, E, W). If diagonals = true, neighbours are all 26 surrounding cells
 * (faces + edges + corners) — useful for catching ore veins that touch only
 * at corners.
 */
public class VeinMiner {

	public static final int DEFAULT_MAX_BLOCKS = 100;

	private static final BlockPos[] CARDINAL_OFFSETS;
	private static final BlockPos[] DIAGONAL_OFFSETS;

	static {
		CARDINAL_OFFSETS = new BlockPos[6];
		int i = 0;
		for (Direction d : Direction.values()) {
			CARDINAL_OFFSETS[i++] = new BlockPos(d.getStepX(), d.getStepY(), d.getStepZ());
		}

		BlockPos[] diag = new BlockPos[26];
		int j = 0;
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				for (int dz = -1; dz <= 1; dz++) {
					if (dx != 0 || dy != 0 || dz != 0) {
						diag[j++] = new BlockPos(dx, dy, dz);
					}
				}
			}
		}
		DIAGONAL_OFFSETS = diag;
	}

	/**
	 * Find every block of the same type connected to {@code start}, up to {@code maxBlocks}.
	 * Returns the positions found (including the start position itself).
	 */
	public static Set<BlockPos> findVein(ServerLevel world, BlockPos start, int maxBlocks, boolean diagonals) {
		Block target = world.getBlockState(start).getBlock();
		Set<BlockPos> found = new HashSet<>();
		Queue<BlockPos> frontier = new ArrayDeque<>();
		BlockPos[] offsets = diagonals ? DIAGONAL_OFFSETS : CARDINAL_OFFSETS;

		frontier.add(start);
		found.add(start);

		while (!frontier.isEmpty() && found.size() < maxBlocks) {
			BlockPos current = frontier.poll();
			for (BlockPos off : offsets) {
				if (found.size() >= maxBlocks) {
					break;
				}
				BlockPos neighbour = current.offset(off);
				if (found.contains(neighbour)) {
					continue;
				}
				if (world.getBlockState(neighbour).getBlock() == target) {
					found.add(neighbour);
					frontier.add(neighbour);
				}
			}
		}
		return found;
	}

	/**
	 * Find every block in the vein and break them all. Each block drops its resources
	 * at its own position (not piled up at the start). Returns the number broken.
	 */
	public static int mineVein(ServerLevel world, BlockPos start, int maxBlocks, boolean diagonals) {
		BlockState startState = world.getBlockState(start);
		if (startState.isAir()) {
			return 0;
		}

		Set<BlockPos> vein = findVein(world, start, maxBlocks, diagonals);
		for (BlockPos pos : vein) {
			BlockState state = world.getBlockState(pos);
			Block.dropResources(state, world, pos);
			world.destroyBlock(pos, false);
		}
		return vein.size();
	}
}
