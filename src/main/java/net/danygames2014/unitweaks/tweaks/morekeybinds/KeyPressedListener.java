package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.class_260;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;


public class KeyPressedListener {

    Minecraft minecraft = null;

    @EventListener
    public void keyPress(KeyStateChangedEvent event) {
        if (minecraft == null) {
            minecraft = ((Minecraft) FabricLoader.getInstance().getGameInstance());
        }

        // FOV 70 and 2560x1440 -> 2304x1440 scaling works well

        if (minecraft.player == null) {
            return;
        }

        if (Keyboard.getEventKeyState() && Keyboard.isKeyDown(KeyBindingListener.panoramaScreenshot.code) && minecraft.currentScreen == null) {

            minecraft.options.hideHud = true;

            for (int i = 0; i < 6; i++) {
                facePlayer(i);
                minecraft.player.baseTick();
                minecraft.field_2818.method_1844(0F);
                System.out.println(i);
                class_260.method_908(Minecraft.getRunDirectory(), minecraft.displayWidth, minecraft.displayHeight);
            }

            minecraft.options.hideHud = false;

        }
    }

    public void facePlayer(int direction) {
        minecraft.player.x = Math.floor(minecraft.player.x) + 0.5;
        minecraft.player.z = Math.floor(minecraft.player.z) + 0.5;
        switch (direction) {
            case 0 -> {
                minecraft.player.pitch = 0;
                minecraft.player.yaw = 0;
            }
            case 1 -> {
                minecraft.player.pitch = 0;
                minecraft.player.yaw = 90;
            }
            case 2 -> {
                minecraft.player.pitch = 0;
                minecraft.player.yaw = 180;
            }
            case 3 -> {
                minecraft.player.pitch = 0;
                minecraft.player.yaw = 270;
            }
            case 4 -> {
                minecraft.player.pitch = 90;
                minecraft.player.yaw = 0;
            }
            case 5 -> {
                minecraft.player.pitch = -90;
                minecraft.player.yaw = 0;
            }
        }
    }
}