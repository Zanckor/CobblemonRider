package dev.zanckor.cobblemonridingfabric.network.handler.server;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.zanckor.cobblemonridingfabric.network.payload.MountC2SPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.UUID;

public class MountReceiver {

    public static void receive() {
        ServerPlayNetworking.registerGlobalReceiver(MountC2SPayload.ID, (mountS2CPayload, context) -> context.server().execute(() -> {
            UUID pokemonID = mountS2CPayload.pokemonID();
            PlayerEntity player = context.player();

            PokemonEntity pokemon = (PokemonEntity) ((ServerWorld) player.getWorld()).getEntity(pokemonID);

            if (pokemon instanceof PokemonEntity && canMount(player, pokemon)) {
                player.startRiding(pokemon);
            }
        }));
    }

    private static boolean canMount(PlayerEntity player, PokemonEntity pokemonEntity) {
        return (player.equals(pokemonEntity.getPokemon().getOwnerPlayer()) || pokemonHasControllingPassenger(pokemonEntity));
    }

    private static boolean pokemonHasControllingPassenger(PokemonEntity pokemon) {
        return !pokemon.getPassengerList().isEmpty();
    }
}
