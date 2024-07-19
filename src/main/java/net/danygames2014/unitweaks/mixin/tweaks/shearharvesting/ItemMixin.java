package net.danygames2014.unitweaks.mixin.tweaks.shearharvesting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Shadow
    @Final
    public int id;

    @Inject(method = "getPlacementMetadata", at = @At(value = "HEAD"), cancellable = true)
    public void overridePlacedMeta(int meta, CallbackInfoReturnable<Integer> cir) {
        if (this.id == Block.GRASS.asItem().id) {
            cir.setReturnValue(1);
        }
    }
}
