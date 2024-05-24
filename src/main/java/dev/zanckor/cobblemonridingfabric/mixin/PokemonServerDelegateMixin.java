package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.entity.pokemon.PokemonServerDelegate;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PokemonServerDelegate.class)
public abstract class PokemonServerDelegateMixin {
    private Vec3d prevPos;

    @Shadow
    public abstract PokemonEntity getEntity();


    @Inject(method = "updatePoseType", at = @At("HEAD"), remap = false)
    private void head(CallbackInfo ci) {
        Vec3d pos = this.getEntity().getPos();

        if (this.prevPos != null) {
            double dx = pos.x - this.prevPos.x;
            double dy = pos.y - this.prevPos.y;
            double dz = pos.z - this.prevPos.z;

            double distance = dx * dx + dy * dy + dz * dz;

            this.getEntity().getDataTracker().set(PokemonEntity.Companion.getMOVING(), distance > 0.002D);
        }

        prevPos = pos;
    }
}