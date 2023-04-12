package com.heyimamethyst.fairyfactions.core.datagen;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.core.registry.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.holdersets.AndHolderSet;

import java.util.Arrays;

public class ModWorldGenProvider extends WorldgenProvider
{
    ModWorldGenProvider(DataGenerator generator, ExistingFileHelper existingFileHelper)
    {
        super(generator, existingFileHelper, FairyFactions.MOD_ID);
    }

    @Override
    protected void generateConfiguredFeatures()
    {

    }

    @Override
    protected void generatePlacedFeatures()
    {

    }

    @Override
    protected void generateBiomeModifiers()
    {
        //biomeModifier("fairy_spawns", ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(and(biomeTag(Tags.Biomes.IS_PLAINS), biomeTag(BiomeTags.IS_FOREST), biomeTag(Tags.Biomes.IS_MOUNTAIN), biomeTag(Tags.Biomes.IS_MUSHROOM)), new MobSpawnSettings.SpawnerData(ModEntities.FAIRY_ENTITY.get(), 60, 1, 4)));
        //biomeModifier("fairy_spawns", ForgeBiomeModifiers.AddSpawnsBiomeModifier.singleSpawn(BiomeTags.IS_OVERWORLD, new MobSpawnSettings.SpawnerData(ModEntities.FAIRY_ENTITY.get(), 60, 1, 4)));
    }
}
