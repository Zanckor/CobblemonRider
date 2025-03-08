package dev.zanckor.cobblemonridingfabric.client;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.client.gui.interact.wheel.InteractWheelOption;
import com.cobblemon.mod.common.client.gui.interact.wheel.Orientation;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.MCUtil;
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
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

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
            ClientWorld world = MinecraftClient.getInstance().world;
            PlayerEntity player = MinecraftClient.getInstance().player;

            if (world == null || player == null) return Unit.INSTANCE;

            world.getEntitiesByClass(PokemonEntity.class, player.getBoundingBox().expand(20), LivingEntity::isAlive).forEach(entity -> {
                if (entity instanceof PokemonEntity pokemon && pokemon.getUuid().equals(event.getPokemonID())) {
                    boolean canRide = canBeRidden(pokemon, event.getMountShoulder());

                    if (canRide) {
                        event.addOption(
                                Orientation.TOP_LEFT,
                                new InteractWheelOption(
                                        Identifier.of(CobblemonRidingFabric.MODID, "textures/gui/interact/rider_icon.png"),
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
                    }
                }
            });


            return Unit.INSTANCE;
        });
    }

    private boolean canBeRidden(PokemonEntity pokemon, boolean mountShoulder) {
        return !mountShoulder && MCUtil.getPassengerObject(pokemon.getPokemon().getSpecies().getName(), pokemon.getForm().getName()) != null;
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
