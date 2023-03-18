package com.heyimamethyst.fairyfactions.core.registry;

import com.heyimamethyst.fairyfactions.FairyFactions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, FairyFactions.MOD_ID);

    public static void Init(IEventBus modEventBus)
    {
        SOUND_EVENTS.register(modEventBus);
    }

    public static RegistryObject<SoundEvent> FAIRY_IDLE = registerSoundEvent("fairy_idle");
    public static RegistryObject<SoundEvent> FAIRY_QUEEN_IDLE = registerSoundEvent("fairy_queen_idle");

    public static RegistryObject<SoundEvent> FAIRY_HURT = registerSoundEvent("fairy_hurt");
    public static RegistryObject<SoundEvent> FAIRY_QUEEN_HURT = registerSoundEvent("fairy_queen_hurt");

    public static RegistryObject<SoundEvent> FAIRY_DEATH = registerSoundEvent("fairy_death");
    public static RegistryObject<SoundEvent> FAIRY_QUEEN_DEATH = registerSoundEvent("fairy_queen_death");

    public static RegistryObject<SoundEvent> FAIRY_ANGRY = registerSoundEvent("fairy_angry");
    public static RegistryObject<SoundEvent> FAIRY_QUEEN_ANGRY = registerSoundEvent("fairy_queen_angry");


    private static RegistryObject<SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(FairyFactions.MOD_ID, name)));
    }
}
