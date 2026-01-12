package net.mcreator.coco.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class QuickCocoBlock extends Block {
	public QuickCocoBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).speedFactor(10f).jumpFactor(1000f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}