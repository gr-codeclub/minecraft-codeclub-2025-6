package net.mcreator.cocomelon.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;

import net.mcreator.cocomelon.procedures.CheeseRightclickedProcedure;
import net.mcreator.cocomelon.init.CocomelonModBlocks;

public class CheeseItem extends Item {
	public CheeseItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).stacksTo(99).fireResistant().enchantable(1000));
	}

	@Override
	public ItemStack getCraftingRemainder(ItemStack itemstack) {
		return new ItemStack(CocomelonModBlocks.HICOCOMELON.get());
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 100000f;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack itemstack, BlockState state) {
		return true;
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		CheeseRightclickedProcedure.execute(entity);
		return ar;
	}
}