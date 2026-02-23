package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
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
		IdidntSeeWhatThisWasMeantToBeCalled = Mth.nextInt(RandomSource.create(), 1, 6);
		if (IdidntSeeWhatThisWasMeantToBeCalled == 1) {
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level, EntitySpawnReason.TRIGGERED);
				entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));;
				_level.addFreshEntity(entityToSpawn);
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
			if (entity instanceof Player _player)
				_player.getFoodData().setFoodLevel(0);
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("You Got 6..."), true);
		}
	}
}