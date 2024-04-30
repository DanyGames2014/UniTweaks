package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.danygames2014.unitweaks.UniTweaks;
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
    public static KeyBinding hideHUD; // F1
    public static KeyBinding takeScreenshot; // F2
    public static KeyBinding debugHud; // F3
    public static KeyBinding thirdPerson; // F5
    public static KeyBinding cinematicCamera; // F6
    public static KeyBinding toggleFullscreen; // F11
    public static KeyBinding dismount;
    public static KeyBinding zoom;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        event.keyBindings.add(dismount = new KeyBinding(UniTweaks.NAMESPACE.id("dismount").toString(), Keyboard.KEY_LSHIFT));
        event.keyBindings.add(zoom = new KeyBinding("Zoom", Keyboard.KEY_LCONTROL));
        event.keyBindings.add(hideHUD = new KeyBinding("Hide HUD", Keyboard.KEY_F1));
        event.keyBindings.add(takeScreenshot = new KeyBinding("Take Screenshot", Keyboard.KEY_F2));
        event.keyBindings.add(debugHud = new KeyBinding("Debug HUD", Keyboard.KEY_F3));
        event.keyBindings.add(thirdPerson = new KeyBinding("Third Person", Keyboard.KEY_F5));
        event.keyBindings.add(cinematicCamera = new KeyBinding("Cinematic Camera", Keyboard.KEY_F6));
        event.keyBindings.add(toggleFullscreen = new KeyBinding("Toggle Fullscreen", Keyboard.KEY_F11));
        event.keyBindings.add(panoramaScreenshot = new KeyBinding("Panorama Screenshot", Keyboard.KEY_NONE));
        event.keyBindings.add(hotbar1 = new KeyBinding("Hotbar 1", Keyboard.KEY_1));
        event.keyBindings.add(hotbar2 = new KeyBinding("Hotbar 2", Keyboard.KEY_2));
        event.keyBindings.add(hotbar3 = new KeyBinding("Hotbar 3", Keyboard.KEY_3));
        event.keyBindings.add(hotbar4 = new KeyBinding("Hotbar 4", Keyboard.KEY_4));
        event.keyBindings.add(hotbar5 = new KeyBinding("Hotbar 5", Keyboard.KEY_5));
        event.keyBindings.add(hotbar6 = new KeyBinding("Hotbar 6", Keyboard.KEY_6));
        event.keyBindings.add(hotbar7 = new KeyBinding("Hotbar 7", Keyboard.KEY_7));
        event.keyBindings.add(hotbar8 = new KeyBinding("Hotbar 8", Keyboard.KEY_8));
        event.keyBindings.add(hotbar9 = new KeyBinding("Hotbar 9", Keyboard.KEY_9));
    }
}
