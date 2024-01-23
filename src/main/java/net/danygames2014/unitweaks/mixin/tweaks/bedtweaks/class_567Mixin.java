package net.danygames2014.unitweaks.mixin.tweaks.bedtweaks;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(net.minecraft.class_567.class)
public class class_567Mixin {
    @Inject(method = "method_1869", at = @At("HEAD"), cancellable = true)
    private static void disableSleepSpawning(World arg, List list, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.disableSleepSpawning) {
            cir.setReturnValue(false);
        }
    }
}
