package net.danygames2014.unitweaks.tweaks.rawinput;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.Util;
import net.java.games.input.Controller;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;
import net.java.games.input.Mouse;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;

@SuppressWarnings({"StringConcatenationArgumentToLogCall", "BusyWait"})
public class RawInputHandler {
    public static Controller[] controllers;
    public static Controller[] mouseControllers;

    public static Mouse mouse;
    public static int dx = 0;
    public static int dy = 0;

    private static int worldJoinTimer;

    private static boolean shouldGetMouse = false;

    public static void init() {
        startInputThread();
    }
    
    public static void startInputThread() {
        Thread inputThread = new Thread(() -> {
            while (true) {
                if (mouse != null && Minecraft.INSTANCE.currentScreen == null) {
                    mouse.poll();
                    dx += (int) mouse.getX().getPollData();
                    dy += (int) mouse.getY().getPollData();
                } else if (mouse != null) {
                    mouse.poll();
                }

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    UniTweaks.logger.error(e.getStackTrace());
                }
            }
        });
        inputThread.setName("inputThread");
        inputThread.start();
    }

    public static void getMouse(String reason) {
        Thread getMouseThread = new Thread(() -> {
            DirectAndRawInputEnvironmentPlugin directEnv = new DirectAndRawInputEnvironmentPlugin();
            controllers = directEnv.getControllers();

            mouseControllers = null;
            mouse = null;

            for (Controller i : controllers) {
                if (i.getType() == Controller.Type.MOUSE) {
                    mouseControllers = ArrayUtils.add(mouseControllers, i);
                }
            }

            while (mouse == null) {
                if (mouseControllers != null) {
                    for (Controller i : mouseControllers) {
                        i.poll();
                        float mouseX = ((Mouse) i).getX().getPollData();

                        if (mouseX > 0.1f || mouseX < -0.1f) {
                            mouse = ((Mouse) i);
                        }
                    }
                }
            }
        });
        getMouseThread.setName("getMouseThread");
        getMouseThread.start();
        UniTweaks.logger.debug(String.format("getMouse called. Reason: %s. Should get mouse: %s", reason, shouldGetMouse));
    }

    public static void toggleRawInput(Component parent) {
        PlayerEntity player = Minecraft.INSTANCE.player;
        float saveYaw = player.yaw;
        float savePitch = player.pitch;

        if (Minecraft.INSTANCE.field_2767 instanceof RawMouseHelper) {
            Minecraft.INSTANCE.field_2767 = new net.minecraft.client.Mouse(parent);
            Minecraft.INSTANCE.field_2767.lockCursor();
            Util.notify("Raw Input Toggled to ON");
        } else {
            Minecraft.INSTANCE.field_2767 = new RawMouseHelper(parent);
            Minecraft.INSTANCE.field_2767.lockCursor();
            Util.notify("Raw Input Toggled to OFF");
        }
        player.yaw = saveYaw;
        player.pitch = savePitch;
    }
    
    @EventListener
    public static void timer(GameTickEvent.End event) {
        if (worldJoinTimer >= 0) {
            worldJoinTimer--;
        }
        if (shouldGetMouse) {
            getMouse("Post Join/Leave Timer");
            shouldGetMouse = false;
        }
    }
    public static void onJoinWorld() {
//        UniTweaks.logger.info(String.format("Player Joined World. Getting Mouse"));
        worldJoinTimer = 3;
        shouldGetMouse = true;

    }
    public static void onLeaveWorld() {
        shouldGetMouse = false;
    }
}


