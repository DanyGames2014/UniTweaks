package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin extends ToolItemMixin {
    @Override
    protected void unitweaks$getMiningSpeedMultiplier(ItemStack stack, Block block, CallbackInfoReturnable<Float> cir) {
        if (UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
            if (EffectiveBlocksLists.axeBlocks.contains(block)) {
                cir.setReturnValue(this.miningSpeed);
            }
        }
    }
}
