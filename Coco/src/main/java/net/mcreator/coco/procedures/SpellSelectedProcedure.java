package net.mcreator.coco.procedures;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

public class SpellSelectedProcedure {
	public static void execute(Entity entity, double X_Coord, double Y_Coord, double Z_Coord) {
		if ( entity == null)
			return;
		Entity Caster = null;
		double X = 0;
		double Y = 0;
		double Z = 0;
		double Spell = 0;
		Caster = entity;
		X = X_Coord;
		Y = Y_Coord;
		Z = Z_Coord;
		Spell = getEntityScore("custom_score", entity);
		entity.setGlowingTag(false);

		if (Spell == 0) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Team c00lkidd. Join today!"), false);
		} else if (Spell == 1) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Tem bluudud. Get inn noww!"), true);
		} else if (Spell == 2) {
			if (entity instanceof Player _player && _player.level() instanceof ServerLevel _world) {
				_world.sendParticles(ParticleTypes.CHERRY_LEAVES, X, Y ,Z , 50, 3, 3, 3, 1);
				_player.displayClientMessage(Component.literal("Flowers").withStyle(ChatFormatting.LIGHT_PURPLE,
						ChatFormatting.ITALIC),	true);
				entity.setGlowingTag(true);
			}
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal("Unknown spell. Please sneak to choose a spell."), false);
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