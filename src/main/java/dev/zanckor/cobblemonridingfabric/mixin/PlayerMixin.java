package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends Entity {

    public abstract void setRemainingFireTicks(int p_36353_);

    public PlayerMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "dismountVehicle", at = @At("RETURN"), cancellable = true)
    @SuppressWarnings("ConstantConditions")
    public void shouldDismount(CallbackInfo ci) {
        Entity vehicle = this.getVehicle();

        if ((vehicle instanceof PokemonEntity && !vehicle.isRemoved() && (!checkShouldDismount()) || isSneaking())) {
            ci.cancel();
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void removeFire(CallbackInfo ci) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            setOnFire(false);
        }
    }

    @Inject(method = "damage", at = @At("RETURN"), cancellable = true)
    public void hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean doesRenderOnFire() {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity) {
            return false;
        } else {
            return super.doesRenderOnFire();
        }
    }


    private boolean checkShouldDismount() {
        return ((IEntityData) this).getPersistentData().getBoolean("pokemon_dismount");
    }
}