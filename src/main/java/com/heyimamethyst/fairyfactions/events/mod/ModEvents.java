package com.heyimamethyst.fairyfactions.events.mod;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.core.registry.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = FairyFactions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents
{

	@SubscribeEvent
	public static void onRegisterItems(final RegisterEvent event)
	{
//		ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
//		{
//			if(!(block instanceof EssenceExtractor))
//			{
//				event.getRegistry().register(new BlockItem(block, new Item.Properties()
//						.tab(ModThingsItemGroup.MOD_CREATIVE_TAB))
//						.setRegistryName(block.getRegistryName() + "_item"));
//			}
//		});
	}

	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event)
	{
		event.put(ModEntities.FAIRY_ENTITY.get(), FairyEntity.createAttributes().build());
	}

	@SubscribeEvent
	public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event)
	{
		event.register(ModEntities.FAIRY_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FairyEntity::checkFairySpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
	}

//	//@SubscribeEvent(priority = EventPriority.HIGHEST)
//	public static void gen(BiomeLoadingEvent event)
//	{
//
//		//Biomes.MEADOW
//		addEntityToSpecificBiomeCategories(event, ModEntities.FAIRY_ENTITY.get(), 40, 1, 4, Biome.BiomeCategory.PLAINS, Biome.BiomeCategory.FOREST, Biome.BiomeCategory.MOUNTAIN, Biome.BiomeCategory.MUSHROOM);
//		//addEntityToSpecificBiomes(event, ModEntities.FAIRY_ENTITY.get(), 40, 2, 4, Biomes.FOREST, Biomes.FLOWER_FOREST, Biomes.PLAINS, Biomes.BIRCH_FOREST, Biomes.MEADOW);
//		//addEntityToAllOverworldBiomes(event, ModEntities.FAIRY_ENTITY.get(), 40, 1, 2);
//	}
//
//
//	private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
//														int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes)
//	{
//		// Goes through each entry in the biomes and sees if it matches the current biome we are loading
//		boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
//				.map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));
//
//		if(!isBiomeSelected)
//		{
//			addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//		}
//	}
//
//	@SafeVarargs
//	private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
//												  int weight, int minCount, int maxCount, ResourceKey<Biome>... biomes)
//	{
//		// Goes through each entry in the biomes and sees if it matches the current biome we are loading
//		boolean isBiomeSelected = Arrays.stream(biomes).map(ResourceKey::location)
//				.map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));
//
//		if(isBiomeSelected)
//		{
//			addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//		}
//	}
//
//	private static void addEntityToSpecificBiomeCategories(BiomeLoadingEvent event, EntityType<?> type,
//													  int weight, int minCount, int maxCount, Biome.BiomeCategory... biomeCategories)
//	{
//
//		// Goes through each entry in the biomeCategories and sees if it matches the current biome category the biome we are loading has
//
////		for (Biome.BiomeCategory category: biomeCategories )
////		{
////			if(event.getCategory().equals(category))
////			{
////				addEntityToAllBiomes(event, type, weight, minCount, maxCount);
////			}
////		}
//
//		if(event.getCategory().equals(Biome.BiomeCategory.PLAINS)
//				|| event.getCategory().equals(Biome.BiomeCategory.FOREST)
//				|| event.getCategory().equals(Biome.BiomeCategory.MOUNTAIN)
//				|| event.getCategory().equals(Biome.BiomeCategory.MUSHROOM))
//		{
//			List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//			base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//		}
//	}
//
//	private static void addEntityToAllOverworldBiomes(BiomeLoadingEvent event, EntityType<?> type,
//													  int weight, int minCount, int maxCount)
//	{
//		if(!event.getCategory().equals(Biome.BiomeCategory.THEEND) && !event.getCategory().equals(Biome.BiomeCategory.NETHER))
//		{
//			addEntityToAllBiomes(event, type, weight, minCount, maxCount);
//		}
//	}
//
//	private static void addEntityToAllBiomesNoNether(BiomeLoadingEvent event, EntityType<?> type,
//													 int weight, int minCount, int maxCount)
//	{
//		if(!event.getCategory().equals(Biome.BiomeCategory.NETHER)) {
//			List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//			base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//		}
//	}
//
//	private static void addEntityToAllBiomesNoEnd(BiomeLoadingEvent event, EntityType<?> type,
//												  int weight, int minCount, int maxCount)
//	{
//		if(!event.getCategory().equals(Biome.BiomeCategory.THEEND))
//		{
//			List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//			base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//		}
//	}
//
//	private static void addEntityToAllBiomes(BiomeLoadingEvent event, EntityType<?> type,
//											 int weight, int minCount, int maxCount)
//	{
//		List<MobSpawnSettings.SpawnerData> base = event.getSpawns().getSpawner(type.getCategory());
//		base.add(new MobSpawnSettings.SpawnerData(type,weight, minCount, maxCount));
//	}
}
