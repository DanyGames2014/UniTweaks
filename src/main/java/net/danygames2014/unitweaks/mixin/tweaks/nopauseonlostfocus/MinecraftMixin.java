package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyPressedListener;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public class_555 field_2818;

    @Inject(method = "method_2107", at = @At(value = "HEAD"))
    public void test(int par1, CallbackInfo ci){
        if(KeyPressedListener.releasedMouse){
            ((class_555Accessor) field_2818).setLastActiveTime(System.currentTimeMillis());
            KeyPressedListener.releasedMouse = false;
            Mouse.setGrabbed(true);
        }
    }
}
