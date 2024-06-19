package dev.zanckor.cobblemonrider.mixin;


import com.cobblemon.mod.common.api.entity.PokemonSideDelegate;
import com.cobblemon.mod.common.api.scheduling.Schedulable;
import com.cobblemon.mod.common.entity.Poseable;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonrider.CobblemonRider;
import dev.zanckor.cobblemonrider.MCUtil;
import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import dev.zanckor.cobblemonrider.mixininterface.IPokemonStamina;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.ShoulderRidingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
    @Unique
    private PokemonJsonObject.PokemonConfigData cobblemonRiding$passengerObject;
    @Unique
    private int cobblemonRiding$stamina = Integer.MAX_VALUE;
    @Unique
    private int cobblemonRiding$maxPassengers = -1;

    @Unique
    private static final int TIME_BETWEEN_SWITCH_SPRINTS = 10;
    @Unique
    private int cobblemonRiding$timeUntilNextSwitchSprint = 0;
    @Unique
    private boolean cobblemonRiding$isSprinting;
    @Unique
    private boolean cobblemonRiding$prevSprintPressed;
    @Unique
    private float cobblemonRiding$speedMultiplier;
    @Unique
    private Vec3 cobblemonRiding$prevMovementInput;
    @Unique
    private int cobblemonRiding$timeUntilNextJump;

    @Shadow
    public abstract Pokemon getPokemon();

    @Shadow
    public abstract void checkDespawn();

    @Shadow
    public abstract @NotNull PokemonSideDelegate getDelegate();

    @Shadow
    public abstract void travel(@NotNull Vec3 movementInput);

    @Shadow
    public abstract boolean isFalling();

    protected PokemonMixin(EntityType<? extends ShoulderRidingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;Lcom/cobblemon/mod/common/pokemon/Pokemon;Lnet/minecraft/world/entity/EntityType;)V", at = @At("RETURN"))
    private void init(Level level, Pokemon pokemon, EntityType<? extends PokemonEntity> entityType, CallbackInfo ci) {
        this.setMaxUpStep(1);
        this.cobblemonRiding$prevMovementInput = Vec3.ZERO;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (getControllingPassenger() != null) {
            if (cobblemonRiding$mayMountOtherEntities() && canAddPassenger(getControllingPassenger())) {
                cobblemonRiding$mountEntity();
            }

            cobblemonRiding$dismountHandler();
            cobblemonRiding$movementHandler();

            cobblemonRiding$rotateBody();
        }
    }

    @Unique
    private void cobblemonRiding$mountEntity() {
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
        if (this.hasPassenger(entity) && cobblemonRiding$getPassengerObject() != null && getControllingPassenger() != null) {
            int passengerIndex = getPassengers().indexOf(entity) - 1;
            boolean isControllingPassenger = getControllingPassenger().equals(entity);
            ArrayList<Float> offSet = isControllingPassenger ? cobblemonRiding$getPassengerObject().getRidingOffSet() : cobblemonRiding$getPassengerObject().getPassengersOffSet().get(passengerIndex);

            setYBodyRot(getControllingPassenger().getYRot());

            float xOffset = offSet.get(2);
            float yOffset = offSet.get(1);
            float zOffset = offSet.get(0);

            Vec3 vec3 = (new Vec3(xOffset, yOffset, zOffset)).yRot(-getControllingPassenger().getYRot() * 0.017453292F);

            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
        }
    }

    @Unique
    private void cobblemonRiding$movementHandler() {
        if (getControllingPassenger() instanceof Player passenger && cobblemonRiding$getPassengerObject() != null) {
            if (!cobblemonRiding$getPassengerObject().getMountTypes().contains(SWIM) && wasTouchingWater) return;

            cobblemonRiding$sprintHandler();
            cobblemonRiding$travelHandler();

            if (cobblemonRiding$getPassengerObject().getMountTypes().contains(SWIM)) {
                cobblemonRiding$swimmingHandler();
            }

            if (cobblemonRiding$getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
                cobblemonRiding$lavaSwimmingHandler();
            }

            if (cobblemonRiding$getPassengerObject().getMountTypes().contains(FLY)) {
                cobblemonRiding$flyingHandler();
            }

            cobblemonRiding$resetKeyData(passenger);
        }
    }

    @Unique
    private void cobblemonRiding$travelHandler() {
        if (getControllingPassenger() != null && cobblemonRiding$canMove()) {
            float speedConfigModifier = cobblemonRiding$getPassengerObject().getSpeedModifier();
            Vec3 movementInput;
            ArrayList<PokemonJsonObject.MountType> mountTypes = cobblemonRiding$getPassengerObject().getMountTypes();
            boolean isNonGravityMount = mountTypes.contains(FLY) || (mountTypes.contains(SWIM) && wasTouchingWater);

            movementInput = getControllingPassenger().getDeltaMovement()
                    .scale(cobblemonRiding$speedMultiplier)
                    .add(cobblemonRiding$prevMovementInput)
                    .scale(0.86)
                    .multiply(1, isNonGravityMount ? 0 : 1, 1);

            move(MoverType.SELF, movementInput);
            setDeltaMovement(movementInput);
            cobblemonRiding$jumpHandler();

            cobblemonRiding$prevMovementInput = getDeltaMovement();

            move(MoverType.SELF, getDeltaMovement().multiply(speedConfigModifier, 1, speedConfigModifier));
            setDeltaMovement(getDeltaMovement().multiply(speedConfigModifier, 1, speedConfigModifier));
        }
    }

    @Unique
    private void cobblemonRiding$jumpHandler() {
        cobblemonRiding$timeUntilNextJump++;

        if (cobblemonRiding$isSpacePressed() && onGround() && cobblemonRiding$timeUntilNextJump > 20) {
            jumpFromGround();

            cobblemonRiding$timeUntilNextJump = 0;
        }
    }

    @Override
    public boolean onGround() {
        return super.onGround();
    }

    @Unique
    private void cobblemonRiding$rotateBody() {
        if (getFirstPassenger() != null) {
            setRot(getFirstPassenger().getYRot(), 0);
        }
    }

    @Unique
    private void cobblemonRiding$sprintHandler() {
        if (!cobblemonRiding$isMoving()) {
            cobblemonRiding$isSprinting = false;
            cobblemonRiding$increaseStamina(1);
            cobblemonRiding$speedMultiplier = 1;
            return;
        }

        if (!cobblemonRiding$isSprinting && cobblemonRiding$isSprintPressed() && canSprint() && cobblemonRiding$timeUntilNextSwitchSprint >= TIME_BETWEEN_SWITCH_SPRINTS) {
            cobblemonRiding$isSprinting = true;
            cobblemonRiding$timeUntilNextSwitchSprint = 0;
        } else if (cobblemonRiding$isSprinting && !cobblemonRiding$prevSprintPressed && cobblemonRiding$isSprintPressed() && cobblemonRiding$timeUntilNextSwitchSprint >= TIME_BETWEEN_SWITCH_SPRINTS) {
            setSprinting(false);
            cobblemonRiding$isSprinting = false;
            cobblemonRiding$timeUntilNextSwitchSprint = 0;
        }

        if (cobblemonRiding$isSprinting && canSprint()) {
            cobblemonRiding$decreaseStamina(1);
            cobblemonRiding$isSprinting = true;
            cobblemonRiding$speedMultiplier = 1.5F;
        } else {
            cobblemonRiding$isSprinting = false;
            cobblemonRiding$increaseStamina(1);
            cobblemonRiding$speedMultiplier = 1;
        }

        cobblemonRiding$timeUntilNextSwitchSprint++;
        cobblemonRiding$prevSprintPressed = cobblemonRiding$isSprintPressed();
    }

    @Unique
    private void cobblemonRiding$swimmingHandler() {
        if (getControllingPassenger() != null && isInWater()) {
            double waterEmergeSpeed = cobblemonRiding$isSpacePressed() ? 0.5 : cobblemonRiding$isShiftPressed() ? -0.25 : onGround() ? 0 : 0.00309;

            setDeltaMovement(getDeltaMovement().x, waterEmergeSpeed, getDeltaMovement().z);

            if (cobblemonRiding$getDistanceToSurface(this) <= 0.5 && cobblemonRiding$isShiftPressed()) {
                moveTo(getX(), getY() + waterEmergeSpeed, getZ());
            }

            for (Entity passenger : getPassengers()) {
                setAirSupply(getMaxAirSupply());
                passenger.setAirSupply(passenger.getMaxAirSupply());
            }
        }
    }

    @Unique
    private void cobblemonRiding$lavaSwimmingHandler() {
        if (getControllingPassenger() != null && isInLava()) {
            double lavaEmergeSpeed = cobblemonRiding$isSpacePressed() ? 0 : 0.203;

            setDeltaMovement(getDeltaMovement().x, lavaEmergeSpeed, getDeltaMovement().z);
        }
    }

    @Unique
    private void cobblemonRiding$flyingHandler() {
        if (getControllingPassenger() == null) return;
        boolean increaseAltitude = cobblemonRiding$isSpacePressed();
        boolean decreaseAltitude = cobblemonRiding$isShiftPressed();

        double altitudeIncreaseValue = increaseAltitude ? 0.3 : decreaseAltitude ? -0.3 : 0;
        setDeltaMovement(getDeltaMovement().x, altitudeIncreaseValue, getDeltaMovement().z);

        if (getPokemon().getEntity() != null) {
            getPokemon().getEntity().setBehaviourFlag(PokemonBehaviourFlag.FLYING, !onGround());
        }
    }

    @Unique
    private float cobblemonRiding$getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.level().getHeight(Heightmap.Types.WORLD_SURFACE, (int) (entity.getX() - entity.getEyeHeight()), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    @Unique
    private void cobblemonRiding$dismountHandler() {
        if (!isAlive() || !isAddedToWorld() || isRemoved() || !(getControllingPassenger() instanceof Player)) {
            ejectPassengers();
        }

        if (cobblemonRiding$checkShouldDismount() && getControllingPassenger() != null && getControllingPassenger() instanceof Player passenger) {
            cobblemonRiding$resetKeyData(passenger);
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
        if (getControllingPassenger() != null && cobblemonRiding$getPassengerObject() != null && cobblemonRiding$getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mobInteract", at = @At("TAIL"))
    public void mobInteractRiding(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (!player.getItemInHand(getUsedItemHand()).getItem().getDescriptionId().equals(megacuff)) {
            if (Objects.equals(getPokemon().getOwnerPlayer(), player) || getControllingPassenger() != null) {
                player.startRiding(this);
                this.setMaxUpStep(2.5F);
                cobblemonRiding$resetKeyData(player);
            }
        }
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    public void mobInteractRemoveMegamonsMegaCuff(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (player.getItemInHand(getUsedItemHand()).getItem().getDescriptionId().equals(megacuff)) {
            if (getPassengers().contains(player)) {
                cir.setReturnValue(InteractionResult.FAIL);
            }
        }
    }

    @Override
    public boolean displayFireAnimation() {
        if (cobblemonRiding$getPassengerObject() != null && cobblemonRiding$getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            return false;
        } else {
            return super.displayFireAnimation();
        }
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity entity) {
        if (cobblemonRiding$maxPassengers == -1) {
            cobblemonRiding$maxPassengers = cobblemonRiding$getPassengerObject() != null ? cobblemonRiding$getPassengerObject().getPassengersOffSet().size() + 1 : 0;
        }

        return getPassengers().size() < cobblemonRiding$maxPassengers;
    }

    @Unique
    private PokemonJsonObject.PokemonConfigData cobblemonRiding$getPassengerObject() {
        if (cobblemonRiding$passengerObject == null) {
            cobblemonRiding$passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName(), getPokemon().getForm().getName());
        }

        return cobblemonRiding$passengerObject;
    }

    @Unique
    private boolean cobblemonRiding$canMove() {
        return (cobblemonRiding$getPassengerObject().getMountTypes().contains(SWIM) && isInWater())
                || (cobblemonRiding$getPassengerObject().getMountTypes().contains(FLY) && !onGround())
                || (cobblemonRiding$getPassengerObject().getMountTypes().contains(WALK));
    }

    @Unique
    private void cobblemonRiding$resetKeyData(Player passenger) {
        passenger.getPersistentData().putBoolean("press_space", false);
        passenger.getPersistentData().putBoolean("press_sprint", false);
        passenger.getPersistentData().putBoolean("pokemon_dismount", false);
        passenger.getPersistentData().putBoolean("press_shift", false);
        passenger.getPersistentData().putBoolean("pokemon_mount_entities", false);
        passenger.setShiftKeyDown(false);
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
    public int cobblemonRiding$getStamina() {
        return Math.min(cobblemonRiding$stamina, cobblemonRiding$getMaxStamina());
    }

    @Override
    public int cobblemonRiding$getMaxStamina() {
        return cobblemonRiding$getPassengerObject().getMaxStamina();
    }

    @Override
    public void cobblemonRiding$setStamina(int stamina) {
        this.cobblemonRiding$stamina = stamina;
    }

    @Override
    public void cobblemonRiding$increaseStamina(int amount) {
        cobblemonRiding$setStamina(Math.min(cobblemonRiding$getStamina() + amount, cobblemonRiding$getMaxStamina()));
    }

    @Override
    public void cobblemonRiding$decreaseStamina(int amount) {
        cobblemonRiding$setStamina(Math.max(cobblemonRiding$getStamina() - amount, 0));
    }

    @Unique
    public boolean cobblemonRiding$checkShouldDismount() {
        return ((cobblemonRiding$isPokemonDismountPressed()) || (getPassengers().isEmpty()));
    }


    @Unique
    private boolean cobblemonRiding$isSpacePressed() {
        return getControllingPassenger() != null
                && getControllingPassenger().getPersistentData().contains("press_space")
                && getControllingPassenger().getPersistentData().getBoolean("press_space");
    }

    @Override
    public boolean canSprint() {
        return (cobblemonRiding$isSprintPressed() || cobblemonRiding$isSprinting)
                && ((cobblemonRiding$isSprinting && cobblemonRiding$getStamina() > 0) || (!cobblemonRiding$isSprinting && cobblemonRiding$getStamina() > cobblemonRiding$getMaxStamina() * 0.3F));
    }

    public void setSprinting(boolean sprinting) {
        if (getControllingPassenger() != null)
            getControllingPassenger().getPersistentData().putBoolean("press_sprint", sprinting);
    }

    @Unique
    private boolean cobblemonRiding$isSprintPressed() {
        return getControllingPassenger() != null
                && (getControllingPassenger().getPersistentData().contains("press_sprint")
                && getControllingPassenger().getPersistentData().getBoolean("press_sprint"));
    }

    @Unique
    private boolean cobblemonRiding$isShiftPressed() {
        return getControllingPassenger() != null
                && getControllingPassenger().getPersistentData().contains("press_shift")
                && getControllingPassenger().getPersistentData().getBoolean("press_shift");
    }

    @Unique
    private boolean cobblemonRiding$isPokemonDismountPressed() {
        return getControllingPassenger() != null
                && getControllingPassenger().getPersistentData().contains("pokemon_dismount")
                && getControllingPassenger().getPersistentData().getBoolean("pokemon_dismount");
    }

    @Unique
    private boolean cobblemonRiding$mayMountOtherEntities() {
        return CobblemonRider.pokemonJsonObject.mustAllowEntityRiding() &&
                getControllingPassenger() != null
                && getControllingPassenger() instanceof Player player
                && player.getPersistentData().contains("pokemon_mount_entities")
                && player.getPersistentData().getBoolean("pokemon_mount_entities");
    }

    @Unique
    private boolean cobblemonRiding$isMoving() {
        return getControllingPassenger() != null
                && (getControllingPassenger().getDeltaMovement().x != 0 || getControllingPassenger().getDeltaMovement().z != 0);
    }
}