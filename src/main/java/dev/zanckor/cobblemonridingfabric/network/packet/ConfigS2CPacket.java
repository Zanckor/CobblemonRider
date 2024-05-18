package dev.zanckor.cobblemonridingfabric.network.packet;

import com.google.gson.Gson;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.network.AbstractPacket;
import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ConfigS2CPacket extends AbstractPacket {
    private final PokemonJsonObject jsonObject;

    public ConfigS2CPacket(PokemonJsonObject json) {
        this.jsonObject = json;
    }

    @Override
    public PacketByteBuf encode() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(new Gson().toJson(this.jsonObject));

        return buf;
    }

    @Override
    public Identifier getID() {
        return NetworkHandler.CONFIG_PACKET;
    }

    public static void receive(MinecraftClient client, ClientPlayPacketListener handler, PacketByteBuf buffer, PacketSender responseSender) {
        CobblemonRidingFabric.pokemonJsonObject = new Gson().fromJson(buffer.readString(), PokemonJsonObject.class);
    }
}
