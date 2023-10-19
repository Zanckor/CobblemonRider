package net.pokepalms.palmsmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup PALMS_ITEMS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "azure_flute"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmsitems"))
                    .icon(() -> new ItemStack(ModItems.AZURE_FLUTE)). entries((displayContext, entries) -> {
                        /** Public Items */
                        entries.add(ModItems.TOKEN_FULLMOON);
                        entries.add(ModItems.TOKEN_NEWMOON);
                        entries.add(ModItems.AZURE_FLUTE);

                        /** Admin Items */
                        entries.add(ModItems.ARCANINE_SPAWN_EGG);
                        entries.add(ModItems.WUMPUS_SPAWN_EGG);
                        entries.add(ModItems.SURVIVAL_SPAWN_EGG);
                        entries.add(ModItems.VILLAIN_TEAMS);
                        entries.add(ModItems.CRATE_MACHINE_ICON);

                    }).build());


    public static final ItemGroup PALMS_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "poke_planks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmsblocks"))
                    .icon(() -> new ItemStack(ModBlocks.POKE_PLANKS)). entries((displayContext, entries) -> {
                        /** Public Blocks */
                        entries.add(ModBlocks.CAUTION_TAPE);
                        entries.add(ModBlocks.POKE_PLANKS);entries.add(ModItems.BEACH_CHAIR_BLUE);
                    entries.add(ModItems.BEACH_CHAIR_GREEN);
                    entries.add(ModItems.BEACH_CHAIR_RED);
                    entries.add(ModItems.BEACH_INFLATABLE_FLAMINGO);
                    entries.add(ModItems.BEACH_LIFEBUOY);
                    entries.add(ModItems.BEACH_PARASOL_BLUE);
                    entries.add(ModItems.BEACH_PARASOL_GREEN);
                    entries.add(ModItems.BEACH_PARASOL_RED);
                    entries.add(ModItems.BEACH_ROOFED_CHAIR_BLUE);
                    entries.add(ModItems.BEACH_ROOFED_CHAIR_GREEN);
                    entries.add(ModItems.BEACH_ROOFED_CHAIR_RED);
                    entries.add(ModItems.BEACH_SANDCASTLE);
                    entries.add(ModItems.BEACH_SURFBOARD_BLUE);
                    entries.add(ModItems.BEACH_SURFBOARD_GREEN);
                    entries.add(ModItems.BEACH_SURFBOARD_RED);
                    entries.add(ModItems.BEACH_TOWEL_BLUE);
                    entries.add(ModItems.BEACH_TOWEL_GREEN);
                    entries.add(ModItems.BEACH_TOWEL_RED);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_ARMORSTAND_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_ARMORSTAND_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_ARMORSTAND_3);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_ARMORSTAND_4);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_ARMORSTAND_HEAD_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CHAIR_3);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CHAIR_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CHAIR_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_3);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_4);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_5);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_CURTAIN_6);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_FLOOR_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_FLOOR_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_FLOOR_3);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_FLOOR_4);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_HANGING_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_HANGING_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_MIRROR);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_SIGN);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_SMALL_TABLE_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_SMALL_TABLE_2);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_SMALL_TABLE_3);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_SMALL_TABLE_4);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_TABLE_1);
                    entries.add(ModItems.MEDIEVAL_PACK_V5_WARDROBE);
                    entries.add(ModItems.PLANTS_BONSAI_BIG);
                    entries.add(ModItems.PLANTS_BONSAI_OAK);
                    entries.add(ModItems.PLANTS_BONSAI_SMALL);
                    entries.add(ModItems.PLANTS_FLOWERPOT_GRASS);
                    entries.add(ModItems.PLANTS_FLOWERPOT_LONGGRASS);
                    entries.add(ModItems.PLANTS_FLOWERPOT_LONGLEAVES);
                    entries.add(ModItems.PLANTS_FLOWERPOT_PURPLE);
                    entries.add(ModItems.PLANTS_FLOWERPOT_REDTULIP);
                    entries.add(ModItems.PLANTS_FLOWERPOT_YELLOW);
                    entries.add(ModItems.PLANTS_GROUND_BLUE);
                    entries.add(ModItems.PLANTS_GROUND_ORANGE);
                    entries.add(ModItems.PLANTS_GROUND_PINK);
                    entries.add(ModItems.PLANTS_GROUND_ROCK_LARGE);
                    entries.add(ModItems.PLANTS_GROUND_ROCK_MEDIUM);
                    entries.add(ModItems.PLANTS_GROUND_ROCK_SMALL);
                    entries.add(ModItems.PLANTS_GROUND_TALLPURPLE);
                    entries.add(ModItems.PLANTS_GROUND_TALLYELLOW);
                    entries.add(ModItems.PLANTS_GROUND_WHITE);
                    entries.add(ModItems.PLANTS_HANGING_FLOWER);
                    entries.add(ModItems.PLANTS_HANGING_LEAVES_COLOR);
                    entries.add(ModItems.PLANTS_HANGING_LEAVES);
                    entries.add(ModItems.PLANTS_HANGING_PLANT);
                    entries.add(ModItems.PLANTS_HANGING_PURPLEFLOWER);
                    entries.add(ModItems.PLANTS_LONG_POT);
                    entries.add(ModItems.PLANTS_MEDIUM_POT);
                    entries.add(ModItems.PLANTS_SMALL_POT);
                    entries.add(ModItems.PLANTS_TABLE);
                    entries.add(ModItems.SUMMER_TIKI_HUT_CHAIR_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_CHAIR);
                    entries.add(ModItems.SUMMER_TIKI_HUT_FIRE_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_FIRE_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_PLANT_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_PLANT_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_PLANT_3);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SIGN_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SIGN_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_STATUES_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_STATUES_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_STATUES_3);
                    entries.add(ModItems.SUMMER_TIKI_HUT_STATUES_4);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_SHELF_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_SHELF_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_SHELF_3);
                    entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_SHELF_4);
                    entries.add(ModItems.SUMMER_TIKI_HUT_TABLE_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_TABLE);
                    entries.add(ModItems.SUMMER_TIKI_HUT_UMBRELLA_1);
                    entries.add(ModItems.SUMMER_TIKI_HUT_UMBRELLA_2);
                    entries.add(ModItems.SUMMER_TIKI_HUT_UMBRELLA);




                    /** Admin Blocks */
                        entries.add(ModItems.LOGO_SQUARE_ITEM);

                    }).build());

    public static final ItemGroup PALMS_COSMETICS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "flag_teamaqua_1"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmscosmetics"))
                    .icon(() -> new ItemStack(ModBlocks.FLAG_TEAMAQUA_1)). entries((displayContext, entries) -> {
                       /** Villain Flags */
                        entries.add(ModBlocks.FLAG_TEAMAQUA_1);
                        entries.add(ModBlocks.FLAG_TEAMAQUA_2);
                        entries.add(ModBlocks.FLAG_TEAMAQUA_3);
                        entries.add(ModBlocks.FLAG_TEAMFLARE_1);
                        entries.add(ModBlocks.FLAG_TEAMFLARE_2);
                        entries.add(ModBlocks.FLAG_TEAMFLARE_3);
                        entries.add(ModBlocks.FLAG_TEAMMAGMA_1);
                        entries.add(ModBlocks.FLAG_TEAMMAGMA_2);
                        entries.add(ModBlocks.FLAG_TEAMMAGMA_3);
                        entries.add(ModBlocks.FLAG_TEAMGALACTIC_1);
                        entries.add(ModBlocks.FLAG_TEAMGALACTIC_2);
                        entries.add(ModBlocks.FLAG_TEAMGALACTIC_3);
                        entries.add(ModBlocks.FLAG_TEAMPLASMA_1);
                        entries.add(ModBlocks.FLAG_TEAMPLASMA_2);
                        entries.add(ModBlocks.FLAG_TEAMPLASMA_3);
                        entries.add(ModBlocks.FLAG_TEAMROCKET_1);
                        entries.add(ModBlocks.FLAG_TEAMROCKET_2);
                        entries.add(ModBlocks.FLAG_TEAMROCKET_3);
                        entries.add(ModBlocks.FLAG_TEAMRAINBOWROCKET_1);
                        entries.add(ModBlocks.FLAG_TEAMRAINBOWROCKET_2);
                        entries.add(ModBlocks.FLAG_TEAMRAINBOWROCKET_3);
                        entries.add(ModBlocks.FLAG_TEAMSKULL_1);
                        entries.add(ModBlocks.FLAG_TEAMSKULL_2);
                        entries.add(ModBlocks.FLAG_TEAMSKULL_3);
                        entries.add(ModBlocks.FLAG_TEAMSTAR_1);
                        entries.add(ModBlocks.FLAG_TEAMSTAR_2);
                        entries.add(ModBlocks.FLAG_TEAMSTAR_3);
                        entries.add(ModBlocks.FLAG_TEAMYELL_1);
                        entries.add(ModBlocks.FLAG_TEAMYELL_2);
                        entries.add(ModBlocks.FLAG_TEAMYELL_3);
                        /** Plushies */
                        //entries.add(ModBlocks.PLUSHIE_MIMIKYU);
                        //entries.add(ModBlocks.PLUSHIE_MIMIKYU_SHINY);
                        /** Cosmetics */
                        entries.add(ModItems.ADVENTURER_HAT);
                        entries.add(ModItems.AMONGUS_HEAD);
                        entries.add(ModItems.ANCHOR_PICKAXE);
                        entries.add(ModItems.BASEBALL_CAP);
                        entries.add(ModItems.BEACHHAT);
                        entries.add(ModItems.BEACHHAT_BLUE);
                        entries.add(ModItems.BEACHHAT_GREEN);
                        entries.add(ModItems.BEACHHAT_PINK);
                        entries.add(ModItems.BEACHHAT_PURPLE);
                        entries.add(ModItems.BEACHHAT_RED);
                        entries.add(ModItems.BEACHHAT_YELLOW);
                        entries.add(ModItems.BLUE_SWAN_FLOATIE);
                        entries.add(ModItems.BUCKETHEAD_BLUE);
                        entries.add(ModItems.BUCKETHEAD_GREEN);
                        entries.add(ModItems.BUCKETHEAD_PINK);
                        entries.add(ModItems.BUCKETHEAD_PURPLE);
                        entries.add(ModItems.BUCKETHEAD_RED);
                        entries.add(ModItems.BUCKETHEAD_YELLOW);
                        entries.add(ModItems.CARDBOARD_BOX);
                        entries.add(ModItems.COCONUT);
                        entries.add(ModItems.CRAB_CLAW);
                        entries.add(ModItems.CRAB_EYES);
                        entries.add(ModItems.CRAB_LEGS);
                        entries.add(ModItems.CUTLASS);
                        entries.add(ModItems.DIVING_GOGGLES);
                        entries.add(ModItems.DUCK_FLOATIE);
                        entries.add(ModItems.EMOLGA_EARS);
                        entries.add(ModItems.FEDORA);
                        entries.add(ModItems.FLOWER_LEI);
                        entries.add(ModItems.FROG_HAT);
                        entries.add(ModItems.HARD_HAT);
                        entries.add(ModItems.ICECREAM);
                        entries.add(ModItems.JELLYFISH_HAT);
                        entries.add(ModItems.JUICE);
                        entries.add(ModItems.KRAKEN_PURPLE);
                        entries.add(ModItems.KRAKEN_SEAFOAM);
                        entries.add(ModItems.LIFEBUOY);
                        entries.add(ModItems.MAGIKARP_HAT);
                        entries.add(ModItems.MAGIKARP_HAT_SHINY);
                        entries.add(ModItems.PIRATE_BANDANA);
                        entries.add(ModItems.PIRATE_HAT);
                        entries.add(ModItems.PIRATE_PARROT);
                        entries.add(ModItems.SAILOR_CAP);
                        entries.add(ModItems.SANDSHOVEL_BLUE);
                        entries.add(ModItems.SANDSHOVEL_GREEN);
                        entries.add(ModItems.SANDSHOVEL_PINK);
                        entries.add(ModItems.SANDSHOVEL_PURPLE);
                        entries.add(ModItems.SANDSHOVEL_RED);
                        entries.add(ModItems.SANDSHOVEL_YELLOW);
                        entries.add(ModItems.SANDSHOVEL);
                        entries.add(ModItems.SATCHEL);
                        entries.add(ModItems.SATCHEL_BLUE);
                        entries.add(ModItems.SATCHEL_GREEN);
                        entries.add(ModItems.SATCHEL_PINK);
                        entries.add(ModItems.SATCHEL_PURPLE);
                        entries.add(ModItems.SATCHEL_RED);
                        entries.add(ModItems.SATCHEL_YELLOW);
                        entries.add(ModItems.SENSEI);
                        entries.add(ModItems.SHADES_BLUE);
                        entries.add(ModItems.SHADES_GREEN);
                        entries.add(ModItems.SHADES_PINK);
                        entries.add(ModItems.SHADES_PURPLE);
                        entries.add(ModItems.SHADES_RED);
                        entries.add(ModItems.SHADES_YELLOW);
                        entries.add(ModItems.SHADES);
                        entries.add(ModItems.SHARK_HEAD);
                        entries.add(ModItems.SNORKEL_GOGGLES_BLUE);
                        entries.add(ModItems.SNORKEL_GOGGLES_GREEN);
                        entries.add(ModItems.SNORKEL_GOGGLES_PINK);
                        entries.add(ModItems.SNORKEL_GOGGLES_PURPLE);
                        entries.add(ModItems.SNORKEL_GOGGLES_RED);
                        entries.add(ModItems.SNORKEL_GOGGLES_YELLOW);
                        entries.add(ModItems.SNORKEL_GOGGLES);
                        entries.add(ModItems.SOMBRERO);
                        entries.add(ModItems.STRAW_HAT_BLUE);
                        entries.add(ModItems.STRAW_HAT_GREEN);
                        entries.add(ModItems.STRAW_HAT_RED);
                        entries.add(ModItems.STRAW_HAT);
                        entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_1);
                        entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_2);
                        entries.add(ModItems.SUMMER_TIKI_HUT_SURFBOARD_3);
                        entries.add(ModItems.SURF_BLUE);
                        entries.add(ModItems.SURF_GREEN);
                        entries.add(ModItems.SURF_PINK);
                        entries.add(ModItems.SURF_PURPLE);
                        entries.add(ModItems.SURF_RED);
                        entries.add(ModItems.SURF_YELLOW);
                        entries.add(ModItems.TOP_HAT);
                        entries.add(ModItems.TV_HEAD);
                        entries.add(ModItems.UFO_HAT);
                        entries.add(ModItems.WOOPER_HAT);

                        /** Backend Stuff*/
                        entries.add(ModItems.POKEDEX);
                        entries.add(ModItems.POKEDEX_ON);
                        entries.add(ModItems.BTOWER_SIGN);
                        entries.add(ModItems.SURGE_SIGN);
                        entries.add(ModItems.PALMEMART_SIGN);

                    }).build());

    public static final ItemGroup PALMS_ARMOR = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "aqua_helmet"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmsarmor"))
                    .icon(() -> new ItemStack(ModItems.AQUA_HELMET)). entries((displayContext, entries) -> {
                        entries.add(ModItems.AQUA_HELMET); entries.add(ModItems.AQUA_CHESTPLATE); entries.add(ModItems.AQUA_LEGGINGS); entries.add(ModItems.AQUA_BOOTS);
                        entries.add(ModItems.ARCHIE_HELMET); entries.add(ModItems.ARCHIE_CHESTPLATE); entries.add(ModItems.ARCHIE_LEGGINGS); entries.add(ModItems.ARCHIE_BOOTS);
                        entries.add(ModItems.CYRUS_HELMET); entries.add(ModItems.CYRUS_CHESTPLATE); entries.add(ModItems.CYRUS_LEGGINGS); entries.add(ModItems.CYRUS_BOOTS);
                        entries.add(ModItems.FLARE_HELMET); entries.add(ModItems.FLARE_CHESTPLATE); entries.add(ModItems.FLARE_LEGGINGS); entries.add(ModItems.FLARE_BOOTS);
                        entries.add(ModItems.GALACTIC_HELMET); entries.add(ModItems.GALACTIC_CHESTPLATE); entries.add(ModItems.GALACTIC_LEGGINGS); entries.add(ModItems.GALACTIC_BOOTS);
                        entries.add(ModItems.GIOVANNI_HELMET); entries.add(ModItems.GIOVANNI_CHESTPLATE); entries.add(ModItems.GIOVANNI_LEGGINGS); entries.add(ModItems.GIOVANNI_BOOTS);
                        entries.add(ModItems.MAGMA_HELMET); entries.add(ModItems.MAGMA_CHESTPLATE); entries.add(ModItems.MAGMA_LEGGINGS); entries.add(ModItems.MAGMA_BOOTS);
                        entries.add(ModItems.MAXIE_HELMET); entries.add(ModItems.MAXIE_CHESTPLATE); entries.add(ModItems.MAXIE_LEGGINGS); entries.add(ModItems.MAXIE_BOOTS);
                        entries.add(ModItems.N_HELMET); entries.add(ModItems.N_CHESTPLATE); entries.add(ModItems.N_LEGGINGS); entries.add(ModItems.N_BOOTS);
                        entries.add(ModItems.NEOPLASMA_HELMET); entries.add(ModItems.NEOPLASMA_CHESTPLATE); entries.add(ModItems.NEOPLASMA_LEGGINGS); entries.add(ModItems.NEOPLASMA_BOOTS);
                        entries.add(ModItems.PLASMA_HELMET); entries.add(ModItems.PLASMA_CHESTPLATE); entries.add(ModItems.PLASMA_LEGGINGS); entries.add(ModItems.PLASMA_BOOTS);
                        entries.add(ModItems.ROCKET_HELMET); entries.add(ModItems.ROCKET_CHESTPLATE); entries.add(ModItems.ROCKET_LEGGINGS); entries.add(ModItems.ROCKET_BOOTS);

                    }).build());

    public static void registerItemGroups() {
        PalmsMod.LOGGER.info("Registering Palms Mod Assets for " + PalmsMod.MOD_ID);
    }
}
