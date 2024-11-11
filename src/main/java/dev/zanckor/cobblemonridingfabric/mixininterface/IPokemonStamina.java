package dev.zanckor.cobblemonridingfabric.mixininterface;

public interface IPokemonStamina {
    int cobblemonRider$getStamina();
    void cobblemonRider$setStamina(int stamina);
    void cobblemonRider$decreaseStamina(int amount);
    void cobblemonRider$increaseStamina(int amount);
    int cobblemonRider$getMaxStamina();
}
