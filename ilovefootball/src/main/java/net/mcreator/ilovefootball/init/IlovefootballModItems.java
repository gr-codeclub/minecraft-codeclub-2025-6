/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovefootball.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.ilovefootball.item.*;
import net.mcreator.ilovefootball.IlovefootballMod;

import java.util.function.Function;

public class IlovefootballModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(IlovefootballMod.MODID);
	public static final DeferredItem<Item> FOOTBALLBLOCK;
	public static final DeferredItem<Item> FOOTBALLSOMETHING;
	public static final DeferredItem<Item> FOOT;
	public static final DeferredItem<Item> DICEBLOCK;
	public static final DeferredItem<Item> SKILLSTONE;
	public static final DeferredItem<Item> SPELLBOOK;
	public static final DeferredItem<Item> ERIKSARMOR_HELMET;
	public static final DeferredItem<Item> ERIKSARMOR_CHESTPLATE;
	public static final DeferredItem<Item> ERIKSARMOR_LEGGINGS;
	public static final DeferredItem<Item> ERIKSARMOR_BOOTS;
	public static final DeferredItem<Item> AMOURISTHEBEST_HELMET;
	public static final DeferredItem<Item> AMOURISTHEBEST_CHESTPLATE;
	public static final DeferredItem<Item> AMOURISTHEBEST_LEGGINGS;
	public static final DeferredItem<Item> AMOURISTHEBEST_BOOTS;
	static {
		FOOTBALLBLOCK = block(IlovefootballModBlocks.FOOTBALLBLOCK);
		FOOTBALLSOMETHING = register("footballsomething", FootballsomethingItem::new);
		FOOT = register("foot", FootItem::new);
		DICEBLOCK = block(IlovefootballModBlocks.DICEBLOCK);
		SKILLSTONE = register("skillstone", SkillstoneItem::new);
		SPELLBOOK = register("spellbook", SpellbookItem::new);
		ERIKSARMOR_HELMET = register("eriksarmor_helmet", EriksarmorItem.Helmet::new);
		ERIKSARMOR_CHESTPLATE = register("eriksarmor_chestplate", EriksarmorItem.Chestplate::new);
		ERIKSARMOR_LEGGINGS = register("eriksarmor_leggings", EriksarmorItem.Leggings::new);
		ERIKSARMOR_BOOTS = register("eriksarmor_boots", EriksarmorItem.Boots::new);
		AMOURISTHEBEST_HELMET = register("amouristhebest_helmet", AmouristhebestItem.Helmet::new);
		AMOURISTHEBEST_CHESTPLATE = register("amouristhebest_chestplate", AmouristhebestItem.Chestplate::new);
		AMOURISTHEBEST_LEGGINGS = register("amouristhebest_leggings", AmouristhebestItem.Leggings::new);
		AMOURISTHEBEST_BOOTS = register("amouristhebest_boots", AmouristhebestItem.Boots::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), properties);
	}
}