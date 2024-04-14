package dev.zanckor.cobblemonrider.event;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.zanckor.cobblemonrider.CobblemonRider;
import dev.zanckor.cobblemonrider.network.NetworkUtil;
import dev.zanckor.cobblemonrider.network.packet.KeyPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static dev.zanckor.cobblemonrider.CobblemonRider.MODID;


@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientPlayerEvent {

    @SubscribeEvent
    public static void tickEvent(TickEvent.ClientTickEvent e) {
        Player player = Minecraft.getInstance().player;
        Options options = Minecraft.getInstance().options;

        if(player != null && player.getVehicle() instanceof PokemonEntity) {
            if (options.keyJump.isDown() && (!player.getPersistentData().getBoolean("press_space"))) {
                NetworkUtil.TO_SERVER(new KeyPacket(KeyPacket.Key.SPACE));
                player.getPersistentData().putBoolean("press_space", true);
            }

            if (options.keySprint.isDown() && !player.getPersistentData().getBoolean("press_sprint")) {
                NetworkUtil.TO_SERVER(new KeyPacket(KeyPacket.Key.SPRINT));
                player.getPersistentData().putBoolean("press_sprint", true);
            }

            if (options.keyShift.isDown() && !player.getPersistentData().getBoolean("press_shift")) {
                NetworkUtil.TO_SERVER(new KeyPacket(KeyPacket.Key.SHIFT));
                player.getPersistentData().putBoolean("press_shift", true);
            }

            if (CobblemonRider.ClientEventHandlerRegister.pokemonDismount.isDown() && !player.getPersistentData().getBoolean("pokemon_dismount")) {
                NetworkUtil.TO_SERVER(new KeyPacket(KeyPacket.Key.POKEMON_DISMOUNT));
                player.getPersistentData().putBoolean("pokemon_dismount", true);
            }
        }
    }
}
