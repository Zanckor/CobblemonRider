package net.pokepalms.palmsmod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.pokepalms.palmsmod.mixininterface.IEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
            compoundTag.put("questapi.entity_data", persistentData);
        }
    }


    @Inject(method = "writeNbt", at = @At("HEAD"))
    public void load(NbtCompound compoundTag, CallbackInfoReturnable<NbtCompound> cir) {
        if (compoundTag.contains("palmsmod.entity_data")) {
            persistentData = compoundTag.getCompound("palmsmod.entity_data");
        }
    }
}
