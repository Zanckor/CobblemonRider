package net.pokepalms.palmsmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import eu.pb4.sgui.api.elements.GuiElementInterface;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.config.slot.HeadSlot;
import net.pokepalms.palmsmod.config.slot.SlotPreset;
import org.spongepowered.include.com.google.gson.annotations.SerializedName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfilesConfig {
    public static final Gson GSON = new GsonBuilder().setLenient().setPrettyPrinting().disableHtmlEscaping().create();

    public static ProfilesConfig instance;

    @SerializedName("blacklisted_dimensions")
    public ArrayList<String> blacklistedDimensions = new ArrayList<>();
    public String format = "default";

    public LinkedHashMap<String, ProfileFormat> formats = new LinkedHashMap<>(Map.of("default", new ProfileFormat()));

    @Expose(serialize = false, deserialize = false)
    public static LinkedHashMap<String, SlotPreset> hardcodedPresets = new LinkedHashMap<>(Map.of(
            "player_head", new HeadSlot()
    ));

    @Expose(serialize = false, deserialize = false)
    public static LinkedHashMap<String, GuiElementInterface.ClickCallback> hardcodedEvents = new LinkedHashMap<>(Map.of(
            "close_gui", (index, type, action, gui) -> gui.close()
    ));

    public ProfileFormat getFormat(String name) {
        for(var entry : formats.entrySet()) {
            if(entry.getKey().equals(name)) return entry.getValue();
        }
        return null;
    }

    public ProfileFormat getFormat() {
        return getFormat(format);
    }

    public static boolean isBlacklistedDimension(Identifier dimension) {
        for(var dim : instance.blacklistedDimensions) {
            if(dim.equals(dimension.toString())) return true;
        }
        return false;
    }

    public static GuiElementInterface.ClickCallback getEvent(String name) {
        for(var entry : hardcodedEvents.entrySet()) {
            if(entry.getKey().equals(name)) return entry.getValue();
        }
        return (index, type, action, gui) -> {};
    }

    public static void reload() {
        instance = read();
    }

    public static void registerEvent(String name, GuiElementInterface.ClickCallback callback) {
        hardcodedEvents.put(name, callback);
    }

    static ProfilesConfig read() {
        String filePath = FabricLoader.getInstance().getConfigDir().toString() + "\\palmsmod";
        new File(filePath).mkdirs();
        filePath = filePath + "\\player-profiles.json";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));
            return GSON.fromJson(reader, ProfilesConfig.class);
        }
        catch(FileNotFoundException e) {
            PalmsMod.LOGGER.info("File " + filePath + " is not found! Setting to default.");
            var conf = new ProfilesConfig();
            conf.save();
            return conf;
        }
        catch(Exception e) {
            PalmsMod.LOGGER.info("Failed to read player-profiles.json config due to an exception. " +
                    "Please delete player-profiles.json to regenerate config or fix the issue:\n" + e);
            e.printStackTrace();
            System.exit(0);
            return new ProfilesConfig();
        }
    }

    public void save() {
        try {
            String filePath = FabricLoader.getInstance().getConfigDir().toString() + "\\palmsmod";
            new File(filePath).mkdirs();
            filePath = filePath + "\\player-profiles.json";
            try(FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
                writer.write(GSON.toJson(this));
            }
        }
        catch(Exception e) {
            PalmsMod.LOGGER.info("Failed to save player-profiles.json config due to an exception:\n" + e);
        }
    }
}
