package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyPressedListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public GameRenderer gameRenderer;

    @Inject(method = "handleMouseClick", at = @At(value = "HEAD"))
    public void test(int par1, CallbackInfo ci){
        if(KeyPressedListener.releasedMouse){
            ((class_555Accessor) gameRenderer).setLastActiveTime(System.currentTimeMillis());
            KeyPressedListener.releasedMouse = false;
            Mouse.setGrabbed(true);
        }
    }
}
