package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerGamePacketListenerMixin {
    @Shadow
    public ServerPlayerEntity player;

    @ModifyConstant(method = "onVehicleMove", constant = @Constant(doubleValue = 0.0625D))
    private double modifyValue(double constant) {
        boolean isPokemon = this.player.getVehicle() instanceof PokemonEntity;

        return isPokemon ? 1.0D : 0.0625D;
    }
}