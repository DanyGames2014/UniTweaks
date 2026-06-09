package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolItem.class)
public class ToolItemMixin {
    @Shadow
    public float miningSpeed;

    @SuppressWarnings("CancellableInjectionUsage")
    @Inject(method = "getMiningSpeedMultiplier", at = @At(value = "HEAD"), cancellable = true)
    protected void unitweaks$getMiningSpeedMultiplier(ItemStack stack, Block block, CallbackInfoReturnable<Float> cir) {
        
    }
}
