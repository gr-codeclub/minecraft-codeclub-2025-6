package net.mcreator.dextermod.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

public class AnikaaaProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String test = "";
		if (getEntityScore("currant_spell", entity) == 1) {
			test = "greet";
		} else if (getEntityScore("currant_spell", entity) == 2) {
			test = "greeting";
		} else {
			test = "unknown";
		}
		return test;
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}