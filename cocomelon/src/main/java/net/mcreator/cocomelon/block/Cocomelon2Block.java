package net.mcreator.cocomelon.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class Cocomelon2Block extends Block {
	public Cocomelon2Block(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.GRAVEL).strength(1f, 10f).friction(4f).speedFactor(9f).jumpFactor(39f));
	}
}