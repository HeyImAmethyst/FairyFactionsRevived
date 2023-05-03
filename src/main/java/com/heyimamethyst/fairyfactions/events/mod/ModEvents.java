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
import net.minecraftforge.event.CreativeModeTabEvent;
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

//	public static void registerCreativeModTab(CreativeModeTabEvent.Register event)
//	{
//
//	}

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
}
