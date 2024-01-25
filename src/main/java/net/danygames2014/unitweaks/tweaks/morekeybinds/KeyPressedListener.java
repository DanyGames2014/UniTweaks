package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;

public class KeyPressedListener {

    @EventListener
    public void keyPress(KeyStateChangedEvent event) {
        Minecraft minecraft = ((Minecraft) FabricLoader.getInstance().getGameInstance());

        if (minecraft.player == null) {
            return;
        }

        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = 0;
            minecraft.player.yaw = 0;
        } else if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = 0;
            minecraft.player.yaw = 90;
        } else if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = 0;
            minecraft.player.yaw = 180;
        } else if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = 0;
            minecraft.player.yaw = 270;
        } else if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = 90;
            minecraft.player.yaw = 0;
        } else if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)) {
            minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
            minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
            minecraft.player.pitch = -90;
            minecraft.player.yaw = 0;
        }

    }
}
