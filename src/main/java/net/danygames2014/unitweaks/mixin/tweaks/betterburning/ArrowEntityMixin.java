package net.danygames2014.unitweaks.mixin.tweaks.betterburning;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends Entity {
    public ArrowEntityMixin(World world) {
        super(world);
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/Entity;I)Z"))
    public boolean setAttackedOnFire(Entity attacked, Entity damageSource, int amount) {
        if (UniTweaks.FEATURES_CONFIG.betterBurning.enableBetterBurning && UniTweaks.FEATURES_CONFIG.betterBurning.burningArrowsSetOnFire && this.fire > 0) {
            attacked.fire = 100;
        }
        return attacked.damage(damageSource, amount);
    }
}
