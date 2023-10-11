package net.pokepalms.palmsmod.world.dimension;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import net.minecraft.world.RegistryWorldView;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.pokepalms.palmsmod.PalmsMod;

public class ModDimensions {
    public static final RegistryKey<World> SURVIVALDIM_DIMENSION_KEY = RegistryKey.of(RegistryKeys.WORLD,
            new Identifier(PalmsMod.MOD_ID, "survivaldim"));
    public static final RegistryKey<DimensionType> SURVIVALDIM_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, SURVIVALDIM_DIMENSION_KEY.getValue());


    public static void register() {
        PalmsMod.LOGGER.debug("Registering Dimensions for " + PalmsMod.MOD_ID);
    }


}
