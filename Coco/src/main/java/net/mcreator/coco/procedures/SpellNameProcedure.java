package net.mcreator.coco.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

public class SpellNameProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double Spell = 0;
		if (getEntityScore("current_spell", entity) == 0) {
			return "Advert";
		} else if (getEntityScore("current_spell", entity) == 1) {
			return "Glow";
		} else if (getEntityScore("current_spell", entity) == 2) {
			return "Petals";
		} else if (getEntityScore("current_spell", entity) == 3) {
			return "Storm";
		}
		return "Unknown";
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}