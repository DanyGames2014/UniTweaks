package net.danygames2014.unitweaks.mixin.tweaks.fpslimitslider;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "CONSTANT", args = "intValue=200", ordinal = 0))
    public int modifyFpsTarget(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.getFpsLimitValue();
        }
        
        return original;
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I", ordinal = 0))
    public int overridePerformanceLevel1(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return -1;
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I", ordinal = 1))
    public int overridePerformanceLevel2(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return -1;
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I", ordinal = 1))
    public int overridePerformanceLevel0(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.isFramerateLimited() ? 2 : 0;
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I", ordinal = 3))
    public int nukeSleep(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return -1;
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I", ordinal = 4))
    public int redirectMenuFpsLimit(int original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.isFramerateLimited() ? 2 : 0;
        } else {
            return original;
        }
    }
}
