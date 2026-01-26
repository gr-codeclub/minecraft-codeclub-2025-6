package net.mcreator.cocomelon.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

public class COCOMELON3Item extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 100, 4f, 0, 2, TagKey.create(Registries.ITEM, ResourceLocation.parse("cocomelon:cocomelon_3_repair_items")));

	public COCOMELON3Item(Item.Properties properties) {
		super(properties.pickaxe(TOOL_MATERIAL, 3f, -3f));
	}
}