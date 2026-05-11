package net.mcreator.ilovesoccer.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.core.SectionPos;

import net.mcreator.ilovesoccer.procedures.SetgreetingspellProcedure;
import net.mcreator.ilovesoccer.procedures.SetfireworkspellProcedure;
import net.mcreator.ilovesoccer.procedures.SelectGlowspellProcedure;
import net.mcreator.ilovesoccer.IlovesoccerMod;

@EventBusSubscriber
public record Jrt6aGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<Jrt6aGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(IlovesoccerMod.MODID, "jrt_6a_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, Jrt6aGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, Jrt6aGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new Jrt6aGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<Jrt6aGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final Jrt6aGUIButtonMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> handleButtonAction(context.player(), message.buttonID, message.x, message.y, message.z)).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level();
		// security measure to prevent arbitrary chunk generation
		if (!world.getChunkSource().hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
			return;
		if (buttonID == 0) {

			SetgreetingspellProcedure.execute(entity);
		}
		if (buttonID == 1) {

			SelectGlowspellProcedure.execute(entity);
		}
		if (buttonID == 3) {

			SetfireworkspellProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		IlovesoccerMod.addNetworkMessage(Jrt6aGUIButtonMessage.TYPE, Jrt6aGUIButtonMessage.STREAM_CODEC, Jrt6aGUIButtonMessage::handleData);
	}
}