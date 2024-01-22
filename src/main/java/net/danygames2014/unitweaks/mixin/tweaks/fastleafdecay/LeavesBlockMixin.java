package net.danygames2014.unitweaks.mixin.tweaks.fastleafdecay;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.class_307;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
    @Inject(method = "onTick", at = @At(value="INVOKE", target = "Lnet/minecraft/block/LeavesBlock;method_990(Lnet/minecraft/world/World;III)V"))
    private void accelerateLeafDecay(World world, int x, int y, int z, Random random, CallbackInfo ci) {
        if(UniTweaks.TWEAKS_CONFIG.fastLeafDecay.enableFastLeafDecay){
            if(!world.isRemote){
                for(int offsetX = -3; offsetX <= 3; offsetX++) {
                    for(int offsetY = -3; offsetY<=3; offsetY++) {
                        for(int offsetZ = -3; offsetZ<=3; offsetZ++) {
                            world.method_216(x+offsetX, y+offsetY, z+offsetZ, ((class_307)(Object)this).id, random.nextInt(UniTweaks.TWEAKS_CONFIG.fastLeafDecay.minimumDecayTime, UniTweaks.TWEAKS_CONFIG.fastLeafDecay.maximumDecayTime));
                        }
                    }
                }
            }
        }
    }
}
