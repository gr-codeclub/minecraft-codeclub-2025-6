package net.mcreator.dextermod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

public class SpellbookRightclickedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("Spell time"), false);
		if (entity instanceof Player _player) {
			_player.displayClientMessage(Component.literal("Hello World").withStyle(net.minecraft.ChatFormatting.RED, net.minecraft.ChatFormatting.BOLD), true);
		}
	}
}