package net.mcreator.ilovesoccer.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.ilovesoccer.init.IlovesoccerModWoodTypes;
import net.mcreator.ilovesoccer.init.IlovesoccerModBlocks;

public class IdkWallSignBlock extends WallSignBlock {
	public IdkWallSignBlock(BlockBehaviour.Properties properties) {
		super(IlovesoccerModWoodTypes.IDK_SIGN_WOOD_TYPE, properties.sound(SoundType.WOOD).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(IlovesoccerModBlocks.IDK_SIGN.get().getLootTable()).overrideDescription(IlovesoccerModBlocks.IDK_SIGN.get().getDescriptionId()));
	}
}