package net.danygames2014.unitweaks.mixin.tweaks.fov;

import net.danygames2014.unitweaks.tweaks.fov.FovData;
import net.minecraft.block.Material;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_555.class)
public class class555Mixin {
    @Shadow
    private Minecraft field_2349;

    @Shadow
    private float field_2350;

    @Unique
    public float getFovMultiplier(float f, boolean isHand) {
        LivingEntity entity = this.field_2349.field_2807;
        float fov = FovData.getRealFov();

        if (isHand) {
            fov = 70F;
        }

        if (entity.isInFluid(Material.WATER)) {
            fov *= 60.0F / 70.0F;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            fov /= 4F;
            field_2349.options.cinematicMode = true;
        } else {
            field_2349.options.cinematicMode = false;
        }

        if (entity.health <= 0) {
            float deathTimeFov = (float) entity.field_1041 + f;
            fov /= (1.0F - 500F / (deathTimeFov + 500F)) * 2.0F + 1.0F;
        }

        return fov;
    }

    @Unique
    public float getFovMultiplier(float f) {
        return getFovMultiplier(f, false);
    }

    @Redirect(method = "method_1840", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_555;method_1848(F)F"))
    public float redirectToCustomFov(class_555 instance, float value) {
        return getFovMultiplier(value);
    }

    @Inject(method = "method_1845", at = @At(value = "HEAD"))
    public void adjustHandFov(float f, int i, CallbackInfo ci) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(getFovMultiplier(f, true), (float) field_2349.displayWidth / (float) field_2349.displayHeight, 0.05F, field_2350 * 2.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
