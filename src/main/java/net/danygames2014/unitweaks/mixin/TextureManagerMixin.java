package net.danygames2014.unitweaks.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.texture.DynamicTexture;
import net.minecraft.client.resource.pack.TexturePacks;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.GlAllocationUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Mixin(TextureManager.class)
public abstract class TextureManagerMixin {
    @Shadow
    public static boolean MIPMAP;

    @Shadow
    private ByteBuffer imageBuffer;

    @Shadow
    protected abstract int crispBlend(int color1, int color2);

    @Shadow
    protected abstract int smoothBlend(int color1, int color2);

    @Unique
    private ByteBuffer[] mipImageDatas;
    
    @Unique
    int terrainAtlasTextureId = 0;

    // Config Placeholder
    @Unique
    public boolean isUseMipmaps() {
        return true;
    }

    @Unique
    public int getMipmapType() {
        return 9984;
    }

    @Unique
    public int getMipmapLevel() {
        return 4;
    }

    // 1
    @WrapWithCondition(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTexParameteri(III)V", ordinal = 0, remap = false))
    public boolean dontRunThisTwiceForTheFucksSake(int target, int pname, int param){
        return false;
    }

    @WrapWithCondition(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTexParameteri(III)V", ordinal = 1, remap = false))
    public boolean dontRunThisTwiceForTheFucksSakePart2ElectricBoogaloo(int target, int pname, int param){
        return false;
    }
    
    @Inject(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBindTexture(II)V", ordinal = 0, shift = At.Shift.AFTER, remap = false))
    public void aVoid(BufferedImage image, int id, CallbackInfo ci) {
        MIPMAP = isUseMipmaps();
        
        if (MIPMAP && id == terrainAtlasTextureId) {
            int mipmapType = getMipmapType();
            GL11.glTexParameteri(3553, 10241, mipmapType);
            GL11.glTexParameteri(3553, 10240, 9728);
            if (GLContext.getCapabilities().OpenGL12) {
                GL11.glTexParameteri(3553, 33084, 0);
                int mipmapLevel = getMipmapLevel();
                if (mipmapLevel >= 4) {
                    int minDim = Math.min(image.getWidth(), image.getHeight());
                    mipmapLevel = this.getMaxMipmapLevel(minDim) - 4;
                    if (mipmapLevel < 0) {
                        mipmapLevel = 0;
                    }
                }

                GL11.glTexParameteri(3553, 33085, mipmapLevel);
            }
        } else {
            GL11.glTexParameteri(3553, 10241, 9728);
            GL11.glTexParameteri(3553, 10240, 9728);
        }
    }

    @Unique
    private int getMaxMipmapLevel(int size) {
        int level;
        for (level = 0; size > 0; level++) {
            size /= 2;
        }

        return level - 1;
    }

    // 2
    @WrapOperation(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "FIELD", target = "Lnet/minecraft/client/texture/TextureManager;MIPMAP:Z", ordinal = 1))
    public boolean dontUseVanillaMipmaps(Operation<Boolean> original) {
        return false;
    }

    @Inject(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTexImage2D(IIIIIIIILjava/nio/ByteBuffer;)V", ordinal = 0, shift = At.Shift.AFTER, remap = false))
    public void useOurOwnInstead(BufferedImage image, int id, CallbackInfo ci) {
        if (MIPMAP) {
            this.generateMipMaps(this.imageBuffer, image.getWidth(), image.getHeight());
        }
    }

    @Unique
    private void generateMipMaps(ByteBuffer data, int width, int height) {
        ByteBuffer parMipData = data;

        for (int level = 1; level <= 16; level++) {
            int parWidth = width >> level - 1;
            int mipWidth = width >> level;
            int mipHeight = height >> level;
            if (mipWidth <= 0 || mipHeight <= 0) {
                break;
            }

            ByteBuffer mipData = this.mipImageDatas[level - 1];

            for (int mipX = 0; mipX < mipWidth; mipX++) {
                for (int mipY = 0; mipY < mipHeight; mipY++) {
                    int p1 = parMipData.getInt((mipX * 2 + 0 + (mipY * 2 + 0) * parWidth) * 4);
                    int p2 = parMipData.getInt((mipX * 2 + 1 + (mipY * 2 + 0) * parWidth) * 4);
                    int p3 = parMipData.getInt((mipX * 2 + 1 + (mipY * 2 + 1) * parWidth) * 4);
                    int p4 = parMipData.getInt((mipX * 2 + 0 + (mipY * 2 + 1) * parWidth) * 4);
                    int pixel = this.weightedAverageColor(p1, p2, p3, p4);
                    mipData.putInt((mipX + mipY * mipWidth) * 4, pixel);
                }
            }

            GL11.glTexImage2D(3553, level, 6408, mipWidth, mipHeight, 0, 6408, 5121, mipData);
            parMipData = mipData;
        }
    }

    // 4
    @Inject(method = "<init>", at = @At(value = "CTOR_HEAD"))
    public void construct(TexturePacks options, GameOptions par2, CallbackInfo ci) {
        this.allocateImageData(256);
    }

    @Unique
    private void allocateImageData(int width) {
        int imgLen = width * width * 4;
        this.imageBuffer = GlAllocationUtils.allocateByteBuffer(imgLen);
        List list = new ArrayList();

        for (int mipWidth = width / 2; mipWidth > 0; mipWidth /= 2) {
            int mipLen = mipWidth * mipWidth * 4;
            ByteBuffer buf = GlAllocationUtils.allocateByteBuffer(mipLen);
            list.add(buf);
        }

        this.mipImageDatas = (ByteBuffer[]) list.toArray(new ByteBuffer[list.size()]);
    }

    @Inject(method = "load(Ljava/awt/image/BufferedImage;I)V", at = @At(value = "INVOKE", target = "Ljava/nio/ByteBuffer;clear()Ljava/nio/Buffer;", ordinal = 0, shift = At.Shift.BEFORE))
    public void checkImageDataCUzWhyNot(BufferedImage image, int id, CallbackInfo ci) {
        this.checkImageDataSize(image.getWidth());
    }

    @Unique
    private void checkImageDataSize(int width) {
        if (this.imageBuffer != null) {
            int len = width * width * 4;
            if (this.imageBuffer.capacity() >= len) {
                return;
            }
        }

        this.allocateImageData(width);
    }

    @Unique
    private int weightedAverageColor(int c1, int c2, int c3, int c4) {
        int cx1 = this.crispBlend(c1, c2);
        int cx2 = this.crispBlend(c3, c4);
        return this.crispBlend(cx1, cx2);
    }

    // 3.8
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTexSubImage2D(IIIIIIIILjava/nio/ByteBuffer;)V", ordinal = 0, remap = false))
    public void useOurOwnInstead1point5 (
            int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels, Operation<Void> original, 
            @Local(ordinal = 1) int var3, @Local(ordinal = 2) int var4
        ) 
    {
        original.call(target, level, internalformat, width, height, border, format, type, pixels);
        
        if (MIPMAP && var3 == 0 && var4 == 0) {
            //this.generateMipMapsSub(xOffset, yOffset, tileWidth, tileHeight, this.imageBuffer, texturefx.tileSize, fastColor);
        }
    }

    // 4.79
    @WrapOperation(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/texture/TextureManager;MIPMAP:Z", ordinal = 1))
    public boolean dontUseVanillaMipmaps2(Operation<Boolean> original) {
        return false;
    }

    @Unique
    DynamicTexture dynamicTexture = null;

    @WrapOperation(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/texture/DynamicTexture;copyTo:I", ordinal = 1))
    public int captureLeDynamicTexture(DynamicTexture instance, Operation<Integer> original) {
        dynamicTexture = instance;
        return original.call(instance);
    }

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTexSubImage2D(IIIIIIIILjava/nio/ByteBuffer;)V", ordinal = 2, remap = false))
    public void useOurOwnInstead2(int target, int level, int internalformat, int width, int height, int border, int format, int type, ByteBuffer pixels, Operation<Void> original) {
        original.call(target, level, internalformat, width, height, border, format, type, pixels);
        if (MIPMAP) {
            this.generateMipMapsSub(0, 0, 16, 16, this.imageBuffer, dynamicTexture.replicate, false);
        }
    }

    @Unique
    private void generateMipMapsSub(int xOffset, int yOffset, int width, int height, ByteBuffer data, int numTiles, boolean fastColor) {
        ByteBuffer parMipData = data;

        for (int level = 1; level <= 16; level++) {
            int parWidth = width >> level - 1;
            int mipWidth = width >> level;
            int mipHeight = height >> level;
            int xMipOffset = xOffset >> level;
            int yMipOffset = yOffset >> level;
            if (mipWidth <= 0 || mipHeight <= 0) {
                break;
            }

            ByteBuffer mipData = this.mipImageDatas[level - 1];

            for (int mipX = 0; mipX < mipWidth; mipX++) {
                for (int mipY = 0; mipY < mipHeight; mipY++) {
                    int p1 = parMipData.getInt((mipX * 2 + 0 + (mipY * 2 + 0) * parWidth) * 4);
                    int p2 = parMipData.getInt((mipX * 2 + 1 + (mipY * 2 + 0) * parWidth) * 4);
                    int p3 = parMipData.getInt((mipX * 2 + 1 + (mipY * 2 + 1) * parWidth) * 4);
                    int p4 = parMipData.getInt((mipX * 2 + 0 + (mipY * 2 + 1) * parWidth) * 4);
                    int pixel;
                    if (fastColor) {
                        pixel = this.smoothBlend(this.smoothBlend(p1, p2), this.smoothBlend(p3, p4));
                    } else {
                        pixel = this.weightedAverageColor(p1, p2, p3, p4);
                    }

                    mipData.putInt((mipX + mipY * mipWidth) * 4, pixel);
                }
            }

            for (int ix = 0; ix < numTiles; ix++) {
                for (int iy = 0; iy < numTiles; iy++) {
                    int dx = ix * mipWidth;
                    int dy = iy * mipHeight;
                    GL11.glTexSubImage2D(3553, level, xMipOffset + dx, yMipOffset + dy, mipWidth, mipHeight, 6408, 5121, mipData);
                }
            }

            parMipData = mipData;
        }
    }

    // 7
    @Unique
    private String texturePath = "";

    @Inject(method = "getTextureId", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/TextureManager;load(Ljava/awt/image/BufferedImage;I)V", ordinal = 4))
    public void captureTexturePath(String path, CallbackInfoReturnable<Integer> cir) {
        texturePath = path;
    }

    @WrapOperation(method = "getTextureId", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/TextureManager;load(Ljava/awt/image/BufferedImage;I)V", ordinal = 4))
    public void captureNeededTextureIds(TextureManager instance, BufferedImage image, int id, Operation<Void> original) {
        if (texturePath.equals("/terrain.png")) {
            this.terrainAtlasTextureId = id;
            System.err.println(texturePath + " has been captured with id " + id);
        }
        
        original.call(instance, image, id);
    }
}
