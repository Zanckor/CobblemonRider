package net.pokepalms.palmsmod.mixin;

import net.minecraft.item.ItemConvertible;
import net.pokepalms.palmsmod.PalmsMod;
import net.pokepalms.palmsmod.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import software.bernie.shadowed.eliotlash.mclib.math.functions.classic.Mod;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel use3dModelAnd2DSprite(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (stack.isOf(ModItems.FLAG_TEAMAQUA_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamaqua_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMAQUA_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamaqua_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMAQUA_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamaqua_3_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMFLARE_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamflare_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMFLARE_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamflare_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMFLARE_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamflare_3_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMMAGMA_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teammagma_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMMAGMA_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teammagma_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMMAGMA_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teammagma_3_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMGALACTIC_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamgalactic_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMGALACTIC_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamgalactic_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMGALACTIC_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamgalactic_3_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMPLASMA_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamplasma_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMPLASMA_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamplasma_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMPLASMA_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamplasma_3_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMROCKET_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrocket_1_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMROCKET_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrocket_2_3d", "inventory"));
        }
        if (stack.isOf(ModItems.FLAG_TEAMROCKET_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrocket_3_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMRAINBOWROCKET_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrainbowrocket_1_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMRAINBOWROCKET_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrainbowrocket_2_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMRAINBOWROCKET_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamrainbowrocket_3_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSKULL_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamskull_1_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSKULL_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamskull_2_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSKULL_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamskull_3_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSTAR_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamstar_1_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSTAR_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamstar_2_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMSTAR_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamstar_3_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMYELL_1) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamyell_1_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMYELL_2) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamyell_2_3d", "inventory"));
        }

        if (stack.isOf(ModItems.FLAG_TEAMYELL_3) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "flag_teamyell_3_3d", "inventory"));
        }

//        if (stack.isOf(ModItems.PLUSHIE_MIMIKYU) && renderMode != ModelTransformationMode.GUI) {
//            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "plushie_mimikyu_3d", "inventory"));
//        }
//        if (stack.isOf(ModItems.PLUSHIE_MIMIKYU_SHINY) && renderMode != ModelTransformationMode.GUI) {
//            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "plushie_mimikyu_shiny_3d", "inventory"));
//        }
        if (stack.isOf(ModItems.POKEDEX) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "pokedex_3d", "inventory"));
        }
        if (stack.isOf(ModItems.POKEDEX_ON) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(PalmsMod.MOD_ID, "pokedex_on_3d", "inventory"));
        }


        return value;
    }
}