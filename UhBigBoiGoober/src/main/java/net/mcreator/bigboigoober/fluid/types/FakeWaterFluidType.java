package net.mcreator.bigboigoober.fluid.types;

import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.common.SoundActions;

import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.sounds.SoundEvents;

public class FakeWaterFluidType extends FluidType {
	public FakeWaterFluidType() {
		super(FluidType.Properties.create().canSwim(false).canDrown(false).pathType(PathType.LAVA).adjacentPathType(null).motionScale(0.007D).sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY).sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH));
	}
}