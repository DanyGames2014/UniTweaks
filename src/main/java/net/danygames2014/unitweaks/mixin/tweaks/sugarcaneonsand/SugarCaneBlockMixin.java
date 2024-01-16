package net.danygames2014.unitweaks.mixin.tweaks.sugarcaneonsand;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SugarCaneBlock.class)
public class SugarCaneBlockMixin {
    @Redirect(
            method = "canPlaceAt",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockId(III)I"
            )
    )
    public int canPlaceAt(World world, int x, int y, int z) {
        if (UniTweaks.BACKPORT_CONFIG.sugarCaneOnSand) {
            if (world.getBlockId(x, y, z) == Block.SAND.id) {
                return Block.DIRT.id;
            }
        }
        return world.getBlockId(x, y, z);
    }
}
