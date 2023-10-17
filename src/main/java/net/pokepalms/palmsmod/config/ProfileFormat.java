package net.pokepalms.palmsmod.config;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import net.minecraft.item.Items;
import net.pokepalms.palmsmod.config.slot.SlotPreset;

import java.util.*;

public class ProfileFormat {
    public String title = "%player:name%'s Profile";

    @JsonAdapter(SlotStackAdapter.class)
    public String[][] grid = new String[][]{
            new String[]{"", "", "", "", "", "", "", "", ""},
            new String[]{"", "", "equipped_head", "", "", "", "", "", ""},
            new String[]{"", "", "equipped_chest", "", "player_info", "", "equipped_mainhand", "equipped_offhand", ""},
            new String[]{"", "", "equipped_legs", "", "", "", "", "", ""},
            new String[]{"", "", "equipped_feet", "", "", "", "", "", ""},
            new String[]{"", "", "", "", "close", "", "", "", ""},
    };

    @SerializedName("slots")
    public LinkedHashMap<String, SlotPreset> slotPresets = new LinkedHashMap<>(Map.of(
            "equipped_head", SlotPreset.builder().setTitle("%player:equipment_slot head%").setItem("%palmsmod:equipment_slot/item_id head%").setNbt("%palmsmod:equipment_slot/item_nbt head%").build(),
            "equipped_chest", SlotPreset.builder().setTitle("%player:equipment_slot chest%").setItem("%palmsmod:equipment_slot/item_id chest%").setNbt("%palmsmod:equipment_slot/item_nbt chest%").build(),
            "equipped_legs", SlotPreset.builder().setTitle("%player:equipment_slot legs%").setItem("%palmsmod:equipment_slot/item_id legs%").setNbt("%palmsmod:equipment_slot/item_nbt legs%").build(),
            "equipped_feet", SlotPreset.builder().setTitle("%player:equipment_slot feet%").setItem("%palmsmod:equipment_slot/item_id feet%").setNbt("%palmsmod:equipment_slot/item_nbt feet%").build(),

            "equipped_mainhand", SlotPreset.builder().setTitle("%player:equipment_slot mainhand%").setItem("%palmsmod:equipment_slot/item_id mainhand%").setNbt("%palmsmod:equipment_slot/item_nbt mainhand%").build(),
            "equipped_offhand", SlotPreset.builder().setTitle("%player:equipment_slot offhand%").setItem("%palmsmod:equipment_slot/item_id offhand%").setNbt("%palmsmod:equipment_slot/item_nbt offhand%").build(),

            "filler", SlotPreset.builder().setItem(Items.WHITE_STAINED_GLASS_PANE).setTitle("").build(),
            "", SlotPreset.builder().setParent("filler").build(),
            "close", SlotPreset.builder().setEvent("close_gui").setItem(Items.BARRIER).setTitle("<red>Close").build(),
            "player_info", SlotPreset.builder().setParent("player_head").setLore(new String[]{"Health: %player:health%/%player:max_health%"}).build()
            ));

    public SlotPreset getSlotPreset(String name) {
        for(var entry : slotPresets.entrySet()) {
            if(entry.getKey().equals(name)) {
                entry.getValue().name = name;
                return entry.getValue();
            }
        }
        for(var entry : ProfilesConfig.hardcodedPresets.entrySet()) {
            if(entry.getKey().equals(name)) {
                entry.getValue().name = name;
                return entry.getValue();
            }
        }
        return SlotPreset.EMPTY;
    }
}
