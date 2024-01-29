package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindingListener {

    public static KeyBinding panoramaScreenshot;
    public static KeyBinding hotbar1;
    public static KeyBinding hotbar2;
    public static KeyBinding hotbar3;
    public static KeyBinding hotbar4;
    public static KeyBinding hotbar5;
    public static KeyBinding hotbar6;
    public static KeyBinding hotbar7;
    public static KeyBinding hotbar8;
    public static KeyBinding hotbar9;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        event.keyBindings.add(panoramaScreenshot = new KeyBinding("key.unitweaks:panorama_screenshot", Keyboard.KEY_NONE));
        event.keyBindings.add(hotbar1 = new KeyBinding("key.unitweaks:hotbar_1", Keyboard.KEY_1));
        event.keyBindings.add(hotbar2 = new KeyBinding("key.unitweaks:hotbar_2", Keyboard.KEY_2));
        event.keyBindings.add(hotbar3 = new KeyBinding("key.unitweaks:hotbar_3", Keyboard.KEY_3));
        event.keyBindings.add(hotbar4 = new KeyBinding("key.unitweaks:hotbar_4", Keyboard.KEY_4));
        event.keyBindings.add(hotbar5 = new KeyBinding("key.unitweaks:hotbar_5", Keyboard.KEY_5));
        event.keyBindings.add(hotbar6 = new KeyBinding("key.unitweaks:hotbar_6", Keyboard.KEY_6));
        event.keyBindings.add(hotbar7 = new KeyBinding("key.unitweaks:hotbar_7", Keyboard.KEY_7));
        event.keyBindings.add(hotbar8 = new KeyBinding("key.unitweaks:hotbar_8", Keyboard.KEY_8));
        event.keyBindings.add(hotbar9 = new KeyBinding("key.unitweaks:hotbar_9", Keyboard.KEY_9));
    }
}
