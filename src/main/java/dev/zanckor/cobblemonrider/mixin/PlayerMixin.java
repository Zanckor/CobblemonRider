package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.zanckor.cobblemonrider.config.PokemonJsonObject.MountType.LAVA_SWIM;

@Mixin(Player.class)
public abstract class PlayerMixin extends Entity {

    @Shadow public abstract void setRemainingFireTicks(int p_36353_);

    @Shadow protected abstract int getFireImmuneTicks();

    public PlayerMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "removeVehicle", at = @At("RETURN"), cancellable = true)
    @SuppressWarnings("ConstantConditions")
    public void shouldDismount(CallbackInfo ci) {
        Entity vehicle = this.getVehicle();

        if ((vehicle instanceof PokemonEntity && !vehicle.isRemoved() && (!checkShouldDismount()) || isShiftKeyDown())) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void removeFire(CallbackInfo ci) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            setRemainingFireTicks(0);
            setSecondsOnFire(0);
        }
    }

    @Inject(method = "getFireImmuneTicks", at = @At("RETURN"), cancellable = true)
    public void getFireImmuneTicks(CallbackInfoReturnable<Integer> cir) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "hurt", at = @At("RETURN"), cancellable = true)
    public void hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean displayFireAnimation() {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            return false;
        } else {
            return super.displayFireAnimation();
        }
    }

    private boolean checkShouldDismount() {
        return this.getPersistentData().getBoolean("pokemon_dismount");
    }
}