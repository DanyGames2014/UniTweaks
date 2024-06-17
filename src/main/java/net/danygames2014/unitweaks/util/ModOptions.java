package net.danygames2014.unitweaks.util;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.option.Option;
import net.modificationstation.stationapi.api.util.math.MathHelper;
import org.lwjgl.input.Keyboard;

public class ModOptions {
    public static Option fogDensityOption;
    public static Option cloudsOption;
    public static Option fovOption;
    public static Option cloudHeightOption;
    public static Option fpsLimitOption;
    public static Option renderDistanceOption;
    public static Option brightnessOption;

    // Brightness
    public static float brightness = 0.0F;
    public static float minimumBrightness = 0.05F;

    public static void updateBrightnessMultiplier() {
        minimumBrightness = 0.05F + (0.20F * ((float) Math.round(brightness * 20) / 20));
        UniTweaks.logger.info("Minimum luminance set to " + minimumBrightness);
    }


    // Render Distance
    public static float renderDistance = 0.2F;

    public static final int maxRenderDistance = 32;

    public static int getRenderDistanceChunks() {
        return (int) (2 + Math.floor(renderDistance * (maxRenderDistance - 2)));
    }

    public static void setRenderDistanceChunks(int chunks) {
        renderDistance = (1.0F / (maxRenderDistance - 2)) * (chunks - 2);
    }

    public static void cycleRenderDistance() {
        boolean inverted = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

        switch (getRenderDistanceChunks()) {
            case 2, 3 -> { // Tiny
                setRenderDistanceChunks(inverted ? 12 : 4);
            }

            case 4, 5, 6, 7 -> { // Short
                setRenderDistanceChunks(inverted ? 2 : 8);
            }

            case 8, 9, 10, 11 -> { // Normal
                setRenderDistanceChunks(inverted ? 4 : 12);
            }

            default -> {
                setRenderDistanceChunks(inverted ? 8 : 2);
            }
        }
        renderDistance = MathHelper.clamp(renderDistance, 0.0F, 1.0F);
    }

    // FPS Limit
    public static float fpsLimit = 0.4F;

    public static int getFpsLimitValue() {
        return (int) (Math.floor(fpsLimit * 59F) * 5) + 5;
    }

    public static int getPerformanceLevel() {
        if (getFpsLimitValue() >= 300) {
            return 0;
        } else {
            return 2;
        }
    }

    // Clouds
    public static boolean clouds = true;
    public static float cloudHeight;

    public static float getCloudHeight() {
        return Math.round(108 + cloudHeight * 148);
    }

    // FOV
    public static float fov;

    public static int getFovInDegrees() {
        return Math.round(70.0f + fov * 40.0f);
    }

    // Fog Density
    public static float photoModeFogMultiplier = 1.0F;
    public static float fogDensity = 0.5F;

    public static float getFogMultiplier() {
        if (fogDensity == 0F) {
            return 100F;
        } else {
            return ((1F - Math.min(getFogDisplayValue(), 0.9F)) * 2F) * (photoModeFogMultiplier);
        }
    }

    public static float getFogDisplayValue() {
        return (float) Math.round(fogDensity * 20) / 20;
    }
}
