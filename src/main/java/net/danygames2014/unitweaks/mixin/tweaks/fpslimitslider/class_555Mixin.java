package net.danygames2014.unitweaks.mixin.tweaks.fpslimitslider;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.opengl.Display;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class class_555Mixin {
    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I"))
    public int overridePerformanceLevel(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return -1; // None of the if conditions will ever trigger
        } else {
            return original;
        }
    }

    @Inject(method = "onFrameUpdate", at = @At(value = "RETURN"))
    public void sync(float tickDelta, CallbackInfo ci) {
        if (ModOptions.getFpsLimitValue() < 300) {
            Display.sync(ModOptions.getFpsLimitValue());
        }
    }
}
