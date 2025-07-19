package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShovelItem.class)
public class ShovelItemMixin extends ToolItem {
    public ShovelItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
//            for (int i = 0; i < EffectiveBlocksLists.shovelEffectiveAgainst.length; i++) {
//                if (BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.shovelEffectiveAgainst[i])) == block) {
//                    return this.miningSpeed;
//                }
//            }

            if (EffectiveBlocksLists.shovelBlocks.contains(block)) {
                return this.miningSpeed;
            }
        }

        return super.getMiningSpeedMultiplier(stack, block);
    }
}
