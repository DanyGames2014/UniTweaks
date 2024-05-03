package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public class WorldMixin {
    @Inject(method = "method_1782", at = @At(value = "RETURN"), cancellable = true)
    public void alterBrightness(int x, int y, int z, CallbackInfoReturnable<Float> cir){
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            cir.setReturnValue(Math.max(cir.getReturnValue(), ModOptions.minimumBrightness));
        }
    }
}
