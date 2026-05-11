package net.mcreator.ilovesoccer.procedures;

import net.minecraft.world.entity.Entity;

public class CastselectedProcedure {
	public static void execute(Entity casting_player, double x_cord, double y_cord, double z_cord) {
		if (casting_player == null)
			return;
		Entity caster = null;
		double x = 0;
		double y = 0;
		double z = 0;
		caster = casting_player;
		x = x_cord;
		y = y_cord;
		z = z_cord;
	}
}