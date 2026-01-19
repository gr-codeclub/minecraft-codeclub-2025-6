package net.mcreator.ilovefootball.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.ilovefootball.init.IlovefootballModBlocks;

public class FootballblockEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()), IlovefootballModBlocks.FOOTBALLBLOCK.get().defaultBlockState(), 3);
	}
}