package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {
    @Shadow public abstract void renderTopFace(Block block, double x, double y, double z, int texture);

    @WrapOperation(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderTopFace(Lnet/minecraft/block/Block;DDDI)V", ordinal = 0))
    public void aVoid(BlockRenderManager instance, Block block, double x, double y, double z, int texture, Operation<Void> original){
        if(block == Block.GRASS_BLOCK){
            renderTopFace(block, x, y, z, 0);
        }
        original.call(instance, block, x, y, z, texture);
    }
}
