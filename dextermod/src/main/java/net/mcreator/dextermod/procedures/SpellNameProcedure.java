package net.mcreator.dextermod.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.Entity;

public class SpellNameProcedure {
	public static String execute(Entity entity) {
		if (entity == null)
			return "";
		double spell = 0;
		String spell_name = "";
		spell = getEntityScore("current_spell", entity);
		if (spell == 0) {
			spell_name = "Greeting";
		} else if (spell == 1) {
			spell_name = "Glow";
		} else {
			spell_name = "Unknown Spell";
		}
		return "Current Spell: " + spell_name;
	}

	private static int getEntityScore(String score, Entity entity) {
		Scoreboard scoreboard = entity.level().getScoreboard();
		Objective scoreboardObjective = scoreboard.getObjective(score);
		if (scoreboardObjective != null)
			return scoreboard.getOrCreatePlayerScore(ScoreHolder.forNameOnly(entity.getScoreboardName()), scoreboardObjective).get();
		return 0;
	}
}