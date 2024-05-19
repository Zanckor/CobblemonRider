package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.api.entity.PokemonSideDelegate;
import com.cobblemon.mod.common.api.scheduling.Schedulable;
import com.cobblemon.mod.common.entity.Poseable;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonrider.MCUtil;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import dev.zanckor.cobblemonrider.mixininterface.IPokemonStamina;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.monster.Monster;
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
import java.util.List;
import java.util.Objects;

import static dev.zanckor.cobblemonrider.config.PokemonJsonObject.MountType.*;

@Mixin(PokemonEntity.class)
public abstract class PokemonMixin extends PathfinderMob implements Poseable, Schedulable, IPokemonStamina {
    private PokemonJsonObject.PokemonConfigData passengerObject;
    private int stamina = Integer.MAX_VALUE;
    private int maxPassengers = -1;

    private static final int TIME_BETWEEN_SWITCH_SPRINTS = 10;
    private int timeUntilNextSwitchSprint = 0;
    private boolean isSprinting;
    private boolean prevSprintPressed;
    private float speedMultiplier;
    private Vec3 prevMovementInput;

    @Shadow
    public abstract Pokemon getPokemon();

    @Shadow
    public abstract void checkDespawn();

    @Shadow
    public abstract @NotNull PokemonSideDelegate getDelegate();

    @Shadow
    public abstract void travel(@NotNull Vec3 movementInput);

    protected PokemonMixin(EntityType<? extends ShoulderRidingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lcom/cobblemon/mod/common/pokemon/Pokemon;Lnet/minecraft/world/entity/EntityType;)V", at = @At("RETURN"))
    private void init(Level level, Pokemon pokemon, EntityType<? extends PokemonEntity> entityType, CallbackInfo ci) {
        this.setMaxUpStep(1);
        this.prevMovementInput = Vec3.ZERO;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (getControllingPassenger() != null) {
            if (mayMountOtherEntities() && canAddPassenger(getControllingPassenger())) {
                mountEntity();
            }

            dismountHandler();
            movementHandler();

            rotateBody();
        }
    }

    private void mountEntity() {
        List<LivingEntity> mobs = level().getEntitiesOfClass(LivingEntity.class, getBoundingBox(),
                entity -> !(entity instanceof Monster) && !(entity instanceof Player) && !getPassengers().contains(entity) && !entity.hasControllingPassenger());

        for (LivingEntity mob : mobs) {
            if (canAddPassenger(mob)) {
                mob.startRiding(this);
            }
        }
    }

    @Override
    protected void positionRider(@NotNull Entity entity, @NotNull MoveFunction moveFunction) {
        if (this.hasPassenger(entity) && getPassengerObject() != null && getControllingPassenger() != null) {
            int passengerIndex = getPassengers().indexOf(entity) - 1;
            boolean isControllingPassenger = getControllingPassenger().equals(entity);
            ArrayList<Float> offSet = isControllingPassenger ? getPassengerObject().getRidingOffSet() : getPassengerObject().getPassengersOffSet().get(passengerIndex);

            setYBodyRot(getControllingPassenger().getYRot());

            float xOffset = offSet.get(2);
            float yOffset = offSet.get(1);
            float zOffset = offSet.get(0);

            Vec3 vec3 = (new Vec3(xOffset, yOffset, zOffset)).yRot(-getControllingPassenger().getYRot() * 0.017453292F);

            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
        }
    }

    private void movementHandler() {
        if (getControllingPassenger() instanceof Player passenger && getPassengerObject() != null) {
            if (!getPassengerObject().getMountTypes().contains(SWIM) && isInWater()) return;

            sprintHandler();
            travelHandler();

            if (getPassengerObject().getMountTypes().contains(WALK) && onGround()) {
                jumpHandler();
            }

            if (getPassengerObject().getMountTypes().contains(SWIM)) {
                swimmingHandler();
            }

            if (getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
                lavaSwimmingHandler();
            }

            if (getPassengerObject().getMountTypes().contains(FLY)) {
                flyingHandler();
            }

            passenger.getPersistentData().putBoolean("press_space", false);
            passenger.getPersistentData().putBoolean("press_sprint", false);
            passenger.getPersistentData().putBoolean("pokemon_dismount", false);
            passenger.getPersistentData().putBoolean("press_shift", false);
            passenger.getPersistentData().putBoolean("pokemon_mount_entities", false);
            passenger.setShiftKeyDown(false);
        }
    }

    private void travelHandler() {
        if (getControllingPassenger() != null && canMove()) {
            final float MAX_SPEED = isSprinting ? 0.4F : 0.25F;
            final float ACCELERATION_MULTIPLIER = 0.6F;
            Vec3 movementInput;

            Vec3 passengerMotion = getControllingPassenger().getDeltaMovement();

            double motionLength = Math.sqrt(passengerMotion.x * passengerMotion.x + passengerMotion.z * passengerMotion.z);
            double normalizedX = passengerMotion.x / motionLength;
            double normalizedZ = passengerMotion.z / motionLength;
            Vec3 normalizedMovement = new Vec3(normalizedX, 0, normalizedZ);

            if (passengerMotion.lengthSqr() > 0.0062) {
                Vec3 acceleratedMovement = normalizedMovement.scale(ACCELERATION_MULTIPLIER).add(prevMovementInput).scale(MAX_SPEED);
                movementInput = MCUtil.clampVec3(acceleratedMovement, -MAX_SPEED, MAX_SPEED);
            } else {
                movementInput = prevMovementInput.scale(0.75);
            }

            movementInput = movementInput.scale(speedMultiplier);

            setDeltaMovement(movementInput.x, getDeltaMovement().y, movementInput.z);
            prevMovementInput = getDeltaMovement();
        }
    }

    private void rotateBody() {
        if (getFirstPassenger() != null) {
            setRot(getFirstPassenger().getYRot(), 0);
        }
    }

    private void sprintHandler() {
        if (!isMoving()) {
            isSprinting = false;
            increaseStamina(1);
            speedMultiplier = 1;
            return;
        }

        if (!isSprinting && isSprintPressed() && canSprint() && timeUntilNextSwitchSprint >= TIME_BETWEEN_SWITCH_SPRINTS) {
            isSprinting = true;
            timeUntilNextSwitchSprint = 0;
        } else if (isSprinting && !prevSprintPressed && isSprintPressed() && timeUntilNextSwitchSprint >= TIME_BETWEEN_SWITCH_SPRINTS) {
            setSprinting(false);
            isSprinting = false;
            timeUntilNextSwitchSprint = 0;
        }

        if (isSprinting && canSprint()) {
            decreaseStamina(1);
            isSprinting = true;
            speedMultiplier = 1.5F;
        } else {
            isSprinting = false;
            increaseStamina(1);
            speedMultiplier = 1;
        }

        timeUntilNextSwitchSprint++;
        prevSprintPressed = isSprintPressed();
    }

    private void jumpHandler() {
        if (onGround() && getPassengerObject().getMountTypes().contains(WALK)
                && isSpacePressed()) {

            jumpFromGround();
        }
    }

    private void swimmingHandler() {
        if (getControllingPassenger() != null && isInWater()) {
            double waterEmergeSpeed = isSpacePressed() ? 0.5 : isShiftPressed() ? -0.25 : 0.00309;

            setDeltaMovement(getDeltaMovement().x, waterEmergeSpeed, getDeltaMovement().z);

            if (getDistanceToSurface(this) <= 0.5 && isShiftPressed()) {
                moveTo(getX(), getY() + waterEmergeSpeed, getZ());
            }

            for (Entity passenger : getPassengers()) {
                setAirSupply(getMaxAirSupply());
                passenger.setAirSupply(passenger.getMaxAirSupply());
            }
        }
    }

    private void lavaSwimmingHandler() {
        if (getControllingPassenger() != null && isInLava()) {
            double lavaEmergeSpeed = isSpacePressed() ? 0.5 : 0.018;

            setDeltaMovement(getDeltaMovement().x, lavaEmergeSpeed, getDeltaMovement().z);
        }
    }

    private void flyingHandler() {
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

    private float getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.level().getHeight(Heightmap.Types.WORLD_SURFACE, (int) (entity.getX() - entity.getEyeHeight()), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    private void dismountHandler() {
        if (!isAlive() || !isAddedToWorld() || isRemoved() || !(getControllingPassenger() instanceof Player)) {
            ejectPassengers();
        }

        if (checkShouldDismount() && getControllingPassenger() != null && getControllingPassenger() instanceof Player passenger) {
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
        if (getControllingPassenger() != null && getPassengerObject() != null && getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (!player.getItemInHand(getUsedItemHand()).getItem().getDescriptionId().equals(megacuff)) {
            if (Objects.equals(getPokemon().getOwnerPlayer(), player) || getControllingPassenger() != null) {
                player.startRiding(this);
                this.setMaxUpStep(2.5F);

                player.getPersistentData().putBoolean("press_space", false);
                player.getPersistentData().putBoolean("press_sprint", false);
                player.getPersistentData().putBoolean("pokemon_dismount", false);
                player.getPersistentData().putBoolean("pokemon_mount_entities", false);
            }
        } else if (player.getItemInHand(getUsedItemHand()).getItem().getDescriptionId().equals(megacuff)) {
            if (getPassengers().contains(player)) {
                cir.setReturnValue(InteractionResult.FAIL);
            }
        }
    }

    @Override
    public boolean displayFireAnimation() {
        if (getPassengerObject() != null && getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            return false;
        } else {
            return super.displayFireAnimation();
        }
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity entity) {
        if (maxPassengers == -1) {
            maxPassengers = getPassengerObject() != null ? getPassengerObject().getPassengersOffSet().size() + 1 : 0;
        }

        return getPassengers().size() < maxPassengers;
    }

    private PokemonJsonObject.PokemonConfigData getPassengerObject() {
        if (passengerObject == null) {
            passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName(), getPokemon().getForm().getName());
        }

        return passengerObject;
    }

    private boolean canMove() {
        return (getPassengerObject().getMountTypes().contains(SWIM) && isInWater())
                || (getPassengerObject().getMountTypes().contains(FLY) && !onGround())
                || (getPassengerObject().getMountTypes().contains(WALK));
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return getPassengers().isEmpty() ? null : (LivingEntity) getPassengers().get(0);
    }

    @Override
    protected float getJumpPower() {
        return 0.6f * this.getBlockJumpFactor() + this.getJumpBoostPower();
    }

    @Override
    public int getStamina() {
        return Math.min(stamina, getMaxStamina());
    }

    @Override
    public int getMaxStamina() {
        return getPassengerObject().getMaxStamina();
    }

    @Override
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public void increaseStamina(int amount) {
        setStamina(Math.min(getStamina() + amount, getMaxStamina()));
    }

    @Override
    public void decreaseStamina(int amount) {
        setStamina(Math.max(getStamina() - amount, 0));
    }

    public boolean checkShouldDismount() {
        return ((isPokemonDismountPressed()) || (getPassengers().isEmpty()));
    }

    private boolean isSpacePressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("press_space") && getControllingPassenger().getPersistentData().getBoolean("press_space");
    }

    @Override
    public boolean canSprint() {
        return (isSprintPressed() || isSprinting) && ((isSprinting && getStamina() > 0) || (!isSprinting && getStamina() > getMaxStamina() * 0.3F));
    }

    public void setSprinting(boolean sprinting) {
        if (getControllingPassenger() != null)
            getControllingPassenger().getPersistentData().putBoolean("press_sprint", sprinting);
    }

    private boolean isSprintPressed() {
        return getControllingPassenger() != null && (getControllingPassenger().getPersistentData().contains("press_sprint") && getControllingPassenger().getPersistentData().getBoolean("press_sprint"));
    }

    private boolean isShiftPressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("press_shift") && getControllingPassenger().getPersistentData().getBoolean("press_shift");
    }

    private boolean isPokemonDismountPressed() {
        return getControllingPassenger() != null && getControllingPassenger().getPersistentData().contains("pokemon_dismount") && getControllingPassenger().getPersistentData().getBoolean("pokemon_dismount");
    }

    private boolean mayMountOtherEntities() {
        return getControllingPassenger() != null && getControllingPassenger() instanceof Player && getControllingPassenger().getPersistentData().contains("pokemon_mount_entities") && getControllingPassenger().getPersistentData().getBoolean("pokemon_mount_entities");
    }

    private boolean isMoving() {
        return getControllingPassenger() != null && (getControllingPassenger().getDeltaMovement().x != 0 || getControllingPassenger().getDeltaMovement().z != 0);
    }
}