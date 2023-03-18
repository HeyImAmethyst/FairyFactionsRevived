package com.heyimamethyst.fairyfactions.core.network;

import com.heyimamethyst.fairyfactions.FairyFactions;
import com.heyimamethyst.fairyfactions.common.entities.FairyEntity;
import com.heyimamethyst.fairyfactions.util.FairyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class PacketSetFairyName
{
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 16;

    private int fairyID;
    private UUID fairyUUID;

    private String name;

    public PacketSetFairyName(FairyEntity fairy, final String name)
    {
        this.fairyID = fairy.getId();
        this.fairyUUID = fairy.getUUID();
        this.name = name;
    }

    public PacketSetFairyName(FriendlyByteBuf buf)
    {
        fairyID = buf.readInt();

        if (buf.readBoolean())
            fairyUUID = new UUID(buf.readLong(), buf.readLong());
        else
            fairyUUID = null;

        try
        {
            name = buf.readUtf(MAX_NAME_LENGTH).trim();

            if( name.length() < MIN_NAME_LENGTH )
            {
                name = "";
            }
        }
        catch (Exception e)
        {
            name = "";
        }
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        FairyFactions.LOGGER.info("PacketSetFairyName.encoding");
        //final FriendlyByteBuf buf = this.getData();

        buf.writeInt(this.fairyID);

        if (fairyUUID != null)
            buf.writeBoolean(true).writeLong(fairyUUID.getMostSignificantBits()).writeLong(fairyUUID.getLeastSignificantBits());
        else
            buf.writeBoolean(false);

        try
        {
            buf.writeUtf(this.name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() ->
        {
            // HERE WE ARE ON THE SERVER!

            FairyFactions.LOGGER.info("PacketSetFairyName.handle");

            final ServerPlayer player = context.getSender();
            final ServerLevel level = player.getLevel();

            if( player != null )
            {
                final FairyEntity fairy = FairyFactions.getFairyFromID(player, this.fairyID);
                //final FairyEntity fairy = FairyFactions.getFairyFromUUID(player, this.fairyUUID);

                if( fairy == null )
                {
                    FairyFactions.LOGGER.warn("Unable to find fairy "+this.fairyUUID+" to rename.");
                    return;
                }

                final String username = player.getGameProfile().getName();
                final String rulername = fairy.rulerName();

                if(fairy.nameEnabled() && rulername.equals(username))
                {
                    FairyUtils.nameFairyEntity(fairy, this.name);
                }
                else
                {
                    FairyFactions.LOGGER.warn("Attempt by '"+username+"' to rename fairy owned by '"+rulername+"'");
                }

                fairy.setNameEnabled(false);
            }
        });

        context.setPacketHandled(true);
        return true;
    }
}
