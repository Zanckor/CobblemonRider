package net.pokepalms.palmsmod.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends Entity {

    public PlayerMixin(EntityType<?> type, World world) {
        super(type, world);
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