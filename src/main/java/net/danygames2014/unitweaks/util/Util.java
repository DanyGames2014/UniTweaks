package net.danygames2014.unitweaks.util;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;

public class Util {
    public static void notify(String message, boolean notifyInChat) {
        UniTweaks.LOGGER.info(message);
        if (Minecraft.INSTANCE.inGameHud != null && notifyInChat) {
            Minecraft.INSTANCE.inGameHud.addChatMessage("[UniTweaks] " + message);
        }
    }

    public static int ceil(float value) {
        int i = (int)value;
        return value > (float)i ? i + 1 : i;
    }
    
    @SuppressWarnings("ManualMinMaxCalculation")
    public static float clamp(float value, float min, float max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
    
    @SuppressWarnings("ManualMinMaxCalculation")
    public static int clamp(int value, int min, int max) {
        if(value < min) return min;
        if(value > max) return max;
        return value;
    }
}
