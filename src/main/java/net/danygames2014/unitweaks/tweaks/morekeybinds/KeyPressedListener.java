package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.danygames2014.unitweaks.tweaks.photomode.PhotoModeScreen;
import net.danygames2014.unitweaks.tweaks.rawinput.RawInputHandler;
import net.danygames2014.unitweaks.util.Util;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@SuppressWarnings({"unused", "deprecation"})
public class KeyPressedListener {

    static Minecraft minecraft = null;
    public static boolean releasedMouse = false;

    @EventListener
    public void stationKeyPress(KeyStateChangedEvent event){
        keyPress();
    }
    
    public static void keyPress() {
        int keyCode = Keyboard.getEventKey();
        
        if (keyCode == Keyboard.KEY_NONE) {
            return;
        }

        if (minecraft == null) {
            minecraft = ((Minecraft) FabricLoader.getInstance().getGameInstance());
        }

        if (!Keyboard.getEventKeyState() && minecraft.currentScreen == null) {
            // Release Mouse
            if (keyCode == KeyBindingListener.releaseMouse.code) {
                if (Mouse.isGrabbed()) {
                    Mouse.setGrabbed(false);
                    releasedMouse = true;
                } else {
                    Mouse.setGrabbed(true);
                    releasedMouse = false;
                }
            }
        }
        
        if (Keyboard.getEventKeyState() && minecraft.currentScreen == null) {
            // Photo Mode
            if (keyCode == KeyBindingListener.photoMode.code) {
                minecraft.setScreen(new PhotoModeScreen(null));
            }

            // Panorama Screenshot
            if (keyCode == KeyBindingListener.panoramaScreenshot.code) {
                panoramaScreenshot();
            }

            // Dismount
            if (keyCode == KeyBindingListener.dismount.code) {
                dismount();
            }

            // Rescan Mouse
            if (keyCode == KeyBindingListener.rescanMouse.code) {
                RawInputHandler.getMouse("Player Triggered Rescan");
                Util.notify("Rescanning for Mice", true);
            }

            // Toggle Raw Input
            if (keyCode == KeyBindingListener.toggleRawInput.code) {
                RawInputHandler.toggleRawInput();
            }

            // Hotbar Slots
            if (minecraft.currentScreen == null) {
                if (keyCode == KeyBindingListener.hotbar1.code) {
                    minecraft.player.inventory.selectedSlot = 0;
                } else if (keyCode == KeyBindingListener.hotbar2.code) {
                    minecraft.player.inventory.selectedSlot = 1;
                } else if (keyCode == KeyBindingListener.hotbar3.code) {
                    minecraft.player.inventory.selectedSlot = 2;
                } else if (keyCode == KeyBindingListener.hotbar4.code) {
                    minecraft.player.inventory.selectedSlot = 3;
                } else if (keyCode == KeyBindingListener.hotbar5.code) {
                    minecraft.player.inventory.selectedSlot = 4;
                } else if (keyCode == KeyBindingListener.hotbar6.code) {
                    minecraft.player.inventory.selectedSlot = 5;
                } else if (keyCode == KeyBindingListener.hotbar7.code) {
                    minecraft.player.inventory.selectedSlot = 6;
                } else if (keyCode == KeyBindingListener.hotbar8.code) {
                    minecraft.player.inventory.selectedSlot = 7;
                } else if (keyCode == KeyBindingListener.hotbar9.code) {
                    minecraft.player.inventory.selectedSlot = 8;
                }
            }
        }


    }

    public static void dismount() {
        if (minecraft.player == null) {
            return;
        }

        if (minecraft.player.vehicle == null) {
            return;
        }

        if (!minecraft.world.isRemote) {
            minecraft.player.vehicle.interact(minecraft.player);
        } else {
            PacketHelper.send(new PlayerInteractEntityC2SPacket(minecraft.player.id, minecraft.player.vehicle.id, 0));
        }
    }

    public static void panoramaScreenshot() {
        // FOV 70 and 2560x1440 -> 2304x1440 scaling works well
        if (minecraft.player == null) {
            return;
        }

        minecraft.options.hideHud = true;

        for (int i = 0; i < 6; i++) {
            facePlayer(i);
            minecraft.player.baseTick();
            minecraft.gameRenderer.onFrameUpdate(0F);
            System.out.println(i);
            Screenshot.take(Minecraft.getRunDirectory(), minecraft.displayWidth, minecraft.displayHeight);
        }

        minecraft.options.hideHud = false;
    }

    public static void facePlayer(int direction) {
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
