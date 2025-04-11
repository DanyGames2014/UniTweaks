package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Dimension.class)
public class DimensionMixin {
    @Shadow
    public float[] lightLevelToLuminance;

    @Shadow
    public boolean isNether;

    @Inject(method = "initBrightnessTable", at = @At(value = "HEAD"), cancellable = true)
    public void initAdjustedBrightnessTable(CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.brightnessSlider) {
            // Calculate brightness
            float brightness = ModOptions.brightness;
            float[] lightLevels = new float[16];
            float minimumLevel = 0.05F;

            if (this.isNether) {
                minimumLevel = 0.1F + brightness * 0.15F;
            }

            float k = 3.0f * (1.0F - brightness);
            for (int level = 0; level <= 15; ++level) {
                float var3 = 1.0F - (float) level / 15.0f;
                lightLevels[level] = (1.0F - var3) / (var3 * k + 1.0F) * (1.0F - minimumLevel) + minimumLevel;
            }

            // Write the light table
            this.lightLevelToLuminance = lightLevels;

            // Cancel to prevent the original calculation
            ci.cancel();
        }
    }
}
