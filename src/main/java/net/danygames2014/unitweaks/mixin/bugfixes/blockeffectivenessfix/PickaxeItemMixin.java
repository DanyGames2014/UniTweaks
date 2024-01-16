package net.danygames2014.unitweaks.mixin.bugfixes.blockeffectivenessfix;

import com.google.common.collect.ObjectArrays;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.EffectiveBlocksLists;
import net.minecraft.block.Block;
import net.minecraft.item.PickaxeItem;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PickaxeItem.class)
public class PickaxeItemMixin {
    @Shadow
    private static Block[] pickaxeEffectiveBlocks;

    @Inject(
            method = "<clinit>",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/item/PickaxeItem;pickaxeEffectiveBlocks:[Lnet/minecraft/block/Block;",
                    opcode = Opcodes.PUTSTATIC,
                    shift = At.Shift.AFTER
            )
    )
    private static void addEffectiveBlocks(CallbackInfo ci) {
        if (!UniTweaks.BUGFIXES_CONFIG.blockEffectivenessFix) {
            return;
        }

        Block[] blocks = new Block[EffectiveBlocksLists.pickaxeEffectiveAgainst.length];
        for (int i = 0; i < EffectiveBlocksLists.pickaxeEffectiveAgainst.length; i++) {
            blocks[i] = BlockRegistry.INSTANCE.get(Identifier.of(EffectiveBlocksLists.pickaxeEffectiveAgainst[i]));
        }

        pickaxeEffectiveBlocks = ObjectArrays.concat(pickaxeEffectiveBlocks, blocks, Block.class);
    }
}
