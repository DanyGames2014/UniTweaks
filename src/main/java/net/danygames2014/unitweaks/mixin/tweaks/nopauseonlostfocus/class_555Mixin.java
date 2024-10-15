package net.danygames2014.unitweaks.mixin.tweaks.nopauseonlostfocus;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyPressedListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class class_555Mixin {
    @WrapWithCondition(method = "onFrameUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;pauseGame()V"))
    public boolean preventPauseScreen(Minecraft instance) {
        return UniTweaks.GENERAL_CONFIG.pauseOnLostFocus && !KeyPressedListener.releasedMouse;
    }
}
