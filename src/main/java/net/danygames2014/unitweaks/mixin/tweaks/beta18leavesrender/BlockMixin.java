package net.danygames2014.unitweaks.mixin.tweaks.beta18leavesrender;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Shadow
    @Final
    public static int[] BLOCKS_LIGHT_LUMINANCE;

    @Shadow
    @Final
    public int id;

    @Inject(method = "getLuminance", at = @At(value = "HEAD"), cancellable = true)
    public void changeLuminance(BlockView blockView, int x, int y, int z, CallbackInfoReturnable<Float> cir) {
        if (UniTweaks.TWEAKS_CONFIG.beta18LeavesRendering) {
            if (blockView.getMaterial(x, y, z) == Material.LEAVES) {
                float luminance = blockView.getNaturalBrightness(x, y, z, BLOCKS_LIGHT_LUMINANCE[this.id]);
                cir.setReturnValue(Math.min(luminance, 0.2F));
            }
        }        
    }
}
