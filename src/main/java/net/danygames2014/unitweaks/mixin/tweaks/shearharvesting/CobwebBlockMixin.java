package net.danygames2014.unitweaks.mixin.tweaks.shearharvesting;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.CobwebBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(CobwebBlock.class)
public class CobwebBlockMixin extends Block {
    public CobwebBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        if (UniTweaks.TWEAKS_CONFIG.shearHarvesting) {
            return 0;
        }
        return super.getDroppedItemCount(random);
    }
}
