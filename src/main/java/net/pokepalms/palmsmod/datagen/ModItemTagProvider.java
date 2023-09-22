package net.pokepalms.palmsmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.pokepalms.palmsmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.AQUA_HELMET, ModItems.AQUA_CHESTPLATE, ModItems.AQUA_LEGGINGS, ModItems.AQUA_BOOTS,
                        ModItems.ARCHIE_HELMET, ModItems.ARCHIE_CHESTPLATE, ModItems.ARCHIE_LEGGINGS, ModItems.ARCHIE_BOOTS,
                        ModItems.CYRUS_HELMET, ModItems.CYRUS_CHESTPLATE, ModItems.CYRUS_LEGGINGS, ModItems.CYRUS_BOOTS,
                        ModItems.FLARE_HELMET, ModItems.FLARE_CHESTPLATE, ModItems.FLARE_LEGGINGS, ModItems.FLARE_BOOTS,
                        ModItems.GALACTIC_HELMET, ModItems.GALACTIC_CHESTPLATE, ModItems.GALACTIC_LEGGINGS, ModItems.GALACTIC_BOOTS,
                        ModItems.GIOVANNI_HELMET, ModItems.GIOVANNI_CHESTPLATE, ModItems.GIOVANNI_LEGGINGS, ModItems.GIOVANNI_BOOTS,
                        ModItems.MAGMA_HELMET, ModItems.MAGMA_CHESTPLATE, ModItems.MAGMA_LEGGINGS, ModItems.MAGMA_BOOTS,
                        ModItems.MAXIE_HELMET, ModItems.MAXIE_CHESTPLATE, ModItems.MAXIE_LEGGINGS, ModItems.MAXIE_BOOTS,
                        ModItems.N_HELMET, ModItems.N_CHESTPLATE, ModItems.N_LEGGINGS, ModItems.N_BOOTS,
                        ModItems.NEOPLASMA_HELMET, ModItems.NEOPLASMA_CHESTPLATE, ModItems.NEOPLASMA_LEGGINGS, ModItems.NEOPLASMA_BOOTS,
                        ModItems.PLASMA_HELMET, ModItems.PLASMA_CHESTPLATE, ModItems.PLASMA_LEGGINGS, ModItems.PLASMA_BOOTS,
                        ModItems.ROCKET_HELMET, ModItems.ROCKET_CHESTPLATE, ModItems.ROCKET_LEGGINGS, ModItems.ROCKET_BOOTS
                );
    }
}
