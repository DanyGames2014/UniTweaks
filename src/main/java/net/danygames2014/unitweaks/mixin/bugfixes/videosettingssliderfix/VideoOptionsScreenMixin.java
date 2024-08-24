package net.danygames2014.unitweaks.mixin.bugfixes.videosettingssliderfix;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {
    @Inject(method = "buttonClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/VideoOptionsScreen;init(Lnet/minecraft/client/Minecraft;II)V",shift = At.Shift.BEFORE), cancellable = true)
    public void cancelInit(ButtonWidget button, CallbackInfo ci){
        if(button.id != 12 && Mouse.isButtonDown(0)){
            ci.cancel();
        }
    }
}
