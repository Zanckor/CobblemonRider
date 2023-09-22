package net.pokepalms.palmsmod.block.entity.client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class LogoSquareRenderer extends GeoBlockRenderer<LogoSquareEntity> {
    public LogoSquareRenderer(BlockEntityRendererFactory.Context context) {
        super(new LogoSquareModel());
    }
}
