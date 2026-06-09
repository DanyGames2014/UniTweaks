package net.danygames2014.unitweaks.mixin.bugfixes.grassblockitemfix;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @SuppressWarnings("CancellableInjectionUsage")
    @Inject(method = "getTexture(II)I", at = @At("HEAD"), cancellable = true)
    public void injectGetTextureId(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        
    }
}
