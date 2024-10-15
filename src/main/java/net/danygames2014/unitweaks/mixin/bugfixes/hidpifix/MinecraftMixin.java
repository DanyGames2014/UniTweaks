package net.danygames2014.unitweaks.mixin.bugfixes.hidpifix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public Canvas canvas;

    @Shadow
    public int displayWidth;

    @Shadow
    public int displayHeight;

    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Ljava/awt/Canvas;getWidth()I", ordinal = 1), remap = false, require = 0)
    public int fixWidth(Canvas instance, Operation<Integer> original) {
        if (UniTweaks.BUGFIXES_CONFIG.hiDpiFix) {
            AffineTransform transform = canvas.getGraphicsConfiguration().getDefaultTransform();
            return (int) Math.ceil(canvas.getParent().getWidth() * transform.getScaleX());
        }
        return original.call(instance);
    }

    @WrapOperation(method = "run", at = @At(value = "INVOKE", target = "Ljava/awt/Canvas;getHeight()I", ordinal = 1), remap = false, require = 0)
    public int fixHeight(Canvas instance, Operation<Integer> original) {
        if (UniTweaks.BUGFIXES_CONFIG.hiDpiFix) {
            AffineTransform transform = canvas.getGraphicsConfiguration().getDefaultTransform();
            return (int) Math.ceil(canvas.getParent().getHeight() * transform.getScaleY());
        }
        return original.call(instance);
    }

    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;resize(II)V"))
    public void fixCanvasSize(CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.hiDpiFix) {
            if (canvas != null) {
                this.canvas.setBounds(0, 0, this.displayWidth, this.displayHeight);
            }
        }
    }
}
