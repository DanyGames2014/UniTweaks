package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeyBindingListener {

    public static KeyBinding panoramaScreenshot;

    @EventListener
    public void registerKeyBindings(KeyBindingRegisterEvent event) {
        event.keyBindings.add(panoramaScreenshot = new KeyBinding("key.unitweaks.panorama_screenshot", Keyboard.KEY_NONE));

    }
}
