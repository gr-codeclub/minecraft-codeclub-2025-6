package net.mcreator.dextermod.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

public class FProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		String tesxt = "";
		if (getEntityScore("b", entity) == 1) {
			tesxt = "Greet";
		} else if (getEntityScore("b", entity) == 27) {
			tesxt = "Glow";
		} else {
			tesxt = "Unknown spell";
		}
		return tesxt;
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}