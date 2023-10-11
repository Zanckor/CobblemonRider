package net.pokepalms.palmsmod.block.entity.custom;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.pokepalms.palmsmod.block.entity.ModBlockEntities;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Map;

public class CrateMachineEntity extends BlockEntity implements GeoBlockEntity {

    protected final Map<String, RawAnimation> triggerableAnimations = new Object2ObjectOpenHashMap<>(0);
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public CrateMachineEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRATE_MACHINE_ENTITY, pos, state);
    }

    public CrateMachineEntity triggerableAnim(String name, RawAnimation animation) {
        this.triggerableAnimations.put(name, animation);

        return this;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<GeoAnimatable>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<GeoAnimatable> geoAnimatableAnimationState) {
        geoAnimatableAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crate_machine.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
