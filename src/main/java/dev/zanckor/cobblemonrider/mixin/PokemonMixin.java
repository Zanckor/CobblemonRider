package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.entity.EntityProperty;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonrider.MCUtil;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
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

@Mixin(PokemonEntity.class)
public abstract class PokemonMixin extends ShoulderRidingEntity {
    PokemonJsonObject.PokemonConfigData passengerObject;
    private Vec3 prevPos;

    @Shadow
    public abstract Pokemon getPokemon();

    @Shadow
    public abstract void checkDespawn();

    @Shadow
    public abstract EntityProperty<Boolean> isMoving();

    protected PokemonMixin(EntityType<? extends ShoulderRidingEntity> p_29893_, Level p_29894_) {
        super(p_29893_, p_29894_);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lcom/cobblemon/mod/common/pokemon/Pokemon;Lnet/minecraft/world/entity/EntityType;)V", at = @At("RETURN"))
    private void init(Level level, Pokemon pokemon, EntityType<? extends PokemonEntity> entityType, CallbackInfo ci) {
        this.setMaxUpStep(1.0F);
    }


    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        if (getControllingPassenger() != null) {
            movementHandler();
            dismountHandler();
        }
    }

    @Override
    public boolean displayFireAnimation() {
        if (passengerObject != null && passengerObject.getMountTypes().contains(LAVA_SWIM)) {
            return false;
        } else {
            return super.displayFireAnimation();
        }
    }

    @Override
    protected void positionRider(@NotNull Entity entity, @NotNull MoveFunction moveFunction) {
        PokemonJsonObject.PokemonConfigData passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName());

        if (this.hasPassenger(entity) && passengerObject != null && getControllingPassenger() != null) {
            int passengerIndex = getPassengers().indexOf(entity) - 1;
            boolean isControllingPassenger = getControllingPassenger().equals(entity);
            ArrayList<Float> offSet = isControllingPassenger ? passengerObject.getRidingOffSet() : passengerObject.getPassengersOffSet().get(passengerIndex);

            setYBodyRot(getControllingPassenger().getYRot());

            float xOffset = offSet.get(2);
            float yOffset = offSet.get(1);
            float zOffset = offSet.get(0);

            Vec3 vec3 = (new Vec3(xOffset, yOffset, zOffset)).yRot(-getControllingPassenger().getYRot() * 0.017453292F);

            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
        }
    }

    private void movementHandler() {
        Player passenger = (Player) getControllingPassenger();

        if (passengerObject != null && passenger != null) {
            travelHandler();

            if (passengerObject.getMountTypes().contains(SWIM)) {
                swimmingHandler();
            }

            if (passengerObject.getMountTypes().contains(LAVA_SWIM)) {
                lavaSwimmingHandler();
            }

            if (passengerObject.getMountTypes().contains(FLY)) {
                flyingHandler();
            }

            passenger.getPersistentData().putBoolean("press_space", false);
            passenger.getPersistentData().putBoolean("press_sprint", false);
            passenger.getPersistentData().putBoolean("pokemon_dismount", false);
            passenger.getPersistentData().putBoolean("press_shift", false);
            passenger.setShiftKeyDown(false);
        }
    }

    void travelHandler() {
        Player passenger;
        if ((passenger = (Player) getControllingPassenger()) == null) return;

        float modifierSpeed = passengerObject.getSpeedModifier();

        // Set the entity's yaw and pitch from the passenger's yaw and pitch
        setRot(getFirstPassenger().getYRot(), 0);

        float x = (float) passenger.getDeltaMovement().x * 10;
        float z = (float) passenger.getDeltaMovement().z * 10;

        if ((passengerObject.getMountTypes().contains(SWIM) && isInWater()) // Not pretty clear code, should be refactored
                || (passengerObject.getMountTypes().contains(FLY) && !onGround())
                || (passengerObject.getMountTypes().contains(WALK))) {
            if (passengerObject.getMountTypes().contains(WALK) && onGround()) {
                if (isSpacePressed()) {
                    jumpFromGround();
                }
            }

            if (isSprintPressed()) {
                modifierSpeed *= 2.5f;
            }

            System.out.println(isMoving().get());
            setDeltaMovement(x * modifierSpeed, getDeltaMovement().y, z * modifierSpeed);
        }

        travel(new Vec3(x, 0, z));
    }

    void swimmingHandler() {
        Player passenger;
        if ((passenger = (Player) getControllingPassenger()) != null && isInWater()) {
            double waterEmergeSpeed = isSpacePressed() ? 0.5 : isShiftPressed() ? -0.25 : 0;
            setAirSupply(getMaxAirSupply());
            passenger.setAirSupply(passenger.getMaxAirSupply());

            setDeltaMovement(getDeltaMovement().x, waterEmergeSpeed, getDeltaMovement().z);

            if (getDistanceToSurface(this) <= 0.5 && isShiftPressed()) {
                moveTo(getX(), getY(), getZ());
            }
        }
    }

    void lavaSwimmingHandler() {
        Player passenger;
        if ((passenger = (Player) getControllingPassenger()) != null && isInLava()) {
            double lavaEmergeSpeed = isSpacePressed() ? 0.5 : isShiftPressed() ? -0.25 : 0.014;

            setDeltaMovement(getDeltaMovement().x, lavaEmergeSpeed, getDeltaMovement().z);

            if (getDistanceToSurface(this) <= 0.5 && isShiftPressed()) {
                moveTo(getX(), getY(), getZ());
            }
        }
    }

    void flyingHandler() {
        if (getControllingPassenger() == null) return;
        boolean increaseAltitude = isSpacePressed();
        boolean decreaseAltitude = isShiftPressed();


        if (!onGround() || increaseAltitude) {
            double altitudeIncreaseValue = increaseAltitude ? 0.3 : decreaseAltitude ? -0.3 : 0;

            setDeltaMovement(getDeltaMovement().x, altitudeIncreaseValue, getDeltaMovement().z);
        }

        if (getPokemon().getEntity() != null) {
            getPokemon().getEntity().setBehaviourFlag(PokemonBehaviourFlag.FLYING, !onGround());
        }
    }

    float getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.level().getHeight(Heightmap.Types.WORLD_SURFACE, (int) (entity.getX() - entity.getEyeHeight()), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    public void dismountHandler() {
        Player passenger;
        if (!isAlive() || !isAddedToWorld() || isRemoved()) {
            ejectPassengers();
        }

        if ((passenger = (Player) getControllingPassenger()) != null && checkShouldDismount()) {
            passenger.getPersistentData().putBoolean("press_space", false);
            passenger.getPersistentData().putBoolean("press_sprint", false);
            passenger.getPersistentData().putBoolean("pokemon_dismount", false);
            passenger.getPersistentData().putBoolean("press_shift", false);

            passenger.stopRiding();
            ejectPassengers();
        }
    }

    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    public void causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null && passengerObject != null && passengerObject.getMountTypes().contains(LAVA_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (canAddPassenger(player) && player.getMainHandItem().isEmpty()) {
            passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName());

            if (passengerObject != null && (Objects.equals(getPokemon().getOwnerPlayer(), player) || getControllingPassenger() != null)) {
                player.startRiding(this);

                player.getPersistentData().putBoolean("press_space", false);
                player.getPersistentData().putBoolean("press_sprint", false);
                player.getPersistentData().putBoolean("pokemon_dismount", false);


                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.FAIL;
    }

    @Inject(method = "isMoving", at = @At("RETURN"), cancellable = true, remap = false)
    public void isMoving(CallbackInfoReturnable<EntityProperty<Boolean>> cir) {
        if(getControllingPassenger() == null) return;
        EntityProperty<Boolean> property = cir.getReturnValue();
        property.set(getControllingPassenger().getDeltaMovement().lengthSqr() > 0.0062);

        cir.setReturnValue(property);
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity entity) {
        PokemonJsonObject.PokemonConfigData passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName());
        int maxPassengers = passengerObject != null ? passengerObject.getPassengersOffSet().size() + 1 : 0;

        return getPassengers().size() < maxPassengers;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return getPassengers().isEmpty() ? null : (LivingEntity) getPassengers().get(0);
    }

    public boolean checkShouldDismount() {
        return ((isPokemonDismountPressed()) || (getPassengers().isEmpty()) ||
                (passengerObject != null && !passengerObject.getMountTypes().contains(SWIM) && isInWater()));
    }

    private boolean isSpacePressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("press_space") && getControllingPassenger().getPersistentData().getBoolean("press_space");
    }

    private boolean isSprintPressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("press_sprint") && getControllingPassenger().getPersistentData().getBoolean("press_sprint");
    }

    private boolean isShiftPressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("press_shift") && getControllingPassenger().getPersistentData().getBoolean("press_shift");
    }

    private boolean isPokemonDismountPressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("pokemon_dismount") && getControllingPassenger().getPersistentData().getBoolean("pokemon_dismount");
    }
}