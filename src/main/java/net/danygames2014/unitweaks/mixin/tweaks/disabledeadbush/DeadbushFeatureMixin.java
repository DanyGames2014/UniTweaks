package net.danygames2014.unitweaks.mixin.tweaks.disabledeadbush;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_246;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(class_246.class)
public class DeadbushFeatureMixin {
    @Inject(method = "method_1142", at = @At(value = "HEAD"), cancellable = true)
    public void disableGeneration(World random, Random i, int j, int k, int par5, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.OLD_FEATURES_CONFIG.disableDeadBushGeneration) {
            cir.cancel();
        }
    }
}
