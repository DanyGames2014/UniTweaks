package net.danygames2014.unitweaks.mixin.bugfixes.torchbottomfix;

import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.Config;
import net.danygames2014.unitweaks.util.Util;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {
    @Inject(method = "renderTiltedTorch", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Tessellator;vertex(DDDDD)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addBottomFace(Block block, double x, double y, double z, double xTilt, double zTilt, CallbackInfo ci,
                              @Local Tessellator tessellator, 
                              @Local(ordinal = 13) double var36,
                              @Local(ordinal = 5) double var20,
                              @Local(ordinal = 6) double var22,
                              @Local(ordinal = 7) double var24,
                              @Local(ordinal = 8) double var26
                              ) {
        if (UniTweaks.BUGFIXES_CONFIG.torchBottomFaceFix) {
            float shift = 2F / Util.atlasHeight;

            tessellator.vertex(x + var36 + xTilt, y, z - var36 + zTilt, var24, var22 + shift);
            tessellator.vertex(x + var36 + xTilt, y, z + var36 + zTilt, var24, var26 + shift);
            tessellator.vertex(x - var36 + xTilt, y, z + var36 + zTilt, var20, var26 + shift);
            tessellator.vertex(x - var36 + xTilt, y, z - var36 + zTilt, var20, var22 + shift);
        }
    }
}
