package net.mcreator.bigboigoober.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.bigboigoober.procedures.VeinMinerBlockDestroyedWithToolProcedure;

public class VeinMinerItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 100, 4f, 0, 2, TagKey.create(Registries.ITEM, ResourceLocation.parse("bigboigoober:vein_miner_repair_items")));

	public VeinMinerItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 3f, -3f));
	}

	@Override
	public boolean mineBlock(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
		boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
		VeinMinerBlockDestroyedWithToolProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ(), blockstate);
		return retval;
	}
}