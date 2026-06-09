package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.GrassBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrassBlock.class)
public class GrassBlockMixin extends BlockMixin {
    @Override
    public void injectGetTextureId(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        if (UniTweaks.BUGFIXES_CONFIG.grassBlockItemFix) {
            switch (side) {
                case 0 -> cir.setReturnValue(2);
                case 1 -> cir.setReturnValue(0);
                default -> cir.setReturnValue(3);
            }
        }
    }
}
