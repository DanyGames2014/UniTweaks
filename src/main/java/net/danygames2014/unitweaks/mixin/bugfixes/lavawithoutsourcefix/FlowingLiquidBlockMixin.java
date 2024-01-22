package net.danygames2014.unitweaks.mixin.bugfixes.lavawithoutsourcefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.FlowingLiquidBlock;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.Material;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FlowingLiquidBlock.class)
public class FlowingLiquidBlockMixin extends LiquidBlock {
    public FlowingLiquidBlockMixin(int i, Material arg) {
        super(i, arg);
    }

    @Redirect(
            method = "onTick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/block/FlowingLiquidBlock;material:Lnet/minecraft/block/Material;",
                    opcode = Opcodes.GETFIELD,
                    ordinal = 3
            )
    )
    private Material allowLavaToDissapear(FlowingLiquidBlock block) {
        if (UniTweaks.BUGFIXES_CONFIG.lavaWithoutSourceFix) {
            return Material.WATER;
        } else {
            return block.material;
        }
    }
}
