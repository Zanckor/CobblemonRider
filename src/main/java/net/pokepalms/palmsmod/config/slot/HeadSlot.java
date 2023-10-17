package net.pokepalms.palmsmod.config.slot;

import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pokepalms.palmsmod.config.ProfileFormat;

public class HeadSlot extends SlotPreset {

    public HeadSlot() {
        this.title = "%player:name%";
        this.item = "minecraft:player_head";
    }

    @Override
    public GuiElementBuilder buildElement(ProfileFormat format, ServerPlayerEntity player, ServerPlayerEntity profileOwner) {
        var builder = super.buildElement(format, player, profileOwner);
        builder.setItem(Items.PLAYER_HEAD);
        builder.getOrCreateNbt().put("SkullOwner", NbtHelper.writeGameProfile(new NbtCompound(), profileOwner.getGameProfile()));

        return builder;
    }
}
