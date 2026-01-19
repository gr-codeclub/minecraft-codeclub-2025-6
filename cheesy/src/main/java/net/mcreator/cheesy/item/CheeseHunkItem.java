package net.mcreator.cheesy.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CheeseHunkItem extends Item {
	public CheeseHunkItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(11).saturationModifier(0.3f).alwaysEdible().build()));
	}
}