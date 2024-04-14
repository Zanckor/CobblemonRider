package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.EntityProperty;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonrider.MCUtil;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import me.rufia.fightorflight.goals.PokemonMeleeAttackGoal;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Objects;

import static dev.zanckor.cobblemonrider.config.PokemonJsonObject.MountType.*;

@Mixin(PokemonMeleeAttackGoal.class)
public abstract class PokemonAttackGoalMixin extends MeleeAttackGoal {
    public PokemonAttackGoalMixin(PathfinderMob p_25552_, double p_25553_, boolean p_25554_) {
        super(p_25552_, p_25553_, p_25554_);
    }

    @Inject(method = "canUse", at = @At("RETURN"), cancellable = true)
    private void canUse(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            PokemonEntity pokemon = (PokemonEntity) this.mob;

            cir.setReturnValue(pokemon.hasControllingPassenger());
        }
    }

    @Inject(method = "canContinueToUse", at = @At("RETURN"), cancellable = true)
    private void canContinueToUse(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            PokemonEntity pokemon = (PokemonEntity) this.mob;

            cir.setReturnValue(pokemon.hasControllingPassenger());
        }
    }
}