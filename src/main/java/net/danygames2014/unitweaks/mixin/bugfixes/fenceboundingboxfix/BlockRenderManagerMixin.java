package net.danygames2014.unitweaks.mixin.bugfixes.fenceboundingboxfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {

    @Shadow
    public abstract boolean renderBlock(Block block, int x, int y, int z);

    @Shadow
    private BlockView blockView;

    // I know this is not good
    @Inject(method = "renderFence", at = @At(value = "HEAD"), cancellable = true)
    public void renderConnectedFence(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.fencesConnectBlocks) {
            float var6 = 0.375F;
            float var7 = 0.625F;

            block.setBoundingBox(var6, 0.0F, var6, var7, 1.0F, var7);
            this.renderBlock(block, x, y, z);

            boolean connectedPosX = this.blockView.method_1780(x + 1, y, z) || this.blockView.getBlockId(x + 1, y, z) == Block.FENCE.id;
            boolean connectedNegX = this.blockView.method_1780(x - 1, y, z) || this.blockView.getBlockId(x - 1, y, z) == Block.FENCE.id;
            boolean connectedNegZ = this.blockView.method_1780(x, y, z - 1) || this.blockView.getBlockId(x, y, z - 1) == Block.FENCE.id;
            boolean connectedPosZ = this.blockView.method_1780(x, y, z + 1) || this.blockView.getBlockId(x, y, z + 1) == Block.FENCE.id;

            boolean connectedX = connectedPosX || connectedNegX;
            boolean connectedZ = connectedPosZ || connectedNegZ;

            if (!connectedX && !connectedZ) {
                connectedX = true;
            }

            var6 = 0.4375F;
            var7 = 0.5625F;
            float minY = 0.75F;
            float maxY = 0.9375F;
            float minX = connectedNegX ? 0.0F : var6;
            float maxX = connectedPosX ? 1.0F : var7;
            float minZ = connectedNegZ ? 0.0F : var6;
            float maxZ = connectedPosZ ? 1.0F : var7;
            if (connectedX) {
                block.setBoundingBox(minX, minY, var6, maxX, maxY, var7);
                this.renderBlock(block, x, y, z);
            }

            if (connectedZ) {
                block.setBoundingBox(var6, minY, minZ, var7, maxY, maxZ);
                this.renderBlock(block, x, y, z);
            }

            minY = 0.375F;
            maxY = 0.5625F;
            if (connectedX) {
                block.setBoundingBox(minX, minY, var6, maxX, maxY, var7);
                this.renderBlock(block, x, y, z);
            }

            if (connectedZ) {
                block.setBoundingBox(var6, minY, minZ, var7, maxY, maxZ);
                this.renderBlock(block, x, y, z);
            }

            block.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            cir.setReturnValue(true);
        }
    }
}
