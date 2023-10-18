package net.pokepalms.palmsmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import software.bernie.geckolib.network.AbstractPacket;

import java.io.IOException;

public class SendPacket {

    public static void NEAR(PlayerEntity sender, AbstractPacket packet, double radius) {
        try {
            if (sender instanceof ServerPlayerEntity serverPlayer) {
                ServerWorld level = serverPlayer.getServerWorld();

                for (ServerPlayerEntity nearPlayer : PlayerLookup.around(level, sender.getPos(), radius)) {
                    ServerPlayNetworking.send(nearPlayer, packet.getPacketID(), packet.encode());
                }

                level.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void LEVEL(PlayerEntity sender, AbstractPacket packet) {
        sender.getWorld().getPlayers().forEach(player -> {
            ServerPlayNetworking.send((ServerPlayerEntity) player, packet.getPacketID(), packet.encode());
        });
    }

    public static void TO_CLIENT(PlayerEntity player, AbstractPacket packet) {
        ServerPlayNetworking.send((ServerPlayerEntity) player, packet.getPacketID(), packet.encode());
    }

    public static void TO_SERVER(AbstractPacket packet) {
        ClientPlayNetworking.send(packet.getPacketID(), packet.encode());
    }
}
