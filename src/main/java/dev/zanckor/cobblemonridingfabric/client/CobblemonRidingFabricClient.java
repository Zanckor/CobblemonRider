package dev.zanckor.cobblemonridingfabric.client;

import dev.zanckor.cobblemonridingfabric.client.screen.StaminaBar;
import dev.zanckor.cobblemonridingfabric.event.ClientPlayerEvent;
import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CobblemonRidingFabricClient implements ClientModInitializer {
    public static KeyBinding pokemonDismount;
    public static KeyBinding pokemonMountEntities;

    @Override
    public void onInitializeClient() {
        keyBindingRegister();
        registerEvents();
        NetworkHandler.registerClientReceiverPacket();
    }

    private void registerEvents() {
        ClientPlayerEvent.tickEvent();
        HudRenderCallback.EVENT.register(new StaminaBar());
    }

    private void keyBindingRegister() {
        // Register Pokemon Dismount Key
        pokemonDismount = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cobblemonrider.pokemon_dismount",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.cobblemonrider.cobblemonrider"
        ));

        // Register Pokemon Mount Entities Key
        pokemonMountEntities = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cobblemonrider.pokemon_mount_entities",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                "category.cobblemonrider.cobblemonrider"
        ));
    }
}
