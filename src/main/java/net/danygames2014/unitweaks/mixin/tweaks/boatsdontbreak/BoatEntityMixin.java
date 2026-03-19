package net.danygames2014.unitweaks.mixin.tweaks.boatsdontbreak;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @ModifyExpressionValue(method = "tick", at = @At(value = "CONSTANT", args = "doubleValue=0.15", ordinal = 1))
    public double increaseBreakVelocityThreshold(double original) {
        if (UniTweaks.TWEAKS_CONFIG.boatsDontBreak) {
            return Double.MAX_VALUE;
        }        
        
        return original;
    }
}
