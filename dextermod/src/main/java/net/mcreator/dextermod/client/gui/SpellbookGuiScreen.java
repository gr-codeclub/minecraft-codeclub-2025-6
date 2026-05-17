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

import net.mcreator.dextermod.world.inventory.SpellbookGuiMenu;
import net.mcreator.dextermod.procedures.SpellNameProcedure;
import net.mcreator.dextermod.network.SpellbookGuiButtonMessage;
import net.mcreator.dextermod.init.DextermodModScreens;

public class SpellbookGuiScreen extends AbstractContainerScreen<SpellbookGuiMenu> implements DextermodModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_greeting;
	private Button button_glow;
	private Button button_x;
	private Button button_firework;
	private Button button_storm;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("dextermod:textures/screens/spellbook_gui.png");

	public SpellbookGuiScreen(SpellbookGuiMenu container, Inventory inventory, Component text) {
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
		guiGraphics.drawString(this.font, Component.translatable("gui.dextermod.spellbook_gui.label_spells"), 69, 9, -12829636, true);
		guiGraphics.drawString(this.font, SpellNameProcedure.execute(entity), 15, 25, -16724737, false);
	}

	@Override
	public void init() {
		super.init();
		button_greeting = Button.builder(Component.translatable("gui.dextermod.spellbook_gui.button_greeting"), e -> {
			int x = SpellbookGuiScreen.this.x;
			int y = SpellbookGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGuiButtonMessage(0, x, y, z));
				SpellbookGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 52, 65, 20).build();
		this.addRenderableWidget(button_greeting);
		button_glow = Button.builder(Component.translatable("gui.dextermod.spellbook_gui.button_glow"), e -> {
			int x = SpellbookGuiScreen.this.x;
			int y = SpellbookGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGuiButtonMessage(1, x, y, z));
				SpellbookGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 79, 45, 20).build();
		this.addRenderableWidget(button_glow);
		button_x = Button.builder(Component.translatable("gui.dextermod.spellbook_gui.button_x"), e -> {
			int x = SpellbookGuiScreen.this.x;
			int y = SpellbookGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGuiButtonMessage(2, x, y, z));
				SpellbookGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 141, this.topPos + 7, 30, 20).build();
		this.addRenderableWidget(button_x);
		button_firework = Button.builder(Component.translatable("gui.dextermod.spellbook_gui.button_firework"), e -> {
			int x = SpellbookGuiScreen.this.x;
			int y = SpellbookGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGuiButtonMessage(3, x, y, z));
				SpellbookGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 106, 65, 20).build();
		this.addRenderableWidget(button_firework);
		button_storm = Button.builder(Component.translatable("gui.dextermod.spellbook_gui.button_storm"), e -> {
			int x = SpellbookGuiScreen.this.x;
			int y = SpellbookGuiScreen.this.y;
			if (true) {
				ClientPacketDistributor.sendToServer(new SpellbookGuiButtonMessage(4, x, y, z));
				SpellbookGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		}).bounds(this.leftPos + 15, this.topPos + 133, 50, 20).build();
		this.addRenderableWidget(button_storm);
	}
}