package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends Entity {

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

    public boolean checkShouldDismount() {
        return ((isPokemonDismount()) || (getPassengers().isEmpty()) ||
                (wasTouchingWater));
    }

    private boolean isPokemonDismount() {
        return this.getPersistentData().getBoolean("pokemon_dismount");
    }
}