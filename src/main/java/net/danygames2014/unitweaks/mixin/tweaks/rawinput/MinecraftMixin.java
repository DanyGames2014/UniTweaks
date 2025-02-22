package net.danygames2014.unitweaks.mixin.tweaks.rawinput;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.rawinput.RawInputHandler;
import net.danygames2014.unitweaks.tweaks.rawinput.RawMouseHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public Mouse mouse;

    @Shadow
    public Canvas canvas;

    @Shadow
    public World world;

    @Inject(method = "init", at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/client/Minecraft;mouse:Lnet/minecraft/client/Mouse;", ordinal = 0, shift = At.Shift.AFTER))
    public void replaceMouseHelper(CallbackInfo ci) {
        if(UniTweaks.GENERAL_CONFIG.rawInput){
            UniTweaks.LOGGER.info("Enabling Raw Input");
            mouse = new RawMouseHelper(this.canvas);
            RawInputHandler.rawInputEnabled = true;
        }

        RawInputHandler.init();
    }
    
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 1, shift = At.Shift.AFTER))
    public void onEnterMainMenu(CallbackInfo ci){
        RawInputHandler.getMouse("Enter Main Menu");
    }

    @Inject(method = "setWorld(Lnet/minecraft/world/World;Ljava/lang/String;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "HEAD"))
    public void onLeaveWorld(World world, String string, PlayerEntity playerEntity, CallbackInfo ci) {
        if (world == null) {
            RawInputHandler.onLeaveWorld();
        }
    }

    @Inject(method = "setWorld(Lnet/minecraft/world/World;Ljava/lang/String;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At(value = "TAIL"))
    public void onJoinWorld(World world, String string, PlayerEntity playerEntity, CallbackInfo ci) {
        if (world != null) {
            RawInputHandler.onJoinWorld();
        }
    }
}
