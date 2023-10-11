package net.pokepalms.palmsmod.entity.client;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.entity.custom.ArcanineEntity;
import net.pokepalms.palmsmod.entity.custom.SkyblockEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SkyblockModel extends GeoModel<SkyblockEntity> {
    @Override
    public Identifier getModelResource(SkyblockEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/skyblock.geo.json");
    }

    @Override
    public Identifier getTextureResource(SkyblockEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/skyblock.png");
    }

    @Override
    public Identifier getAnimationResource(SkyblockEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/skyblock.animation.json");
    }
}
