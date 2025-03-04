package dev.zanckor.cobblemonridingfabric.client;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption;
import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation;
import dev.zanckor.cobblemonridingfabric.client.screen.StaminaBar;
import dev.zanckor.cobblemonridingfabric.event.ClientPlayerEvent;
import dev.zanckor.cobblemonridingfabric.network.SendPacket;
import dev.zanckor.cobblemonridingfabric.network.handler.ClientReceiveHandler;
import dev.zanckor.cobblemonridingfabric.network.payload.MountC2SPayload;
import kotlin.Unit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static com.cobblemon.mod.common.util.MiscUtilsKt.cobblemonResource;

public class CobblemonRidingFabricClient implements ClientModInitializer {
    public static KeyBinding pokemonDismount;
    public static KeyBinding pokemonMountEntities;

    @Override
    public void onInitializeClient() {
        keyBindingRegister();
        registerEvents();
        ClientReceiveHandler.register();
    }

    private void registerEvents() {
        ClientPlayerEvent.tickEvent();
        HudRenderCallback.EVENT.register(new StaminaBar());

        CobblemonEvents.POKEMON_INTERACTION_GUI_CREATION.subscribe(Priority.NORMAL, event -> {
            event.addOption(
                    Orientation.BOTTOM_LEFT,
                    new InteractWheelOption(
                            cobblemonResource("textures/gui/interact/icon_shoulder.png"),
                            null,
                            "Mount",
                            () -> null,
                            () -> {
                                SendPacket.TO_SERVER(new MountC2SPayload(event.getPokemonID()));
                                MinecraftClient.getInstance().setScreen(null);

                                return Unit.INSTANCE;
                            }
                    )
            );

            return Unit.INSTANCE;
        });
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
