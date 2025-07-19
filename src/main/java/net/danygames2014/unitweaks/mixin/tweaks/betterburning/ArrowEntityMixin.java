package net.danygames2014.unitweaks.mixin.tweaks.betterburning;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends Entity {
    public ArrowEntityMixin(World world) {
        super(world);
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/Entity;I)Z"))
    public boolean setAttackedOnFire(Entity attacked, Entity damageSource, int amount, Operation<Boolean> original) {
        if (UniTweaks.FEATURES_CONFIG.betterBurning.enableBetterBurning && UniTweaks.FEATURES_CONFIG.betterBurning.burningArrowsSetOnFire && this.fireTicks > 0) {
            attacked.fireTicks = 100;
        }
        return original.call(attacked, damageSource, amount);
    }
}
