package net.danygames2014.unitweaks.mixin.bugfixes.bowheldfix;

import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @SuppressWarnings("CancellableInjectionUsage")
    @Inject(method = "isHandheld", at = @At(value = "HEAD"), cancellable = true)
    protected void unitweaks$isHandheld(CallbackInfoReturnable<Boolean> cir) {
        
    }
}
