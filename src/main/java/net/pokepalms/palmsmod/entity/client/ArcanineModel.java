package net.pokepalms.palmsmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.ArcanineEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ArcanineModel extends GeoModel<ArcanineEntity> {
    @Override
    public Identifier getModelResource(ArcanineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/arcanine.geo.json");
    }

    @Override
    public Identifier getTextureResource(ArcanineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/arcanine.png");
    }

    @Override
    public Identifier getAnimationResource(ArcanineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/arcanine.animation.json");
    }

    public void setCustomAnimations(ArcanineEntity animatable, long instanceId, AnimationState<ArcanineEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
