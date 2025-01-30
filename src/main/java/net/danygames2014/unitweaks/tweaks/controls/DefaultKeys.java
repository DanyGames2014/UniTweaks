package net.danygames2014.unitweaks.tweaks.controls;

import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;

public class DefaultKeys {
    public static HashMap<KeyBinding, Integer> defaultKeybinds = new HashMap<>();

    public static void addDefaultKeybind(KeyBinding keyBinding, int keyCode) {
        defaultKeybinds.put(keyBinding, keyCode);
    }

    public static int getDefaultKeybind(KeyBinding keyBinding) {
        return defaultKeybinds.getOrDefault(keyBinding, Keyboard.KEY_NONE);
    }
}
