package dev.zanckor.cobblemonridingfabric.mixin;

import dev.zanckor.cobblemonridingfabric.mixininterface.IEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric.MODID;

@Mixin(Entity.class)
public class MixinEntityDataSaver implements IEntityData {
    private NbtCompound persistentData;

    @Override
    public NbtCompound getPersistentData() {
        if (persistentData == null) {
            persistentData = new NbtCompound();
        }

        return persistentData;
    }

    @Inject(method = "saveNbt", at = @At("HEAD"))
    public void saveWithoutId(NbtCompound compoundTag, CallbackInfoReturnable<NbtCompound> cir) {
        if (persistentData != null) {
            compoundTag.put(MODID + ".entity_data", persistentData);
        }
    }


    @Inject(method = "writeNbt", at = @At("HEAD"))
    public void load(NbtCompound compoundTag, CallbackInfoReturnable<NbtCompound> cir) {
        if (compoundTag.contains(MODID + ".entity_data")) {
            persistentData = compoundTag.getCompound(MODID + ".entity_data");
        }
    }
}