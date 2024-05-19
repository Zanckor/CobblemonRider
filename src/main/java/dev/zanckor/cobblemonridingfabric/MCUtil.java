package dev.zanckor.cobblemonridingfabric;

import com.google.common.collect.HashBasedTable;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import net.minecraft.util.math.Vec3d;

public class MCUtil {
    private static final HashBasedTable<String, String, PokemonJsonObject.PokemonConfigData> cachedConfig = HashBasedTable.create();

    public static PokemonJsonObject.PokemonConfigData getPassengerObject(String pokemonType, String formName) {
        PokemonJsonObject pokemonJsonObject = CobblemonRidingFabric.pokemonJsonObject;

        if (cachedConfig.contains(pokemonType, formName)) {
            return cachedConfig.get(pokemonType, formName);
        } else if (pokemonJsonObject != null) {
            // Check if Pokemon is in the list of pokemon that can be mounted
            for (String translationKey : pokemonJsonObject.getPokemonIDs()) {
                PokemonJsonObject.PokemonConfigData pokemonConfigData = pokemonJsonObject.getPokemonData(translationKey);

                if (pokemonConfigData != null) {
                    formName = formName.equalsIgnoreCase("normal") || formName.equalsIgnoreCase("base") ? "none" : formName;
                    boolean isSameForm = formName.equalsIgnoreCase(pokemonConfigData.getFormName());
                    boolean isSameType = pokemonType.equalsIgnoreCase(translationKey);

                    if (isSameType && isSameForm) {
                        cachedConfig.put(pokemonType, formName, pokemonConfigData);
                        return pokemonConfigData;
                    }
                }
            }
        }

        return null;
    }

    public static Vec3d clampVec3(Vec3d vec3, double min, double max) {
        double x = Math.min(max, Math.max(min, vec3.x));
        double z = Math.min(max, Math.max(min, vec3.z));

        return new Vec3d(x, vec3.y, z);
    }
}