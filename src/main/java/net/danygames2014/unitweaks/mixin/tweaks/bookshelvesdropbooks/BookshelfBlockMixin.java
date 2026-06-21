package net.danygames2014.unitweaks.mixin.tweaks.bookshelvesdropbooks;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BookshelfBlock.class)
public class BookshelfBlockMixin extends Block {
    public BookshelfBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "getDroppedItemCount", at = @At(value = "HEAD"), cancellable = true)
    public void changeDropItemCount(Random random, CallbackInfoReturnable<Integer> cir) {
        if (UniTweaks.TWEAKS_CONFIG.bookshelvesDropBooks) {
            cir.setReturnValue(3);
        }
    }

    // TODO: Convert to mixin inheritance and add the master toggle from config like method above
    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return Item.BOOK.id;
    }
}
