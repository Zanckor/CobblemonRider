package dev.zanckor.cobblemonridingfabric.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.io.IOException;

public class SendPacket {

    public static void NEAR(PlayerEntity sender, CustomPayload payload, double radius) {
        try {
            if (sender instanceof ServerPlayerEntity serverPlayer) {
                ServerWorld level = serverPlayer.getServerWorld();

                for (ServerPlayerEntity nearPlayer : PlayerLookup.around(level, sender.getPos(), radius)) {
                    ServerPlayNetworking.send(nearPlayer, payload);
                }

                level.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void LEVEL(PlayerEntity sender, CustomPayload payload) {
        sender.getWorld().getPlayers().forEach(player ->
                ServerPlayNetworking.send((ServerPlayerEntity) player, payload));
    }

    public static void TO_CLIENT(PlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, payload);
    }

    public static void TO_SERVER(CustomPayload payload) {
        ClientPlayNetworking.send(payload);
    }
}