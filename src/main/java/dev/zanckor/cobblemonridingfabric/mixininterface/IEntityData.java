package dev.zanckor.cobblemonridingfabric.mixininterface;

import net.minecraft.nbt.NbtCompound;

public interface IEntityData {
    NbtCompound getPersistentData();
}