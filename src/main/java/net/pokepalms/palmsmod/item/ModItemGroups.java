package net.pokepalms.palmsmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;

public class ModItemGroups {
    public static final ItemGroup PALMSMOD_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "poke_ball"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmsmod"))
                    .icon(() -> new ItemStack(ModItems.POKE_BALL)). entries((displayContext, entries) -> {
                        entries.add(ModItems.POKE_BALL);
                        entries.add(ModItems.TOKEN_NEWMOON);
                        entries.add(ModItems.TOKEN_FULLMOON);
                        entries.add(ModItems.AZURE_FLUTE);

                    }).build());


    public static void registerItemGroups() {
        PalmsMod.LOGGER.info("Registering Item Groups for " + PalmsMod.MOD_ID);
    }
}
