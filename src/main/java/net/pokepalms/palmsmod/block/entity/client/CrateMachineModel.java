package net.pokepalms.palmsmod.block.entity.client;

import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.entity.custom.CrateMachineEntity;
import net.pokepalms.palmsmod.block.entity.custom.LogoSquareEntity;
import software.bernie.geckolib.model.GeoModel;

public class CrateMachineModel extends GeoModel<CrateMachineEntity> {
    @Override
    public Identifier getModelResource(CrateMachineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "geo/crate_machine.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrateMachineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "textures/entity/crate_machine.png");
    }

    @Override
    public Identifier getAnimationResource(CrateMachineEntity animatable) {
        return new Identifier(PalmsMod.MOD_ID, "animations/crate_machine.animation.json");
    }
}
