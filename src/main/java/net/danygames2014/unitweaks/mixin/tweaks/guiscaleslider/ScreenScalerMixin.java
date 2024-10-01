package net.danygames2014.unitweaks.mixin.tweaks.guiscaleslider;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.ScreenScaler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ScreenScaler.class)
public class ScreenScalerMixin {
    @WrapOperation(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;guiScale:I", opcode = Opcodes.GETFIELD, ordinal = 0))
    public int modifyScaleFactor(GameOptions instance, Operation<Integer> original){
        return ModOptions.getGuiScale();
    }
}
