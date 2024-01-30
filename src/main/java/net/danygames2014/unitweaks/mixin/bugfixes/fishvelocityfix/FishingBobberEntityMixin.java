package net.danygames2014.unitweaks.mixin.bugfixes.fishvelocityfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.projectile.FishingBobberEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @ModifyConstant(method = "method_956", constant = @Constant(doubleValue = 0.08))
    public double reduceFishVerticalVelocity(double constant) {
        if (UniTweaks.BUGFIXES_CONFIG.fishVelocityFix) {
            return 0.04;
        } else {
            return constant;
        }
    }
}
