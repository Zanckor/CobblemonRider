package dev.zanckor.cobblemonridingfabric.client.screen;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.zanckor.cobblemonridingfabric.mixininterface.IPokemonStamina;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric.MODID;

public class StaminaBar implements HudRenderCallback {
    private static final Identifier BAR = new Identifier(MODID, "textures/gui/stamina.png");
    private static final float BAR_WIDTH = 5, BAR_HEIGHT = 81;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        PlayerEntity player = MinecraftClient.getInstance().player;


        if(player != null && player.getVehicle() instanceof PokemonEntity pokemon) {
            MatrixStack poseStack = drawContext.getMatrices();
            float width = MinecraftClient.getInstance().getWindow().getScaledWidth();
            float height = MinecraftClient.getInstance().getWindow().getScaledHeight();

            float stamina = ((IPokemonStamina) pokemon).getStamina();
            float maxStamina = ((IPokemonStamina) pokemon).getMaxStamina();
            float percentage = (maxStamina - stamina) / maxStamina;

            int xPos = (int) ((width / 2) + 100);
            int yPos = (int) (height - BAR_HEIGHT);

            poseStack.push();
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderTexture(0, BAR);

            drawContext.drawTexture(BAR, xPos, yPos,
                    BAR_WIDTH, 0, (int) BAR_WIDTH, (int) BAR_HEIGHT, (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);

            drawContext.drawTexture(BAR, xPos, yPos,
                    0, 0, (int) BAR_WIDTH, (int) (BAR_HEIGHT * percentage), (int) BAR_WIDTH * 2, (int) BAR_HEIGHT);
            poseStack.pop();
        }
    }
}