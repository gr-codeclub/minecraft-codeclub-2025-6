package net.mcreator.dextermod.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

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