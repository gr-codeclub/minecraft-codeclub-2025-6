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

import net.mcreator.dextermod.procedures.SelectParticlesSpellProcedure;
import net.mcreator.dextermod.procedures.SelectGreetingSpellProcedure;
import net.mcreator.dextermod.procedures.SelectGlowSpellProcedure;
import net.mcreator.dextermod.procedures.CloseProcedure;
import net.mcreator.dextermod.DextermodMod;

@EventBusSubscriber
public record SpellbookGuiButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<SpellbookGuiButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(DextermodMod.MODID, "spellbook_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SpellbookGuiButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, SpellbookGuiButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new SpellbookGuiButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<SpellbookGuiButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final SpellbookGuiButtonMessage message, final IPayloadContext context) {
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

			SelectGreetingSpellProcedure.execute(entity);
		}
		if (buttonID == 1) {

			SelectGlowSpellProcedure.execute(entity);
		}
		if (buttonID == 2) {

			CloseProcedure.execute(entity);
		}
		if (buttonID == 3) {

			SelectParticlesSpellProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		DextermodMod.addNetworkMessage(SpellbookGuiButtonMessage.TYPE, SpellbookGuiButtonMessage.STREAM_CODEC, SpellbookGuiButtonMessage::handleData);
	}
}