package net.mcreator.coco.client.gui;

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

import net.mcreator.coco.world.inventory.SpellbookGUIMenu;
import net.mcreator.coco.procedures.SpellNameProcedure;
import net.mcreator.coco.network.SpellbookGUIButtonMessage;
import net.mcreator.coco.init.CocoModScreens;

public class SpellbookGUIScreen extends AbstractContainerScreen<SpellbookGUIMenu> implements CocoModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_advertisement;
	private Button button_glow;
	private Button button_x;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("coco:textures/screens/spellbook_gui.png");

	public SpellbookGUIScreen(SpellbookGUIMenu container, Inventory inventory, Component text) {
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
		guiGraphics.drawString(this.font, Component.translatable("gui.coco.spellbook_gui.label_welcome_to_the_spellbook"), 7, 6, -16751002, false);
		guiGraphics.drawString(this.font, SpellNameProcedure.execute(entity), 6, 34, -16777165, false);
	}

	@Override
	public void init() {
		super.init();
		button_advertisement = Button.builder(Component.translatable("gui.coco.spellbook_gui.button_advertisement"), e -> {
			int x = SpellbookGUIScreen.this.x;
			int y = SpellbookGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGUIButtonMessage(0, x, y, z));
				SpellbookGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 54, 90, 20).build();
		this.addRenderableWidget(button_advertisement);
		button_glow = Button.builder(Component.translatable("gui.coco.spellbook_gui.button_glow"), e -> {
			int x = SpellbookGUIScreen.this.x;
			int y = SpellbookGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGUIButtonMessage(1, x, y, z));
				SpellbookGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 6, this.topPos + 83, 45, 20).build();
		this.addRenderableWidget(button_glow);
		button_x = Button.builder(Component.translatable("gui.coco.spellbook_gui.button_x"), e -> {
			int x = SpellbookGUIScreen.this.x;
			int y = SpellbookGUIScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGUIButtonMessage(2, x, y, z));
				SpellbookGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 142, this.topPos + 3, 30, 20).build();
		this.addRenderableWidget(button_x);
	}
}