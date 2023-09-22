package net.pokepalms.palmsmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.RenderLayer;
import net.pokepalms.palmsmod.block.ModBlocks;
import net.pokepalms.palmsmod.block.entity.ModBlockEntities;
import net.pokepalms.palmsmod.entity.ModEntities;
import net.pokepalms.palmsmod.entity.custom.ArcanineEntity;
import net.pokepalms.palmsmod.entity.custom.SurvivalEntity;
import net.pokepalms.palmsmod.entity.custom.WumpusEntity;
import net.pokepalms.palmsmod.item.ModItemGroups;
import net.pokepalms.palmsmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class PalmsMod implements ModInitializer {
	public static final String MOD_ID = "palmsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		GeckoLib.initialize();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerAllBlockEntities();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		FabricDefaultAttributeRegistry.register(ModEntities.ARCANINE, ArcanineEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WUMPUS, WumpusEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.SURVIVAL, SurvivalEntity.setAttributes());
	}
}