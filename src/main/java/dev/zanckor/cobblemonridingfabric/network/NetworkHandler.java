package dev.zanckor.cobblemonridingfabric.network;

import dev.zanckor.cobblemonridingfabric.network.payload.ConfigS2CPayload;
import dev.zanckor.cobblemonridingfabric.network.payload.KeyC2SPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;

import static dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric.MODID;

public class NetworkHandler {
    public static final Identifier CONFIG_PACKET = Identifier.of(MODID, "config_packet");
    public static final Identifier KEY_PACKET = Identifier.of(MODID, "key_packet");


    public static void registerPayload() {
        PayloadTypeRegistry.playC2S().register(KeyC2SPayload.ID, KeyC2SPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ConfigS2CPayload.ID, ConfigS2CPayload.CODEC);
    }
}