package net.pokepalms.palmsmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.pokepalms.palmsmod.block.ModBlocks;
import net.pokepalms.palmsmod.block.entity.ModBlockEntities;
import net.pokepalms.palmsmod.block.entity.client.LogoSquareRenderer;
import net.pokepalms.palmsmod.entity.ModEntities;
import net.pokepalms.palmsmod.entity.client.*;

/**import net.pokepalms.palmsmod.block.entity.client.WumpusBlockRenderer;*/

public class PalmsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        /** Blocks */
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LOGO_SQUARE, RenderLayer.getTranslucent());
        BlockEntityRendererFactories.register(ModBlockEntities.LOGO_SQUARE_ENTITY, LogoSquareRenderer::new);
        /**Villain Flags */
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMAQUA_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMAQUA_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMAQUA_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMFLARE_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMFLARE_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMFLARE_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMMAGMA_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMMAGMA_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMMAGMA_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMGALACTIC_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMGALACTIC_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMGALACTIC_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMPLASMA_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMPLASMA_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMPLASMA_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMROCKET_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMROCKET_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMROCKET_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMRAINBOWROCKET_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMRAINBOWROCKET_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMRAINBOWROCKET_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSKULL_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSKULL_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSKULL_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSTAR_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSTAR_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMSTAR_3, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMYELL_1, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMYELL_2, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLAG_TEAMYELL_3, RenderLayer.getTranslucent());

        /** Cosmetics */
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLUSHIE_MIMIKYU, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLUSHIE_MIMIKYU_SHINY, RenderLayer.getTranslucent());

        /** Entities */
        EntityRendererRegistry.register(ModEntities.ARCANINE, ArcanineRenderer::new);
        EntityRendererRegistry.register(ModEntities.WUMPUS, WumpusRenderer::new);
        EntityRendererRegistry.register(ModEntities.SURVIVAL, SurvivalRenderer::new);
        EntityRendererRegistry.register(ModEntities.TORTERRA, TorterraRenderer::new);
        EntityRendererRegistry.register(ModEntities.SKYBLOCK, SkyblockRenderer::new);



    }
}
