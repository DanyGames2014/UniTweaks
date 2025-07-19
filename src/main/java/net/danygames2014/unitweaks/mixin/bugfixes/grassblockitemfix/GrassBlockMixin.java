package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GrassBlock.class)
public class GrassBlockMixin extends Block {
    public GrassBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public int getTexture(int side) {
        if(UniTweaks.BUGFIXES_CONFIG.grassBlockItemFix){
            if (side == 1) {
                return 0;
            } else if (side == 0) {
                return 2;
            } else {
                return 3;
            }
        }
        return super.getTexture(side);
    }
}
