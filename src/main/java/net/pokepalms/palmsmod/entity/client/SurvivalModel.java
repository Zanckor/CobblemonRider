package net.pokepalms.palmsmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.SurvivalEntity;
import net.pokepalms.palmsmod.entity.custom.WumpusEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SurvivalModel extends GeoModel<SurvivalEntity> {
    @Override
    public Identifier getModelResource(SurvivalEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/survival.geo.json");
    }

    @Override
    public Identifier getTextureResource(SurvivalEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/survival.png");
    }

    @Override
    public Identifier getAnimationResource(SurvivalEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/survival.animation.json");
    }
}
