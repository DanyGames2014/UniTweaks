package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends ToolItem {
    public PickaxeItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
            if (EffectiveBlocksLists.pickaxeBlocks.contains(block)) {
                return this.miningSpeed;
            }
        }

        return super.getMiningSpeedMultiplier(stack, block);
    }
}
