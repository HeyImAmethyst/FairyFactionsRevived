package com.heyimamethyst.fairyfactions.core.registry;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.common.items.ModSpawnEggItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FairyFactions.MOD_ID);

    public static void Init(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
    }

    public static final RegistryObject<ForgeSpawnEggItem> FAIRY_SPAWN_EGG = ITEMS.register("fairy_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FAIRY_ENTITY, 0xea8fde, 0x8658bf, new Item.Properties()));

}
