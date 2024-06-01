package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.entity.pokemon.PokemonServerDelegate;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PokemonServerDelegate.class)
public abstract class PokemonServerDelegateMixin {

    @Shadow
    public abstract PokemonEntity getEntity();


    @Inject(method = "updatePoseType", at = @At("HEAD"), remap = false)
    private void head(CallbackInfo ci) {

        if (this.getEntity().getControllingPassenger() != null) {
            boolean isMoving = getEntity().getControllingPassenger().getDeltaMovement().lengthSqr() > 0.0062D;

            this.getEntity().getEntityData().set(PokemonEntity.Companion.getMOVING(), isMoving);
        }
    }
}