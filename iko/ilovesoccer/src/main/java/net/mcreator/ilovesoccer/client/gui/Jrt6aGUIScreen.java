package net.mcreator.ilovesoccer.client.gui;

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

import net.mcreator.ilovesoccer.world.inventory.Jrt6aGUIMenu;
import net.mcreator.ilovesoccer.network.Jrt6aGUIButtonMessage;
import net.mcreator.ilovesoccer.init.IlovesoccerModScreens;

public class Jrt6aGUIScreen extends AbstractContainerScreen<Jrt6aGUIMenu> implements IlovesoccerModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_greeting;
	private Button button_glow;
	private Button button_x;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("ilovesoccer:textures/screens/jrt_6a_gui.png");

	public Jrt6aGUIScreen(Jrt6aGUIMenu container, Inventory inventory, Component text) {
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
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovesoccer.jrt_6a_gui.label_spell"), 63, 54, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovesoccer.jrt_6a_gui.label_spell1"), 78, 11, -12829636, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.ilovesoccer.jrt_6a_gui.label_spellname"), 19, 40, -16737895, false);
	}

	@Override
	public void init() {
		super.init();
		button_greeting = Button.builder(Component.translatable("gui.ilovesoccer.jrt_6a_gui.button_greeting"), e -> {
			int x = Jrt6aGUIScreen.this.x;
			int y = Jrt6aGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new Jrt6aGUIButtonMessage(0, x, y, z));
				Jrt6aGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 60, 65, 20).build();
		this.addRenderableWidget(button_greeting);
		button_glow = Button.builder(Component.translatable("gui.ilovesoccer.jrt_6a_gui.button_glow"), e -> {
			int x = Jrt6aGUIScreen.this.x;
			int y = Jrt6aGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new Jrt6aGUIButtonMessage(1, x, y, z));
				Jrt6aGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 22, this.topPos + 91, 45, 20).build();
		this.addRenderableWidget(button_glow);
		button_x = Button.builder(Component.translatable("gui.ilovesoccer.jrt_6a_gui.button_x"), e -> {
		}).bounds(this.leftPos + 142, this.topPos + 4, 30, 20).build();
		this.addRenderableWidget(button_x);
	}
}