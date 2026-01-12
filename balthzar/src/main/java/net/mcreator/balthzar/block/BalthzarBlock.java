package net.mcreator.balthzar.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class BalthzarBlock extends Block {
	public BalthzarBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).friction(3.49f).speedFactor(20f).jumpFactor(67.5f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}