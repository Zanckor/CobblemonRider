package net.pokepalms.palmsmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.block.custom.AcaciaBenchBlock;
import net.pokepalms.palmsmod.block.custom.LogoSquare;
import net.pokepalms.palmsmod.block.custom.VillainFlag;

/**import net.pokepalms.palmsmod.block.custom.WumpusBlock;*/


public class ModBlocks {

    /** Public Blocks */
    public static final Block CAUTION_TAPE = registerBlock("caution_tape",
            new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK)));
    public static final Block POKE_PLANKS = registerBlock("poke_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));


    /** Villain Flags */
    public static final Block FLAG_TEAMAQUA_1 = registerBlock("flag_teamaqua_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMAQUA_2 = registerBlock("flag_teamaqua_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMAQUA_3 = registerBlock("flag_teamaqua_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMFLARE_1 = registerBlock("flag_teamflare_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMFLARE_2 = registerBlock("flag_teamflare_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMFLARE_3 = registerBlock("flag_teamflare_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMMAGMA_1 = registerBlock("flag_teammagma_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMMAGMA_2 = registerBlock("flag_teammagma_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMMAGMA_3 = registerBlock("flag_teammagma_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMGALACTIC_1 = registerBlock("flag_teamgalactic_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMGALACTIC_2 = registerBlock("flag_teamgalactic_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMGALACTIC_3 = registerBlock("flag_teamgalactic_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMPLASMA_1 = registerBlock("flag_teamplasma_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMPLASMA_2 = registerBlock("flag_teamplasma_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMPLASMA_3 = registerBlock("flag_teamplasma_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMROCKET_1 = registerBlock("flag_teamrocket_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMROCKET_2 = registerBlock("flag_teamrocket_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMROCKET_3 = registerBlock("flag_teamrocket_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMRAINBOWROCKET_1 = registerBlock("flag_teamrainbowrocket_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMRAINBOWROCKET_2 = registerBlock("flag_teamrainbowrocket_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMRAINBOWROCKET_3 = registerBlock("flag_teamrainbowrocket_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSKULL_1 = registerBlock("flag_teamskull_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSKULL_2 = registerBlock("flag_teamskull_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSKULL_3 = registerBlock("flag_teamskull_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSTAR_1 = registerBlock("flag_teamstar_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSTAR_2 = registerBlock("flag_teamstar_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMSTAR_3 = registerBlock("flag_teamstar_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMYELL_1 = registerBlock("flag_teamyell_1_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMYELL_2 = registerBlock("flag_teamyell_2_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));

    public static final Block FLAG_TEAMYELL_3 = registerBlock("flag_teamyell_3_3d",
            new VillainFlag(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).nonOpaque()));


    /** Admin Blocks */
    public static final Block LOGO_SQUARE = Registry.register(Registries.BLOCK, new Identifier(PalmsMod.MOD_ID, "logo_square"),
            new LogoSquare(AbstractBlock.Settings.copy(Blocks.BEDROCK).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(PalmsMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(PalmsMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        PalmsMod.LOGGER.info("Registering ModBlocks for " + PalmsMod.MOD_ID);
    }
}
