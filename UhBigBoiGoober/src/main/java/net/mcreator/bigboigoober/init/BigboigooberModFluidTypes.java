/*
 * MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.bigboigoober.init;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.fluids.FluidType;

import net.mcreator.bigboigoober.fluid.types.FakeWaterFluidType;
import net.mcreator.bigboigoober.BigboigooberMod;

public class BigboigooberModFluidTypes {
	public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, BigboigooberMod.MODID);
	public static final DeferredHolder<FluidType, FluidType> FAKE_WATER_TYPE = REGISTRY.register("fake_water", () -> new FakeWaterFluidType());
}