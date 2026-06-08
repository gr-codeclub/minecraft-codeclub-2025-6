package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

public class LavacreeperPlayerCollidesWithThisEntityProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		world.setBlock(BlockPos.containing(entity.getX(), entity.getY() + 2, entity.getZ()), Blocks.LAVA.defaultBlockState(), 3);
	}
}