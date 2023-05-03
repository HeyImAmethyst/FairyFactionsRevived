package com.heyimamethyst.fairyfactions.core.datagen;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.common.items.ModSpawnEggItem;
import com.heyimamethyst.fairyfactions.core.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider
{
	public ModItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, FairyFactions.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		spawnEggItem(ModItems.FAIRY_SPAWN_EGG);

	}
	
	private ItemModelBuilder spawnEggItem(RegistryObject<ForgeSpawnEggItem> item)
	{
		return withExistingParent(item.getId().getPath(),
						new ResourceLocation("item/template_spawn_egg"));
	}
}
