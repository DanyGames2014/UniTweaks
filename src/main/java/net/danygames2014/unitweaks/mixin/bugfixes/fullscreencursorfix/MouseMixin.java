package net.danygames2014.unitweaks.mixin.bugfixes.fullscreencursorfix;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// MouseHelper
@Mixin(net.minecraft.client.Mouse.class)
public class MouseMixin {
    @Inject(method = "unlockCursor", at = @At("HEAD"), cancellable = true)
    public void fixCursorPosition(CallbackInfo ci) {
        if (Display.isFullscreen()) {
            Mouse.setCursorPosition(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2);
            Mouse.setGrabbed(false);
            ci.cancel();
        }
    }
}
