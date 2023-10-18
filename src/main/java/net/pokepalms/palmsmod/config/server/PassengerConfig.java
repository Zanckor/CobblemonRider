package net.pokepalms.palmsmod.config.server;

import com.google.gson.Gson;
import com.mojang.datafixers.util.Pair;
import net.pokepalms.palmsmod.config.ConfigRegistry;
import net.pokepalms.palmsmod.config.SimpleConfig;
import net.pokepalms.palmsmod.config.passengerConfigObject.MountType;
import net.pokepalms.palmsmod.config.passengerConfigObject.PokemonJsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.pokepalms.palmsmod.PalmsMod.MOD_ID;

public class PassengerConfig {
    public static SimpleConfig config;
    private static ConfigRegistry configs;
    static Gson gson = new Gson().newBuilder().create();

    public static String PASSENGER_POSITION;


    public static void register() throws IOException {
        configs = new ConfigRegistry();
        createConfig();
        config = SimpleConfig.of(MOD_ID + "server_config").provider(configs).request();

        assignConfig();
    }


    private static void createConfig() {
        ArrayList<Float> offSet = new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f));
        ArrayList<MountType> mountType = new ArrayList<>(List.of(MountType.WALK));

        PokemonJsonObject pokemonJsonObject = new PokemonJsonObject("test", new PokemonJsonObject.PokemonConfigData(mountType, offSet));

        configs.addPairData(new Pair<>("key.palms.passenger-config", gson.toJson(pokemonJsonObject)), "Position of the passenger on the entity, and the type of mount it is.");
    }

    private static void assignConfig() {
        PASSENGER_POSITION = config.get("key.palms.passenger-config");
    }
}
