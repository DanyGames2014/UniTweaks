package net.danygames2014.unitweaks.mixin.bugfixes.fullscreencursorfix;

import net.minecraft.class_596;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// MouseHelper
@Mixin(class_596.class)
public class class_596Mixin {
    @Inject(method = "method_1971", at = @At("HEAD"), cancellable = true)
    public void fixCursorPosition(CallbackInfo ci){
        if (Display.isFullscreen()) {
            Mouse.setCursorPosition(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2);
            Mouse.setGrabbed(false);
            ci.cancel();
        }
    }
}
