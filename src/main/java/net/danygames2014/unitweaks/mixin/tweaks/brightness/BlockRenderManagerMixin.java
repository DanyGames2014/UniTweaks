package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {

    @WrapOperation(method = "renderFlat", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float brigthnessRenderFlat(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original){
        return original.call(instance, blockView, x, y, z) * ModOptions.brightnessMultiplier;
    }
}
