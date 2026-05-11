package net.mcreator.ilovesoccer.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.EntityType;

import net.mcreator.ilovesoccer.init.IlovesoccerModItems;

public class IdkChestBoatEntity extends ChestBoat {
	public IdkChestBoatEntity(EntityType<IdkChestBoatEntity> type, Level world) {
		super(type, world, IlovesoccerModItems.IDK_CHEST_BOAT);
	}
}