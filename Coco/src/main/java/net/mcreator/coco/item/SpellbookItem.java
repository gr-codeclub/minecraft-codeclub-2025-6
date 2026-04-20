package net.mcreator.coco.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.coco.procedures.SpellbookRightclickedProcedure;

public class SpellbookItem extends Item {
	public SpellbookItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SpellbookRightclickedProcedure.execute(entity);
		return ar;
	}
}