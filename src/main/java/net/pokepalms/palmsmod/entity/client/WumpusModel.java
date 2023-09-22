package net.pokepalms.palmsmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.WumpusEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WumpusModel extends GeoModel<WumpusEntity> {
    @Override
    public Identifier getModelResource(WumpusEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/wumpus.geo.json");
    }

    @Override
    public Identifier getTextureResource(WumpusEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/wumpus.png");
    }

    @Override
    public Identifier getAnimationResource(WumpusEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/wumpus.animation.json");
    }

    public void setCustomAnimations(WumpusEntity animatable, long instanceId, AnimationState<WumpusEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
