package net.pokepalms.palmsmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.ArmorItem;
import net.pokepalms.palmsmod.block.ModBlocks;
import net.pokepalms.palmsmod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) { super(output); }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerParentedItemModel(ModItems.ARCANINE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.WUMPUS_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.SURVIVAL_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CAUTION_TAPE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.POKE_PLANKS);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        /** Items */
        itemModelGenerator.register(ModItems.TOKEN_NEWMOON, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOKEN_FULLMOON, Models.GENERATED);
        itemModelGenerator.register(ModItems.AZURE_FLUTE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VILLAIN_TEAMS, Models.GENERATED);

        /** Armor */
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AQUA_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AQUA_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AQUA_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.AQUA_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARCHIE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARCHIE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARCHIE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ARCHIE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CYRUS_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CYRUS_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CYRUS_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.CYRUS_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.FLARE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.FLARE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.FLARE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.FLARE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GALACTIC_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GALACTIC_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GALACTIC_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GALACTIC_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GIOVANNI_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GIOVANNI_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GIOVANNI_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.GIOVANNI_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAGMA_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAGMA_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAGMA_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAGMA_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAXIE_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAXIE_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAXIE_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.MAXIE_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.N_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.N_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.N_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.N_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NEOPLASMA_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NEOPLASMA_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NEOPLASMA_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.NEOPLASMA_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLASMA_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLASMA_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLASMA_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.PLASMA_BOOTS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ROCKET_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ROCKET_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ROCKET_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.ROCKET_BOOTS));

    }
}
