package net.danygames2014.unitweaks.util;

import net.minecraft.client.option.Option;

public class ModOptions {
    public static Option fogDensityOption;
    public static Option cloudsOption;
    public static Option fovOption;

    // Clouds
    public static boolean clouds;

    // FOV
    public static float fov;
    public static int getFovInDegrees() {
        return Math.round(70.0f + fov * 40.0f);
    }

    // Fog Density
    public static float fogDensity = 0.5F;
    public static float getFogMultiplier() {
        if (fogDensity == 0F) {
            return 100F;
        } else {
            return (1F - Math.min(getFogDisplayValue(), 0.9F)) * 2F;
        }
    }

    public static float getFogDisplayValue() {
        return (float) Math.round(fogDensity * 20) / 20;
    }
}
