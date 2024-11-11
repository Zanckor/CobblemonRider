package dev.zanckor.cobblemonridingfabric.network.payload;

import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record KeyC2SPayload(int ordinal) implements CustomPayload {
    public static final Id<KeyC2SPayload> ID = new Id<>(NetworkHandler.KEY_PACKET);
    public static final PacketCodec<RegistryByteBuf, KeyC2SPayload> CODEC = PacketCodec.tuple(PacketCodecs.INTEGER, KeyC2SPayload::ordinal, KeyC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
