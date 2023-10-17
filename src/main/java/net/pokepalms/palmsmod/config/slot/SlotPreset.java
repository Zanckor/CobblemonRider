package net.pokepalms.palmsmod.config.slot;

import com.google.gson.annotations.Expose;
import eu.pb4.placeholders.api.PlaceholderContext;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.TextParserUtils;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.StringNbtReader;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.config.ProfileFormat;
import net.pokepalms.palmsmod.config.ProfilesConfig;

import java.util.ArrayList;
import java.util.List;

public class SlotPreset {
    public String parent;
    public String title;
    public String item;
    public String permission;
    public String event;
    public String nbt;
    public String[] lore;
    public String fallback;

    @Expose(deserialize = false, serialize = false)
    public String name;

    public static final SlotPreset EMPTY = new SlotPreset();

    public SlotPreset() {
    }

    public GuiElementBuilder buildElement(ProfileFormat format, ServerPlayerEntity player, ServerPlayerEntity profileOwner) {
        GuiElementBuilder builder = GuiElementBuilder.from(Items.LIGHT_GRAY_STAINED_GLASS_PANE.getDefaultStack()).setName(Text.of(""));

        if(permission == null || Permissions.check(player, permission)) {

            if (parent != null) {
                var slot = format.getSlotPreset(parent);

                if(slot.name.equals(this.name)) {
                    PalmsMod.LOGGER.error("Slot can't parenting itself: " + slot.name);
                }
                else builder = slot.buildElement(format, player, profileOwner);
            }

            if(item != null) Registries.ITEM.getOrEmpty(new Identifier(Placeholders.parseText(Text.of(this.item), PlaceholderContext.of(profileOwner)).getString())).ifPresent(builder::setItem);
            if(event != null) builder.setCallback(ProfilesConfig.getEvent(this.event));
            if(title != null) builder.setName(Placeholders.parseText(TextParserUtils.formatText(this.title), PlaceholderContext.of(profileOwner)));
            if(lore != null) builder.setLore(this.getLore(profileOwner));
            try {
                if(nbt != null) builder.getOrCreateNbt().copyFrom(StringNbtReader.parse(Placeholders.parseText(TextParserUtils.formatText(this.nbt), PlaceholderContext.of(profileOwner)).getString()));
            }
            catch (Exception e) {
                PalmsMod.LOGGER.error("Failed to parse nbt of the " + this.name + " slot: " + this.nbt);
                e.printStackTrace();
            }
        }
        else if(permission != null && !Permissions.check(player, permission) && this.fallback != null) {
            var backupSlot = format.getSlotPreset(fallback);

            if(backupSlot.name.equals(this.name)) {
                PalmsMod.LOGGER.error("Slot can't backup itself: " + backupSlot.name);
            }
            else builder = backupSlot.buildElement(format, player, profileOwner);
        }

        return builder;
    }

    public List<Text> getLore(ServerPlayerEntity profileOwner) {
        ArrayList<Text> list = new ArrayList<>();

        for(String str : lore) {
            list.add(Placeholders.parseText(TextParserUtils.formatText(str), PlaceholderContext.of(profileOwner)));
        }
        return list;
    }

    public static SlotPresetBuilder builder() {
        return new SlotPresetBuilder();
    }

    @SuppressWarnings("unused")
    public static class SlotPresetBuilder {
        public SlotPreset preset = new SlotPreset();

        public SlotPresetBuilder setParent(String parent) {
            preset.parent = parent;
            return this;
        }

        public SlotPresetBuilder setTitle(String title) {
            preset.title = title;
            return this;
        }

        public SlotPresetBuilder setItem(Item item) {
            preset.item = Registries.ITEM.getId(item).toString();
            return this;
        }

        public SlotPresetBuilder setItem(String itemId) {
            preset.item = itemId;
            return this;
        }

        public SlotPresetBuilder setPermission(String permission) {
            preset.permission = permission;
            return this;
        }

        public SlotPresetBuilder setEvent(String event) {
            preset.event = event;
            return this;
        }

        public SlotPresetBuilder setLore(String[] lore) {
            preset.lore = lore;
            return this;
        }

        public SlotPresetBuilder setNbt(String nbt) {
            preset.nbt = nbt;
            return this;
        }

        public SlotPresetBuilder setBackup(String backup) {
            preset.fallback = backup;
            return this;
        }

        public SlotPreset build() {
            return preset;
        }
    }
}
