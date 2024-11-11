package dev.zanckor.cobblemonridingfabric.network.handler.server;

import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import dev.zanckor.cobblemonridingfabric.network.payload.KeyC2SPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;

import java.io.Serializable;

public class KeyReceiver {

    public static void receive() {
        ServerPlayNetworking.registerGlobalReceiver(KeyC2SPayload.ID, (keyC2SPayload, context) -> {
            context.server().execute(() -> {
                NbtCompound tag = ((IEntityData) context.player()).cobblemonRider$getPersistentData();
                Key key = Key.values()[keyC2SPayload.ordinal()];

                switch (key) {
                    case SPACE -> tag.putBoolean("press_space", true);
                    case SPRINT -> tag.putBoolean("press_sprint", true);
                    case SHIFT -> tag.putBoolean("press_shift", true);
                    case POKEMON_DISMOUNT -> {
                        tag.putBoolean("pokemon_dismount", true);
                        context.player().stopRiding();
                    }
                    case POKEMON_MOUNT_ENTITIES -> tag.putBoolean("pokemon_mount_entities", true);
                }
            });
        });
    }

    public enum Key implements Serializable {
        SPACE,
        SPRINT,
        SHIFT,
        POKEMON_DISMOUNT,
        POKEMON_MOUNT_ENTITIES
    }
}