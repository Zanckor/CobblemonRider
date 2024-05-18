package dev.zanckor.cobblemonridingfabric.network;

import dev.zanckor.cobblemonridingfabric.network.packet.ConfigS2CPacket;
import dev.zanckor.cobblemonridingfabric.network.packet.KeyC2SPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import static dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric.MODID;

public class NetworkHandler {
    public static final Identifier CONFIG_PACKET = new Identifier(MODID, "config_packet");
    public static final Identifier KEY_PACKET = new Identifier(MODID, "key_packet");


    public static void registerServerReceiverPacket() {
        ServerPlayNetworking.registerGlobalReceiver(KEY_PACKET, KeyC2SPacket::receive);
    }

    public static void registerClientReceiverPacket() {
        ClientPlayNetworking.registerGlobalReceiver(CONFIG_PACKET, ConfigS2CPacket::receive);
    }
}