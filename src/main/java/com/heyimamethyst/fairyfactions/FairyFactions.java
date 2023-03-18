package com.heyimamethyst.fairyfactions;

import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.core.network.ModNetwork;
import com.heyimamethyst.fairyfactions.core.proxy.ClientMethods;
import com.heyimamethyst.fairyfactions.core.proxy.CommonMethods;
import com.heyimamethyst.fairyfactions.core.registry.*;
import com.heyimamethyst.fairyfactions.events.mod.ModEvents;
import com.mojang.logging.LogUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.UUID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FairyFactions.MOD_ID)
public class FairyFactions
{
    public static final String MOD_ID = "fairyfactions";

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static CommonMethods commonMethods = new CommonMethods();
    public static ClientMethods clientMethods = new ClientMethods();

    public FairyFactions()
    {
        // Register the setup method for modloading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModEntities.Init(modEventBus);
        ModItems.Init(modEventBus);
        ModSounds.Init(modEventBus);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FairyConfig.SPEC, "fairyfactions-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModEvents::gen);
    }

    private void setup(final FMLCommonSetupEvent event)
    {

        event.enqueueWork(() ->
        {
            ModNetwork.registerMessages();
            ModSpawns.registerSpawns();
        });

        FairyConfig.BEHAVIOR_PURSUE_RANGE.set(FairyConfig.BEHAVIOR_PATH_RANGE.get().doubleValue() * FairyConfig.pursue_range_mult.get().doubleValue());
        FairyConfig.BEHAVIOR_DEFEND_RANGE.set(FairyConfig.BEHAVIOR_PATH_RANGE.get().doubleValue() * FairyConfig.defend_range_mult.get().doubleValue());

    }

    /**
     * Find a fairy in the world by entity id. This method was in the base class
     * in the original mod, and I can't find a better place to put it for now...
     *
     * @param player
     * @param fairyID
     * @return The fairy in question, null if not found.
     */
    public static FairyEntity getFairyFromID(ServerPlayer player, int fairyID)
    {
        MinecraftServer server = player.getServer();

        for( ServerLevel dim : server.getAllLevels())
        {
            if (dim != null)
            {
//                List<Entity> entities = new ArrayList<>();
//                entities.add(dim.getEntities().getAll().iterator().next());

//                if (entities != null && !entities.isEmpty())
//                {
//                    for (Entity entity : entities)
//                    {
//                        if (entity instanceof FairyEntity && entity.getId() == fairyID)
//                            return (FairyEntity) entity;
//                    }
//                }

                Entity entity = dim.getEntities().get(fairyID);

                if (entity instanceof FairyEntity && entity.getId() == fairyID)
                    return (FairyEntity) entity;
            }
        }

        return null;
    }

    /**
     * Find a fairy in the world by entity uuid.
     *
     * @param player
     * @param fairyUUID
     * @return The fairy in question, null if not found.
     */
    public static FairyEntity getFairyFromUUID(ServerPlayer player, UUID fairyUUID)
    {
        MinecraftServer server = player.getServer();

        for( ServerLevel dim : server.getAllLevels())
        {
            if (dim != null)
            {
//                List<Entity> entities = new ArrayList<>();
//                entities.add(dim.getEntities().getAll().iterator().next());
//
//                if (entities != null && !entities.isEmpty())
//                {
//                    for (Entity entity : entities)
//                    {
//                        if (entity instanceof FairyEntity && entity.getUUID() == fairyUUID)
//                            return (FairyEntity) entity;
//                    }
//                }

                Entity entity = dim.getEntities().get(fairyUUID);

                if (entity instanceof FairyEntity && entity.getUUID() == fairyUUID)
                    return (FairyEntity) entity;
            }
        }

        return null;
    }
}
