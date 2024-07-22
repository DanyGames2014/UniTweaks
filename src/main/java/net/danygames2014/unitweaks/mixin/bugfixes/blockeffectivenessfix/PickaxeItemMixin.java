package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin extends ToolItem {
//    @Shadow
//    private static Block[] pickaxeEffectiveBlocks;

    public PickaxeItemMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

//    @Inject(
//            method = "<clinit>",
//            at = @At(
//                    value = "FIELD",
//                    target = "Lnet/minecraft/item/PickaxeItem;pickaxeEffectiveBlocks:[Lnet/minecraft/block/Block;",
//                    opcode = Opcodes.PUTSTATIC,
//                    shift = At.Shift.AFTER
//            )
//    )
//    private static void addEffectiveBlocks(CallbackInfo ci) {
//        if (!UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
//            return;
//        }
//
//        Block[] blocks = new Block[EffectiveBlocksLists.pickaxeEffectiveAgainst.length];
//        for (int i = 0; i < EffectiveBlocksLists.pickaxeEffectiveAgainst.length; i++) {
//            blocks[i] = BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.pickaxeEffectiveAgainst[i]));
//        }
//
//        pickaxeEffectiveBlocks = ObjectArrays.concat(pickaxeEffectiveBlocks, blocks, Block.class);
//    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, Block block) {
        if (UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
            for (int i = 0; i < EffectiveBlocksLists.pickaxeEffectiveAgainst.length; i++) {
                if (BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.pickaxeEffectiveAgainst[i])) == block) {
                    return this.miningSpeed;
                }
            }
        }

        return super.getMiningSpeedMultiplier(stack, block);
    }
}
