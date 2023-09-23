package net.pokepalms.palmsmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.TorterraEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TorterraRenderer extends GeoEntityRenderer<TorterraEntity> {
    public TorterraRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TorterraModel());
    }

    @Override
    public Identifier getTextureLocation(TorterraEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/torterra.png");
    }

    @Override
    public void render(TorterraEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f,0.4f,0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
