package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SkillstoneRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double issueskill = 0;
		issueskill = getEntityScore("skill_issue", entity);
		if (issueskill < 5) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("you are a humble peasant. skill issue " + issueskill)), true);
		} else if (issueskill < 10) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("you are a just a slave. less of a skill issue " + issueskill)), true);
		} else if (issueskill < 15) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("you are the owner of a slave. no more skill issues " + issueskill)), true);
		} else if (issueskill < 20) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("you are the owner of all people. " + issueskill)), true);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("YOU ARE THE SIGMA OF ALL " + issueskill)), true);
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