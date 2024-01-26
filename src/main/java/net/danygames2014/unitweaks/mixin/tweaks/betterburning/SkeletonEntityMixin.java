package net.danygames2014.unitweaks.mixin.tweaks.betterburning;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SkeletonEntity.class)
public abstract class SkeletonEntityMixin extends Entity {

    public SkeletonEntityMixin(World world) {
        super(world);
    }

    @Redirect(method = "method_637", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;method_210(Lnet/minecraft/entity/Entity;)Z"))
    public boolean createBurningArrow(World world, Entity arrowEntity) {
        if (UniTweaks.FEATURES_CONFIG.betterBurning.enableBetterBurning && UniTweaks.FEATURES_CONFIG.betterBurning.skeletonsBurningArrows && this.fire > 0) {
            if (this.random.nextInt(0, 100) < UniTweaks.FEATURES_CONFIG.betterBurning.skeletonBurningArrowChance) {
                arrowEntity.fire = 400;
            }
        }
        return world.method_210(arrowEntity);
    }
}
