package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindingListener {
    public static KeyBinding panoramaScreenshot;
    public static KeyBinding photoMode;
    public static KeyBinding hotbar1;
    public static KeyBinding hotbar2;
    public static KeyBinding hotbar3;
    public static KeyBinding hotbar4;
    public static KeyBinding hotbar5;
    public static KeyBinding hotbar6;
    public static KeyBinding hotbar7;
    public static KeyBinding hotbar8;
    public static KeyBinding hotbar9;
    public static KeyBinding hideHUD; // F1
    public static KeyBinding takeScreenshot; // F2
    public static KeyBinding debugHud; // F3
    public static KeyBinding thirdPerson; // F5
    public static KeyBinding cinematicCamera; // F6
    public static KeyBinding toggleFullscreen; // F11
    public static KeyBinding dismount;
    public static KeyBinding zoom;
    public static KeyBinding releaseMouse;
    public static KeyBinding rescanMouse;
    public static KeyBinding toggleRawInput;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        event.keyBindings.add(dismount = new KeyBinding("key.unitweaks.dismount", Keyboard.KEY_LSHIFT));
        event.keyBindings.add(zoom = new KeyBinding("key.unitweaks.zoom", Keyboard.KEY_C));
        event.keyBindings.add(photoMode = new KeyBinding("key.unitweaks.photo_mode", Keyboard.KEY_P));
        event.keyBindings.add(hideHUD = new KeyBinding("key.unitweaks.hide_hud", Keyboard.KEY_F1));
        event.keyBindings.add(takeScreenshot = new KeyBinding("key.unitweaks.take_screenshot", Keyboard.KEY_F2));
        event.keyBindings.add(debugHud = new KeyBinding("key.unitweaks.debug_hud", Keyboard.KEY_F3));
        event.keyBindings.add(thirdPerson = new KeyBinding("key.unitweaks.third_person", Keyboard.KEY_F5));
        event.keyBindings.add(cinematicCamera = new KeyBinding("key.unitweaks.cinematic_camera", Keyboard.KEY_F6));
        event.keyBindings.add(toggleFullscreen = new KeyBinding("key.unitweaks.toggle_fullscreen", Keyboard.KEY_F11));
        event.keyBindings.add(releaseMouse = new KeyBinding("key.unitweaks.release_mouse", Keyboard.KEY_LMENU));
        event.keyBindings.add(hotbar1 = new KeyBinding("key.unitweaks.hotbar_1", Keyboard.KEY_1));
        event.keyBindings.add(hotbar2 = new KeyBinding("key.unitweaks.hotbar_2", Keyboard.KEY_2));
        event.keyBindings.add(hotbar3 = new KeyBinding("key.unitweaks.hotbar_3", Keyboard.KEY_3));
        event.keyBindings.add(hotbar4 = new KeyBinding("key.unitweaks.hotbar_4", Keyboard.KEY_4));
        event.keyBindings.add(hotbar5 = new KeyBinding("key.unitweaks.hotbar_5", Keyboard.KEY_5));
        event.keyBindings.add(hotbar6 = new KeyBinding("key.unitweaks.hotbar_6", Keyboard.KEY_6));
        event.keyBindings.add(hotbar7 = new KeyBinding("key.unitweaks.hotbar_7", Keyboard.KEY_7));
        event.keyBindings.add(hotbar8 = new KeyBinding("key.unitweaks.hotbar_8", Keyboard.KEY_8));
        event.keyBindings.add(hotbar9 = new KeyBinding("key.unitweaks.hotbar_9", Keyboard.KEY_9));
        event.keyBindings.add(panoramaScreenshot = new KeyBinding("key.unitweaks.panorama_screenshot", Keyboard.KEY_NONE));
        event.keyBindings.add(rescanMouse = new KeyBinding("key.unitweaks.rescan", Keyboard.KEY_NONE));
        event.keyBindings.add(toggleRawInput = new KeyBinding("key.unitweaks.toggle_raw_input", Keyboard.KEY_NONE));
    }
}
