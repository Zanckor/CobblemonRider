package dev.zanckor.cobblemonrider.event;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.google.gson.Gson;
import dev.zanckor.cobblemonrider.CobblemonRider;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import dev.zanckor.cobblemonrider.network.NetworkUtil;
import dev.zanckor.cobblemonrider.network.packet.ConfigPacket;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static dev.zanckor.cobblemonrider.CobblemonRider.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerPlayerEvent {

    @SubscribeEvent
    public static void playerJoin(PlayerEvent.PlayerLoggedInEvent e) {
        NetworkUtil.TO_CLIENT(e.getEntity(), new ConfigPacket(loadConfig()));
    }

    @SubscribeEvent
    public static void removeLavaDamageOnRide(LivingHurtEvent e) {
        if (e.getEntity() instanceof Player player &&
                player.getVehicle() != null &&
                player.getVehicle() instanceof PokemonEntity &&
                e.getSource().equals(player.damageSources().lava())) {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void removeFireOnRide(LivingEvent.LivingTickEvent e) {
        if (e.getEntity() instanceof Player player &&
                player.getVehicle() != null &&
                player.getVehicle() instanceof PokemonEntity &&
                player.isOnFire()) {
            player.extinguishFire();
        }
    }


    public static PokemonJsonObject loadConfig() {
        File pokemonRideConfigFile = CobblemonRider.PokemonRideConfigFile;
        String pokemonRideConfig = null;

        try {
            if (pokemonRideConfigFile != null)
                pokemonRideConfig = new String(Files.readAllBytes(pokemonRideConfigFile.toPath()));
        } catch (IOException e) {
            CobblemonRider.LOGGER.info("Error reading cobblemon pokemon ride config file" + pokemonRideConfigFile);

            return null;
        }

        return pokemonRideConfig != null ? new Gson().fromJson(pokemonRideConfig, PokemonJsonObject.class) : null;
    }
}