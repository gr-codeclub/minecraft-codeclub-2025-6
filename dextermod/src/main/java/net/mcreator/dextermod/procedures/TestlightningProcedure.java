package net.mcreator.dextermod.procedures;

import net.neoforged.neoforge.event.entity.EntityStruckByLightningEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import javax.annotation.Nullable;

@EventBusSubscriber
public class TestlightningProcedure {
	@SubscribeEvent
	public static void onEntityStruckByLightning(EntityStruckByLightningEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level().isClientSide())
			_player.displayClientMessage(Component.literal("cancelled"), true);
		if (event instanceof ICancellableEvent _cancellable) {
			_cancellable.setCanceled(true);
		}
	}
}