package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AxeItem.class)
public class AxeItemMixin extends ToolItem {
//    @Shadow
//    private static Block[] axeEffectiveBlocks;

    public AxeItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

//    @Inject(
//            method = "<clinit>",
//            at = @At(
//                    value = "FIELD",
//                    target = "Lnet/minecraft/item/AxeItem;axeEffectiveBlocks:[Lnet/minecraft/block/Block;",
//                    opcode = Opcodes.PUTSTATIC,
//                    shift = At.Shift.AFTER
//            )
//    )
//    private static void addEffectiveBlocks(CallbackInfo ci) {
//        if (!UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
//            return;
//        }
//
//        Block[] blocks = new Block[EffectiveBlocksLists.axeEffectiveAgainst.length];
//        for (int i = 0; i < EffectiveBlocksLists.axeEffectiveAgainst.length; i++) {
//            blocks[i] = BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.axeEffectiveAgainst[i]));
//        }
//
//        axeEffectiveBlocks = ObjectArrays.concat(axeEffectiveBlocks, blocks, Block.class);
//    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
            for (int i = 0; i < EffectiveBlocksLists.axeEffectiveAgainst.length; i++) {
                if (BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.axeEffectiveAgainst[i])) == block) {
                    return this.miningSpeed;
                }
            }
        }

        return super.getMiningSpeedMultiplier(stack, block);
    }
}
