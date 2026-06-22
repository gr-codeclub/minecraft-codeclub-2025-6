package net.mcreator.coco.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class BurgerItem extends Item {
	public BurgerItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(4).saturationModifier(0.3f).alwaysEdible().build()));
	}
}