package net.pokepalms.palmsmod.item.client;

import net.pokepalms.palmsmod.item.custom.CrateMachineItem;
import net.pokepalms.palmsmod.item.custom.LogoSquareItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class CrateMachineItemRenderer extends GeoItemRenderer<CrateMachineItem> {
    public CrateMachineItemRenderer() {
        super(new CrateMachineItemModel());
    }
}
