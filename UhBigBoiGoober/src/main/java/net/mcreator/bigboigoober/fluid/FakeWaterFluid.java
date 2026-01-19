package net.mcreator.bigboigoober.fluid;

import org.apache.logging.log4j.core.util.Source;

import net.neoforged.neoforge.fluids.BaseFlowingFluid;

import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.LiquidBlock;

import net.mcreator.bigboigoober.init.BigboigooberModItems;
import net.mcreator.bigboigoober.init.BigboigooberModFluids;
import net.mcreator.bigboigoober.init.BigboigooberModFluidTypes;
import net.mcreator.bigboigoober.init.BigboigooberModBlocks;

public abstract class FakeWaterFluid extends BaseFlowingFluid {
	public static final BaseFlowingFluid.Properties PROPERTIES = new BaseFlowingFluid.Properties(() -> BigboigooberModFluidTypes.FAKE_WATER_TYPE.get(), () -> BigboigooberModFluids.FAKE_WATER.get(),
			() -> BigboigooberModFluids.FLOWING_FAKE_WATER.get()).explosionResistance(100f).bucket(() -> BigboigooberModItems.FAKE_WATER_BUCKET.get()).block(() -> (LiquidBlock) BigboigooberModBlocks.FAKE_WATER.get());

	private FakeWaterFluid() {
		super(PROPERTIES);
	}

	public static class Source extends FakeWaterFluid {
		public int getAmount(FluidState state) {
			return 8;
		}

		public boolean isSource(FluidState state) {
			return true;
		}
	}

	public static class Flowing extends FakeWaterFluid {
		protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		public int getAmount(FluidState state) {
			return state.getValue(LEVEL);
		}

		public boolean isSource(FluidState state) {
			return false;
		}
	}
}