package net.mcreator.ilovesoccer.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.ilovesoccer.procedures.VeinminerRightclickedOnBlockProcedure;

public class VeinminerItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 100, 4f, 0, 2, TagKey.create(Registries.ITEM, ResourceLocation.parse("ilovesoccer:veinminer_repair_items")));

	public VeinminerItem(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 3f, -3f));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		VeinminerRightclickedOnBlockProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), context.getLevel().getBlockState(context.getClickedPos()));
		return InteractionResult.SUCCESS;
	}
}