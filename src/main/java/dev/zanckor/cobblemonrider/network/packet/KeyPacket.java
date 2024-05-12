package dev.zanckor.cobblemonrider.network.packet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class KeyPacket {
    private final Key key;

    public KeyPacket(Key key) {
        this.key = key;
    }

    public KeyPacket(FriendlyByteBuf buffer) {
        key = buffer.readEnum(Key.class);
    }

    public void encodeBuffer(FriendlyByteBuf buffer) {
        buffer.writeEnum(this.key);
    }

    public static void handler(KeyPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();

            CompoundTag tag = Objects.requireNonNull(player).getPersistentData();

            switch (msg.key) {
                case SPACE -> tag.putBoolean("press_space", true);
                case SPRINT -> tag.putBoolean("press_sprint", true);
                case SHIFT -> tag.putBoolean("press_shift", true);
                case POKEMON_DISMOUNT -> {
                    tag.putBoolean("pokemon_dismount", true);
                    player.stopRiding();
                }
                case POKEMON_MOUNT_ENTITIES -> tag.putBoolean("pokemon_mount_entities", true);
            }
        });

        ctx.get().setPacketHandled(true);
    }

    public enum Key {
        SPACE,
        SPRINT,
        SHIFT,
        POKEMON_DISMOUNT,
        POKEMON_MOUNT_ENTITIES
    }
}
