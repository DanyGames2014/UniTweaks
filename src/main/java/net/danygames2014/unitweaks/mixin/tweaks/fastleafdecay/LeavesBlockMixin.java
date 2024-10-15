package net.danygames2014.unitweaks.mixin.tweaks.fastleafdecay;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(method = "onTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LeavesBlock;breakLeaves(Lnet/minecraft/world/World;III)V"))
    private void accelerateLeafDecay(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        if (UniTweaks.FEATURES_CONFIG.fastLeafDecay.enableFastLeafDecay) {
            if (!world.isRemote) {
                for (int offsetX = -3; offsetX <= 3; offsetX++) {
                    for (int offsetY = -3; offsetY <= 3; offsetY++) {
                        for (int offsetZ = -3; offsetZ <= 3; offsetZ++) {
                            world.scheduleBlockUpdate(x + offsetX, y + offsetY, z + offsetZ, ((TransparentBlock) (Object) this).id, random.nextInt(UniTweaks.FEATURES_CONFIG.fastLeafDecay.minimumDecayTime, UniTweaks.FEATURES_CONFIG.fastLeafDecay.maximumDecayTime));
                        }
                    }
                }
            }
        }
    }
}
