package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.entity.pokemon.PokemonServerDelegate;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;


@Mixin(PokemonServerDelegate.class)
public abstract class PokemonServerDelegateMixin {
    @Shadow
    public abstract PokemonEntity getEntity();


    @Inject(method = "updatePoseType", at = @At("HEAD"), remap = false)
    private void head(CallbackInfo ci) {
        if (getEntity().hasControllingPassenger())
            getEntity().setDeltaMovement(
                    Objects.requireNonNull(getEntity().getControllingPassenger()).getDeltaMovement()
                            .multiply(1.0, 0, 1.0));
    }

    @Inject(method = "updatePoseType", at = @At("TAIL"), remap = false)
    private void tail(CallbackInfo ci) {
        if (getEntity().hasControllingPassenger()){
            getEntity().setDeltaMovement(0, 0, 0);
        }
    }
}