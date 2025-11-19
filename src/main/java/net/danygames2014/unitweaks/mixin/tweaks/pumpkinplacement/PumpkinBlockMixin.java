package net.danygames2014.unitweaks.mixin.tweaks.pumpkinplacement;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.PumpkinBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PumpkinBlock.class)
public class PumpkinBlockMixin extends Block {
    public PumpkinBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(
            method = "canPlaceAt",
            at = @At("RETURN"),
            cancellable = true
    )
    private void annoyanceFix_canPlaceAt(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.pumpkinsPlaceableLikeNormal) {
            int blockId = world.getBlockId(x, y, z);
            cir.setReturnValue(blockId == 0 || BLOCKS[blockId].material.isReplaceable());
        }
    }
}
