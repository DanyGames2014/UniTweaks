package net.danygames2014.unitweaks.mixin.tweaks.fpslimitslider;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GameRenderer.class)
public class class_555Mixin {
    @ModifyConstant(method = "onFrameUpdate", constant = @Constant(intValue = 200))
    public int modifyPerformanceTargetFps(int constant) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.getFpsLimitValue();
        } else {
            return constant;
        }
    }

    @ModifyConstant(method = "onFrameUpdate", constant = @Constant(intValue = 120))
    public int modifyBalancedTargetFps(int constant) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.getFpsLimitValue();
        } else {
            return constant;
        }
    }

    @ModifyConstant(method = "onFrameUpdate", constant = @Constant(intValue = 40))
    public int modifyPowerSaverTargetFps(int constant) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.getFpsLimitValue();
        } else {
            return constant;
        }
    }

    @WrapOperation(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I"))
    public int overridePerformanceLevel(GameOptions instance, Operation<Integer> original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            return ModOptions.getPerformanceLevel();
        } else {
            return original.call(instance);
        }
    }
}
