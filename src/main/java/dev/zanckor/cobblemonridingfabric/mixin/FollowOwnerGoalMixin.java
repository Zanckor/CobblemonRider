package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FollowOwnerGoal.class)
public abstract class FollowOwnerGoalMixin extends Goal {

    @Final
    private TameableEntity tamable;

    @Inject(method = "canStart", at = @At("RETURN"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && this.tamable instanceof PokemonEntity pokemon) {
            if (pokemon.getControllingPassenger() != null) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "shouldContinue", at = @At("RETURN"), cancellable = true)
    private void canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue() && this.tamable instanceof PokemonEntity pokemon) {
            if (pokemon.getControllingPassenger() != null) {
                cir.setReturnValue(false);
            }
        }
    }
}