package net.pokepalms.palmsmod.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.pokepalms.palmsmod.PalmsMod;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    AQUA("aqua", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    ARCHIE("archie", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    CYRUS("cyrus", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    FLARE("flare", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    GALACTIC("galactic", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    GIOVANNI("giovanni", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    MAGMA("magma", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    MAXIE("maxie", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    N("n", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    NEOPLASMA("neoplasma", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    PLASMA("plasma", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER)),
    ROCKET("rocket", 25, new int[] { 3, 8, 6, 3 }, 19,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2f, 0.1f, () -> Ingredient.ofItems(Items.LEATHER));


    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 15, 13 };

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return protectionAmounts[type.ordinal()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return PalmsMod.MOD_ID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}