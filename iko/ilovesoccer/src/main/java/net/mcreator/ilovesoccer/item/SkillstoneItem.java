package net.mcreator.ilovesoccer.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.ilovesoccer.procedures.SkillstoneRightclickedProcedure;

public class SkillstoneItem extends Item {
	public SkillstoneItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SkillstoneRightclickedProcedure.execute(entity);
		return ar;
	}
}