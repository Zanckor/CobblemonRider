package dev.zanckor.cobblemonridingfabric.config;


import java.util.*;

public class PokemonJsonObject {

    Boolean mustAllowEntityRiding = false;
    Map<String, PokemonConfigData> pokemonTypes = new HashMap<>();

    public PokemonJsonObject() {
    }

    public void add(String pokemonType, PokemonConfigData pokemonConfigData) {
        pokemonTypes.put(pokemonType, pokemonConfigData);
    }

    public boolean mustAllowEntityRiding() {
        return mustAllowEntityRiding == null ? false : mustAllowEntityRiding;
    }

    public static class PokemonConfigData {
        String formName;
        int stamina;
        ArrayList<MountType> mountType;
        ArrayList<Float> ridingOffSet;
        ArrayList<ArrayList<Float>> passengersOffSet;

        float speedModifier = 1;

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet, ArrayList<ArrayList<Float>> passengersOffset) {
            this.mountType = mountType;
            this.ridingOffSet = offSet;
            this.passengersOffSet = passengersOffset;
        }

        public PokemonConfigData(ArrayList<MountType> mountType, ArrayList<Float> offSet) {
            this.mountType = mountType;
            this.ridingOffSet = offSet;
            this.passengersOffSet = new ArrayList<>();

            passengersOffSet.add(new ArrayList<>(List.of(0.0f, 0.0f, 0.0f)));
        }

        public PokemonConfigData(ArrayList<MountType> mountType) {
            this.mountType = mountType;
            this.ridingOffSet = new ArrayList<>(List.of(0.0f, 0.0f, 0.0f));
            this.passengersOffSet = new ArrayList<>();
        }

        public PokemonConfigData() {
            this.mountType = new ArrayList<>(List.of(MountType.WALK));
            this.ridingOffSet = new ArrayList<>(List.of(0.0f, 0.0f, 0.0f));
            this.passengersOffSet = new ArrayList<>();
        }

        public int getMaxStamina() {
            return stamina != 0 ? stamina : 200;
        }
        public String getFormName() {
            return formName == null ? "none" : formName;
        }

        public ArrayList<MountType> getMountTypes() {
            return mountType;
        }

        public ArrayList<Float> getRidingOffSet() {
            return ridingOffSet;
        }

        public float getSpeedModifier() {
            return speedModifier;
        }

        public ArrayList<Float> getPassengerOffSet(int passenger) {
            return passenger >= 0 && passenger < passengersOffSet.size() ? passengersOffSet.get(passenger) : null;
        }

        public ArrayList<ArrayList<Float>> getPassengersOffSet() {
            return passengersOffSet;
        }
    }


    public Set<String> getPokemonIDs() {
        return pokemonTypes.keySet();
    }

    public PokemonConfigData getPokemonData(String pokemonType) {
        return pokemonTypes.get(pokemonType);
    }

    public enum MountType{
        WALK,
        SWIM,
        LAVA_SWIM,
        FLY
    }
}