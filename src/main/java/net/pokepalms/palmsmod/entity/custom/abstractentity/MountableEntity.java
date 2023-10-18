package net.pokepalms.palmsmod.entity.custom.abstractentity;

import com.google.gson.Gson;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableShoulderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pokepalms.palmsmod.config.passengerConfigObject.PokemonJsonObject;
import net.pokepalms.palmsmod.config.server.PassengerConfig;
import org.jetbrains.annotations.Nullable;

public abstract class MountableEntity extends TameableShoulderEntity {
    Gson gson = new Gson().newBuilder().create();
    PokemonJsonObject.PokemonConfigData passengerObject;

    protected MountableEntity(EntityType<? extends TameableShoulderEntity> entityType, World world) {
        super(entityType, world);

        // Obtain the passenger object from the config
        PokemonJsonObject pokemonJsonObject = gson.fromJson(PassengerConfig.PASSENGER_POSITION, PokemonJsonObject.class);

        if (pokemonJsonObject != null) {
            // Check if pokemon is in the list of pokemon that can be mounted
            for (String translationKey : pokemonJsonObject.getPokemonIDs()) {
                if (translationKey.equals(getType().getTranslationKey())) {
                    this.passengerObject = pokemonJsonObject.getPokemonData(translationKey);

                    return;
                }
            }
        }
    }

    @Override
    protected MoveEffect getMoveEffect() {
        return MoveEffect.EVENTS;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        // On player interaction, if the player is not already riding the entity, add the player as a passenger
        if (canAddPassenger(player)) {
            player.startRiding(this);
        }

        return super.interactMob(player, hand);
    }

    @Override
    public void tick() {
        if (!isAlive()) {
            removeAllPassengers();
        }

        super.tick();
    }

    @Override
    public void travel(Vec3d pos) {
        if (this.isAlive()) {
            if (this.hasPassengers() && this.getFirstPassenger() != null && this.getFirstPassenger().isAlive()) {
                LivingEntity passenger = (LivingEntity) getControllingPassenger();
                this.prevYaw = getYaw();
                this.prevPitch = getPitch();

                setYaw(passenger.getYaw());
                setPitch(passenger.getPitch() * 0.5f);
                setRotation(getYaw(), getPitch());

                this.bodyYaw = this.getYaw();
                this.headYaw = this.bodyYaw;
                float x = passenger.sidewaysSpeed * 0.5F;
                float z = passenger.forwardSpeed;

                if (z <= 0)
                    z *= 0.25f;

                this.setMovementSpeed(0.3f);
                super.travel(new Vec3d(x, pos.y, z));
            }
        }
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        if (this.hasPassenger(passenger)) {
            Float xOffset = passengerObject.getOffSet().get(0);
            Float yOffset = passengerObject.getOffSet().get(1);
            Float zOffset = passengerObject.getOffSet().get(2);

            double yPos = getY() + getMountedHeightOffset() + passenger.getHeightOffset();
            positionUpdater.accept(passenger, getX() + xOffset, yPos + yOffset, getZ() + zOffset);
        }
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return getPassengerList().size() < 1 && passenger instanceof PlayerEntity;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return (LivingEntity) super.getFirstPassenger();
    }
}