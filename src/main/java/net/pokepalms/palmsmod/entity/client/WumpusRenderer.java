package net.pokepalms.palmsmod.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.WumpusEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WumpusRenderer extends GeoEntityRenderer<WumpusEntity> {
    public WumpusRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new WumpusModel());
    }

    @Override
    public Identifier getTextureLocation(WumpusEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/wumpus.png");
    }

    @Override
    public void render(WumpusEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f,0.4f,0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
