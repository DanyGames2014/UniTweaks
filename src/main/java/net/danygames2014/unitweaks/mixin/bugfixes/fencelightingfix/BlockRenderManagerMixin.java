package net.danygames2014.unitweaks.mixin.bugfixes.fencelightingfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {
    // This is to reduce the amount of instanceof checks from 4 to 1
    @Unique
    public boolean isFence = false;
    
    @WrapOperation(method = "renderSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F", ordinal = 7))
    public float luminance1(Block block, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        isFence = block instanceof FenceBlock && UniTweaks.BUGFIXES_CONFIG.fenceLightingFix;
        
        if (isFence) {
            return original.call(block, blockView, x, y + 1, z);
        }
        return original.call(block, blockView, x, y, z);
    }

    @WrapOperation(method = "renderSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F", ordinal = 8))
    public float luminance2(Block block, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if (isFence) {
            return original.call(block, blockView, x, y + 1, z);
        }
        return original.call(block, blockView, x, y, z);
    }

    @WrapOperation(method = "renderSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F", ordinal = 9))
    public float luminance3(Block block, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if (isFence) {
            return original.call(block, blockView, x, y + 1, z);
        }
        return original.call(block, blockView, x, y, z);
    }
    
    @WrapOperation(method = "renderSmooth", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F", ordinal = 10))
    public float luminance4(Block block, BlockView blockView, int x, int y, int z, Operation<Float> original) {
        if (isFence) {
            return original.call(block, blockView, x, y + 1, z);
        }
        return original.call(block, blockView, x, y, z);
    }
    

}
