package com.heyimamethyst.fairyfactions.core.registry;

import com.heyimamethyst.fairyfactions.FairyFactions;

import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.common.entities.FairyFishHookEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES  = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FairyFactions.MOD_ID);
	
	public static void Init(IEventBus modEventBus) 
	{
		ENTITY_TYPES.register(modEventBus);
	}

	public static final RegistryObject<EntityType<FairyEntity>> FAIRY_ENTITY = ENTITY_TYPES.register("fairy_entity",
            () -> EntityType.Builder.<FairyEntity>of(FairyEntity::new, MobCategory.AMBIENT)
            .sized(0.4F, 1.0F) // Hitbox Size //sized(0.4F, 0.8F)
            .clientTrackingRange(8)
            .build(new ResourceLocation(FairyFactions.MOD_ID, "fairy_entity").toString()));

	public static final RegistryObject<EntityType<FairyFishHookEntity>> FAIRY_FISHING_BOBBER_ENTITY = ENTITY_TYPES.register("fairy_fishing_bobber_entity",
			() -> EntityType.Builder.<FairyFishHookEntity>of(FairyFishHookEntity::new, MobCategory.MISC)
					.noSave()
					.noSummon()
					.sized(0.25F, 0.25F) // Hitbox Size
					.clientTrackingRange(4)
					.updateInterval(5)
					.build(new ResourceLocation(FairyFactions.MOD_ID, "fairy_fishing_bobber_entity").toString()));

}
