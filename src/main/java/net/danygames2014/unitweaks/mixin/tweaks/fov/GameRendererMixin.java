package net.danygames2014.unitweaks.mixin.tweaks.fov;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.danygames2014.unitweaks.util.CompatHelper;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.modificationstation.stationapi.api.util.math.MathHelper;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    private Minecraft client;

    @Shadow
    private float viewDistance;

    @Unique
    public float fov = 70F;

    @Unique
    float fovZoom = 0F;

    @Unique
    boolean zoomedIn = false;

    @Unique
    public float getFovMultiplier(float partialTicks, boolean isHand) {
        LivingEntity cameraEntity = this.client.camera;
        fov = ModOptions.getFovInDegrees();

        if (isHand) {
            fov = 70F;
        }

        if (cameraEntity.isInFluid(Material.WATER)) {
            fov *= 60.0F / 70.0F;
        }

        if (Keyboard.isKeyDown(KeyBindingListener.zoom.code) && client.currentScreen == null) {
            if (!zoomedIn) {
                ModOptions.zoomFovOffset = 0;
                fovZoom = 0F;
                zoomedIn = true;
            }

            if (!isHand) {
                fov /= 4F;

                fovZoom += ModOptions.zoomFovOffset * 5;
                ModOptions.zoomFovOffset = 0;

                fovZoom = MathHelper.clamp(fovZoom, 5F - fov, 130F - fov);
            } else {
                fov -= (ModOptions.getFovInDegrees() - 50F);
            }

            fov += fovZoom;

//            System.out.println("isHand = " + isHand + " | fov = " + fov + " | fovZoom = " + fovZoom + " | 5f-fov = " + (5f - fov) + " | 130f-fov = " + (130f - fov));

        } else {
            zoomedIn = false;
        }

        if (cameraEntity.health <= 0) {
            float deathTimeFov = (float) cameraEntity.deathTime + partialTicks;
            fov /= (1.0F - 500F / (deathTimeFov + 500F)) * 2.0F + 1.0F;
        }

        // Mod Compat
        for (BiFunction<Float, Float, Float> modFovModifier : CompatHelper.fovMethods) {
            fov = modFovModifier.apply(fov, partialTicks);
        }

        return fov;
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;cinematicMode:Z"))
    public boolean smoothCameraWhenZooming(boolean original) {
        return original || Keyboard.isKeyDown(KeyBindingListener.zoom.code);
    }

    @Unique
    public float getFovMultiplier(float f) {
        return getFovMultiplier(f, false);
    }

    @Redirect(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;getFov(F)F"))
    public float redirectToCustomFov(GameRenderer instance, float value) {
        return getFovMultiplier(value);
    }

    @Inject(method = "renderFirstPersonHand", at = @At(value = "HEAD"))
    public void adjustHandFov(float f, int i, CallbackInfo ci) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(getFovMultiplier(f, true), (float) client.displayWidth / (float) client.displayHeight, 0.05F, viewDistance * 2.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
