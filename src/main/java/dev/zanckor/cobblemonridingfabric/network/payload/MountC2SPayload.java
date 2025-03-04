package dev.zanckor.cobblemonridingfabric.network.payload;

import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record MountC2SPayload(UUID pokemonID) implements CustomPayload {
    public static final Id<MountC2SPayload> ID = new Id<>(NetworkHandler.MOUNT_PACKET);
    public static final PacketCodec<RegistryByteBuf, MountC2SPayload> CODEC = PacketCodec.tuple(
            Uuids.PACKET_CODEC, MountC2SPayload::pokemonID,
            MountC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
