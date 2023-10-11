package net.pokepalms.palmsmod.block.entity.client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.pokepalms.palmsmod.block.entity.custom.CrateMachineEntity;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CrateMachineRenderer extends GeoBlockRenderer<CrateMachineEntity> {
    public CrateMachineRenderer(BlockEntityRendererFactory.Context context) {
        super(new CrateMachineModel());
    }
}
