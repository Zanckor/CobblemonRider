package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import me.rufia.fightorflight.goals.PokemonMeleeAttackGoal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FollowOwnerGoal.class)
public abstract class FollowOwnerGoalMixin extends Goal {

    @Shadow @Final private TamableAnimal tamable;

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        PokemonEntity pokemon = (PokemonEntity) this.tamable;

        if (cir.getReturnValue() && pokemon.getControllingPassenger() != null) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "canContinueToUse", at = @At("RETURN"), cancellable = true)
    private void canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        PokemonEntity pokemon = (PokemonEntity) this.tamable;

        if (cir.getReturnValue() && pokemon.getControllingPassenger() != null) {
            cir.setReturnValue(false);
        }
    }
}