package net.mcreator.dextermod.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

import java.util.Random;

public class CastSpellProcedure {
	public static void execute(Entity entity, double x, double y, double z) {
		if (entity == null)
			return;
		int spell = getEntityScore("current_spell", entity);
		entity.setGlowingTag(false);
		switch (spell) {
			case 0:
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Hi From MCreator"), false);
				break;
			case 1:
				if (entity instanceof Player _player) {
				    _player.displayClientMessage(Component.literal("Hello World").withStyle(net.minecraft.ChatFormatting.RED, net.minecraft.ChatFormatting.BOLD), true);
				    entity.setGlowingTag(true);
				}
				break;
			case 2:
				if (entity instanceof LivingEntity _le && _le.level() instanceof ServerLevel _world)
					_world.sendParticles(ParticleTypes.CHERRY_LEAVES, x, y, z, 50, 3, 3, 3, 1);
				break;
			case 3:
				if (entity instanceof LivingEntity _le && _le.level() instanceof ServerLevel _world) {
					int clearDuration = 0; // Duration of clear in ticks (0 means clear immediately)
					int rainDuration = 20 * 60; // Duration of rain in ticks (1 minute 20 ticks a second times seconds)
					boolean isThundering = true; // Set to true to enable thunder
					boolean isRaining = true; // Set to true to enable rain
					_world.setWeatherParameters(clearDuration, rainDuration, isRaining, isThundering);
//					_world.setThunderLevel(1f); // Set thunder level (0.0 to 1.0, where 1.0 is full thunder)

					int spellMode = getEntityScore("spell_mode", entity);
					if (entity instanceof Player _player) {
						String shapeName = switch (spellMode) {
							case 1 -> "Smooth Direction";
							case 2 -> "Ring";
							case 3 -> "Cone";
							case 4 -> "Square";
							case 5 -> "Smooth Square";
							default -> "Straight Line";
						};
						_player.displayClientMessage(Component.literal("Storm shape: " + shapeName).withStyle(net.minecraft.ChatFormatting.AQUA, net.minecraft.ChatFormatting.BOLD), true);
					}
					Random r = new Random();
					for(int i = 0; i < 50; i++) {
						_world.sendParticles(ParticleTypes.END_ROD, x, y + i, z, 5, 0.2, 0, 0.2, 0);
						if (r.nextBoolean()) {
							LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_world, EntitySpawnReason.TRIGGERED);

							// Pick a shape based on the spell_mode scoreboard.
							// In game, type "/spellmode <0-5>" to switch shapes (registered in SpellModeCommand.java).
							// Anything outside 1-5 falls through to straightLineBolt via the default case.
							Vec3 target = switch (spellMode) {
								case 1 -> smoothDirectionBolt(entity, x, y, z, i, r);
								case 2 -> ringBolt(entity, x, y, z, i, r);
								case 3 -> coneBolt(entity, x, y, z, i, r);
								case 4 -> squareBolt(entity, x, y, z, i, r);
								case 5 -> smoothSquareBolt(entity, x, y, z, i, r);
								default -> straightLineBolt(entity, x, y, z, i, r);
							};

//							entityToSpawn.snapTo(x + entity.getDirection().getStepX() * r.nextInt(1, i + 2),
//									y,
//									z + entity.getDirection().getStepZ() * r.nextInt(1, i + 2));
							entityToSpawn.snapTo(Vec3.atBottomCenterOf(BlockPos.containing(target)));
							_world.addFreshEntity(entityToSpawn);

						}
					}
				}
				break;

			default:
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Unknown spell, please sneak to pick a spell"), true);
				break;
		}
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}

	// --- Lightning shape helpers ---
	// Each returns the (x, y, z) where one lightning bolt should strike.
	// They all take the same arguments so swapping them in the loop above is a one-line change.

	// Straight line in the direction the player is facing.
	// entity.getDirection() snaps to N/S/E/W only - that's why the bolts form a straight line.
	private static Vec3 straightLineBolt(Entity entity, double x, double y, double z, int i, Random r) {
		// +2 stops the bolt landing inside the player and killing them instantly.
		return new Vec3(
				x + entity.getDirection().getStepX() * r.nextInt(1, i + 2),
				y,
				z + entity.getDirection().getStepZ() * r.nextInt(1, i + 2));
	}

	// Same idea as straightLineBolt, but follows the smooth look direction so diagonal aiming works.
	private static Vec3 smoothDirectionBolt(Entity entity, double x, double y, double z, int i, Random r) {
		Vec3 forward = horizontalLook(entity);             // flat unit vector in the look direction
		double dist = r.nextInt(1, i + 2);
		return new Vec3(x + forward.x * dist, y, z + forward.z * dist);
	}

	// 360° ring around the player. Any direction, growing outward with i.
	private static Vec3 ringBolt(Entity entity, double x, double y, double z, int i, Random r) {
		double angle = r.nextDouble() * Math.PI * 2;       // any direction (0 to 360°)
		double dist = r.nextInt(1, i + 2);                 // grows outward as i grows
		return new Vec3(x + Math.cos(angle) * dist, y, z + Math.sin(angle) * dist);
	}

	// Cone in front of the player. Uses their smooth look direction (not snapped to N/S/E/W).
	private static Vec3 coneBolt(Entity entity, double x, double y, double z, int i, Random r) {
		Vec3 look = entity.getLookAngle();                 // unit vector pointing where they're looking
		double baseAngle = Math.atan2(look.z, look.x);     // convert that direction to an angle
		double spread = Math.toRadians(45);                // half-width: 45° → 90° wide cone. Try 15 for a beam!
		double angle = baseAngle + (r.nextDouble() - 0.5) * 2 * spread;
		double dist = r.nextInt(1, i + 2);
		return new Vec3(x + Math.cos(angle) * dist, y, z + Math.sin(angle) * dist);
	}

	// 30×30 square of lightning in front of the player.
	// Takes the facing direction (forward axis) and rotates it 90° to get a sideways axis.
	// Then picks a random spot inside the rectangle they form.
	private static Vec3 squareBolt(Entity entity, double x, double y, double z, int i, Random r) {
		Direction facing = entity.getDirection();          // forward axis (N/S/E/W)
		Direction sideways = facing.getClockWise();        // ←- the "rotate 90°" method

		int forward = r.nextInt(2, 31);                    // 2 to 30 blocks in front (skip 0/1 so we don't hit the player)
		int side = r.nextInt(-15, 16);                     // -15 to +15 blocks sideways (30 wide)

		return new Vec3(
				x + facing.getStepX() * forward + sideways.getStepX() * side,
				y,
				z + facing.getStepZ() * forward + sideways.getStepZ() * side);
	}

	// Same as squareBolt but follows the smooth look direction so diagonal aiming gives a diagonal wall.
	// Vec3.yRot is the smooth equivalent of Direction.getClockWise() - rotates around the vertical axis.
	private static Vec3 smoothSquareBolt(Entity entity, double x, double y, double z, int i, Random r) {
		Vec3 forward = horizontalLook(entity);             // flat unit vector in the look direction
		Vec3 sideways = forward.yRot((float) (Math.PI / 2));   // rotate 90° (Math.PI/2 radians) around the up axis

		int forwardDist = r.nextInt(2, 31);                // 2 to 30 blocks in front
		int sideDist = r.nextInt(-15, 16);                 // -15 to +15 blocks sideways

		return new Vec3(
				x + forward.x * forwardDist + sideways.x * sideDist,
				y,
				z + forward.z * forwardDist + sideways.z * sideDist);
	}

	// Flattens the look direction to the ground plane and normalizes it.
	// Without this, looking up/down would shrink the horizontal distance.
	private static Vec3 horizontalLook(Entity entity) {
		Vec3 look = entity.getLookAngle();
		return new Vec3(look.x, 0, look.z).normalize();
	}

	/*
	 * Duplicated to show the original without switch
	 * if (spell == 0) {
	 *     if (entity instanceof Player _player && !_player.level().isClientSide())
	 *         _player.displayClientMessage(Component.literal("Hi From MCreator"), false);
	 * } else if (spell == 1) {
	 *     if (entity instanceof Player _player) {
	 *         _player.displayClientMessage(Component.literal("Hello World").withStyle(net.minecraft.ChatFormatting.RED, net.minecraft.ChatFormatting.BOLD), true);
	 *         entity.setGlowingTag(true);
	 *     }
	 * } else {
	 *     if (entity instanceof Player _player && !_player.level().isClientSide())
	 *         _player.displayClientMessage(Component.literal("Unknown spell, please sneak to pick a spell"), true);
	 * }
 	 */


}