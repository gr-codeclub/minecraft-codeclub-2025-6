package net.mcreator.cocomelon.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.cocomelon.procedures.COCOMELON676676767RightclickedProcedure;

public class COCOMELON676676767Item extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 100, 100f, 0, 100, TagKey.create(Registries.ITEM, ResourceLocation.parse("cocomelon:cocomelon_676676767_repair_items")));

	public COCOMELON676676767Item(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 99f, 96f));
	}

	@Override
	public InteractionResult use(Level world, Player entity, InteractionHand hand) {
		InteractionResult ar = super.use(world, entity, hand);
		COCOMELON676676767RightclickedProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
		return ar;
	}
}