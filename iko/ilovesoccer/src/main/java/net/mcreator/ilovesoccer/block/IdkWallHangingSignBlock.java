package net.mcreator.ilovesoccer.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;

import net.mcreator.ilovesoccer.init.IlovesoccerModWoodTypes;
import net.mcreator.ilovesoccer.init.IlovesoccerModBlocks;

public class IdkWallHangingSignBlock extends WallHangingSignBlock {
	public IdkWallHangingSignBlock(BlockBehaviour.Properties properties) {
		super(IlovesoccerModWoodTypes.IDK_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1f).noCollission().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(IlovesoccerModBlocks.IDK_HANGING_SIGN.get().getLootTable()).overrideDescription(IlovesoccerModBlocks.IDK_HANGING_SIGN.get().getDescriptionId()));
	}
}