package net.mcreator.balthzar.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SkillStoneRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (getEntityScore("skill_warrior", entity) < 5) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You are a peasant. Skill: " + getEntityScore("skill_warrior", entity))), true);
		} else if (getEntityScore("skill_warrior", entity) < 10) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You are a warrior. Skill: " + getEntityScore("skill_warrior", entity))), true);
		} else if (getEntityScore("skill_warrior", entity) < 10) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("domain expansion malevlant shrine " + getEntityScore("skill_warrior", entity))), true);
		} else {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("domain expansion infnite void " + getEntityScore("skill_warrior", entity))), true);
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