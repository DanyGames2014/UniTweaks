package net.danygames2014.unitweaks.mixin.tweaks.fpslimitslider;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;logGlError(Ljava/lang/String;)V", ordinal = 1))
    public void sync(CallbackInfo ci) {
        if (ModOptions.isFramerateLimited() && UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            Display.sync(ModOptions.getFpsLimitValue());
        }
    }
}
