package net.mcreator.balthzar.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.balthzar.procedures.SkillStoneRightclickedProcedure;

public class SkillStoneItem extends Item {
	public SkillStoneItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		SkillStoneRightclickedProcedure.execute(entity);
		return ar;
	}
}