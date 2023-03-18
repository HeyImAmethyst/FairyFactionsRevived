package com.heyimamethyst.fairyfactions.core.network;

import com.heyimamethyst.fairyfactions.FairyFactions;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork
{
    public static SimpleChannel INSTANCE;

    private static int ID = 0;
    public static int nextID(){return ID++;}

    public static void registerMessages()
    {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(FairyFactions.MOD_ID, "network"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(PacketSetFairyName.class, nextID(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PacketSetFairyName::new)
                .encoder(PacketSetFairyName::toBytes)
                .consumer(PacketSetFairyName::handle)
                .add();
    }
}
