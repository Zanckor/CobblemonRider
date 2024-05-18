package dev.zanckor.cobblemonridingfabric.mixin;


import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerGamePacketListenerMixin {
    @ModifyConstant(method = "onVehicleMove", constant = @Constant(doubleValue = 0.0625D))
    private double modifyValue(double originalValue) {
        return 10;
    }
}