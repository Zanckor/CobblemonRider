package net.pokepalms.palmsmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.pokepalms.palmsmod.block.ModBlocks;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.POKE_PLANKS);
        addDrop(ModBlocks.CAUTION_TAPE);
        addDrop(ModBlocks.LOGO_SQUARE);
    }
}
