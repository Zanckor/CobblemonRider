package dev.zanckor.cobblemonrider.client.screen;


import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.zanckor.cobblemonrider.mixininterface.IPokemonStamina;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

import static dev.zanckor.cobblemonrider.CobblemonRider.MODID;

public class StaminaBar {
    private static final ResourceLocation BAR = new ResourceLocation(MODID, "textures/gui/stamina.png");
    private static final float BAR_WIDTH = 5, BAR_HEIGHT = 81;

    public static void renderStaminaBar(PokemonEntity pokemon, GuiGraphics graphics, int width, int height) {
        PoseStack poseStack = new PoseStack();
        float stamina = ((IPokemonStamina) pokemon).getStamina();
        float maxStamina = ((IPokemonStamina) pokemon).getMaxStamina();
        float percentage = (maxStamina - stamina) / maxStamina;

        int xPos = (width / 2) + 100;
        int yPos = (int) (height - BAR_HEIGHT);

        poseStack.pushPose();
        poseStack.translate(0, 0, Integer.MAX_VALUE);
        graphics.blit(BAR, xPos, yPos,
                BAR_WIDTH, 0, (int) BAR_WIDTH, (int) BAR_HEIGHT, (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);

        graphics.blit(BAR, xPos, yPos,
                0, 0, (int) BAR_WIDTH, (int) (BAR_HEIGHT * percentage), (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);
        poseStack.popPose();
    }
}