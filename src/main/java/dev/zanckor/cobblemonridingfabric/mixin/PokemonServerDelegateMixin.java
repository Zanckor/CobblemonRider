package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.entity.pokemon.PokemonServerDelegate;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PokemonServerDelegate.class)
public abstract class PokemonServerDelegateMixin {
    @SuppressWarnings("unused")
    @Unique
    private Vec3d prevPos;

    @Shadow(remap = false)
    public abstract PokemonEntity getEntity();


    @Inject(method = "updatePoseType", at = @At("HEAD"), remap = false)
    private void head(CallbackInfo ci) {
        float minimumSpeed = getEntity().isTouchingWater() ? 0.00026F : 0.0062F;

        boolean isMoving = getEntity().getControllingPassenger() != null ?
                getEntity().getControllingPassenger().getVelocity().lengthSquared() > minimumSpeed :
                getEntity().getVelocity().lengthSquared() > minimumSpeed;

        getEntity().getDataTracker().set(PokemonEntity.Companion.getMOVING(), isMoving);
    }
}