package net.mcreator.ilovesoccer.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

public class CastProcedure {
	public static void execute(Entity entity, double x_coord, double y_coord, double z_coord) {
		if (entity == null)
			return;
		double spell = 0;
		double x = 0;
		double y = 0;
		double z = 0;
		spell = getEntityScore("custom_score", entity);
		x = x_coord;
		y = y_coord;
		z = z_coord;

		if (spell == 0) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Hi from MCreator"), false);
		} else if (spell == 1) {
			if (entity instanceof Player _player && !_player.level().isClientSide()) {
				_player.displayClientMessage(Component.literal("Hi From Java").withStyle(net.minecraft.ChatFormatting.GOLD, net.minecraft.ChatFormatting.BOLD), false);
			}
		}else if (spell == 2) {
			if (entity instanceof Player _player && _player.level() instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.FIREWORK, x, y, z, 5, 3, 3, 3, 1);


		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("crouch right now"), false);
		}

	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}