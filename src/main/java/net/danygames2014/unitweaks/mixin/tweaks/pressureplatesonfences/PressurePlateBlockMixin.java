package net.danygames2014.unitweaks.mixin.tweaks.pressureplatesonfences;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PressurePlateBlock.class)
public class PressurePlateBlockMixin {
    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    public void allowPlacementOnFences(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if(UniTweaks.TWEAKS_CONFIG.pressurePlatesOnFences){
            if (world.getBlockId(x, y - 1, z) == Block.FENCE.id) {
                cir.setReturnValue(true);
            }
        }
    }

    @Redirect(method = "neighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;method_1780(III)Z"))
    public boolean preventBreakingOnUpdate(World world, int x, int y, int z) {
        if(UniTweaks.TWEAKS_CONFIG.pressurePlatesOnFences){
            if (world.getBlockId(x, y, z) == Block.FENCE.id) {
                return true;
            } else {
                return world.method_1780(x, y, z);
            }
        }
        return world.method_1780(x, y, z);
    }
}
