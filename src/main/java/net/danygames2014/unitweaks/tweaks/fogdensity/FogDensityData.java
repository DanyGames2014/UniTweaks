package net.danygames2014.unitweaks.tweaks.fogdensity;

import net.minecraft.client.option.Option;

public class FogDensityData {
    public static Option fogDensityOption;
    public static float fogDensityValue = 0.5F;

    public static float getFogMultiplier() {
        if (fogDensityValue == 0F) {
            return 100F;
        } else {
            return (1F - Math.min(getDisplayValue(), 0.9F)) * 2F;
        }
    }

    public static float getDisplayValue() {
        return (float) Math.round(fogDensityValue * 20) / 20;
    }
}
