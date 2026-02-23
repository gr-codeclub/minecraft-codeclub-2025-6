package net.mcreator.cheesy.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class DiceBloxOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double roll = 0;
		roll = Mth.nextInt(RandomSource.create(), 1, 6);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("You rolled" + new java.text.DecimalFormat("##").format(roll))), false);
		if (roll == 1) {
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
				entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(entity.getX(), entity.getY(), entity.getZ())));;
				_level.addFreshEntity(entityToSpawn);
			}
		} else if (roll == 2) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.AXOLOTL.spawn(_level, BlockPos.containing(x, y + 1, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (roll == 3) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.ZOMBIE.spawn(_level, BlockPos.containing(x + 2, y, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (roll == 4) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.ARMADILLO.spawn(_level, BlockPos.containing(x, y + 1, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else if (roll == 5) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.BEE.spawn(_level, BlockPos.containing(x, y + 1, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
				}
			}
		} else {
			for (int index0 = 0; index0 < 10; index0++) {
				if (world instanceof ServerLevel _level)
					_level.addFreshEntity(new ExperienceOrb(_level, (Mth.nextInt(RandomSource.create(), -3, 3) + x), (Mth.nextInt(RandomSource.create(), -3, 3) + y), (Mth.nextInt(RandomSource.create(), -3, 3) + z), 2));
			}
		}
	}
}