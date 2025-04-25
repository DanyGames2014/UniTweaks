package net.danygames2014.unitweaks.mixin.tweaks.stackablechests;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.ChestBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChestBlock.class)
public class ChestBlockMixin {
    @WrapOperation(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;shouldSuffocate(III)Z"))
    public boolean aBoolean(World world, int x, int y, int z, Operation<Boolean> original) {
        if (UniTweaks.TWEAKS_CONFIG.stackableChests) {
            if (world.getBlockState(x, y, z).getBlock() instanceof ChestBlock) {
                return false;
            }
        }
        return original.call(world, x, y, z);
    }
}
