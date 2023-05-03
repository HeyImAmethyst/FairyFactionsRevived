package com.heyimamethyst.fairyfactions.core.datagen;

import com.google.gson.JsonElement;
import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.core.registry.ModBiomeTags;
import com.heyimamethyst.fairyfactions.core.registry.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryOps;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.holdersets.AndHolderSet;

import java.util.Arrays;

public class ModWorldGenProvider //extends WorldgenProvider
{
//    ModWorldGenProvider(DataGenerator generator, ExistingFileHelper existingFileHelper, RegistryOps<JsonElement> ops)
//    {
//        super(generator, existingFileHelper, ops, FairyFactions.MOD_ID);
//    }
//
//    @Override
//    protected void generateConfiguredFeatures()
//    {
//
//    }
//
//    @Override
//    protected void generatePlacedFeatures()
//    {
//
//    }
//
//    @Override
//    protected void generateBiomeModifiers()
//    {
//        biomeModifier("fairy_spawns", ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(getBiomeTag(ModBiomeTags.IS_FAIRY_BIOME), new MobSpawnSettings.SpawnerData(ModEntities.FAIRY_ENTITY.get(), 40, 1, 4)));
//    }
}
