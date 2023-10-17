package net.pokepalms.palmsmod.gui;

import eu.pb4.placeholders.api.PlaceholderContext;
import eu.pb4.placeholders.api.Placeholders;
import eu.pb4.placeholders.api.TextParserUtils;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pokepalms.palmsmod.config.ProfileFormat;
import net.pokepalms.palmsmod.config.ProfilesConfig;

import java.util.UUID;

public class ProfileGui extends SimpleGui {

    public UUID profileOwner;

    public ProfileGui(ServerPlayerEntity player, ServerPlayerEntity profileOwner) {
        super(ScreenHandlerType.GENERIC_9X6, player, false);
        this.profileOwner = profileOwner.getUuid();
    }

    public static void open(ServerPlayerEntity player, ServerPlayerEntity profileOwner) {
        var gui = new ProfileGui(player, profileOwner);

        ProfileFormat format = ProfilesConfig.instance.getFormat(ProfilesConfig.instance.format);
        gui.setTitle(Placeholders.parseText(TextParserUtils.formatText(format.title), PlaceholderContext.of(profileOwner)));

        int index = 0;

        for(String[] line : format.grid) {
            for(int i = 0; i <= 8; i++) {
                var element = format.getSlotPreset(line[i]).buildElement(format, player, profileOwner);
                if(!element.asStack().isEmpty()) gui.setSlot(index, element);
                index++;
            }
        }
        gui.open();
    }

}
