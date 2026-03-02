package net.mcreator.bigboigoober.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SkillStoneIdkOnBlockRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (getEntityScore("idk", entity) < 5) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You suck - Level:" + getEntityScore("idk", entity))), true);
		} else if (getEntityScore("idk", entity) < 10) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("Eh you're mid - Level:" + getEntityScore("idk", entity))), true);
		} else if (getEntityScore("idk", entity) < 25) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("Good enough - Level:" + getEntityScore("idk", entity))), true);
		} else if (getEntityScore("idk", entity) < 50) {
			if (entity instanceof Player _player && !_player.level().isClientSide())
				_player.displayClientMessage(Component.literal(("You're probs cheating - Level:" + getEntityScore("idk", entity))), true);
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