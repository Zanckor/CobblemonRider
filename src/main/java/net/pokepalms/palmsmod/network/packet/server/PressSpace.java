package net.pokepalms.palmsmod.network.packet.server;


import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPacketListener;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.mixininterface.IEntityData;
import net.pokepalms.palmsmod.network.NetworkHandler;
import software.bernie.geckolib.network.AbstractPacket;


public class PressSpace extends AbstractPacket {

    @Override
    public PacketByteBuf encode() {
        return PacketByteBufs.create();
    }

    @Override
    public Identifier getPacketID() {
        return NetworkHandler.PRESS_SPACE;
    }

    public static void receive(MinecraftServer server, PlayerEntity player, ServerPacketListener handler, PacketByteBuf buffer, PacketSender sender) {
        server.execute(() -> {
            NbtCompound tag = ((IEntityData) player).getPersistentData();
            tag.putBoolean("press_space", true);
        });
    }
}
