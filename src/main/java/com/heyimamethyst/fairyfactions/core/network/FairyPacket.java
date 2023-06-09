package com.heyimamethyst.fairyfactions.core.network;

import com.heyimamethyst.fairyfactions.FairyFactions;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class FairyPacket //extends ServerboundCustomPayloadPacket
{

    protected FairyPacket(FairyEventListener.PacketType packetType)
    {
        //super(new ResourceLocation(FairyFactions.MOD_ID), new FriendlyByteBuf(Unpooled.buffer()));
        //final FriendlyByteBuf buf = this.getData();
        //buf.writeByte(packetType.packet_id);
    }

    public abstract void toBytes(FriendlyByteBuf buf);
    public abstract void handlePacket(Supplier<NetworkEvent.Context> supplier);

}
