package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.PostProcess;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.texture.TextureManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

@Mixin(TextureManager.class)
public abstract class TextureManagerMixin {
    @Shadow private ByteBuffer field_1250;

    @Shadow protected abstract int method_1098(int i, int j);

    @Shadow public static boolean field_1245;

    @Shadow private GameOptions gameOptions;

    @Shadow private boolean field_1254;

    @Shadow private boolean field_1255;

    @Inject(
            method = "method_1089",
            at = @At("RETURN"),
            cancellable = true
    )
    public void clientsideEssentials_bindImageToIdBufferedImage(BufferedImage bufferedImage, int i, CallbackInfo ci) {
        if (  (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER)
           && (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_GUI)
        ) {
            int n;
            int n2;
            int n3;
            int n4;
            int n5;
            int n6;
            int n7;
            int n8;
            GL11.glBindTexture(3553, i);
            if (field_1245) {
                GL11.glTexParameteri(3553, 10241, 9986);
                GL11.glTexParameteri(3553, 10240, 9728);
            } else {
                GL11.glTexParameteri(3553, 10241, 9728);
                GL11.glTexParameteri(3553, 10240, 9728);
            }
            if (this.field_1255) {
                GL11.glTexParameteri(3553, 10241, 9729);
                GL11.glTexParameteri(3553, 10240, 9729);
            }
            if (this.field_1254) {
                GL11.glTexParameteri(3553, 10242, 10496);
                GL11.glTexParameteri(3553, 10243, 10496);
            } else {
                GL11.glTexParameteri(3553, 10242, 10497);
                GL11.glTexParameteri(3553, 10243, 10497);
            }
            int n9 = bufferedImage.getWidth();
            int n10 = bufferedImage.getHeight();
            int[] nArray = new int[n9 * n10];
            byte[] byArray = new byte[n9 * n10 * 4];
            bufferedImage.getRGB(0, 0, n9, n10, nArray, 0, n9);
            PostProcess pp = PostProcess.instance;
            for (n8 = 0; n8 < nArray.length; ++n8) {
                n7 = nArray[n8] >> 24 & 0xFF;
                n6 = nArray[n8] >> 16 & 0xFF;
                n5 = nArray[n8] >> 8 & 0xFF;
                n4 = nArray[n8] & 0xFF;
                if (this.gameOptions != null && this.gameOptions.anaglyph3d) {
                    n3 = (n6 * 30 + n5 * 59 + n4 * 11) / 100;
                    n2 = (n6 * 30 + n5 * 70) / 100;
                    n = (n6 * 30 + n4 * 70) / 100;
                    n6 = n3;
                    n5 = n2;
                    n4 = n;
                }
                if (pp != null) {
                    n3 = pp.red(n6, n5, n4);
                    n2 = pp.green(n6, n5, n4);
                    n = pp.blue(n6, n5, n4);
                    n6 = n3;
                    n5 = n2;
                    n4 = n;
                }
                byArray[n8 * 4 + 0] = (byte) n6;
                byArray[n8 * 4 + 1] = (byte) n5;
                byArray[n8 * 4 + 2] = (byte) n4;
                byArray[n8 * 4 + 3] = (byte) n7;
            }
            this.field_1250.clear();
            this.field_1250.put(byArray);
            this.field_1250.position(0).limit(byArray.length);
            GL11.glTexImage2D(3553, 0, 6408, n9, n10, 0, 6408, 5121, this.field_1250);
            if (field_1245) {
                for (n8 = 1; n8 <= 4; ++n8) {
                    n7 = n9 >> n8 - 1;
                    n6 = n9 >> n8;
                    n5 = n10 >> n8;
                    for (n4 = 0; n4 < n6; ++n4) {
                        for (n3 = 0; n3 < n5; ++n3) {
                            n2 = this.field_1250.getInt((n4 * 2 + 0 + (n3 * 2 + 0) * n7) * 4);
                            n = this.field_1250.getInt((n4 * 2 + 1 + (n3 * 2 + 0) * n7) * 4);
                            int n11 = this.field_1250.getInt((n4 * 2 + 1 + (n3 * 2 + 1) * n7) * 4);
                            int n12 = this.field_1250.getInt((n4 * 2 + 0 + (n3 * 2 + 1) * n7) * 4);
                            int n13 = this.method_1098(this.method_1098(n2, n), this.method_1098(n11, n12));
                            this.field_1250.putInt((n4 + n3 * n6) * 4, n13);
                        }
                    }
                    GL11.glTexImage2D(3553, n8, 6408, n6, n5, 0, 6408, 5121, this.field_1250);
                }
            }
        }
    }

    @Inject(
            method = "method_1095",
            at = @At("RETURN"),
            cancellable = true
    )
    public void clientsideEssentials_bindImageToId(int[] is, int i, int j, int k, CallbackInfo ci) {
        if (  (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_SLIDER)
           && (UniTweaks.TWEAKS_CONFIG.BRIGHTNESS_CONFIG.ENABLE_BRIGHTNESS_GUI)
        ) {
            GL11.glBindTexture(3553, k);
            if (field_1245) {
                GL11.glTexParameteri(3553, 10241, 9986);
                GL11.glTexParameteri(3553, 10240, 9728);
            } else {
                GL11.glTexParameteri(3553, 10241, 9728);
                GL11.glTexParameteri(3553, 10240, 9728);
            }
            if (this.field_1255) {
                GL11.glTexParameteri(3553, 10241, 9729);
                GL11.glTexParameteri(3553, 10240, 9729);
            }
            if (this.field_1254) {
                GL11.glTexParameteri(3553, 10242, 10496);
                GL11.glTexParameteri(3553, 10243, 10496);
            } else {
                GL11.glTexParameteri(3553, 10242, 10497);
                GL11.glTexParameteri(3553, 10243, 10497);
            }
            byte[] byArray = new byte[i * j * 4];
            PostProcess pp = PostProcess.instance;
            for (int i2 = 0; i2 < is.length; ++i2) {
                int n = is[i2] >> 24 & 0xFF;
                int n2 = is[i2] >> 16 & 0xFF;
                int n3 = is[i2] >> 8 & 0xFF;
                int n4 = is[i2] & 0xFF;
                if (this.gameOptions != null && this.gameOptions.anaglyph3d) {
                    int n5 = (n2 * 30 + n3 * 59 + n4 * 11) / 100;
                    int n6 = (n2 * 30 + n3 * 70) / 100;
                    int n7 = (n2 * 30 + n4 * 70) / 100;
                    n2 = n5;
                    n3 = n6;
                    n4 = n7;
                }
                if (pp != null) {
                    int n5 = pp.red(n2, n3, n4);
                    int n6 = pp.green(n2, n3, n4);
                    int n7 = pp.blue(n2, n3, n4);
                    n2 = n5;
                    n3 = n6;
                    n4 = n7;
                }
                byArray[i2 * 4 + 0] = (byte) n2;
                byArray[i2 * 4 + 1] = (byte) n3;
                byArray[i2 * 4 + 2] = (byte) n4;
                byArray[i2 * 4 + 3] = (byte) n;
            }
            this.field_1250.clear();
            this.field_1250.put(byArray);
            this.field_1250.position(0).limit(byArray.length);
            GL11.glTexSubImage2D(3553, 0, 0, 0, i, j, 6408, 5121, this.field_1250);
        }
    }
}
