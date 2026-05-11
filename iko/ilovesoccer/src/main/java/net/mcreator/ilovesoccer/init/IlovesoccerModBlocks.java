/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.Sheets;

import net.mcreator.ilovesoccer.block.*;
import net.mcreator.ilovesoccer.IlovesoccerMod;

import java.util.function.Function;

@EventBusSubscriber
public class IlovesoccerModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(IlovesoccerMod.MODID);
	public static final DeferredBlock<Block> VOLDERMOLTHASA_BI_GNOSE;
	public static final DeferredBlock<Block> DICE_BLOCK;
	public static final DeferredBlock<Block> IDK_LOG;
	public static final DeferredBlock<Block> IDK_WOOD;
	public static final DeferredBlock<Block> STRIPPED_IDK_LOG;
	public static final DeferredBlock<Block> STRIPPED_IDK_WOOD;
	public static final DeferredBlock<Block> IDK_PLANKS;
	public static final DeferredBlock<Block> IDK_LEAVES;
	public static final DeferredBlock<Block> IDK_STAIRS;
	public static final DeferredBlock<Block> IDK_SLAB;
	public static final DeferredBlock<Block> IDK_FENCE;
	public static final DeferredBlock<Block> IDK_FENCE_GATE;
	public static final DeferredBlock<Block> IDK_DOOR;
	public static final DeferredBlock<Block> IDK_TRAPDOOR;
	public static final DeferredBlock<Block> IDK_PRESSURE_PLATE;
	public static final DeferredBlock<Block> IDK_BUTTON;
	public static final DeferredBlock<Block> IDK_SIGN;
	public static final DeferredBlock<Block> IDK_WALL_SIGN;
	public static final DeferredBlock<Block> IDK_HANGING_SIGN;
	public static final DeferredBlock<Block> IDK_WALL_HANGING_SIGN;
	static {
		VOLDERMOLTHASA_BI_GNOSE = register("voldermolthasa_bi_gnose", VoldermolthasaBIGnoseBlock::new);
		DICE_BLOCK = register("dice_block", DiceBlockBlock::new);
		IDK_LOG = register("idk_log", IdkLogBlock::new);
		IDK_WOOD = register("idk_wood", IdkWoodBlock::new);
		STRIPPED_IDK_LOG = register("stripped_idk_log", StrippedIdkLogBlock::new);
		STRIPPED_IDK_WOOD = register("stripped_idk_wood", StrippedIdkWoodBlock::new);
		IDK_PLANKS = register("idk_planks", IdkPlanksBlock::new);
		IDK_LEAVES = register("idk_leaves", IdkLeavesBlock::new);
		IDK_STAIRS = register("idk_stairs", IdkStairsBlock::new);
		IDK_SLAB = register("idk_slab", IdkSlabBlock::new);
		IDK_FENCE = register("idk_fence", IdkFenceBlock::new);
		IDK_FENCE_GATE = register("idk_fence_gate", IdkFenceGateBlock::new);
		IDK_DOOR = register("idk_door", IdkDoorBlock::new);
		IDK_TRAPDOOR = register("idk_trapdoor", IdkTrapdoorBlock::new);
		IDK_PRESSURE_PLATE = register("idk_pressure_plate", IdkPressurePlateBlock::new);
		IDK_BUTTON = register("idk_button", IdkButtonBlock::new);
		IDK_SIGN = register("idk_sign", IdkSignBlock::new);
		IDK_WALL_SIGN = register("idk_wall_sign", IdkWallSignBlock::new);
		IDK_HANGING_SIGN = register("idk_hanging_sign", IdkHangingSignBlock::new);
		IDK_WALL_HANGING_SIGN = register("idk_wall_hanging_sign", IdkWallHangingSignBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			Sheets.addWoodType(IlovesoccerModWoodTypes.IDK_SIGN_WOOD_TYPE);
			Sheets.addWoodType(IlovesoccerModWoodTypes.IDK_HANGING_SIGN_WOOD_TYPE);
		}
	}

	@SubscribeEvent
	public static void registerSigns(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN, IDK_SIGN.get(), IDK_WALL_SIGN.get());
		event.modify(BlockEntityType.HANGING_SIGN, IDK_HANGING_SIGN.get(), IDK_WALL_HANGING_SIGN.get());
	}
}