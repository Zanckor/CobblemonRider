package net.pokepalms.palmsmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.*;

public class ModEntities {
    public static final EntityType<ArcanineEntity> ARCANINE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(PalmsMod.MOD_ID, "arcanine"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ArcanineEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f,1.75f)).build());
    public static final EntityType<WumpusEntity> WUMPUS = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(PalmsMod.MOD_ID, "wumpus"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WumpusEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f,1.75f)).build());
    public static final EntityType<SurvivalEntity> SURVIVAL = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(PalmsMod.MOD_ID, "survival"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SurvivalEntity::new)
                    .dimensions(EntityDimensions.fixed(1.5f,1.75f)).build());
    public static final EntityType<TorterraEntity> TORTERRA = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(PalmsMod.MOD_ID, "torterra"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TorterraEntity::new)
                    .dimensions(EntityDimensions.fixed(44.0f,80.0f)).build());
    public static final EntityType<SkyblockEntity> SKYBLOCK = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(PalmsMod.MOD_ID, "skyblock"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkyblockEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f,1.0f)).build());
}
