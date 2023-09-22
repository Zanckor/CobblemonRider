package net.pokepalms.palmsmod.item.client;

import net.pokepalms.palmsmod.item.custom.LogoSquareItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LogoSquareItemRenderer extends GeoItemRenderer<LogoSquareItem> {
    public LogoSquareItemRenderer() {
        super(new LogoSquareItemModel());
    }
}
