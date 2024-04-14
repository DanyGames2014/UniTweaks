package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.PostProcess;
import net.minecraft.class_583;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.FloatBuffer;

@Mixin(class_583.class)
public class RenderHelperMixin {
    @Shadow private static FloatBuffer field_2521;

    @Inject(
            method = "method_1929",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void clientsideEssentials_method_1929(float red, float green, float blue, float i, CallbackInfoReturnable<FloatBuffer> cir) {
        if (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER) {
            if (i != 0) {
                PostProcess pp = PostProcess.instance;
                field_2521.clear();
                field_2521.put(pp.red(red, green, blue)).put(pp.green(red, green, blue)).put(pp.blue(red, green, blue)).put(i);
                field_2521.flip();
                cir.setReturnValue(field_2521);
                cir.cancel();
            }
        }
    }
}
