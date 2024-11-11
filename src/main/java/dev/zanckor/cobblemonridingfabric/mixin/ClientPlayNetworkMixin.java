package dev.zanckor.cobblemonridingfabric.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkMixin {

    @ModifyConstant(method = "onEntityPassengersSet", constant = @Constant(stringValue = "mount.onboard"))
    private String modifyValue(String constant) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        boolean isPokemon = player != null && player.getVehicle() instanceof PokemonEntity;

        return isPokemon ? "" : "mount.onboard";
    }
}