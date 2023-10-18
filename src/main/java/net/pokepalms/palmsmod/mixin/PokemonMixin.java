package net.pokepalms.palmsmod.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.google.gson.Gson;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EntityView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.pokepalms.palmsmod.config.passengerConfigObject.MountType;
import net.pokepalms.palmsmod.config.passengerConfigObject.PokemonJsonObject;
import net.pokepalms.palmsmod.config.server.PassengerConfig;
import net.pokepalms.palmsmod.mixininterface.IEntityData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(PokemonEntity.class)
public abstract class PokemonMixin extends TameableShoulderEntity {
    Gson gson = new Gson().newBuilder().create();
    PokemonJsonObject.PokemonConfigData passengerObject;
    @Shadow
    private Pokemon pokemon;
    public boolean flying = false;

    @Shadow
    public abstract boolean canWalkOnFluid(@NotNull FluidState state);

    @Shadow
    public abstract void setAir(int air);

    protected PokemonMixin(EntityType<? extends TameableShoulderEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        getPassengerPositionOffset(player);

        if (canAddPassenger(player) && passengerObject != null) {
            player.startRiding(this);
        }

        return super.interactMob(player, hand);
    }

    @Override
    public void tick() {
        if (!isAlive()) {
            removeAllPassengers();
        }

        if (getFirstPassenger() != null && getFirstPassenger() instanceof PlayerEntity player) {
            if (player.isSneaking() && !isPokemonFlying()) {
                player.stopRiding();
            }
        }

        super.tick();
    }

    @Override
    protected int computeFallDamage(float fallDistance, float damageMultiplier) {
        return isPokemonFlying() ? 0 : super.computeFallDamage(fallDistance, damageMultiplier);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void travel(Vec3d pos) {
        if (this.hasPassengers() && this.getFirstPassenger() != null && this.getFirstPassenger().isAlive() && passengerObject != null) {
            // Get the passenger
            LivingEntity passenger = getControllingPassenger();
            this.prevYaw = getYaw();
            this.prevPitch = getPitch();
            float modifierSpeed = passengerObject != null ? passengerObject.getSpeedModifier() : 1;

            // Set the entity's yaw and pitch from the passenger's yaw and pitch
            setYaw(passenger.getYaw());
            setPitch(passenger.getPitch() * 0.5f);
            setRotation(getYaw(), getPitch());

            this.bodyYaw = this.getYaw();
            this.headYaw = this.bodyYaw;
            float x = passenger.sidewaysSpeed * 0.5F;
            float z = passenger.forwardSpeed;

            if (z <= 0)
                z *= 0.25f;

            this.setMovementSpeed(0.3f * modifierSpeed);

            if (getFirstPassenger() != null && passengerObject != null) {
                NbtCompound tag = ((IEntityData) getFirstPassenger()).getPersistentData();

                if (this.submergedInWater && passengerObject.getMountTypes().contains(MountType.SWIM))
                    swimmingController(tag);
                if (!this.submergedInWater && passengerObject.getMountTypes().contains(MountType.FLY))
                    flyingController((PlayerEntity) getFirstPassenger(), tag);

                tag.putBoolean("press_space", false);
            }


            super.travel(new Vec3d(x, pos.y, z));
        } else {
            super.travel(pos);
        }
    }

    void swimmingController(NbtCompound tag) {
        float distanceToSurface = getDistanceToSurface(this);

        if (distanceToSurface > 0.55 || distanceToSurface < 0) {
            double waterEmergeSpeed = tag.contains("press_space") && tag.getBoolean("press_space") ? 0.05 : 0.0025;
            setAir(getMaxAir());

            setVelocity(getVelocity().x, waterEmergeSpeed, getVelocity().z);
        }
    }

    void flyingController(PlayerEntity player, NbtCompound tag) {
        boolean increaseAltitude = tag.contains("press_space") && tag.getBoolean("press_space");
        boolean decreaseAltitude = player.isSneaking();

        if (isPokemonFlying()) {
            double altitudeIncreaseValue = increaseAltitude ? 0.3 : decreaseAltitude ? -0.3 : 0;
            setVelocity(getVelocity().x, altitudeIncreaseValue, getVelocity().z);
        }

        shouldStopFlying(player);
        shouldStartFlying(increaseAltitude);
    }

    void shouldStartFlying(boolean increaseAltitude) {
        if (!isPokemonFlying())
            setPokemonFlying(!submergedInWater && increaseAltitude);
    }

    void shouldStopFlying(PlayerEntity player) {
        if (isPokemonFlying())
            setPokemonFlying(!((isOnGround() || submergedInWater) && player.isSneaking()));
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        // If the passenger is a player, update their position
        if (this.hasPassenger(passenger)) {
            Vec3d offset = getPassengerPositionOffset(passenger);

            // Update the passenger's position
            double xPos = this.getX() + offset.x;
            double yPos = this.getY() + this.getMountedHeightOffset() + passenger.getHeightOffset() + offset.y;
            double zPos = this.getZ() + offset.z;

            positionUpdater.accept(passenger, xPos, yPos, zPos);
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return getPassengerList().size() < 1 && passenger instanceof PlayerEntity && isPokemonOwner(passenger);
    }

    boolean isPokemonOwner(Entity entity) {
        return entity instanceof PlayerEntity && Objects.equals(pokemon.getOwnerPlayer(), entity);
    }

    private PokemonJsonObject.PokemonConfigData getPassengerObject(String pokemonType) {
        // Obtain the passenger object from the config
        PokemonJsonObject pokemonJsonObject = gson.fromJson(PassengerConfig.PASSENGER_POSITION, PokemonJsonObject.class);

        if (pokemonJsonObject != null) {
            // Check if Pokemon is in the list of pokemon that can be mounted
            for (String translationKey : pokemonJsonObject.getPokemonIDs()) {
                if (translationKey.equals(pokemonType)) {
                    return pokemonJsonObject.getPokemonData(translationKey);
                }
            }
        }

        return null;
    }

    Vec3d getPassengerPositionOffset(Entity passenger) {
        // If the passenger object is null, get the passenger object from the config
        if (passengerObject == null) {
            String pokemonType = pokemon.getSpecies().getName();
            passengerObject = getPassengerObject(pokemonType);
        }

        // If the passenger is a player, update their position
        if (this.hasPassenger(passenger)) {
            // Get the offsets from the config
            float xOffset = passengerObject != null ? passengerObject.getOffSet().get(0) : 0;
            float yOffset = passengerObject != null ? passengerObject.getOffSet().get(1) : 0;
            float zOffset = passengerObject != null ? passengerObject.getOffSet().get(2) : 0;

            return new Vec3d(xOffset, yOffset, zOffset);
        }

        return null;
    }


    float getDistanceToSurface(Entity entity) {
        double yPos = entity.getY();
        double surfaceYPos = entity.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE_WG, (int) entity.getX(), (int) entity.getZ());

        return (float) (surfaceYPos - yPos);
    }

    boolean isPokemonFlying() {
        return flying;
    }

    void setPokemonFlying(boolean flying) {
        this.flying = flying;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) super.getFirstPassenger();
    }


    @Override
    public EntityView method_48926() {
        return null;
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }

    @Override
    public boolean cannotBeSilenced() {
        return super.cannotBeSilenced();
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
