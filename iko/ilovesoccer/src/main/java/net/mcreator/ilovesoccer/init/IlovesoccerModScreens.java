/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.ilovesoccer.client.gui.Jrt6aGUIScreen;

@EventBusSubscriber(Dist.CLIENT)
public class IlovesoccerModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(IlovesoccerModMenus.JRT_6A_GUI.get(), Jrt6aGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}