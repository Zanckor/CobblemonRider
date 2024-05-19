package dev.zanckor.cobblemonridingfabric.event;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.zanckor.cobblemonridingfabric.client.CobblemonRidingFabricClient;
import dev.zanckor.cobblemonridingfabric.client.screen.StaminaBar;
import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import dev.zanckor.cobblemonridingfabric.network.SendPacket;
import dev.zanckor.cobblemonridingfabric.network.packet.KeyC2SPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.player.PlayerEntity;

public class ClientPlayerEvent {

    public static void tickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = client.player;
            GameOptions options = client.options;

            if (player != null && player.getVehicle() instanceof PokemonEntity) {
                if (options.jumpKey.isPressed() && (!((IEntityData) player).getPersistentData().getBoolean("press_space"))) {
                    SendPacket.TO_SERVER(new KeyC2SPacket(KeyC2SPacket.Key.SPACE));
                    ((IEntityData) player).getPersistentData().putBoolean("press_space", true);
                }

                if (options.sprintKey.isPressed() && !((IEntityData) player).getPersistentData().getBoolean("press_sprint")) {
                    SendPacket.TO_SERVER(new KeyC2SPacket(KeyC2SPacket.Key.SPRINT));
                    ((IEntityData) player).getPersistentData().putBoolean("press_sprint", true);
                }

                if (options.sneakKey.isPressed() && !((IEntityData) player).getPersistentData().getBoolean("press_shift")) {
                    SendPacket.TO_SERVER(new KeyC2SPacket(KeyC2SPacket.Key.SHIFT));
                    ((IEntityData) player).getPersistentData().putBoolean("press_shift", true);
                }

                if (CobblemonRidingFabricClient.pokemonDismount.isPressed() && !((IEntityData) player).getPersistentData().getBoolean("pokemon_dismount")) {
                    SendPacket.TO_SERVER(new KeyC2SPacket(KeyC2SPacket.Key.POKEMON_DISMOUNT));
                    ((IEntityData) player).getPersistentData().putBoolean("pokemon_dismount", true);
                }

                if (CobblemonRidingFabricClient.pokemonMountEntities.isPressed() && !((IEntityData) player).getPersistentData().getBoolean("pokemon_mount_entities")) {
                    SendPacket.TO_SERVER(new KeyC2SPacket(KeyC2SPacket.Key.POKEMON_MOUNT_ENTITIES));
                    ((IEntityData) player).getPersistentData().putBoolean("pokemon_mount_entities", true);
                }

            }
        });
    }
}
