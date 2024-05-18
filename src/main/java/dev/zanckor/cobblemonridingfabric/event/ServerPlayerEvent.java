package dev.zanckor.cobblemonridingfabric.event;

import com.google.gson.Gson;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.network.SendPacket;
import dev.zanckor.cobblemonridingfabric.network.packet.ConfigS2CPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ServerPlayerEvent {

    public static void playerJoin() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            SendPacket.TO_CLIENT(handler.player, new ConfigS2CPacket(loadConfig()));
        });
    }

    public static PokemonJsonObject loadConfig() {
        File pokemonRideConfigFile = CobblemonRidingFabric.PokemonRideConfigFile;
        String pokemonRideConfig = null;

        try {
            if (pokemonRideConfigFile != null)
                pokemonRideConfig = new String(Files.readAllBytes(pokemonRideConfigFile.toPath()));
        } catch (IOException e) {
            CobblemonRidingFabric.LOGGER.info("Error reading cobblemon pokemon ride config file" + pokemonRideConfigFile);

            return null;
        }

        return pokemonRideConfig != null ? new Gson().fromJson(pokemonRideConfig, PokemonJsonObject.class) : null;
    }
}