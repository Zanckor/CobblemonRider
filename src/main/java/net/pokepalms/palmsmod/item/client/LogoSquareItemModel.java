package net.pokepalms.palmsmod.item.client;

import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;
import net.pokepalms.palmsmod.item.custom.LogoSquareItem;
import software.bernie.geckolib.model.GeoModel;

public class LogoSquareItemModel extends GeoModel<LogoSquareItem> {
    @Override
    public Identifier getModelResource(LogoSquareItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/logo_square.geo.json");
    }

    @Override
    public Identifier getTextureResource(LogoSquareItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/logo_square.png");
    }

    @Override
    public Identifier getAnimationResource(LogoSquareItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/logo_square.animation.json");
    }
}
