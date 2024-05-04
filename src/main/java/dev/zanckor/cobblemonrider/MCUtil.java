package dev.zanckor.cobblemonrider;

import dev.zanckor.cobblemonrider.config.PokemonJsonObject;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class MCUtil {

    public static Entity getEntityLookinAt(Entity rayTraceEntity, double distance) {
        float playerRotX = rayTraceEntity.getXRot();
        float playerRotY = rayTraceEntity.getYRot();
        Vec3 startPos = rayTraceEntity.getEyePosition();
        float f2 = Mth.cos(-playerRotY * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-playerRotY * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-playerRotX * ((float) Math.PI / 180F));
        float additionY = Mth.sin(-playerRotX * ((float) Math.PI / 180F));
        float additionX = f3 * f4;
        float additionZ = f2 * f4;
        double d0 = distance;
        Vec3 endVec = startPos.add(((double) additionX * d0), ((double) additionY * d0), ((double) additionZ * d0));

        AABB startEndBox = new AABB(startPos, endVec);
        Entity entity = null;
        for (Entity entity1 : rayTraceEntity.level().getEntities(rayTraceEntity, startEndBox, (val) -> true)) {
            AABB aabb = entity1.getBoundingBox().inflate(entity1.getPickRadius());
            Optional<Vec3> optional = aabb.clip(startPos, endVec);
            if (aabb.contains(startPos)) {
                if (d0 >= 0.0D) {
                    entity = entity1;
                    startPos = optional.orElse(startPos);
                    d0 = 0.0D;
                }
            } else if (optional.isPresent()) {
                Vec3 vec31 = optional.get();
                double d1 = startPos.distanceToSqr(vec31);
                if (d1 < d0 || d0 == 0.0D) {
                    if (entity1.getRootVehicle() == rayTraceEntity.getRootVehicle() && !entity1.canRiderInteract()) {
                        if (d0 == 0.0D) {
                            entity = entity1;
                            startPos = vec31;
                        }
                    } else {
                        entity = entity1;
                        startPos = vec31;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }

    public static PokemonJsonObject.PokemonConfigData getPassengerObject(String pokemonType, String formName) {
        PokemonJsonObject pokemonJsonObject = CobblemonRider.pokemonJsonObject;

        if (pokemonJsonObject != null) {
            // Check if Pokemon is in the list of pokemon that can be mounted
            for (String translationKey : pokemonJsonObject.getPokemonIDs()) {
                PokemonJsonObject.PokemonConfigData pokemonConfigData = pokemonJsonObject.getPokemonData(translationKey);

                if(pokemonConfigData != null) {
                    formName = formName.equalsIgnoreCase("normal") || formName.equalsIgnoreCase("base") ? "none" : formName;
                    boolean isSameForm = formName.equalsIgnoreCase(pokemonConfigData.getFormName());
                    boolean isSameType = pokemonType.equalsIgnoreCase(translationKey);

                    if(isSameType && isSameForm) {
                        return pokemonConfigData;
                    }
                }
            }
        }

        return null;
    }
}
