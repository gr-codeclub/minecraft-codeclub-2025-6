package net.mcreator.testmod.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class DexterBlockBlock extends Block {
	public DexterBlockBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).jumpFactor(3f));
	}

	@Override
	public int getLightBlock(BlockState state) {
		return 15;
	}
}