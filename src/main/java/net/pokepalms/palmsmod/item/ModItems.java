package net.pokepalms.palmsmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item POKE_BALL = registerItem("poke_ball", new Item(new FabricItemSettings()));
    public static final Item TOKEN_NEWMOON = registerItem("token_newmoon", new Item(new FabricItemSettings()));
    public static final Item TOKEN_FULLMOON = registerItem("token_fullmoon", new Item(new FabricItemSettings()));
    public static final Item AZURE_FLUTE = registerItem("azure_flute", new Item(new FabricItemSettings()));

    private static void addItemstoIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(POKE_BALL);
        entries.add(TOKEN_NEWMOON);
        entries.add(TOKEN_FULLMOON);
        entries.add(AZURE_FLUTE);
    }

    private static Item registerItem(String name,Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PalmsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PalmsMod.LOGGER.info("Registering mod items for " + PalmsMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemstoIngredientTabItemGroup);
    }
}
