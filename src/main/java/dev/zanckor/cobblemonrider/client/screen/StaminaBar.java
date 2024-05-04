package dev.zanckor.cobblemonrider.client.screen;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zanckor.cobblemonrider.mixininterface.IPokemonStamina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;

import static dev.zanckor.cobblemonrider.CobblemonRider.MODID;

public class StaminaBar {
    private static final ResourceLocation BAR = new ResourceLocation(MODID, "textures/gui/stamina.png");
    private static final float BAR_WIDTH = 5, BAR_HEIGHT = 182;

    public static void renderQuestTracked(PokemonEntity pokemon, GuiGraphics graphics, int width, int height) {
        float stamina = ((IPokemonStamina) pokemon).getStamina();
        float maxStamina = ((IPokemonStamina) pokemon).getMaxStamina();
        float percentage = (maxStamina - stamina) / maxStamina;

        int xPos = (int) (width - (BAR_WIDTH / 2) - 10);
        int yPos = (int) (height - BAR_HEIGHT - 10);

        graphics.blit(BAR, xPos, yPos,
                BAR_WIDTH, 0, (int) BAR_WIDTH, (int) BAR_HEIGHT, (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);

        graphics.blit(BAR, xPos, yPos,
                0, 0, (int) BAR_WIDTH, (int) (BAR_HEIGHT * percentage), (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);
    }
}