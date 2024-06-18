package dev.zanckor.cobblemonrider.mixininterface;

public interface IPokemonStamina {
    public int cobblemonRiding$getStamina();
    public void cobblemonRiding$setStamina(int stamina);
    public void cobblemonRiding$decreaseStamina(int amount);
    public void cobblemonRiding$increaseStamina(int amount);
    public int cobblemonRiding$getMaxStamina();
}
