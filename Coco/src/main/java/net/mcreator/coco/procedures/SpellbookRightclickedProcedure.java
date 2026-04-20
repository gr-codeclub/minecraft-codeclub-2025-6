package net.mcreator.coco.procedures;

import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SpellbookRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Team c00lkid. Join today!").withStyle(ChatFormatting.DARK_RED, ChatFormatting.STRIKETHROUGH), false);
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Team bluudud. Get in today!").withStyle(ChatFormatting.DARK_AQUA, ChatFormatting.STRIKETHROUGH), true);
	}
}