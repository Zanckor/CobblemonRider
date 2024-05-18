package dev.zanckor.cobblemonridingfabric.network.packet;


import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import dev.zanckor.cobblemonridingfabric.network.AbstractPacket;
import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.io.Serializable;

public class KeyC2SPacket extends AbstractPacket {
    private final Key key;

    public KeyC2SPacket(Key key) {
        this.key = key;
    }

    @Override
    public PacketByteBuf encode() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeEnumConstant(key);

        return buf;
    }

    @Override
    public Identifier getID() {
        return NetworkHandler.KEY_PACKET;
    }

    public static void receive(MinecraftServer server, PlayerEntity player, ServerPacketListener handler, PacketByteBuf buffer, PacketSender sender) {
        NbtCompound tag = ((IEntityData) player).getPersistentData();
        Key key = buffer.readEnumConstant(Key.class);

        switch (key) {
            case SPACE -> tag.putBoolean("press_space", true);
            case SPRINT -> tag.putBoolean("press_sprint", true);
            case SHIFT -> tag.putBoolean("press_shift", true);
            case POKEMON_DISMOUNT -> {
                tag.putBoolean("pokemon_dismount", true);
                player.stopRiding();
            }
            case POKEMON_MOUNT_ENTITIES -> tag.putBoolean("pokemon_mount_entities", true);
        }
    }

    public enum Key implements Serializable {
        SPACE,
        SPRINT,
        SHIFT,
        POKEMON_DISMOUNT,
        POKEMON_MOUNT_ENTITIES
    }
}
