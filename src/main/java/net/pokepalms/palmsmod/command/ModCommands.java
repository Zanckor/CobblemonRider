package net.pokepalms.palmsmod.command;

import com.mojang.brigadier.CommandDispatcher;
import eu.pb4.sgui.api.GuiHelpers;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.pokepalms.palmsmod.config.ProfilesConfig;
import net.pokepalms.palmsmod.extension.PlayerExtension;
import net.pokepalms.palmsmod.gui.ProfileGui;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class ModCommands {

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> register(dispatcher));
    }

    private static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("profile").then(literal("config").requires(source -> source.hasPermissionLevel(4)).then(literal("reload").executes(context -> {
            ProfilesConfig.reload();
            var server = context.getSource().getServer();
            if(server != null) {
                server.getPlayerManager().getPlayerList().forEach(player -> {
                    if(GuiHelpers.getCurrentGui(player) instanceof ProfileGui gui) {
                        gui.close();
                    }
                });
            }
            context.getSource().sendFeedback(() -> Text.of("Config successfully reloaded!"), true);
            return 1;
        }))).then(literal("settings").then(literal("toggle").executes(context -> {
            var player = context.getSource().getPlayer();
            if(player instanceof PlayerExtension ex) {
                context.getSource().sendFeedback(() -> ex.isProfileHidden() ? Text.of("Your profile is now shown") : Text.of("Your profile is now hidden"), false);
                ex.setProfileHidden(!ex.isProfileHidden());
            }
            return 1;
        }))).then(literal("debug").requires(source -> source.hasPermissionLevel(4)).executes(context -> {
            var player = context.getSource().getPlayer();
            if(player != null) {
                ProfileGui.open(player, player);
            }
            return 1;
        })));
    }
}
