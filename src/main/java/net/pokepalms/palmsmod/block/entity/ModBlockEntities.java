package net.pokepalms.palmsmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.ModBlocks;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;

public class ModBlockEntities {
    public static BlockEntityType<LogoSquareEntity> LOGO_SQUARE_ENTITY;

    public static void registerAllBlockEntities() {
        LOGO_SQUARE_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
                new Identifier(PalmsMod.MOD_ID, "logo_square_entity"),
                FabricBlockEntityTypeBuilder.create(LogoSquareEntity::new,
                        ModBlocks.LOGO_SQUARE).build());

    }
}
