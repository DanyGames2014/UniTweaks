package net.danygames2014.unitweaks.mixin.tweaks.sugarcaneonsand;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @WrapOperation(
            method = "canPlaceAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockId(III)I"
            )
    )
    public int canPlaceAt(World world, int x, int y, int z, Operation<Integer> original) {
        if (UniTweaks.TWEAKS_CONFIG.sugarCaneOnSand) {
            if (world.getBlockId(x, y, z) == Block.SAND.id) {
                return Block.DIRT.id;
            }
        }
        return original.call(world, x, y, z);
    }
}
