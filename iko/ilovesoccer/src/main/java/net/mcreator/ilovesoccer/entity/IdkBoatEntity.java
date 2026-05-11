package net.mcreator.ilovesoccer.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.EntityType;

import net.mcreator.ilovesoccer.init.IlovesoccerModItems;

public class IdkBoatEntity extends Boat {
	public IdkBoatEntity(EntityType<IdkBoatEntity> type, Level world) {
		super(type, world, IlovesoccerModItems.IDK_BOAT);
	}
}