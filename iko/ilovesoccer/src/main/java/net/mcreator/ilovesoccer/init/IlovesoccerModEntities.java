/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.ilovesoccer.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;

import net.mcreator.ilovesoccer.entity.IdkChestBoatEntity;
import net.mcreator.ilovesoccer.entity.IdkBoatEntity;
import net.mcreator.ilovesoccer.IlovesoccerMod;

public class IlovesoccerModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, IlovesoccerMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<IdkBoatEntity>> IDK_BOAT = register("idk_boat",
			EntityType.Builder.<IdkBoatEntity>of(IdkBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
	public static final DeferredHolder<EntityType<?>, EntityType<IdkChestBoatEntity>> IDK_CHEST_BOAT = register("idk_chest_boat",
			EntityType.Builder.<IdkChestBoatEntity>of(IdkChestBoatEntity::new, MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(IlovesoccerMod.MODID, registryname))));
	}
}