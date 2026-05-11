package net.mcreator.ilovesoccer.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import net.mcreator.ilovesoccer.init.IlovesoccerModWoodTypes;

public class IdkHangingSignBlock extends CeilingHangingSignBlock {
	public IdkHangingSignBlock(BlockBehaviour.Properties properties) {
		super(IlovesoccerModWoodTypes.IDK_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}