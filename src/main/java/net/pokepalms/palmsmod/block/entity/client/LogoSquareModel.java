package net.pokepalms.palmsmod.block.entity.client;

import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;
import software.bernie.geckolib.model.GeoModel;

public class LogoSquareModel extends GeoModel<LogoSquareEntity> {
    @Override
    public Identifier getModelResource(LogoSquareEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/logo_square.geo.json");
    }

    @Override
    public Identifier getTextureResource(LogoSquareEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/logo_square.png");
    }

    @Override
    public Identifier getAnimationResource(LogoSquareEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/logo_square.animation.json");
    }
}
