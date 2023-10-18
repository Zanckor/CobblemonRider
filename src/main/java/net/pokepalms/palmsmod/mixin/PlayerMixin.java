package net.pokepalms.palmsmod.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.pokepalms.palmsmod.extension.PlayerExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerExtension {

    public boolean isProfileHidden = false;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isProfileHidden() {
        return this.isProfileHidden;
    }

    @Override
    public void setProfileHidden(boolean hide) {
        this.isProfileHidden = hide;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("IsProfileHidden", this.isProfileHidden);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.isProfileHidden = nbt.getBoolean("IsProfileHidden");
    }

    @Shadow
    public abstract void dismountVehicle();


    @Inject(method = "dismountVehicle", at = @At("HEAD"), cancellable = true)
    @SuppressWarnings("ConstantConditions")
    public void shouldDismount(CallbackInfo ci) {
        Entity vehicle = this.getVehicle();

        if (vehicle instanceof PokemonEntity && !vehicle.isOnGround()) {
            ci.cancel();
        }
    }

}
