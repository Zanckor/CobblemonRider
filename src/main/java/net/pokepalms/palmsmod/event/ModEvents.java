package net.pokepalms.palmsmod.event;

import eu.pb4.placeholders.api.TextParserUtils;
import eu.pb4.sgui.api.GuiHelpers;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.pokepalms.palmsmod.config.ProfilesConfig;
import net.pokepalms.palmsmod.extension.PlayerExtension;
import net.pokepalms.palmsmod.gui.ProfileGui;

public class ModEvents {

    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if(!world.isClient() && hand.equals(Hand.MAIN_HAND) && player instanceof ServerPlayerEntity serverPlayer && entity instanceof ServerPlayerEntity profileOwner && profileOwner instanceof PlayerExtension ex) {

                if(!ProfilesConfig.isBlacklistedDimension(world.getDimensionKey().getValue())) {
                    if(!ex.isProfileHidden()) {
                        ProfileGui.open(serverPlayer, profileOwner);
                        return ActionResult.SUCCESS;
                    }
                    else {
                        serverPlayer.sendMessage(TextParserUtils.formatText("<lang:'message.palmsmod.hidden_profile'>"));
                        return ActionResult.PASS;
                    }
                }
            }
            return ActionResult.PASS;
        });

        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            ((PlayerExtension)newPlayer).setProfileHidden(((PlayerExtension)oldPlayer).isProfileHidden());
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            server.getPlayerManager().getPlayerList().forEach(player -> {
                if(GuiHelpers.getCurrentGui(player) instanceof ProfileGui gui) {
                    if(gui.profileOwner.equals(handler.player.getUuid())) gui.close();
                }
            });
        });
    }
}
