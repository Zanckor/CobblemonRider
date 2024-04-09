package dev.zanckor.cobblemonrider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import dev.zanckor.cobblemonrider.network.NetworkHandler;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.zanckor.cobblemonrider.config.PokemonJsonObject.MountType.*;

@Mod(CobblemonRider.MODID)
public class CobblemonRider {
    public static final String MODID = "cobblemonrider";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static File PokemonRideConfigFile;
    public static PokemonJsonObject pokemonJsonObject;

    public CobblemonRider() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        NetworkHandler.register();
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class FolderManager {

        /**
         * Each time that server starts <code> serverFolderManager </code> is called to create json files
         */
        @SubscribeEvent
        public static void serverFolderManager(ServerAboutToStartEvent e) {
            Path serverDirectory = e.getServer().getWorldPath(LevelResource.ROOT).toAbsolutePath();
            File pokemonRideConfig = Paths.get(serverDirectory.toString(), "serverconfig" + FileSystems.getDefault().getSeparator() + "pokemonRideConfig.json").toFile();
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
                CobblemonRider.pokemonJsonObject = new Gson().fromJson(pokemonRideConfigObject, PokemonJsonObject.class);;
            } catch (IOException ex) {
                CobblemonRider.LOGGER.info("Error reading cobblemon pokemon ride config file" + pokemonRideConfig);
            }
        }
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEventHandlerRegister {

        public static KeyMapping pokemonDismount;

        public static KeyMapping registerKey(String name, int keycode) {
            LOGGER.debug("Registering keys");

            final var key = new KeyMapping("key." + MODID + "." + name, keycode, "key.categories.Cobblemounts");

            return key;
        }

        @SubscribeEvent
        public static void keyInit(RegisterKeyMappingsEvent e) {
            pokemonDismount = registerKey("Pokemon Dismount", InputConstants.KEY_K);

            e.register(pokemonDismount);
        }
    }
}