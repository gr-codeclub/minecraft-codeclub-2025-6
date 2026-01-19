/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.capability.wrappers.FluidBucketWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.bigboigoober.item.FakeWaterItem;
import net.mcreator.bigboigoober.BigboigooberMod;

import java.util.function.Function;

public class BigboigooberModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(BigboigooberMod.MODID);
	public static final DeferredItem<Item> BLOCK_THINGY;
	public static final DeferredItem<Item> FAKE_WATER_BUCKET;
	static {
		BLOCK_THINGY = block(BigboigooberModBlocks.BLOCK_THINGY, new Item.Properties().stacksTo(99).rarity(Rarity.EPIC).fireResistant());
		FAKE_WATER_BUCKET = register("fake_water_bucket", FakeWaterItem::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.FluidHandler.ITEM, (stack, context) -> new FluidBucketWrapper(stack), FAKE_WATER_BUCKET.get());
	}
}