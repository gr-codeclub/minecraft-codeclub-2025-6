package net.mcreator.dextermod.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import net.mcreator.dextermod.DextermodMod;

@EventBusSubscriber(modid = DextermodMod.MODID)
public class SpellModeCommand {

	@SubscribeEvent
	public static void register(RegisterCommandsEvent event) {
		event.getDispatcher().register(
				Commands.literal("spellmode")
						.then(Commands.argument("mode", IntegerArgumentType.integer(0, 5))
								.executes(SpellModeCommand::setMode)));
	}

	private static int setMode(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
		int mode = IntegerArgumentType.getInteger(ctx, "mode");
		ServerPlayer player = ctx.getSource().getPlayerOrException();

		Scoreboard scoreboard = player.level().getScoreboard();
		Objective obj = scoreboard.getObjective("spell_mode");
		if (obj == null) {
			obj = scoreboard.addObjective(
					"spell_mode",
					ObjectiveCriteria.DUMMY,
					Component.literal("Spell Mode"),
					ObjectiveCriteria.RenderType.INTEGER,
					false,
					null);
		}
		scoreboard.getOrCreatePlayerScore(
				ScoreHolder.forNameOnly(player.getScoreboardName()), obj).set(mode);

		String shapeName = switch (mode) {
			case 1 -> "Smooth Direction";
			case 2 -> "Ring";
			case 3 -> "Cone";
			case 4 -> "Square";
			case 5 -> "Smooth Square";
			default -> "Straight Line";
		};
		ctx.getSource().sendSuccess(
				() -> Component.literal("Spell mode set to: " + shapeName),
				false);
		return 1;
	}
}
