package dev.zanckor.cobblemonrider.mixininterface;

public interface IPokemonStamina {
    public int getStamina();
    public void setStamina(int stamina);
    public void decreaseStamina(int amount);
    public void increaseStamina(int amount);
    public int getMaxStamina();
}
