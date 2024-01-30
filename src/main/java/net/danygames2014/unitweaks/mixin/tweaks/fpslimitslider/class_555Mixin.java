package net.danygames2014.unitweaks.mixin.tweaks.fpslimitslider;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.class_555;
import net.minecraft.client.option.GameOptions;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_555.class)
public class class_555Mixin {
    @ModifyConstant(method = "method_1844", constant = @Constant(intValue = 200))
    public int modifyPerformanceTargetFps(int constant){
        return ModOptions.getFpsLimitValue();
    }

    @ModifyConstant(method = "method_1844", constant = @Constant(intValue = 120))
    public int modifyBalancedTargetFps(int constant){
        return ModOptions.getFpsLimitValue();
    }

    @ModifyConstant(method = "method_1844", constant = @Constant(intValue = 40))
    public int modifyPowerSaverTargetFps(int constant){
        return ModOptions.getFpsLimitValue();
    }

    @Redirect(method = "method_1844", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;fpsLimit:I"))
    public int overridePerformanceLevel(GameOptions instance){
        return ModOptions.getPerformanceLevel();
    }
}
