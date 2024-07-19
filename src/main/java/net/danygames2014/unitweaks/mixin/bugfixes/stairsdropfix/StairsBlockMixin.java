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

@Mixin(StairsBlock.class)
public class StairsBlockMixin extends Block {
    public StairsBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "dropStacks", at = @At("HEAD"), cancellable = true)
    public void dropStacks(World world, int x, int y, int z, int meta, float f, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.stairsDropFix) {
            if (!world.isRemote) {
                int droppedItemCount = this.getDroppedItemCount(world.random);

                for (int i = 0; i < droppedItemCount; ++i) {
                    this.dropStack(world, x, y, z, new ItemStack(id, 1, this.getDroppedItemMeta(meta)));
                }
                ci.cancel();
            }
        }
    }
}
