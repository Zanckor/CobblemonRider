package net.pokepalms.palmsmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.minecraft.registry.Registry;
import net.pokepalms.palmsmod.block.ModBlocks;
import net.pokepalms.palmsmod.entity.ModEntities;
import net.pokepalms.palmsmod.item.custom.LogoSquareItem;

/**import net.pokepalms.palmsmod.item.custom.WumpusBlockItem;*/

public class ModItems {

    /** Items */
    public static final Item TOKEN_FULLMOON = registerItem("token_fullmoon", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item AZURE_FLUTE = registerItem("azure_flute", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TOKEN_NEWMOON = registerItem("token_newmoon", new Item(new FabricItemSettings()));

    /** Armor */
    public static final Item AQUA_HELMET = registerItem("aqua_helmet",
            new ArmorItem(ModArmorMaterials.AQUA, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item AQUA_CHESTPLATE = registerItem("aqua_chestplate",
            new ArmorItem(ModArmorMaterials.AQUA, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item AQUA_LEGGINGS = registerItem("aqua_leggings",
            new ArmorItem(ModArmorMaterials.AQUA, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item AQUA_BOOTS = registerItem("aqua_boots",
            new ArmorItem(ModArmorMaterials.AQUA, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item ARCHIE_HELMET = registerItem("archie_helmet",
            new ArmorItem(ModArmorMaterials.ARCHIE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ARCHIE_CHESTPLATE = registerItem("archie_chestplate",
            new ArmorItem(ModArmorMaterials.ARCHIE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ARCHIE_LEGGINGS = registerItem("archie_leggings",
            new ArmorItem(ModArmorMaterials.ARCHIE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ARCHIE_BOOTS = registerItem("archie_boots",
            new ArmorItem(ModArmorMaterials.ARCHIE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item CYRUS_HELMET = registerItem("cyrus_helmet",
            new ArmorItem(ModArmorMaterials.CYRUS, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item CYRUS_CHESTPLATE = registerItem("cyrus_chestplate",
            new ArmorItem(ModArmorMaterials.CYRUS, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item CYRUS_LEGGINGS = registerItem("cyrus_leggings",
            new ArmorItem(ModArmorMaterials.CYRUS, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item CYRUS_BOOTS = registerItem("cyrus_boots",
            new ArmorItem(ModArmorMaterials.CYRUS, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item FLARE_HELMET = registerItem("flare_helmet",
            new ArmorItem(ModArmorMaterials.FLARE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item FLARE_CHESTPLATE = registerItem("flare_chestplate",
            new ArmorItem(ModArmorMaterials.FLARE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item FLARE_LEGGINGS = registerItem("flare_leggings",
            new ArmorItem(ModArmorMaterials.FLARE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item FLARE_BOOTS = registerItem("flare_boots",
            new ArmorItem(ModArmorMaterials.FLARE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item GALACTIC_HELMET = registerItem("galactic_helmet",
            new ArmorItem(ModArmorMaterials.GALACTIC, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item GALACTIC_CHESTPLATE = registerItem("galactic_chestplate",
            new ArmorItem(ModArmorMaterials.GALACTIC, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item GALACTIC_LEGGINGS = registerItem("galactic_leggings",
            new ArmorItem(ModArmorMaterials.GALACTIC, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item GALACTIC_BOOTS = registerItem("galactic_boots",
            new ArmorItem(ModArmorMaterials.GALACTIC, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item GIOVANNI_HELMET = registerItem("giovanni_helmet",
            new ArmorItem(ModArmorMaterials.GIOVANNI, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item GIOVANNI_CHESTPLATE = registerItem("giovanni_chestplate",
            new ArmorItem(ModArmorMaterials.GIOVANNI, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item GIOVANNI_LEGGINGS = registerItem("giovanni_leggings",
            new ArmorItem(ModArmorMaterials.GIOVANNI, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item GIOVANNI_BOOTS = registerItem("giovanni_boots",
            new ArmorItem(ModArmorMaterials.GIOVANNI, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item MAGMA_HELMET = registerItem("magma_helmet",
            new ArmorItem(ModArmorMaterials.MAGMA, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MAGMA_CHESTPLATE = registerItem("magma_chestplate",
            new ArmorItem(ModArmorMaterials.MAGMA, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MAGMA_LEGGINGS = registerItem("magma_leggings",
            new ArmorItem(ModArmorMaterials.MAGMA, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MAGMA_BOOTS = registerItem("magma_boots",
            new ArmorItem(ModArmorMaterials.MAGMA, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item MAXIE_HELMET = registerItem("maxie_helmet",
            new ArmorItem(ModArmorMaterials.MAXIE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MAXIE_CHESTPLATE = registerItem("maxie_chestplate",
            new ArmorItem(ModArmorMaterials.MAXIE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MAXIE_LEGGINGS = registerItem("maxie_leggings",
            new ArmorItem(ModArmorMaterials.MAXIE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MAXIE_BOOTS = registerItem("maxie_boots",
            new ArmorItem(ModArmorMaterials.MAXIE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item N_HELMET = registerItem("n_helmet",
            new ArmorItem(ModArmorMaterials.N, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item N_CHESTPLATE = registerItem("n_chestplate",
            new ArmorItem(ModArmorMaterials.N, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item N_LEGGINGS = registerItem("n_leggings",
            new ArmorItem(ModArmorMaterials.N, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item N_BOOTS = registerItem("n_boots",
            new ArmorItem(ModArmorMaterials.N, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item NEOPLASMA_HELMET = registerItem("neoplasma_helmet",
            new ArmorItem(ModArmorMaterials.NEOPLASMA, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item NEOPLASMA_CHESTPLATE = registerItem("neoplasma_chestplate",
            new ArmorItem(ModArmorMaterials.NEOPLASMA, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item NEOPLASMA_LEGGINGS = registerItem("neoplasma_leggings",
            new ArmorItem(ModArmorMaterials.NEOPLASMA, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item NEOPLASMA_BOOTS = registerItem("neoplasma_boots",
            new ArmorItem(ModArmorMaterials.NEOPLASMA, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item PLASMA_HELMET = registerItem("plasma_helmet",
            new ArmorItem(ModArmorMaterials.PLASMA, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item PLASMA_CHESTPLATE = registerItem("plasma_chestplate",
            new ArmorItem(ModArmorMaterials.PLASMA, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item PLASMA_LEGGINGS = registerItem("plasma_leggings",
            new ArmorItem(ModArmorMaterials.PLASMA, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item PLASMA_BOOTS = registerItem("plasma_boots",
            new ArmorItem(ModArmorMaterials.PLASMA, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item ROCKET_HELMET = registerItem("rocket_helmet",
            new ArmorItem(ModArmorMaterials.ROCKET, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ROCKET_CHESTPLATE = registerItem("rocket_chestplate",
            new ArmorItem(ModArmorMaterials.ROCKET, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ROCKET_LEGGINGS = registerItem("rocket_leggings",
            new ArmorItem(ModArmorMaterials.ROCKET, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ROCKET_BOOTS = registerItem("rocket_boots",
            new ArmorItem(ModArmorMaterials.ROCKET, ArmorItem.Type.BOOTS, new FabricItemSettings()));




    /** Villain Team Flags */
    public static final Item FLAG_TEAMAQUA_1 = registerItem("flag_teamaqua_1",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMAQUA_2 = registerItem("flag_teamaqua_2",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMAQUA_3 = registerItem("flag_teamaqua_3",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMFLARE_1 = registerItem("flag_teamflare_1",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMFLARE_2 = registerItem("flag_teamflare_2",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMFLARE_3 = registerItem("flag_teamflare_3",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMMAGMA_1 = registerItem("flag_teammagma_1",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMMAGMA_2 = registerItem("flag_teammagma_2",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMMAGMA_3 = registerItem("flag_teammagma_3",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMGALACTIC_1 = registerItem("flag_teamgalactic_1",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMGALACTIC_2 = registerItem("flag_teamgalactic_2",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMGALACTIC_3 = registerItem("flag_teamgalactic_3",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMPLASMA_1 = registerItem("flag_teamplasma_1",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMPLASMA_2 = registerItem("flag_teamplasma_2",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMPLASMA_3 = registerItem("flag_teamplasma_3",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMROCKET_1 = registerItem("flag_teamrocket_1",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMROCKET_2 = registerItem("flag_teamrocket_2",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMROCKET_3 = registerItem("flag_teamrocket_3",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMRAINBOWROCKET_1 = registerItem("flag_teamrainbowrocket_1",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMRAINBOWROCKET_2 = registerItem("flag_teamrainbowrocket_2",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMRAINBOWROCKET_3 = registerItem("flag_teamrainbowrocket_3",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSKULL_1 = registerItem("flag_teamskull_1",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSKULL_2 = registerItem("flag_teamskull_2",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSKULL_3 = registerItem("flag_teamskull_3",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSTAR_1 = registerItem("flag_teamstar_1",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSTAR_2 = registerItem("flag_teamstar_2",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMSTAR_3 = registerItem("flag_teamstar_3",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_3, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMYELL_1 = registerItem("flag_teamyell_1",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_1, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMYELL_2 = registerItem("flag_teamyell_2",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_2, new Item.Settings().maxCount(1)));
    public static final Item FLAG_TEAMYELL_3 = registerItem("flag_teamyell_3",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_3, new Item.Settings().maxCount(1)));



    /** Admin Block Stuff */
    public static final Item ARCANINE_SPAWN_EGG = registerItem("arcanine_spawn_egg", new SpawnEggItem(ModEntities.ARCANINE,0xDF9358,0x292929,
            new FabricItemSettings()));
    public static final Item WUMPUS_SPAWN_EGG = registerItem("wumpus_spawn_egg", new SpawnEggItem(ModEntities.WUMPUS,0x89A5F8,0x0A0816,
            new FabricItemSettings()));
    public static final Item SURVIVAL_SPAWN_EGG = registerItem("survival_spawn_egg", new SpawnEggItem(ModEntities.SURVIVAL,0x93bf55,0x135a15,
            new FabricItemSettings()));
    public static final Item LOGO_SQUARE_ITEM = registerItem("logo_square",
            new LogoSquareItem(ModBlocks.LOGO_SQUARE, new FabricItemSettings()));







    /** Backend Jargon */
    private static Item registerItem(String name,Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PalmsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        PalmsMod.LOGGER.info("Registering mod items for " + PalmsMod.MOD_ID);
    }
}
