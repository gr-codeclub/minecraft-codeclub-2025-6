package net.mcreator.eleazar.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.eleazar.init.EleazarModBlocks;

public class KpopsucksEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()), EleazarModBlocks.KPOPSUCKS.get().defaultBlockState(), 3);
	}
}