package net.pokepalms.palmsmod.item.client;

import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.entity.custom.CrateMachineEntity;
import net.pokepalms.palmsmod.item.custom.CrateMachineItem;
import net.pokepalms.palmsmod.item.custom.LogoSquareItem;
import software.bernie.geckolib.model.GeoModel;

public class CrateMachineItemModel extends GeoModel<CrateMachineItem> {
    @Override
    public Identifier getModelResource(CrateMachineItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/crate_machine.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrateMachineItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/crate_machine.png");
    }

    @Override
    public Identifier getAnimationResource(CrateMachineItem animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/crate_machine.animation.json");
    }
}
