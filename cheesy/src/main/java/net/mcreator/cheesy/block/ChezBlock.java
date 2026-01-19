package net.mcreator.cheesy.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ChezBlock extends Block {
	public ChezBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).speedFactor(2f).jumpFactor(3f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}