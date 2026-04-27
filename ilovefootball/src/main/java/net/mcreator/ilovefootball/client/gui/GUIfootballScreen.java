package net.mcreator.ilovefootball.client.gui;

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

import net.mcreator.ilovefootball.world.inventory.GUIfootballMenu;
import net.mcreator.ilovefootball.network.GUIfootballButtonMessage;
import net.mcreator.ilovefootball.init.IlovefootballModScreens;

public class GUIfootballScreen extends AbstractContainerScreen<GUIfootballMenu> implements IlovefootballModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_glow;
	private Button button_greeting;
	private Button button_x;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("ilovefootball:textures/screens/gu_ifootball.png");

	public GUIfootballScreen(GUIfootballMenu container, Inventory inventory, Component text) {
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
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovefootball.gu_ifootball.label_spell"), 62, 6, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovefootball.gu_ifootball.label_empty"), 117, 55, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovefootball.gu_ifootball.label_spellname"), 17, 19, -13369345, false);
	}

	@Override
	public void init() {
		super.init();
		button_glow = Button.builder(Component.translatable("gui.ilovefootball.gu_ifootball.button_glow"), e -> {
			int x = GUIfootballScreen.this.x;
			int y = GUIfootballScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new GUIfootballButtonMessage(0, x, y, z));
				GUIfootballButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 13, this.topPos + 72, 45, 20).build();
		this.addRenderableWidget(button_glow);
		button_greeting = Button.builder(Component.translatable("gui.ilovefootball.gu_ifootball.button_greeting"), e -> {
			int x = GUIfootballScreen.this.x;
			int y = GUIfootballScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new GUIfootballButtonMessage(1, x, y, z));
				GUIfootballButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 8, this.topPos + 38, 65, 20).build();
		this.addRenderableWidget(button_greeting);
		button_x = Button.builder(Component.translatable("gui.ilovefootball.gu_ifootball.button_x"), e -> {
		}).bounds(this.leftPos + 141, this.topPos + 4, 30, 20).build();
		this.addRenderableWidget(button_x);
	}
}