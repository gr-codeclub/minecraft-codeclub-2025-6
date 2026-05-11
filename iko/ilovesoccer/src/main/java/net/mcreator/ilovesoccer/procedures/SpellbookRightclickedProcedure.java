package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.ilovesoccer.world.inventory.Jrt6aGUIMenu;

import io.netty.buffer.Unpooled;

public class SpellbookRightclickedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double spell = 0;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("spell time"), false);
		if (entity.isShiftKeyDown()) {
			if (entity instanceof ServerPlayer _ent) {
				BlockPos _bpos = BlockPos.containing(x, y, z);
				_ent.openMenu(new MenuProvider() {
					@Override
					public Component getDisplayName() {
						return Component.literal("Jrt6aGUI");
					}

					@Override
					public boolean shouldTriggerClientSideContainerClosingOnOpen() {
						return false;
					}

					@Override
					public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
						return new Jrt6aGUIMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
					}
				}, _bpos);
			}
		} else {
			spell = getEntityScore("current_spell", entity);
			if (spell == 0) {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("Hi from MCreator"), false);
			} else if (spell == 1) {
				if (entity instanceof Player _player && !_player.level().isClientSide()) {
					_player.displayClientMessage(Component.literal("Hi From Java").withStyle(net.minecraft.ChatFormatting.GOLD, net.minecraft.ChatFormatting.BOLD), false);
				}
			} else {
				if (entity instanceof Player _player && !_player.level().isClientSide())
					_player.displayClientMessage(Component.literal("crouch right now"), false);
			}
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