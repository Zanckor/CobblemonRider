package dev.zanckor.cobblemonridingfabric.network.handler.server;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonridingfabric.network.payload.MountC2SPayload;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Objects;
import java.util.UUID;

public class MountReceiver {

    public static void receive() {
        System.out.println("MountReceiver.receive");

        ServerPlayNetworking.registerGlobalReceiver(MountC2SPayload.ID, (mountS2CPayload, context) -> {
            System.out.println("MountReceiver.receive.registerGlobalReceiver");

            context.server().execute(() -> {
                UUID pokemonID = mountS2CPayload.pokemonID();
                PlayerEntity player = context.player();

                PokemonEntity pokemon = (PokemonEntity) ((ServerWorld) player.getWorld()).getEntity(pokemonID);

                System.out.println("Mounting pokemon: " + pokemonID);
                if (pokemon instanceof PokemonEntity && canMount(player, pokemon.getPokemon())) {
                    player.startRiding(pokemon);
                }
            });
        });
    }

    private static boolean canMount(PlayerEntity player, Pokemon pokemon) {
        return !pokemon.isBattleClone() && (Objects.equals(pokemon.getOwnerPlayer(), player) || pokemonHasControllingPassenger(Objects.requireNonNull(pokemon.getEntity())) != null);
    }

    private static LivingEntity pokemonHasControllingPassenger(PokemonEntity pokemon) {
        return pokemon.getPassengerList().isEmpty() ? null : (LivingEntity) pokemon.getPassengerList().getFirst();
    }
}
