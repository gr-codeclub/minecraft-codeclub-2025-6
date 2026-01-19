package net.mcreator.bigboigoober.item;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BucketItem;

import net.mcreator.bigboigoober.init.BigboigooberModFluids;

public class FakeWaterItem extends BucketItem {
	public FakeWaterItem(Item.Properties properties) {
		super(BigboigooberModFluids.FAKE_WATER.get(), properties.craftRemainder(Items.BUCKET).stacksTo(1)

		);
	}
}