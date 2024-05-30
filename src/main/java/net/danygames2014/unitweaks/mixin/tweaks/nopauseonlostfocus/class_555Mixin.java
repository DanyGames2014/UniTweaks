package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyPressedListener;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_555.class)
public class class_555Mixin {
    @WrapWithCondition(method = "method_1844", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;method_2135()V"))
    public boolean preventPauseScreen(Minecraft instance) {
        return UniTweaks.GENERAL_CONFIG.pauseOnLostFocus && !KeyPressedListener.releasedMouse;
    }
}
