package net.pokepalms.palmsmod.config.passengerConfigObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PokemonJsonObject {

    Map<String, PokemonConfigData> pokemonTypes = new HashMap<>();

    public PokemonJsonObject(String pokemonType, PokemonConfigData pokemonConfigData) {
        this.pokemonTypes.put(pokemonType, pokemonConfigData);
    }

    public static class PokemonConfigData {
        ArrayList<MountType> mountType = new ArrayList<>(3);

        ArrayList<Float> offSet = new ArrayList<>(3);
        float speedModifier = 1;

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet) {
            this.mountType = mountType;
            this.offSet = offSet;
        }

        public ArrayList<MountType> getMountTypes() {
            return mountType;
        }

        public ArrayList<Float> getOffSet() {
            return offSet;
        }

        public float getSpeedModifier() {
            return speedModifier;
        }
    }


    public Set<String> getPokemonIDs() {
        return pokemonTypes.keySet();
    }

    public PokemonConfigData getPokemonData(String pokemonType) {
        return pokemonTypes.get(pokemonType);
    }
}
