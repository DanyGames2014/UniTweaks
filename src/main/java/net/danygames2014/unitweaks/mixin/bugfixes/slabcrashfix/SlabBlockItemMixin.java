package net.danygames2014.unitweaks.mixin.bugfixes.slabcrashfix;

import net.minecraft.block.SlabBlock;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.item.SlabBlockItem.class)
public class SlabBlockItemMixin {

    @Inject(method = "getTranslationKey", at = @At("HEAD"), cancellable = true)
    public void preventCrash(ItemStack stack, CallbackInfoReturnable<String> cir){
        if(stack.getDamage() >= SlabBlock.field_2323.length){
            cir.setReturnValue(null);
        }
    }
}
