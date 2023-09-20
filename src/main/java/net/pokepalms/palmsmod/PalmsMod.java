package net.pokepalms.palmsmod;

import net.fabricmc.api.ModInitializer;

import net.pokepalms.palmsmod.item.ModItemGroups;
import net.pokepalms.palmsmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PalmsMod implements ModInitializer {
	public static final String MOD_ID = "palmsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}