package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.PostProcess;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GL11.class)
public abstract class GL11Mixin {

    @Shadow
    static void nglColor4f(float red, float green, float blue, float alpha, long function_pointer) {
    }

    @Shadow
    static void nglColor3f(float red, float green, float blue, long function_pointer) {
    }

    @Redirect(
            method = "glColor3f",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;nglColor3f(FFFJ)V"
            ),
            remap = false
    )
    private static void clientsideEssentials_glColor3f(float red, float green, float blue, long l) {
        if (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER) {
            PostProcess pp = PostProcess.instance;
            nglColor3f(pp.red(red, green, blue), pp.green(red, green, blue), pp.blue(red, green, blue), l);
        } else {
            nglColor3f(red, green, blue, l);
        }
    }

    @Redirect(
            method = "glColor4f",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;nglColor4f(FFFFJ)V"
            ),
            remap = false
    )
    private static void clientsideEssentials_glColor4f(float red, float green, float blue, float alpha, long l) {
        if (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER) {
            PostProcess pp = PostProcess.instance;
            nglColor4f(pp.red(red, green, blue), pp.green(red, green, blue), pp.blue(red, green, blue), alpha, l);
        } else {
            nglColor4f(red, green, blue, alpha, l);
        }
    }
}
