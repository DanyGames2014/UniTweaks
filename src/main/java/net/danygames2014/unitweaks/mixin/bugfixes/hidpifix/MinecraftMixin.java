package net.danygames2014.unitweaks.mixin.bugfixes.hidpifix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.awt.geom.AffineTransform;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow public Canvas canvas;

    @Shadow public int displayWidth;

    @Shadow public int displayHeight;

    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Ljava/awt/Canvas;getWidth()I"), remap = false)
    public int fixWidth(Canvas canvas){
        if(UniTweaks.BUGFIXES_CONFIG.hiDpiFix){
            AffineTransform transform = canvas.getGraphicsConfiguration().getDefaultTransform();
            return (int) Math.ceil(canvas.getParent().getWidth() * transform.getScaleX());
        }
        return canvas.getWidth();
    }

    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Ljava/awt/Canvas;getHeight()I"), remap = false)
    public int fixHeight(Canvas canvas){
        if(UniTweaks.BUGFIXES_CONFIG.hiDpiFix){
            AffineTransform transform = canvas.getGraphicsConfiguration().getDefaultTransform();
            return (int) Math.ceil(canvas.getParent().getHeight() * transform.getScaleY());
        }
        return canvas.getHeight();
    }

    @Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;method_2108(II)V"))
    public void fixCanvasSize(CallbackInfo ci){
        if(UniTweaks.BUGFIXES_CONFIG.hiDpiFix){
            this.canvas.setBounds(0,0, this.displayWidth, this.displayHeight);
        }
    }
}
