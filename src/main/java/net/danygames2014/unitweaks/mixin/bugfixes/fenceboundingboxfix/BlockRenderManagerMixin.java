package net.danygames2014.unitweaks.mixin.bugfixes.fenceboundingboxfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin {

    @Shadow
    public abstract boolean renderBlock(Block block, int x, int y, int z);

    @Shadow
    private BlockView blockView;

    @Unique
    boolean connectedPosX;
    @Unique
    boolean connectedNegX;
    @Unique
    boolean connectedNegZ;
    @Unique
    boolean connectedPosZ;

    @Unique
    boolean connectedX;
    @Unique
    boolean connectedZ;

    @Unique
    float minX;
    @Unique
    float maxX;
    @Unique
    float minZ;
    @Unique
    float maxZ;
    
    // I know this is not good
    @Inject(method = "renderFence", at = @At(value = "HEAD"), cancellable = true)
    public void renderConnectedFence(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.fencesConnectBlocks) {
            block.setBoundingBox(0.375F, 0.0F, 0.375F, 0.625F, 1.0F, 0.625F);
            this.renderBlock(block, x, y, z);

            connectedPosX = (this.blockView.shouldSuffocate(x + 1, y, z) || this.blockView.getBlockId(x + 1, y, z) == Block.FENCE.id) && this.blockView.getBlockId(x + 1, y, z) != Block.GLOWSTONE.id;
            connectedNegX = (this.blockView.shouldSuffocate(x - 1, y, z) || this.blockView.getBlockId(x - 1, y, z) == Block.FENCE.id) && this.blockView.getBlockId(x - 1, y, z) != Block.GLOWSTONE.id;
            connectedNegZ = (this.blockView.shouldSuffocate(x, y, z - 1) || this.blockView.getBlockId(x, y, z - 1) == Block.FENCE.id) && this.blockView.getBlockId(x, y, z - 1) != Block.GLOWSTONE.id;
            connectedPosZ = (this.blockView.shouldSuffocate(x, y, z + 1) || this.blockView.getBlockId(x, y, z + 1) == Block.FENCE.id) && this.blockView.getBlockId(x, y, z + 1) != Block.GLOWSTONE.id;

            connectedX = connectedPosX || connectedNegX;
            connectedZ = connectedPosZ || connectedNegZ;

            if (!connectedX && !connectedZ) {
                connectedX = true;
            }

            minX = connectedNegX ? 0.0F : 0.4375F;
            maxX = connectedPosX ? 1.0F : 0.5625F;
            minZ = connectedNegZ ? 0.0F : 0.4375F;
            maxZ = connectedPosZ ? 1.0F : 0.5625F;
            
            if (connectedX) {
                block.setBoundingBox(minX, 0.75F, 0.4375F, maxX, 0.9375F, 0.5625F);
                this.renderBlock(block, x, y, z);
            }

            if (connectedZ) {
                block.setBoundingBox(0.4375F, 0.75F, minZ, 0.5625F, 0.9375F, maxZ);
                this.renderBlock(block, x, y, z);
            }

            if (connectedX) {
                block.setBoundingBox(minX, 0.375F, 0.4375F, maxX, 0.5625F, 0.5625F);
                this.renderBlock(block, x, y, z);
            }

            if (connectedZ) {
                block.setBoundingBox(0.4375F, 0.375F, minZ, 0.5625F, 0.5625F, maxZ);
                this.renderBlock(block, x, y, z);
            }

            block.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            cir.setReturnValue(true);
        }
    }
}
