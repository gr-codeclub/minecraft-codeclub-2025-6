/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;

import net.mcreator.bigboigoober.fluid.FakeWaterFluid;
import net.mcreator.bigboigoober.BigboigooberMod;

public class BigboigooberModFluids {
	public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, BigboigooberMod.MODID);
	public static final DeferredHolder<Fluid, FlowingFluid> FAKE_WATER = REGISTRY.register("fake_water", () -> new FakeWaterFluid.Source());
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_FAKE_WATER = REGISTRY.register("flowing_fake_water", () -> new FakeWaterFluid.Flowing());

	@EventBusSubscriber(Dist.CLIENT)
	public static class FluidsClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ItemBlockRenderTypes.setRenderLayer(FAKE_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
			ItemBlockRenderTypes.setRenderLayer(FLOWING_FAKE_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
		}
	}
}