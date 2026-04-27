/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovefootball.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.ilovefootball.client.gui.GUIfootballScreen;

@EventBusSubscriber(Dist.CLIENT)
public class IlovefootballModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(IlovefootballModMenus.GU_IFOOTBALL.get(), GUIfootballScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}