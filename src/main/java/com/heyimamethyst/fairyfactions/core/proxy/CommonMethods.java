package com.heyimamethyst.fairyfactions.core.proxy;

import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.core.network.ModNetwork;
import com.heyimamethyst.fairyfactions.core.network.PacketSetFairyName;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraftforge.network.PacketDistributor;

import java.util.List;

public class CommonMethods
{

    public void sendChat(ServerPlayer player, Component component )
    {
        if ( player != null && component != null && !component.getString().isEmpty() )
            player.displayClientMessage(component, false);
    }

    public <MSG> void sendToServer(MSG message)
    {
        ModNetwork.INSTANCE.sendToServer(message);
    }

    public <MSG> void sendToPlayer(MSG message, ServerPlayer serverPlayer)
    {
        ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), message);
    }

    public <MSG> void sendToAllPlayers(MSG message)
    {
        List<ServerPlayer> players = Minecraft.getInstance().player.getServer().getPlayerList().getPlayers();

        for( ServerPlayer player : players )
        {
            ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
        }
    }

    public void sendFairyRename(final FairyEntity fairy, final String name)
    {
        final PacketSetFairyName packet = new PacketSetFairyName( fairy, name );
        sendToServer( packet );
    }

    // Packet that handles fairy mounting.
    public void sendFairyMount(final Entity rider, final Entity vehicle)
    {
        final Entity newVehicle;

        if (rider.getVehicle() != null && rider.getVehicle() == vehicle)
        {
            newVehicle = null;
        }
        else
        {
            newVehicle = vehicle;
        }

        //final S1BPacketEntityAttach packet = new S1BPacketEntityAttach(0, rider, newVehicle);
        //sendToAllPlayers(packet);

        if (!(rider instanceof FishingHook))
        {
            if(newVehicle != null)
            {
                rider.startRiding(newVehicle);
            }
            else
            {
                rider.stopRiding();
            }

        }
    }

}
