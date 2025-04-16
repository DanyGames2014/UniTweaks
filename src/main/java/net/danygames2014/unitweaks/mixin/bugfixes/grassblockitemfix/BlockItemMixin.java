package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import net.minecraft.block.Block;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockItem.class)
public class BlockItemMixin extends Item {
    public BlockItemMixin(int id) {
        super(id);
    }

    @Override
    public int getColorMultiplier(int color) {
        if (this.id == Block.GRASS.id || this.id == Block.GRASS_BLOCK.id) {
            return GrassColors.getColor(0.5F, 1.0F);
        }
        return super.getColorMultiplier(color);
    }
}
