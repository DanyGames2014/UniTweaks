package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.block.BlockRenderManager;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {
    @Shadow
    public boolean inventoryColorEnabled;

    @WrapOperation(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderTopFace(Lnet/minecraft/block/Block;DDDI)V", ordinal = 0))
    public void aVoid(BlockRenderManager instance, Block block, double x, double y, double z, int texture, Operation<Void> original, @Local(ordinal = 0, argsOnly = true) float brightness) {
        if (UniTweaks.BUGFIXES_CONFIG.grassBlockItemFix && block == Block.GRASS_BLOCK) {
            Color color = new Color(GrassColors.getColor(0.5F, 1.0F));
            if (inventoryColorEnabled) {
                int grassColor = GrassColors.getColor(0.5F, 1.0F);
                float r = 0.4862745F;
                float g = 0.7411765F;
                float b = 0.41960785F;
                
                if (grassColor != -8602261){
                    r = (float) (grassColor >> 16 & 255) / 255.0F;
                    g = (float) (grassColor >> 8 & 255) / 255.0F;
                    b = (float) (grassColor & 255) / 255.0F;
                }
                
                GL11.glColor4f(r * brightness, g * brightness, b * brightness, 1.0F);
            } else {
                GL11.glColor3f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F);
            }
        }

        original.call(instance, block, x, y, z, texture);
    }

    @Inject(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Tessellator;draw()V", ordinal = 2, shift = At.Shift.AFTER))
    public void aVoid2(Block block, int metadata, float brightness, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.grassBlockItemFix && block == Block.GRASS_BLOCK) {
            if (this.inventoryColorEnabled) {
                int blockColor = block.getColor(metadata);
                float r = (float) (blockColor >> 16 & 255) / 255.0F;
                float g = (float) (blockColor >> 8 & 255) / 255.0F;
                float b = (float) (blockColor & 255) / 255.0F;
                GL11.glColor4f(r * brightness, g * brightness, b * brightness, 1.0F);
            } else {
                GL11.glColor3f(1.0F, 1.0F, 1.0F);
            }
        }
    }
}
