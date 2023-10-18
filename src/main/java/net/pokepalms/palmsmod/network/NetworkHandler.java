package net.pokepalms.palmsmod.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.network.packet.server.PressSpace;

import static net.pokepalms.palmsmod.PalmsMod.MOD_ID;

public class NetworkHandler {
    public static final Identifier PRESS_SPACE = new Identifier(MOD_ID, "press_space");


    public static void registerServerReceiverPacket() {
        ServerPlayNetworking.registerGlobalReceiver(PRESS_SPACE, PressSpace::receive);
    }

    public static void registerClientReceiverPacket() {
    }
}
