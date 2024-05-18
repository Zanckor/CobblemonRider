package dev.zanckor.cobblemonridingfabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.event.ServerPlayerEvent;
import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject.MountType.*;

public class CobblemonRidingFabric implements ModInitializer {
    public static final String MODID = "cobblemonridingfabric";
    public static final org.slf4j.Logger LOGGER = LogUtils.getLogger();
    public static File PokemonRideConfigFile;
    public static PokemonJsonObject pokemonJsonObject;


    @Override
    public void onInitialize() {
        registerEvents();
        NetworkHandler.registerServerReceiverPacket();
    }

    private void registerEvents() {
        ServerPlayerEvent.playerJoin();
        ServerPlayerEvent.loadConfig();

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            PokemonRideConfigFile = new File(server.getRunDirectory(), "pokemonRideConfig.json");
            serverFolderManager(server);
        });
    }

    @SuppressWarnings("all")
    public static void serverFolderManager(MinecraftServer server) {
        Path serverDirectory = server.getSavePath(WorldSavePath.ROOT).toAbsolutePath();
        File pokemonRideConfig = Paths.get(serverDirectory.toString(), "pokemonRideConfig.json").toFile();
        PokemonRideConfigFile = pokemonRideConfig;

        PokemonJsonObject pokemonJsonObject = new PokemonJsonObject();

        pokemonJsonObject.add("Charizard",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Blastoise",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Pidgeot",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.7f, 0.0f))));

        pokemonJsonObject.add("Fearow",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Nidoking",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Ninetales",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f))));

        pokemonJsonObject.add("Arcanine",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Tentacruel",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.5f, 0.0f))));

        pokemonJsonObject.add("Rapidash",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.8f, 0.0f))));

        pokemonJsonObject.add("Magnezone",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Dodrio",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.2f, 0.0f))));

        pokemonJsonObject.add("Dewgong",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f))));

        pokemonJsonObject.add("Exeggutor",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 3.0f, 0.0f))));

        pokemonJsonObject.add("Tauros",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Gyarados",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f))));

        pokemonJsonObject.add("Lapras",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 0.0f, 0.0f))));

        pokemonJsonObject.add("Aerodactyl",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Dragonite",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Meganium",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Feraligatr",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Steelix",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 6.0f, 0.0f))));

        pokemonJsonObject.add("Stantler",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Swampert",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Mightyena",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Sharpedo",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Wailmer",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Camerupt",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Relicanth",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Metagross",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Staraptor",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Luxray",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Garchomp",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Yanmega",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Mamoswine",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 5.0f, 0.0f))));

        pokemonJsonObject.add("Samurott",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Stoutland",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Scolipede",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Sawsbuck",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Golurk",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 5.0f, 0.0f))));

        pokemonJsonObject.add("Bouffalant",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Avalugg",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, SWIM)),
                        new ArrayList<>(Arrays.asList(0.0f, 3.0f, 0.0f))));

        pokemonJsonObject.add("Mudsdale",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 2.0f, 0.0f))));

        pokemonJsonObject.add("Corviknight",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK, FLY)),
                        new ArrayList<>(Arrays.asList(0.0f, 3.0f, 0.0f))));

        pokemonJsonObject.add("Wyrdeer",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Ursaluna",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Skeledirge",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        pokemonJsonObject.add("Espahtla",
                new PokemonJsonObject.PokemonConfigData(
                        new ArrayList<>(List.of(WALK)),
                        new ArrayList<>(Arrays.asList(0.0f, 1.0f, 0.0f))));

        if (pokemonRideConfig.exists()) {
            LOGGER.info("Cobblemon pokemon ride config file already exists at " + pokemonRideConfig);
        } else {
            try (FileWriter file = new FileWriter(pokemonRideConfig)) {
                file.write(new GsonBuilder().setPrettyPrinting().create().toJson(pokemonJsonObject));
                LOGGER.info("File created: " + pokemonRideConfig.getName());

                LOGGER.info("Cobblemon pokemon ride config file created at " + pokemonRideConfig);
            } catch (
                    IOException ex) {
                ex.printStackTrace();
                LOGGER.info("Error creating cobblemon pokemon ride config file" + pokemonRideConfig);
            }
        }

        String pokemonRideConfigObject;

        try {
            pokemonRideConfigObject = new String(Files.readAllBytes(pokemonRideConfig.toPath()));
            pokemonJsonObject = new Gson().fromJson(pokemonRideConfigObject, PokemonJsonObject.class);
        } catch (IOException ex) {
            LOGGER.info("Error reading cobblemon pokemon ride config file" + pokemonRideConfig);
        }
    }
}