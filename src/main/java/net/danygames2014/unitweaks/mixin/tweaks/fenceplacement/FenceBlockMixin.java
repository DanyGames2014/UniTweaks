package net.danygames2014.unitweaks.mixin.tweaks.fenceplacement;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
public class FenceBlockMixin extends Block {
    public FenceBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void liftPlacementRestrictions(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.fencesPlaceableLikeNormal) {
            cir.setReturnValue(super.canPlaceAt(world, x, y, z));
        }
    }
}
