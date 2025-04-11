package net.danygames2014.unitweaks.mixin.tweaks.morekeybinds;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/PlayerInventory;selectedSlot:I", opcode = Opcodes.PUTFIELD))
    public void cancelSelectSlot(PlayerInventory instance, int value, Operation<Void> original) {
        // Do Nothing
    }

    // F1
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 59))
    public int modifyHideHudKeybind(int constant) {
        return KeyBindingListener.hideHUD.code;
    }

    // F2
    @ModifyConstant(method = "handleScreenshotKey", constant = @Constant(intValue = 60))
    public int modifyTakeScreenshotKeybind(int constant) {
        return KeyBindingListener.takeScreenshot.code;
    }

    // F3
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 61))
    public int modifyDebugHudKeybind(int constant) {
        return KeyBindingListener.debugHud.code;
    }

    // F5
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 63))
    public int modifyThirdPersonKeybind(int constant) {
        return KeyBindingListener.thirdPerson.code;
    }

    // F6
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 66))
    public int modifyCinematicCameraKeybind(int constant) {
        return KeyBindingListener.cinematicCamera.code;
    }

    // F11
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 87))
    public int modifyToggleFullscreenKeybind(int constant) {
        return KeyBindingListener.toggleFullscreen.code;
    }
}
