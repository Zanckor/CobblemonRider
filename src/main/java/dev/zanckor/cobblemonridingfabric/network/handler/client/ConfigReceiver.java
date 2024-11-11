package dev.zanckor.cobblemonridingfabric.network.handler.client;

import com.google.gson.Gson;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.network.payload.ConfigS2CPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class ConfigReceiver {

    public static void receive() {
        ClientPlayNetworking.registerGlobalReceiver(ConfigS2CPayload.ID, (configS2CPayload, context) -> {
            context.client().execute(() -> {
                CobblemonRidingFabric.pokemonJsonObject = new Gson().fromJson(configS2CPayload.pokemonJson(), PokemonJsonObject.class);
            });
        });
    }
}
