package net.danygames2014.unitweaks.util;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;

public class Util {
    public static void notify(String message) {
        UniTweaks.logger.info(message);
        Minecraft.INSTANCE.inGameHud.addChatMessage("[UniTweaks] " + message);
    }
}
