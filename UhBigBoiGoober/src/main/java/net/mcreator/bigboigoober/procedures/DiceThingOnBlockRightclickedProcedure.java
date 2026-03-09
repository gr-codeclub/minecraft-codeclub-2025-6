package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class DiceThingOnBlockRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double IdidntSeeWhatThisWasMeantToBeCalled = 0;
		if (!world.isClientSide()) {
			BlockPos _bp = BlockPos.containing(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null) {
				_blockEntity.getPersistentData().putDouble("rolls", (getBlockNBTNumber(world, BlockPos.containing(x, y, z), "rolls") + 1));
			}
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		IdidntSeeWhatThisWasMeantToBeCalled = Mth.nextInt(RandomSource.create(), 1, 6);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("idk?" + new java.text.DecimalFormat("##").format(IdidntSeeWhatThisWasMeantToBeCalled) + "Rolls thingy:" + getBlockNBTNumber(world, BlockPos.containing(x, y, z), "rolls"))), true);
		if (IdidntSeeWhatThisWasMeantToBeCalled == 1) {
			if (getEntityScore("idk", entity) >= 10) {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
					entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
					entityToSpawn.setVisualOnly(true);
					_level.addFreshEntity(entityToSpawn);
				}
			} else {
				if (world instanceof ServerLevel _level) {
					LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
					entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
					_level.addFreshEntity(entityToSpawn);
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 1..."), true);
		} else if (IdidntSeeWhatThisWasMeantToBeCalled == 2) {
			entity.hurt(new DamageSource(world.holderOrThrow(DamageTypes.GENERIC)), (float) 9.5);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 2..."), true);
		} else if (IdidntSeeWhatThisWasMeantToBeCalled == 3) {
			if (entity instanceof Player _player) {
				_player.getAbilities().mayfly = true;
				_player.onUpdateAbilities();
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 3..."), true);
		} else if (IdidntSeeWhatThisWasMeantToBeCalled == 4) {
			if (entity instanceof Player _player)
				_player.giveExperienceLevels(-(1000000));
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 4..."), true);
		} else if (IdidntSeeWhatThisWasMeantToBeCalled == 5) {
			if (entity instanceof Player _player)
				_player.getInventory().clearContent();
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 5..."), true);
		} else if (IdidntSeeWhatThisWasMeantToBeCalled == 6) {
			if (world instanceof ServerLevel _level) {
				Entity entityToSpawn = EntityType.ZOMBIE.spawn(_level, BlockPos.containing(x, y, z), EntitySpawnReason.MOB_SUMMONED);
				if (entityToSpawn != null) {
					entityToSpawn.setDeltaMovement(0, 0, 0);
				}
			}
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 6..."), true);
		}
	}

	private static double getBlockNBTNumber(LevelAccessor world, BlockPos pos, String tag) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity != null)
			return blockEntity.getPersistentData().getDoubleOr(tag, 0);
		return -1;
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}