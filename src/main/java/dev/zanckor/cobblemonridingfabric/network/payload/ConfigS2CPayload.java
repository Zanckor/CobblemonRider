package dev.zanckor.cobblemonridingfabric.network.payload;

import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

import static net.minecraft.network.codec.PacketCodecs.string;

public record ConfigS2CPayload(String pokemonJson) implements CustomPayload {
    public static final CustomPayload.Id<ConfigS2CPayload> ID = new CustomPayload.Id<>(NetworkHandler.CONFIG_PACKET);
    public static final PacketCodec<RegistryByteBuf, ConfigS2CPayload> CODEC = PacketCodec.tuple(string(327670), ConfigS2CPayload::pokemonJson, ConfigS2CPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
