package net.mcreator.dextermod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.dextermod.procedures.PlatformWandRightclickedProcedure;

public class PlatformWandItem extends Item {
	public PlatformWandItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		PlatformWandRightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
		return ar;
	}
}