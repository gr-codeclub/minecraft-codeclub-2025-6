package net.mcreator.dextermod.network;

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

import net.mcreator.dextermod.procedures.AnikaaProcedure;
import net.mcreator.dextermod.procedures.AnikaProcedure;
import net.mcreator.dextermod.DextermodMod;

@EventBusSubscriber
public record AnikaaaaButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<AnikaaaaButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(DextermodMod.MODID, "anikaaaa_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, AnikaaaaButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, AnikaaaaButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new AnikaaaaButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<AnikaaaaButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final AnikaaaaButtonMessage message, final IPayloadContext context) {
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

			AnikaProcedure.execute(entity);
		}
		if (buttonID == 1) {

			AnikaaProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		DextermodMod.addNetworkMessage(AnikaaaaButtonMessage.TYPE, AnikaaaaButtonMessage.STREAM_CODEC, AnikaaaaButtonMessage::handleData);
	}
}