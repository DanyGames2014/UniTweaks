package net.danygames2014.unitweaks.tweaks.morekeybinds;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.class_260;
import net.minecraft.client.Minecraft;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import org.lwjgl.input.Keyboard;


@SuppressWarnings({"unused", "deprecation"})
public class KeyPressedListener {

    Minecraft minecraft = null;

    @EventListener
    public void keyPress(KeyStateChangedEvent event) {
        if (minecraft == null) {
            minecraft = ((Minecraft) FabricLoader.getInstance().getGameInstance());
        }

        if (Keyboard.getEventKeyState() && minecraft.currentScreen == null) {
            /// Dev Keybinds
            // Print luminance
            if(FabricLoader.getInstance().isDevelopmentEnvironment()){
                if(Keyboard.isKeyDown(Keyboard.KEY_L)){
                    if(minecraft.player != null){
                        Block block = minecraft.world.getBlockState(new BlockPos((int) Math.round(minecraft.player.x), (int) Math.round(minecraft.player.y), (int) Math.round(minecraft.player.z))).getBlock();
                        System.out.println(block.getLuminance(minecraft.world, (int) Math.round(minecraft.player.x), (int) Math.round(minecraft.player.y), (int) Math.round(minecraft.player.z)));
                    }
                }
            }

            // Panorama Screenshot
            if (Keyboard.isKeyDown(KeyBindingListener.panoramaScreenshot.code)) {
                panoramaScreenshot();
            }

            // Dismount
            if (Keyboard.isKeyDown(KeyBindingListener.dismount.code)) {
                dismount();
            }

            // Hotbar Slots
            if (minecraft.currentScreen == null) {
                if (Keyboard.isKeyDown(KeyBindingListener.hotbar1.code)) {
                    minecraft.player.inventory.selectedSlot = 0;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar2.code)) {
                    minecraft.player.inventory.selectedSlot = 1;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar3.code)) {
                    minecraft.player.inventory.selectedSlot = 2;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar4.code)) {
                    minecraft.player.inventory.selectedSlot = 3;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar5.code)) {
                    minecraft.player.inventory.selectedSlot = 4;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar6.code)) {
                    minecraft.player.inventory.selectedSlot = 5;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar7.code)) {
                    minecraft.player.inventory.selectedSlot = 6;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar8.code)) {
                    minecraft.player.inventory.selectedSlot = 7;
                } else if (Keyboard.isKeyDown(KeyBindingListener.hotbar9.code)) {
                    minecraft.player.inventory.selectedSlot = 8;
                }
            }
        }


    }

    public void dismount() {
        if (minecraft.player == null) {
            return;
        }

        if (minecraft.player.field_1595 == null) {
            return;
        }

        if (!minecraft.world.isRemote) {
            minecraft.player.field_1595.method_1323(minecraft.player);
        } else {
            PacketHelper.send(new PlayerInteractEntityC2SPacket(minecraft.player.id, minecraft.player.field_1595.id, 0));
        }
    }

    public void panoramaScreenshot() {
        // FOV 70 and 2560x1440 -> 2304x1440 scaling works well
        if (minecraft.player == null) {
            return;
        }

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
