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
import org.jetbrains.annotations.NotNull;

/**import net.pokepalms.palmsmod.item.custom.WumpusBlockItem;*/

public class ModItems {

    /** Items */
    public static final Item TOKEN_FULLMOON = registerItem("token_fullmoon", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item AZURE_FLUTE = registerItem("azure_flute", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item TOKEN_NEWMOON = registerItem("token_newmoon", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item VILLAIN_TEAMS = registerItem("villain_teams", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item CRATE_MACHINE_ICON = registerItem("crate_machine_icon", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item SURGE_SIGN = registerItem("surge", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item BTOWER_SIGN = registerItem("btower", new Item(new FabricItemSettings().maxCount(16)));
    public static final Item PALMEMART_SIGN = registerItem("palmemart", new Item(new FabricItemSettings().maxCount(16)));


    /** Cosmetics */
    public static final Item ADVENTURER_HAT = registerItem("adventurer_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item AMONGUS_HEAD = registerItem("amongus_head", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item ANCHOR_PICKAXE = registerItem("anchor_pickaxe", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item AXOLOTL_HAT = registerItem("axolotl_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BASEBALL_CAP = registerItem("baseball_cap", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT = registerItem("beachhat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_BLUE = registerItem("beachhat_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_GREEN = registerItem("beachhat_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_PINK = registerItem("beachhat_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_PURPLE = registerItem("beachhat_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_RED = registerItem("beachhat_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACHHAT_YELLOW = registerItem("beachhat_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BLUE_SWAN_FLOATIE = registerItem("blue_swan_floatie", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_BLUE = registerItem("buckethead_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_GREEN = registerItem("buckethead_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_PINK = registerItem("buckethead_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_PURPLE = registerItem("buckethead_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_RED = registerItem("buckethead_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BUCKETHEAD_YELLOW = registerItem("buckethead_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CARDBOARD_BOX = registerItem("cardboard_box", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item COCONUT = registerItem("coconut", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CRAB_CLAW = registerItem("crab_claw", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CRAB_EYES = registerItem("crab_eyes", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CRAB_LEGS = registerItem("crab_legs", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item CUTLASS = registerItem("cutlass", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item DIVING_GOGGLES = registerItem("diving_goggles", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item DUCK_FLOATIE = registerItem("duck_floatie", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item EMOLGA_EARS = registerItem("emolga_ears", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item FEDORA = registerItem("fedora", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item FLOWER_LEI = registerItem("flower_lei", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item FROG_HAT = registerItem("frog_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item HARD_HAT = registerItem("hard_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item ICECREAM = registerItem("icecream", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item JELLYFISH_HAT = registerItem("jellyfish_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item JUICE = registerItem("juice", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item KRAKEN_PURPLE = registerItem("kraken_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item KRAKEN_SEAFOAM = registerItem("kraken_seafoam", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item LIFEBUOY = registerItem("lifebuoy", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MAGIKARP_HAT = registerItem("magikarp_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MAGIKARP_HAT_SHINY = registerItem("magikarp_hat_shiny", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PIRATE_BANDANA = registerItem("pirate_bandana", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PIRATE_HAT = registerItem("pirate_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PIRATE_PARROT = registerItem("pirate_parrot", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SAILOR_CAP = registerItem("sailor_cap", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_BLUE = registerItem("sandshovel_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_GREEN = registerItem("sandshovel_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_PINK = registerItem("sandshovel_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_PURPLE = registerItem("sandshovel_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_RED = registerItem("sandshovel_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL_YELLOW = registerItem("sandshovel_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SANDSHOVEL = registerItem("sandshovel", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL = registerItem("satchel", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_BLUE = registerItem("satchel_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_GREEN = registerItem("satchel_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_PINK = registerItem("satchel_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_PURPLE = registerItem("satchel_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_RED = registerItem("satchel_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SATCHEL_YELLOW = registerItem("satchel_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SENSEI = registerItem("sensei", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_BLUE = registerItem("shades_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_GREEN = registerItem("shades_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_PINK = registerItem("shades_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_PURPLE = registerItem("shades_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_RED = registerItem("shades_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES_YELLOW = registerItem("shades_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHADES = registerItem("shades", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SHARK_HEAD = registerItem("shark_head", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_BLUE = registerItem("snorkel_goggles_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_GREEN = registerItem("snorkel_goggles_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_PINK = registerItem("snorkel_goggles_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_PURPLE = registerItem("snorkel_goggles_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_RED = registerItem("snorkel_goggles_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES_YELLOW = registerItem("snorkel_goggles_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SNORKEL_GOGGLES = registerItem("snorkel_goggles", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SOMBRERO = registerItem("sombrero", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item STRAW_HAT_BLUE = registerItem("straw_hat_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item STRAW_HAT_GREEN = registerItem("straw_hat_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item STRAW_HAT_RED = registerItem("straw_hat_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item STRAW_HAT = registerItem("straw_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_1 = registerItem("summer_tiki_hut_surfboard_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_2 = registerItem("summer_tiki_hut_surfboard_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_3 = registerItem("summer_tiki_hut_surfboard_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_BLUE = registerItem("surf_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_GREEN = registerItem("surf_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_PINK = registerItem("surf_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_PURPLE = registerItem("surf_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_RED = registerItem("surf_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SURF_YELLOW = registerItem("surf_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item TOP_HAT = registerItem("top_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item TV_HEAD = registerItem("tv_head", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item UFO_HAT = registerItem("ufo_hat", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item WOOPER_HAT = registerItem("wooper_hat", new Item(new FabricItemSettings().maxCount(64)));

    /** Blocks */
    public static final Item BEACH_CHAIR_BLUE = registerItem("beach_chair_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_CHAIR_GREEN = registerItem("beach_chair_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_CHAIR_RED = registerItem("beach_chair_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_INFLATABLE_FLAMINGO = registerItem("beach_inflatable_flamingo", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_LIFEBUOY = registerItem("beach_lifebuoy", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_PARASOL_BLUE = registerItem("beach_parasol_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_PARASOL_GREEN = registerItem("beach_parasol_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_PARASOL_RED = registerItem("beach_parasol_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_ROOFED_CHAIR_BLUE = registerItem("beach_roofed_chair_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_ROOFED_CHAIR_GREEN = registerItem("beach_roofed_chair_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_ROOFED_CHAIR_RED = registerItem("beach_roofed_chair_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_SANDCASTLE = registerItem("beach_sandcastle", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_SURFBOARD_BLUE = registerItem("beach_surfboard_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_SURFBOARD_GREEN = registerItem("beach_surfboard_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_SURFBOARD_RED = registerItem("beach_surfboard_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_TOWEL_BLUE = registerItem("beach_towel_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_TOWEL_GREEN = registerItem("beach_towel_green", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BEACH_TOWEL_RED = registerItem("beach_towel_red", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_ARMORSTAND_1 = registerItem("medieval_pack_v5_armorstand_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_ARMORSTAND_2 = registerItem("medieval_pack_v5_armorstand_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_ARMORSTAND_3 = registerItem("medieval_pack_v5_armorstand_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_ARMORSTAND_4 = registerItem("medieval_pack_v5_armorstand_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_ARMORSTAND_HEAD_1 = registerItem("medieval_pack_v5_armorstand_head_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CHAIR_3 = registerItem("medieval_pack_v5_chair_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CHAIR_1 = registerItem("medieval_pack_v5_chair_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CHAIR_2 = registerItem("medieval_pack_v5_chair_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_1 = registerItem("medieval_pack_v5_curtain_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_2 = registerItem("medieval_pack_v5_curtain_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_3 = registerItem("medieval_pack_v5_curtain_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_4 = registerItem("medieval_pack_v5_curtain_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_5 = registerItem("medieval_pack_v5_curtain_5", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_CURTAIN_6 = registerItem("medieval_pack_v5_curtain_6", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_FLOOR_1 = registerItem("medieval_pack_v5_floor_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_FLOOR_2 = registerItem("medieval_pack_v5_floor_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_FLOOR_3 = registerItem("medieval_pack_v5_floor_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_FLOOR_4 = registerItem("medieval_pack_v5_floor_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_HANGING_1 = registerItem("medieval_pack_v5_hanging_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_HANGING_2 = registerItem("medieval_pack_v5_hanging_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_MIRROR = registerItem("medieval_pack_v5_mirror", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_SIGN = registerItem("medieval_pack_v5_sign", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_SMALL_TABLE_1 = registerItem("medieval_pack_v5_small_table_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_SMALL_TABLE_2 = registerItem("medieval_pack_v5_small_table_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_SMALL_TABLE_3 = registerItem("medieval_pack_v5_small_table_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_SMALL_TABLE_4 = registerItem("medieval_pack_v5_small_table_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_TABLE_1 = registerItem("medieval_pack_v5_table_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item MEDIEVAL_PACK_V5_WARDROBE = registerItem("medieval_pack_v5_wardrobe", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_BONSAI_BIG = registerItem("plants_bonsai_big", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_BONSAI_OAK = registerItem("plants_bonsai_oak", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_BONSAI_SMALL = registerItem("plants_bonsai_small", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_GRASS = registerItem("plants_flowerpot_grass", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_LONGGRASS = registerItem("plants_flowerpot_longgrass", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_LONGLEAVES = registerItem("plants_flowerpot_longleaves", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_PURPLE = registerItem("plants_flowerpot_purple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_REDTULIP = registerItem("plants_flowerpot_redtulip", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_FLOWERPOT_YELLOW = registerItem("plants_flowerpot_yellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_BLUE = registerItem("plants_ground_blue", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_ORANGE = registerItem("plants_ground_orange", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_PINK = registerItem("plants_ground_pink", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_ROCK_LARGE = registerItem("plants_ground_rock_large", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_ROCK_MEDIUM = registerItem("plants_ground_rock_medium", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_ROCK_SMALL = registerItem("plants_ground_rock_small", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_TALLPURPLE = registerItem("plants_ground_tallpurple", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_TALLYELLOW = registerItem("plants_ground_tallyellow", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_GROUND_WHITE = registerItem("plants_ground_white", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_HANGING_FLOWER = registerItem("plants_hanging_flower", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_HANGING_LEAVES_COLOR = registerItem("plants_hanging_leaves_color", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_HANGING_LEAVES = registerItem("plants_hanging_leaves", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_HANGING_PLANT = registerItem("plants_hanging_plant", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_HANGING_PURPLEFLOWER = registerItem("plants_hanging_purpleflower", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_LONG_POT = registerItem("plants_long_pot", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_MEDIUM_POT = registerItem("plants_medium_pot", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_SMALL_POT = registerItem("plants_small_pot", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item PLANTS_TABLE = registerItem("plants_table", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_CHAIR_2 = registerItem("summer_tiki_hut_chair_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_CHAIR = registerItem("summer_tiki_hut_chair", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_FIRE_1 = registerItem("summer_tiki_hut_fire_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_FIRE_2 = registerItem("summer_tiki_hut_fire_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_PLANT_1 = registerItem("summer_tiki_hut_plant_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_PLANT_2 = registerItem("summer_tiki_hut_plant_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_PLANT_3 = registerItem("summer_tiki_hut_plant_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SIGN_1 = registerItem("summer_tiki_hut_sign_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SIGN_2 = registerItem("summer_tiki_hut_sign_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_STATUES_1 = registerItem("summer_tiki_hut_statues_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_STATUES_2 = registerItem("summer_tiki_hut_statues_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_STATUES_3 = registerItem("summer_tiki_hut_statues_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_STATUES_4 = registerItem("summer_tiki_hut_statues_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_SHELF_1 = registerItem("summer_tiki_hut_surfboard_shelf_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_SHELF_2 = registerItem("summer_tiki_hut_surfboard_shelf_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_SHELF_3 = registerItem("summer_tiki_hut_surfboard_shelf_3", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_SURFBOARD_SHELF_4 = registerItem("summer_tiki_hut_surfboard_shelf_4", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_TABLE_2 = registerItem("summer_tiki_hut_table_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_TABLE = registerItem("summer_tiki_hut_table", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_UMBRELLA_1 = registerItem("summer_tiki_hut_umbrella_1", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_UMBRELLA_2 = registerItem("summer_tiki_hut_umbrella_2", new Item(new FabricItemSettings().maxCount(64)));
    public static final Item SUMMER_TIKI_HUT_UMBRELLA = registerItem("summer_tiki_hut_umbrella", new Item(new FabricItemSettings().maxCount(64)));


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

    /** Plushies */
    public static final Item PLUSHIE_MIMIKYU = registerItem("plushie_mimikyu",
            new BlockItem(ModBlocks.PLUSHIE_MIMIKYU, new Item.Settings().maxCount(16)));
    public static final Item PLUSHIE_MIMIKYU_SHINY = registerItem("plushie_mimikyu_shiny",
            new BlockItem(ModBlocks.PLUSHIE_MIMIKYU_SHINY, new Item.Settings().maxCount(16)));
    public static final Item POKEDEX = registerItem("pokedex",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item POKEDEX_ON = registerItem("pokedex_on",
            new Item(new FabricItemSettings().maxCount(1)));


    /** Villain Team Flags */
    public static final Item FLAG_TEAMAQUA_1 = registerItem("flag_teamaqua_1",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMAQUA_2 = registerItem("flag_teamaqua_2",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMAQUA_3 = registerItem("flag_teamaqua_3",
            new BlockItem(ModBlocks.FLAG_TEAMAQUA_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMFLARE_1 = registerItem("flag_teamflare_1",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMFLARE_2 = registerItem("flag_teamflare_2",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMFLARE_3 = registerItem("flag_teamflare_3",
            new BlockItem(ModBlocks.FLAG_TEAMFLARE_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMMAGMA_1 = registerItem("flag_teammagma_1",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMMAGMA_2 = registerItem("flag_teammagma_2",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMMAGMA_3 = registerItem("flag_teammagma_3",
            new BlockItem(ModBlocks.FLAG_TEAMMAGMA_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMGALACTIC_1 = registerItem("flag_teamgalactic_1",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMGALACTIC_2 = registerItem("flag_teamgalactic_2",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMGALACTIC_3 = registerItem("flag_teamgalactic_3",
            new BlockItem(ModBlocks.FLAG_TEAMGALACTIC_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMPLASMA_1 = registerItem("flag_teamplasma_1",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMPLASMA_2 = registerItem("flag_teamplasma_2",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMPLASMA_3 = registerItem("flag_teamplasma_3",
            new BlockItem(ModBlocks.FLAG_TEAMPLASMA_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMROCKET_1 = registerItem("flag_teamrocket_1",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMROCKET_2 = registerItem("flag_teamrocket_2",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMROCKET_3 = registerItem("flag_teamrocket_3",
            new BlockItem(ModBlocks.FLAG_TEAMROCKET_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMRAINBOWROCKET_1 = registerItem("flag_teamrainbowrocket_1",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMRAINBOWROCKET_2 = registerItem("flag_teamrainbowrocket_2",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMRAINBOWROCKET_3 = registerItem("flag_teamrainbowrocket_3",
            new BlockItem(ModBlocks.FLAG_TEAMRAINBOWROCKET_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSKULL_1 = registerItem("flag_teamskull_1",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSKULL_2 = registerItem("flag_teamskull_2",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSKULL_3 = registerItem("flag_teamskull_3",
            new BlockItem(ModBlocks.FLAG_TEAMSKULL_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSTAR_1 = registerItem("flag_teamstar_1",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSTAR_2 = registerItem("flag_teamstar_2",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMSTAR_3 = registerItem("flag_teamstar_3",
            new BlockItem(ModBlocks.FLAG_TEAMSTAR_3, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMYELL_1 = registerItem("flag_teamyell_1",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_1, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMYELL_2 = registerItem("flag_teamyell_2",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_2, new Item.Settings().maxCount(16)));
    public static final Item FLAG_TEAMYELL_3 = registerItem("flag_teamyell_3",
            new BlockItem(ModBlocks.FLAG_TEAMYELL_3, new Item.Settings().maxCount(16)));



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
