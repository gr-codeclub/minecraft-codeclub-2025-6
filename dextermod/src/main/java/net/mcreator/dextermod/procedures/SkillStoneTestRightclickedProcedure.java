package net.mcreator.dextermod.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SkillStoneTestRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double warriorScore = 0;
		String warrior_level = "";
		warriorScore = getEntityScore("skill_warrior", entity);
		if (warriorScore < 5) {
			warrior_level = "humble peasant.";
		} else if (warriorScore < 10) {
			warrior_level = "Fighter!";
		} else if (warriorScore < 20) {
			warrior_level = "WARRIOR.";
		} else {
			warrior_level = "CHAMPION!";
		}
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal(("You are a " + warrior_level + "Level: " + warriorScore)), true);
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}