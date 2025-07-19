package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockItem.class)
public class BlockItemMixin extends Item {
    public BlockItemMixin(int id) {
        super(id);
    }
}
