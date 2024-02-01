package net.danygames2014.unitweaks.mixin.bugfixes.fenceboundingboxfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
public class FenceBlockMixin extends Block {

    @Unique
    private static World world;

    public FenceBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Unique
    private Box generateBox(World world, int x, int y, int z) {
        boolean connectPosX;
        boolean connectNegX;
        boolean connectPosZ;
        boolean connectNegZ;
        int fenceId = Block.FENCE.id;

        if (UniTweaks.TWEAKS_CONFIG.fencesConnectBlocks) {
            connectPosX = world.method_1780(x + 1, y, z) || world.getBlockId(x + 1, y, z) == fenceId;
            connectNegX = world.method_1780(x - 1, y, z) || world.getBlockId(x - 1, y, z) == fenceId;
            connectNegZ = world.method_1780(x, y, z - 1) || world.getBlockId(x, y, z - 1) == fenceId;
            connectPosZ = world.method_1780(x, y, z + 1) || world.getBlockId(x, y, z + 1) == fenceId;
        } else {
            connectPosX = world.getBlockId(x + 1, y, z) == fenceId;
            connectNegX = world.getBlockId(x - 1, y, z) == fenceId;
            connectPosZ = world.getBlockId(x, y, z + 1) == fenceId;
            connectNegZ = world.getBlockId(x, y, z - 1) == fenceId;
        }

        return Box.create(connectNegX ? 0 : 0.375F, 0F, connectNegZ ? 0F : 0.375F, connectPosX ? 1F : 0.625F, 1F, connectPosZ ? 1.F : 0.625F);
    }

    @Unique
    private Box generateBox(World world, int x, int y, int z, boolean collider) {
        Box box = generateBox(world, x, y, z);

        box.minX += x;
        box.minY += y;
        box.minZ += z;
        box.maxX += x;
        box.maxY += y;
        box.maxZ += z;

        if (collider) {
            if (UniTweaks.GAMEPLAY_CONFIG.fenceJumping) {
                box.maxY += 0.26F;
            } else {
                box.maxY += 0.5F;
            }
        }

        return box;
    }

    @Inject(method = "getCollisionShape", at = @At("RETURN"), cancellable = true)
    public void getCollisionShape(World world, int x, int y, int z, CallbackInfoReturnable<Box> cir) {
        if (UniTweaks.BUGFIXES_CONFIG.fenceBoundingBoxFix) {
            cir.setReturnValue(generateBox(world, x, y, z, true));
        }
    }

    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        if (UniTweaks.BUGFIXES_CONFIG.fenceBoundingBoxFix) {
            FenceBlockMixin.world = world;
            return generateBox(world, x, y, z, false);
        } else {
            return super.getBoundingBox(world, x, y, z);
        }
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        if (UniTweaks.BUGFIXES_CONFIG.fenceBoundingBoxFix) {
            if (world == null)
                return;
            Box box = generateBox(world, x, y, z);
            setBoundingBox((float) box.minX, (float) box.minY, (float) box.minZ, (float) box.maxX, (float) box.maxY, (float) box.maxZ);
        } else {
            super.updateBoundingBox(blockView, x, y, z);
        }
    }
}
