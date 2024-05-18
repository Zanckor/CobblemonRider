package dev.zanckor.cobblemonridingfabric.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public abstract class AbstractPacket {
    public abstract PacketByteBuf encode();
    public abstract Identifier getID();
}