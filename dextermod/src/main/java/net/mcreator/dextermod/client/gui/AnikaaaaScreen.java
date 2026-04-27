package net.mcreator.dextermod.client.gui;

import net.neoforged.neoforge.client.network.ClientPacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.dextermod.world.inventory.AnikaaaaMenu;
import net.mcreator.dextermod.procedures.AnikaaaProcedure;
import net.mcreator.dextermod.network.AnikaaaaButtonMessage;
import net.mcreator.dextermod.init.DextermodModScreens;

public class AnikaaaaScreen extends AbstractContainerScreen<AnikaaaaMenu> implements DextermodModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_greetin;
	private Button button_glow;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("dextermod:textures/screens/anikaaaa.png");

	public AnikaaaaScreen(AnikaaaaMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.dextermod.anikaaaa.label_spells_yay"), 58, 5, -12829636, false);
		guiGraphics.drawString(this.font, AnikaaaProcedure.execute(entity), 23, 31, -16724737, false);
	}

	@Override
	public void init() {
		super.init();
		button_greetin = Button.builder(Component.translatable("gui.dextermod.anikaaaa.button_greetin"), e -> {
			int x = AnikaaaaScreen.this.x;
			int y = AnikaaaaScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new AnikaaaaButtonMessage(0, x, y, z));
				AnikaaaaButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 24, this.topPos + 48, 60, 20).build();
		this.addRenderableWidget(button_greetin);
		button_glow = Button.builder(Component.translatable("gui.dextermod.anikaaaa.button_glow"), e -> {
			int x = AnikaaaaScreen.this.x;
			int y = AnikaaaaScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new AnikaaaaButtonMessage(1, x, y, z));
				AnikaaaaButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 25, this.topPos + 88, 45, 20).build();
		this.addRenderableWidget(button_glow);
	}
}