package net.danygames2014.unitweaks.mixin.tweaks.fov;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.danygames2014.unitweaks.util.CompatHelper;
import net.danygames2014.unitweaks.util.ModOptions;
import net.danygames2014.unitweaks.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiFunction;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    private Minecraft client;

    @Shadow
    private float viewDistance;

    @Shadow
    protected abstract float getFov(float tickDelta);

    @Unique
    public float fov = 70F;

    @Unique
    float fovZoom = 0F;

    @Unique
    boolean zoomedIn = false;

    @Unique
    public float getFovMultiplier(float partialTicks, boolean isHand, float originalValue) {
        fov = isHand ? originalValue : originalValue + ModOptions.getFovOffsetInDegrees();

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

                fovZoom = Util.clamp(fovZoom, 5F - fov, 130F - fov);
            } else {
                fov -= 50F;
            }

            fov += fovZoom;
        } else {
            zoomedIn = false;
        }

        // Mod Compat
        for (BiFunction<Float, Float, Float> modFovModifier : CompatHelper.fovMethods) {
            fov = modFovModifier.apply(fov, partialTicks);
        }

        return fov;
    }

    @ModifyExpressionValue(method = "onFrameUpdate", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/client/option/GameOptions;cinematicMode:Z"))
    public boolean smoothCameraWhenZooming(boolean original) {
        if (!UniTweaks.USER_INTERFACE_CONFIG.fovSlider) {
            return original;
        }
        
        return original || Keyboard.isKeyDown(KeyBindingListener.zoom.code);
    }

    @WrapOperation(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;getFov(F)F"))
    public float redirectToCustomFov(GameRenderer instance, float value, Operation<Float> originalOperation) {
        float original = originalOperation.call(instance, value);
        
        if (UniTweaks.USER_INTERFACE_CONFIG.fovSlider) {
            return getFovMultiplier(value, false, original);
        }
        
        return original;
    }

    @Inject(method = "renderFirstPersonHand", at = @At(value = "HEAD"))
    public void adjustHandFov(float f, int i, CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.fovSlider) {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GLU.gluPerspective(getFovMultiplier(f, true, this.getFov(f)), (float) client.displayWidth / (float) client.displayHeight, 0.05F, viewDistance * 2.0F);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
        }
    }
}
