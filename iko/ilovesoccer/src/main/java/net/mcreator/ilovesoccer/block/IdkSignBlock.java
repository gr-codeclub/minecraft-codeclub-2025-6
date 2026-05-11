package net.mcreator.ilovesoccer.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.ilovesoccer.init.IlovesoccerModWoodTypes;

public class IdkSignBlock extends StandingSignBlock {
	public IdkSignBlock(BlockBehaviour.Properties properties) {
		super(IlovesoccerModWoodTypes.IDK_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}