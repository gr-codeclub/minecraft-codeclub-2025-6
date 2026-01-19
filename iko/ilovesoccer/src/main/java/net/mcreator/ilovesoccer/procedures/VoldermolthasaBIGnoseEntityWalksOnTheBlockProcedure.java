package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import net.mcreator.ilovesoccer.init.IlovesoccerModBlocks;

public class VoldermolthasaBIGnoseEntityWalksOnTheBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing(x + (entity.getDirection()).getStepX(), y, z + (entity.getDirection()).getStepZ()), IlovesoccerModBlocks.VOLDERMOLTHASA_BI_GNOSE.get().defaultBlockState(), 3);
	}
}