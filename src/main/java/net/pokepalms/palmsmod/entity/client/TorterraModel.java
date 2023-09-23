package net.pokepalms.palmsmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.ArcanineEntity;
import net.pokepalms.palmsmod.entity.custom.TorterraEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TorterraModel extends GeoModel<TorterraEntity> {
    @Override
    public Identifier getModelResource(TorterraEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/torterra.geo.json");
    }

    @Override
    public Identifier getTextureResource(TorterraEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/torterra.png");
    }

    @Override
    public Identifier getAnimationResource(TorterraEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/torterra.animation.json");
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
