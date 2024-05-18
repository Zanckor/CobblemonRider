package dev.zanckor.cobblemonridingfabric.mixin;


import com.cobblemon.mod.common.api.entity.PokemonSideDelegate;
import com.cobblemon.mod.common.api.scheduling.Schedulable;
import com.cobblemon.mod.common.entity.Poseable;
import com.cobblemon.mod.common.entity.pokemon.PokemonBehaviourFlag;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import dev.zanckor.cobblemonridingfabric.MCUtil;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import dev.zanckor.cobblemonridingfabric.mixininterface.IPokemonStamina;
import kotlin.jvm.internal.DefaultConstructorMarker;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
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

import static dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject.MountType.*;

@Mixin(PokemonEntity.class)
public abstract class PokemonMixin extends PathAwareEntity implements Poseable, Schedulable, IPokemonStamina {
    private PokemonJsonObject.PokemonConfigData passengerObject;
    private int stamina = Integer.MAX_VALUE;
    private static final int TIME_TO_SWITCH_SPRINT = 10;
    private boolean wasSprinting;
    private boolean wasSprintPressed;
    private float speedMultiplier;
    private int maxPassengers = -1;
    private int timeUntilSwitchSprint;

    protected PokemonMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract Pokemon getPokemon();

    @Shadow
    public abstract void checkDespawn();

    @Shadow
    public abstract @NotNull PokemonSideDelegate getDelegate();

    @Shadow
    public abstract void travel(@NotNull Vec3d movementInput);

    @Shadow
    public abstract void setAir(int air);

    @Inject(method = "<init>(Lnet/minecraft/world/World;Lcom/cobblemon/mod/common/pokemon/Pokemon;Lnet/minecraft/entity/EntityType;ILkotlin/jvm/internal/DefaultConstructorMarker;)V", at = @At("RETURN"))
    private void init(World par1, Pokemon par2, EntityType par3, int par4, DefaultConstructorMarker par5, CallbackInfo ci) {
        this.setStepHeight(1);
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

    private void movementHandler() {
        if (getControllingPassenger() instanceof PlayerEntity passenger && getPassengerObject() != null) {
            if (getPassengerObject().getMountTypes().contains(SWIM) && this.isTouchingWater()) return;

            sprintHandler();
            travelHandler();

            if (getPassengerObject().getMountTypes().contains(WALK) && isOnGround()) {
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

            ((IEntityData) passenger).getPersistentData().putBoolean("press_space", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("press_sprint", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("pokemon_dismount", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("press_shift", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("pokemon_mount_entities", false);
            passenger.setSneaking(false);
        }
    }

    private void travelHandler() {
        if (getControllingPassenger() != null && canMove()) {
            float x = (float) getControllingPassenger().getVelocity().x * 10;
            float z = (float) getControllingPassenger().getVelocity().z * 10;
            setVelocity(getVelocity().multiply(0, 1, 0).add(x * speedMultiplier, 0, z * speedMultiplier));

            travel(new Vec3d(x, getVelocity().y, z));
        }
    }

    private void rotateBody() {
        if (getFirstPassenger() != null) {
            setRotation(getFirstPassenger().getYaw(), 0);
        }
    }

    private void sprintHandler() {
        // If the player is sprinting, decrease the stamina and increase the speed multiplier, else reset the speed multiplier
        // If player press sprint again while sprinting, change to not sprinting
        if (isSprintPressed() && canSprintAsVehicle()) {
            if (timeUntilSwitchSprint >= TIME_TO_SWITCH_SPRINT) {
                wasSprinting = !wasSprinting;
                timeUntilSwitchSprint = 0;
            }

            if (wasSprinting) {
                decreaseStamina(1);
                speedMultiplier = 1.5F;
            } else {
                speedMultiplier = 1;
            }
        } else {
            speedMultiplier = 1;
            timeUntilSwitchSprint = 0;
        }

        timeUntilSwitchSprint++;
        wasSprintPressed = isSprintPressed();
    }

    private void jumpHandler() {
        if (isOnGround() && getPassengerObject().getMountTypes().contains(WALK)
                && isSpacePressed()) {

            jump();
        }
    }

    private void swimmingHandler() {
        if (getControllingPassenger() != null && isTouchingWater()) {
            double waterEmergeSpeed = isSpacePressed() ? 0.5 : isShiftPressed() ? -0.25 : 0.00309;

            setVelocity(getVelocity().x, waterEmergeSpeed, getVelocity().z);

            if (getDistanceToSurface(this) <= 0.5 && isShiftPressed()) {
                move(MovementType.SELF, new Vec3d(0, waterEmergeSpeed, 0));
            }

            for (Entity passenger : getPassengerList()) {
                setAir(getMaxAir());
                passenger.setAir(passenger.getMaxAir());
            }
        }
    }

    private void lavaSwimmingHandler() {
        if (getControllingPassenger() != null && isInLava()) {
            double lavaEmergeSpeed = isSpacePressed() ? 0.5 : 0.018;

            setVelocity(getVelocity().x, lavaEmergeSpeed, getVelocity().z);
        }
    }

    private void flyingHandler() {
        if (getControllingPassenger() == null) return;
        boolean increaseAltitude = isSpacePressed();
        boolean decreaseAltitude = isShiftPressed();


        if (!isOnGround() || increaseAltitude) {
            double altitudeIncreaseValue = increaseAltitude ? 0.3 : decreaseAltitude ? -0.3 : 0;

            setVelocity(getVelocity().x, altitudeIncreaseValue, getVelocity().z);
        }

        if (getPokemon().getEntity() != null) {
            getPokemon().getEntity().setBehaviourFlag(PokemonBehaviourFlag.FLYING, !isOnGround());
        }
    }

    private float getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, (int) (entity.getX() - entity.getEyeHeight(EntityPose.STANDING)), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    private void dismountHandler() {
        if (!isAlive() || isRemoved() || !(getControllingPassenger() instanceof PlayerEntity)) {
            removeAllPassengers();
        }

        if (checkShouldDismount() && getControllingPassenger() != null && getControllingPassenger() instanceof PlayerEntity passenger) {
            ((IEntityData) passenger).getPersistentData().putBoolean("press_space", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("press_sprint", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("pokemon_dismount", false);
            ((IEntityData) passenger).getPersistentData().putBoolean("press_shift", false);

            passenger.stopRiding();
            removeAllPassengers();
        }
    }

    @Inject(method = "handleFallDamage", at = @At("HEAD"), cancellable = true)
    public void causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void hurt(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        if (getControllingPassenger() != null && getPassengerObject() != null && getPassengerObject().getMountTypes().contains(LAVA_SWIM)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    public void mobInteract(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        String megacuff = "item.megamons.mega_cuff";

        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (!player.getMainHandStack().getItem().getTranslationKey().equals(megacuff)) {
            if (Objects.equals(getPokemon().getOwnerPlayer(), player) || getControllingPassenger() != null) {
                player.startRiding(this);
                this.setStepHeight(2.5F);

                ((IEntityData) player).getPersistentData().putBoolean("press_space", false);
                ((IEntityData) player).getPersistentData().putBoolean("press_sprint", false);
                ((IEntityData) player).getPersistentData().putBoolean("pokemon_dismount", false);
                ((IEntityData) player).getPersistentData().putBoolean("pokemon_mount_entities", false);
            }
        } else if (player.getMainHandStack().getItem().getTranslationKey().equals(megacuff)) {
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

    private PokemonJsonObject.PokemonConfigData getPassengerObject() {
        if (passengerObject == null) {
            passengerObject = MCUtil.getPassengerObject(getPokemon().getSpecies().getName(), getPokemon().getForm().getName());
        }

        return passengerObject;
    }

    private boolean canMove() {
        return (getPassengerObject().getMountTypes().contains(SWIM) && isTouchingWater())
                || (getPassengerObject().getMountTypes().contains(FLY) && !isOnGround())
                || (getPassengerObject().getMountTypes().contains(WALK));
    }

    @Override
    public boolean canSprintAsVehicle() {
        return isSprinting() && ((wasSprinting && getStamina() > 0) || (!wasSprinting && getStamina() > getMaxStamina() * 0.3F));
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return getPassengerList().isEmpty() ? null : (LivingEntity) getPassengerList().get(0);
    }

    @Override
    protected float getJumpVelocity() {
        return 0.6f * this.getJumpVelocityMultiplier() + this.getJumpBoostVelocityModifier();
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
        return ((isPokemonDismountPressed()) || (getPassengerList().isEmpty()));
    }

    private boolean isSpacePressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).getPersistentData().contains("press_space") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("press_space");
    }

    @Override
    public void setSprinting(boolean sprinting) {
        if (getControllingPassenger() != null) {
            ((IEntityData) getControllingPassenger()).getPersistentData().putBoolean("press_sprint", sprinting);
        }
    }

    @Override
    public boolean isSprinting() {
        return getControllingPassenger() != null &&
                ((wasSprinting && isMoving()) || (((IEntityData) getControllingPassenger()).getPersistentData().contains("press_sprint") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("press_sprint")));
    }

    private boolean isSprintPressed() {
        return getControllingPassenger() != null && (((IEntityData) getControllingPassenger()).getPersistentData().contains("press_sprint") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("press_sprint"));
    }


    private boolean isShiftPressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).getPersistentData().contains("press_shift") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("press_shift");
    }

    private boolean isPokemonDismountPressed() {
        return getControllingPassenger() != null && ((IEntityData) getControllingPassenger()).getPersistentData().contains("pokemon_dismount") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("pokemon_dismount");
    }

    private boolean mayMountOtherEntities() {
        return getControllingPassenger() != null && getControllingPassenger() instanceof PlayerEntity && ((IEntityData) getControllingPassenger()).getPersistentData().contains("pokemon_mount_entities") && ((IEntityData) getControllingPassenger()).getPersistentData().getBoolean("pokemon_mount_entities");
    }

    private boolean isMoving() {
        return getControllingPassenger() != null && (getControllingPassenger().getVelocity().x != 0 || getControllingPassenger().getVelocity().z != 0);
    }
}