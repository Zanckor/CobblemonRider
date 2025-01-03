package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.api.entity.PokemonSideDelegate;
import com.cobblemon.mod.common.api.scheduling.Schedulable;
import com.cobblemon.mod.common.entity.PosableEntity;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.MCUtil;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import dev.zanckor.cobblemonridingfabric.mixininterface.IPokemonStamina;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject.MountType.*;

@Mixin(PokemonEntity.class)
public abstract class PokemonMixin extends PathAwareEntity implements PosableEntity, Schedulable, IPokemonStamina {
    @Unique
    private PokemonJsonObject.PokemonConfigData passengerObject;
    @Unique
    private int stamina = Integer.MAX_VALUE;
    @Unique
    private int maxPassengers = -1;

    @Unique
    private static final int TIME_BETWEEN_SWITCH_SPRINTS = 10;
    @Unique
    private int timeUntilNextSwitchSprint = 0;
    @Unique
    private boolean isSprinting;
    @Unique
    private boolean prevSprintPressed;
    @Unique
    private float speedMultiplier;
    @Unique
    private Vec3d prevMovementInput;
    @Unique
    private int timeUntilNextJump;


    protected PokemonMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow(remap = false)
    public abstract Pokemon getPokemon();

    @Shadow
    public abstract void checkDespawn();

    @Shadow(remap = false)
    public abstract @NotNull PokemonSideDelegate getDelegate();

    @Shadow
    public abstract void setAir(int air);


    @Inject(method = "<init>(Lnet/minecraft/world/World;Lcom/cobblemon/mod/common/pokemon/Pokemon;Lnet/minecraft/entity/EntityType;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", at = @At("RETURN"))
    private void init(World par1, Pokemon par2, EntityType<?> par3, int par4, DefaultConstructorMarker par5, CallbackInfo ci) {
        this.prevMovementInput = Vec3d.ZERO;
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

    @Inject(method = "recallWithAnimation", at = @At("HEAD"), remap = false)
    public void recall(CallbackInfoReturnable<CompletableFuture<Pokemon>> cir) {
        if (getControllingPassenger() != null) {
            getControllingPassenger().stopRiding();
        }
    }

    @Unique
    private void mountEntity() {
        List<LivingEntity> mobs = getWorld().getEntitiesByClass(LivingEntity.class, getBoundingBox(),
                entity -> !(entity instanceof Monster) && !(entity instanceof PlayerEntity) && !getPassengerList().contains(entity) && !entity.hasControllingPassenger());

        for (LivingEntity mob : mobs) {
            if (canAddPassenger(mob)) {
                mob.startRiding(this);
            }
        }
    }

    @Override
    protected void updatePassengerPosition(Entity entity, PositionUpdater moveFunction) {
        if (this.hasPassenger(entity) && getPassengerObject() != null && getControllingPassenger() != null) {
            int passengerIndex = getPassengerList().indexOf(entity) - 1;
            boolean isControllingPassenger = getControllingPassenger().equals(entity);
            ArrayList<Float> offSet = isControllingPassenger ? getPassengerObject().getRidingOffSet() : getPassengerObject().getPassengersOffSet().get(passengerIndex);

            setBodyYaw(getControllingPassenger().getBodyYaw());

            float xOffset = offSet.get(2);
            float yOffset = offSet.get(1);
            float zOffset = offSet.get(0);

            Vec3d vec3 = (new Vec3d(xOffset, yOffset, zOffset)).rotateY(-getControllingPassenger().getBodyYaw() * 0.017453292F);

            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z);
        }
    }

    @Unique
    private void movementHandler() {
        if (getControllingPassenger() instanceof PlayerEntity passenger && getPassengerObject() != null) {
            if (!getPassengerObject().getMountTypes().contains(SWIM) && isTouchingWater()) return;

            sprintHandler();
            travelHandler();

            if (getPassengerObject().getMountTypes().contains(SWIM)) {
                swimmingHandler();
            }

            if (getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
                lavaSwimmingHandler();
            }

            if (getPassengerObject().getMountTypes().contains(FLY)) {
                flyingHandler();
            }

            resetKeyData(passenger);
        }
    }

    @Unique
    private void travelHandler() {
        if (getControllingPassenger() != null && canMove()) {
            float speedConfigModifier = getPassengerObject().getSpeedModifier();
            Vec3d movementInput;
            ArrayList<PokemonJsonObject.MountType> mountTypes = getPassengerObject().getMountTypes();
            boolean isNonGravityMount = mountTypes.contains(FLY) || (mountTypes.contains(SWIM) && touchingWater);

            movementInput = getControllingPassenger().getVelocity()
                    .multiply(speedMultiplier * 1.2)
                    .add(prevMovementInput)
                    .multiply(0.86)
                    .multiply(1, isNonGravityMount ? 0 : 1, 1);

            move(MovementType.SELF, movementInput);
            setVelocity(movementInput);
            jumpHandler();

            prevMovementInput = getVelocity();

            move(MovementType.SELF, getVelocity().multiply(speedConfigModifier, 1, speedConfigModifier));
            setVelocity(getVelocity().multiply(speedConfigModifier, 1, speedConfigModifier));
        }
    }

    @Unique
    private void jumpHandler() {
        timeUntilNextJump++;

        if (isSpacePressed() && isOnGround() && timeUntilNextJump > 20) {
            jump();

            timeUntilNextJump = 0;
        }
    }

    @Unique
    private void rotateBody() {
        if (getFirstPassenger() != null) {
            setRotation(getFirstPassenger().getYaw(), 0);
        }
    }

    @Unique
    private void sprintHandler() {
        if (!isMoving()) {
            isSprinting = false;
            cobblemonRider$increaseStamina(1);
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
            cobblemonRider$decreaseStamina(1);
            isSprinting = true;
            speedMultiplier = 1.5F;
        } else {
            isSprinting = false;
            cobblemonRider$increaseStamina(1);
            speedMultiplier = 1;
        }

        timeUntilNextSwitchSprint++;
        prevSprintPressed = isSprintPressed();
    }


    @Unique
    private void swimmingHandler() {
        if (getControllingPassenger() != null && isTouchingWater()) {
            double waterEmergeSpeed = isSpacePressed() ? 0.5 : isShiftPressed() ? -0.25 : 0.00300;

            setVelocity(getVelocity().x, waterEmergeSpeed, getVelocity().z);
            setAir(getMaxAir());

            if (getDistanceToSurface(this) <= 0.5 && isShiftPressed()) {
                setPosition(getX(), getY() - 1, getZ());
            }
        }
    }

    @Unique
    private void lavaSwimmingHandler() {
        if (getControllingPassenger() != null && isInLava()) {
            double lavaEmergeSpeed = isSprinting ? 0.45 : 0.305;

            setVelocity(getVelocity().x, lavaEmergeSpeed, getVelocity().z);
        }
    }

    @Unique
    private void flyingHandler() {
        if (getControllingPassenger() == null) return;
        boolean increaseAltitude = isSpacePressed();
        boolean decreaseAltitude = isShiftPressed();

        double altitudeIncreaseValue = increaseAltitude ? 0.3 : decreaseAltitude ? -0.3 : 0;
        setVelocity(getVelocity().x, altitudeIncreaseValue, getVelocity().z);

        if (getPokemon().getEntity() != null) {
            getPokemon().getEntity().setBehaviourFlag(PokemonBehaviourFlag.FLYING, getWorld().getBlockState(getBlockPos().down()).isAir());
        }
    }

    @Unique
    private float getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, (int) (entity.getX() - entity.getEyeHeight(EntityPose.STANDING)), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    @Unique
    private void dismountHandler() {
        if (!isAlive() || isRemoved() || !(getControllingPassenger() instanceof PlayerEntity)) {
            removeAllPassengers();
        }

        if (checkShouldDismount() && getControllingPassenger() != null && getControllingPassenger() instanceof PlayerEntity passenger) {
            resetKeyData(passenger);
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null && fallDistance < 5) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null && getPassengerObject() != null && getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "interactMob", at = @At("TAIL"))
    public void mobInteractRiding(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (!player.getMainHandStack().getItem().getTranslationKey().equals(megacuff) && getPassengerObject() != null) {
            if (Objects.equals(getPokemon().getOwnerPlayer(), player) || getControllingPassenger() != null) {
                player.startRiding(this);
                resetKeyData(player);
            }
        }
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void mobInteractRemoveMegamonsMegaCuff(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (player.getMainHandStack().getItem().getTranslationKey().equals(megacuff) && getPassengerObject() != null) {
            if (getPassengerList().contains(player)) {
                cir.setReturnValue(ActionResult.PASS);
            }
        }
    }

    @Override
    public boolean doesRenderOnFire() {
        if (getPassengerObject() != null && getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            return false;
        } else {
            return super.doesRenderOnFire();
        }
    }

    @Override
    protected boolean canAddPassenger(@NotNull Entity entity) {
        if (maxPassengers == -1) {
            maxPassengers = getPassengerObject() != null ? getPassengerObject().getPassengersOffSet().size() + 1 : 0;
        }

        return getPassengerList().size() < maxPassengers;
    }

    @Unique
    private PokemonJsonObject.PokemonConfigData getPassengerObject() {
        if (passengerObject == null) {
            passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName(), getPokemon().getForm().getName());
        }

        return passengerObject;
    }

    @Unique
    private boolean canMove() {
        return (getPassengerObject().getMountTypes().contains(SWIM) && isTouchingWater())
                || (getPassengerObject().getMountTypes().contains(FLY) && !isOnGround())
                || (getPassengerObject().getMountTypes().contains(WALK));
    }


    @Unique
    private void resetKeyData(PlayerEntity passenger) {
        ((IEntityData) passenger).cobblemonRider$getPersistentData().putBoolean("press_space", false);
        ((IEntityData) passenger).cobblemonRider$getPersistentData().putBoolean("press_sprint", false);
        ((IEntityData) passenger).cobblemonRider$getPersistentData().putBoolean("pokemon_dismount", false);
        ((IEntityData) passenger).cobblemonRider$getPersistentData().putBoolean("press_shift", false);
        passenger.setSneaking(false);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return getPassengerList().isEmpty() ? null : (LivingEntity) getPassengerList().getFirst();
    }

    @Override
    protected float getJumpVelocity() {
        return 0.6f * this.getJumpVelocityMultiplier() + this.getJumpBoostVelocityModifier();
    }

    @Override
    public float getStepHeight() {
        return isOnGround() ? 2.5F : 0F;
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        if(getPassengerObject() == null || getPassengerObject().getMountTypes() == null) return super.computeFallDamage(fallDistance, damageMultiplier);

        boolean canFly = getPassengerObject().getMountTypes().contains(FLY);

        return canFly ? 0 : super.computeFallDamage(fallDistance, damageMultiplier);
    }

    @Override
    public int cobblemonRider$getStamina() {
        return Math.min(stamina, cobblemonRider$getMaxStamina());
    }

    @Override
    public int cobblemonRider$getMaxStamina() {
        if (getPassengerObject() == null) return 0;

        return getPassengerObject().getMaxStamina();
    }

    @Override
    public void cobblemonRider$setStamina(int stamina) {
        this.stamina = stamina;
    }

    @Override
    public void cobblemonRider$increaseStamina(int amount) {
        cobblemonRider$setStamina(Math.min(cobblemonRider$getStamina() + amount, cobblemonRider$getMaxStamina()));
    }

    @Override
    public void cobblemonRider$decreaseStamina(int amount) {
        cobblemonRider$setStamina(Math.max(cobblemonRider$getStamina() - amount, 0));
    }

    @Unique
    public boolean checkShouldDismount() {
        return ((isPokemonDismountPressed()) || (getPassengerList().isEmpty()));
    }

    @Unique
    private boolean isSpacePressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().contains("press_space") && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().getBoolean("press_space");
    }

    @Unique
    public boolean canSprint() {
        return (isSprintPressed() || isSprinting) && ((isSprinting && cobblemonRider$getStamina() > 0) || (!isSprinting && cobblemonRider$getStamina() > cobblemonRider$getMaxStamina() * 0.3F));
    }

    public void setSprinting(boolean sprinting) {
        if (getControllingPassenger() != null)
            ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().putBoolean("press_sprint", sprinting);
    }


    @Unique
    private boolean isSprintPressed() {
        return getControllingPassenger() != null && (((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().contains("press_sprint") && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().getBoolean("press_sprint"));
    }


    @Unique
    private boolean isShiftPressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().contains("press_shift") && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().getBoolean("press_shift");
    }

    @Unique
    private boolean isPokemonDismountPressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().contains("pokemon_dismount") && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().getBoolean("pokemon_dismount");
    }

    @Unique
    private boolean mayMountOtherEntities() {
        return CobblemonRidingFabric.pokemonJsonObject != null && CobblemonRidingFabric.pokemonJsonObject.mustAllowEntityRiding() && getControllingPassenger() != null && getControllingPassenger() instanceof PlayerEntity && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().contains("pokemon_mount_entities") && ((IEntityData) getControllingPassenger()).cobblemonRider$getPersistentData().getBoolean("pokemon_mount_entities");
    }

    @Unique
    private boolean isMoving() {
        return getControllingPassenger() != null && (getControllingPassenger().getVelocity().x != 0 || getControllingPassenger().getVelocity().z != 0);
    }
}