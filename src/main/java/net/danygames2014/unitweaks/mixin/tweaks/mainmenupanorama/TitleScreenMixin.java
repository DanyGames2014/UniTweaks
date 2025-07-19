package net.danygames2014.unitweaks.mixin.tweaks.mainmenupanorama;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.awt.image.BufferedImage;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {
    @Unique
    boolean panoramaInit = false;
    @Unique
    int panoramaTexture;
    @Shadow
    private float ticks;
    @Unique
    float fwidth;
    @Unique
    float fheight;
    @Unique
    Tessellator tessellator;

    @Unique
    public int panoramaImageSize = 256;

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawTextWithShadow(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal = 0))
    public void moveVersionText(TitleScreen instance, TextRenderer textRenderer, String text, int x, int y, int color, Operation<Void> original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.panoramaConfig.enablePanorma && FabricLoader.getInstance().isModLoaded("modmenu")) {
            textRenderer.drawWithShadow(text, x, this.height - 10, Color.white.getRGB());
        } else {
            original.call(instance, textRenderer, text, x, y, color);
        }
    }


    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;renderBackground()V"))
    public void cancelDefaultBackgroundRendering(TitleScreen instance, Operation<Void> original) {
        // Redirect to nothing
        if (!UniTweaks.USER_INTERFACE_CONFIG.panoramaConfig.enablePanorma || !FabricLoader.getInstance().isModLoaded("modmenu")) {
            original.call(instance);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;renderBackground()V", shift = At.Shift.AFTER))
    public void redirectBackgroundRendering(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.panoramaConfig.enablePanorma && FabricLoader.getInstance().isModLoaded("modmenu")) {
            this.fwidth = (float) width;
            this.fheight = (float) height;
            if (!panoramaInit) {
                this.tessellator = Tessellator.INSTANCE;
                this.panoramaInit = true;
                this.panoramaTexture = this.minecraft.textureManager.load(new BufferedImage(panoramaImageSize, panoramaImageSize, 2));
            }
            renderSkybox(delta);
            fillGradient(0, 0, this.width, this.height, -2130706433, 16777215);
            fillGradient(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
        }
    }

    @Unique
    private void renderSkybox(float delta) {
        GL11.glViewport(0, 0, panoramaImageSize, panoramaImageSize);
        drawPanorama(delta);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        for (int i = 0; i < 8; i++) {
            rotateAndBlurSkybox();
        }
        GL11.glViewport(0, 0, this.minecraft.displayWidth, this.minecraft.displayHeight);
        tessellator.startQuads();

        float var5 = fwidth > fheight ? (120.0F / fwidth) : (120.0F / fheight);
        float var6 = fheight * var5 / 256.0F;
        float var7 = fwidth * var5 / 256.0F;
        tessellator.color(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.vertex(0.0D, height, this.zOffset, 0.5F - var6, 0.5F + var7);
        tessellator.vertex(width, height, this.zOffset, 0.5F - var6, 0.5F - var7);
        tessellator.vertex(width, 0.0D, this.zOffset, 0.5F + var6, 0.5F - var7);
        tessellator.vertex(0.0D, 0.0D, this.zOffset, 0.5F + var6, 0.5F + var7);
        tessellator.draw();

        // Scaling filter
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
    }

    @Unique
    private void rotateAndBlurSkybox() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.panoramaTexture);
        GL11.glCopyTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 0, 0, panoramaImageSize, panoramaImageSize);

        // Without this, Panorama breaks when blur is enabled on AMD drivers :abyss:
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColorMask(true, true, true, false);

        tessellator.startQuads();
        for (int i = 0; i < 3; ++i) {
            if (UniTweaks.USER_INTERFACE_CONFIG.panoramaConfig.blurBackground) {
                tessellator.color(1.0F, 1.0F, 1.0F, 1.0F / (float) (i + 1)); //BLURRY FILTER
            }
            float var7 = (float) (i - 3 / 2) / 256.0F;
            tessellator.vertex(fwidth, fheight, this.zOffset, 0.0F + var7, 0.0D);
            tessellator.vertex(fwidth, 0.0D, this.zOffset, 1.0F + var7, 0.0D);
            tessellator.vertex(0.0D, 0.0D, this.zOffset, 1.0F + var7, 1.0D);
            tessellator.vertex(0.0D, fheight, this.zOffset, 0.0F + var7, 1.0D);
        }
        tessellator.draw();

        GL11.glColorMask(true, true, true, true);
    }

    @Unique
    private void drawPanorama(float delta) {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GLU.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        byte var5 = 8;

        for (int var6 = 0; var6 < var5 * var5; ++var6) {
            GL11.glPushMatrix();
            float var7 = ((float) (var6 % var5) / (float) var5 - 0.5F) / 64.0F;
            float var8 = ((float) (var6 / var5) / (float) var5 - 0.5F) / 64.0F;
            GL11.glTranslatef(var7, var8, 0F);
            GL11.glRotatef(MathHelper.sin((this.ticks + delta) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F); // Up & Down Waving
            GL11.glRotatef(-(this.ticks + delta) * 0.1F, 0.0F, 1.0F, 0.0F); // Horizontal Rotation

            for (int rotation = 0; rotation < 6; ++rotation) {
                GL11.glPushMatrix();

                switch (rotation) {
                    case 1 -> GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    case 2 -> GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
                    case 3 -> GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
                    case 4 -> GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                    case 5 -> GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                }

                GL11.glBindTexture(
                        GL11.GL_TEXTURE_2D,
                        this.minecraft.textureManager.getTextureId("assets/unitweaks/textures/" + UniTweaks.USER_INTERFACE_CONFIG.panoramaConfig.panoramaFolder + "/panorama" + rotation + ".png")
                );
                tessellator.startQuads();
                tessellator.color(16777215, 255 / (var6 + 1));
                tessellator.vertex(-1.0D, -1.0D, 1.0D, 0.0F, 0.0F);
                tessellator.vertex(1.0D, -1.0D, 1.0D, 1.0F, 0.0F);
                tessellator.vertex(1.0D, 1.0D, 1.0D, 1.0F, 1.0F);
                tessellator.vertex(-1.0D, 1.0D, 1.0D, 0.0F, 1.0F);
                tessellator.draw();
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glColorMask(true, true, true, false);
        }

        tessellator.translate(0.0D, 0.0D, 0.0D);
        GL11.glColorMask(true, true, true, true);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPopMatrix();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
}