package net.danygames2014.unitweaks.tweaks.rawinput;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.Util;
import net.java.games.input.AbstractController;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Mouse;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.modificationstation.stationapi.api.event.tick.GameTickEvent;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings({"StringConcatenationArgumentToLogCall", "BusyWait"})
public class RawInputHandler {
    private static final Logger logger = UniTweaks.logger;
    
    public static Controller[] controllers;
    public static ArrayList<Mouse> mice = new ArrayList<>();

    public static int dx = 0;
    public static int dy = 0;

    private static int worldJoinTimer;

    private static boolean shouldGetMouse = false;
    public static boolean rawInputEnabled = false;

    public static void init() {
        startInputThread();
    }
    
    public static void startInputThread() {
        Thread inputThread = new Thread(() -> {
            while (true) {
                if (!mice.isEmpty() && Minecraft.INSTANCE.currentScreen == null){
                    mice.forEach(mouse -> {
                        mouse.poll();
                        dx += (int) mouse.getX().getPollData();
                        dy += (int) mouse.getY().getPollData();
                        logger.error(mouse.getX().getPollData());
                    });
                } else if (!mice.isEmpty()) {
                    mice.forEach(AbstractController::poll);
                }

                try {
                    // Don't run that often if raw input aint enabled anyway
                    if(!rawInputEnabled){
                        Thread.sleep(1000);
                        
                        // If for some reason the value is wrong, correct it
                        if (Minecraft.INSTANCE.field_2767 instanceof RawMouseHelper) {
                            rawInputEnabled = true;
                        }
                    }
                    
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
            ControllerEnvironment controllerEnvironment = ControllerEnvironment.getDefaultEnvironment();
            controllers = controllerEnvironment.getControllers();

            mice = new ArrayList<>();

            for (Controller controller : controllers) {
                if (controller.getType() == Controller.Type.MOUSE && controller instanceof Mouse mouseController) {
                    mice.add(mouseController);
                }
            }
            
            logger.info("Found " + mice.size() + " mouse controllers");
        });
        
        getMouseThread.setName("getMouseThread");
        getMouseThread.start();
        logger.debug(String.format("getMouse called. Reason: %s. Should get mouse: %s", reason, shouldGetMouse));
    }

    public static void toggleRawInput(Component parent) {
        PlayerEntity player = Minecraft.INSTANCE.player;
        float saveYaw = player.yaw;
        float savePitch = player.pitch;

        if (Minecraft.INSTANCE.field_2767 instanceof RawMouseHelper) {
            Minecraft.INSTANCE.field_2767 = new net.minecraft.client.Mouse(parent);
            Minecraft.INSTANCE.field_2767.lockCursor();
            rawInputEnabled = false;
            Util.notify("Raw Input Toggled OFF");
        } else {
            Minecraft.INSTANCE.field_2767 = new RawMouseHelper(parent);
            Minecraft.INSTANCE.field_2767.lockCursor();
            rawInputEnabled = true;
            Util.notify("Raw Input Toggled ON");
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


