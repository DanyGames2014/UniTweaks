package net.danygames2014.unitweaks.mixin.bugfixes.stairsdropfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StairsBlock.class)
public class StairsBlockMixin extends Block {
    public StairsBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "getDroppedItemId", at = @At("HEAD"), cancellable = true)
    public void overrideDroppedItemId(int blockMeta, Random random, CallbackInfoReturnable<Integer> cir) {
        if (!UniTweaks.BUGFIXES_CONFIG.stairsDropFix) {
            return;
        }
        
        cir.setReturnValue(super.getDroppedItemId(blockMeta, random));
    }
    
    @Inject(method = "getDroppedItemCount", at = @At("HEAD"), cancellable = true)
    public void overrideDroppedItemCount(Random random, CallbackInfoReturnable<Integer> cir) {
        if (!UniTweaks.BUGFIXES_CONFIG.stairsDropFix) {
            return;
        }

        cir.setReturnValue(super.getDroppedItemCount(random));
    }
    
    @Inject(method = "dropStacks", at = @At("HEAD"), cancellable = true)
    public void overrideDropStacks(World world, int x, int y, int z, int meta, float f, CallbackInfo ci) {
        if (!UniTweaks.BUGFIXES_CONFIG.stairsDropFix) {
            return;
        }

        if (world.isRemote) {
            return;
        }
        
        super.dropStacks(world, x, y, z, meta, f);
        ci.cancel();
    }
}
