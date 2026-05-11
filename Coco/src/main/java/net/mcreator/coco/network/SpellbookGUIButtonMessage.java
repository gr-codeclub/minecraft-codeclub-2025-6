package net.mcreator.coco.network;

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

import net.mcreator.coco.procedures.SelectPetalSpellProcedure;
import net.mcreator.coco.procedures.SelectGlowSpellProcedure;
import net.mcreator.coco.procedures.SelectAdvertisementSpellProcedure;
import net.mcreator.coco.procedures.CloseButtonProcedure;
import net.mcreator.coco.CocoMod;

@EventBusSubscriber
public record SpellbookGUIButtonMessage(int buttonID, int x, int y, int z) implements CustomPacketPayload {
	public static final Type<SpellbookGUIButtonMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(CocoMod.MODID, "spellbook_gui_buttons"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SpellbookGUIButtonMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, SpellbookGUIButtonMessage message) -> {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}, (RegistryFriendlyByteBuf buffer) -> new SpellbookGUIButtonMessage(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt()));

	@Override
	public Type<SpellbookGUIButtonMessage> type() {
		return TYPE;
	}

	public static void handleData(final SpellbookGUIButtonMessage message, final IPayloadContext context) {
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

			SelectAdvertisementSpellProcedure.execute(entity);
		}
		if (buttonID == 1) {

			SelectGlowSpellProcedure.execute(entity);
		}
		if (buttonID == 2) {

			CloseButtonProcedure.execute(entity);
		}
		if (buttonID == 3) {

			SelectPetalSpellProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		CocoMod.addNetworkMessage(SpellbookGUIButtonMessage.TYPE, SpellbookGUIButtonMessage.STREAM_CODEC, SpellbookGUIButtonMessage::handleData);
	}
}