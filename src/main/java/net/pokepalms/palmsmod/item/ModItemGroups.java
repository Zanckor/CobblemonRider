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
import net.pokepalms.palmsmod.block.custom.AcaciaBenchBlock;

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

                    }).build());

    public static final ItemGroup PALMS_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PalmsMod.MOD_ID, "poke_planks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.palmsblocks"))
                    .icon(() -> new ItemStack(ModBlocks.POKE_PLANKS)). entries((displayContext, entries) -> {
                        /** Public Blocks */
                        entries.add(ModBlocks.CAUTION_TAPE);
                        entries.add(ModBlocks.POKE_PLANKS);

                        /** Admin Blocks */
                        entries.add(ModBlocks.LOGO_SQUARE);

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
