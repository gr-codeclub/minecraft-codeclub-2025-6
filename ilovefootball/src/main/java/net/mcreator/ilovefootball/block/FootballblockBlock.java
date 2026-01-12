package net.mcreator.ilovefootball.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class FootballblockBlock extends Block {
	public FootballblockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).friction(0.8f).speedFactor(20f).jumpFactor(5f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}