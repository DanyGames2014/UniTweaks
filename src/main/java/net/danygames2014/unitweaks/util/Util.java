package net.danygames2014.unitweaks.util;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;

public class Util {
    public static void notify(String message, boolean notifyInChat) {
        UniTweaks.LOGGER.info(message);
        if(Minecraft.INSTANCE.inGameHud != null && notifyInChat){
            Minecraft.INSTANCE.inGameHud.addChatMessage("[UniTweaks] " + message);
        }
    }
}
