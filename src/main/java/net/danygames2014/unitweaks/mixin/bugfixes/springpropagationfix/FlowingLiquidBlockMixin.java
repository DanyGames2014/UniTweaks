package net.danygames2014.unitweaks.mixin.bugfixes.springpropagationfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.FlowingLiquidBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = FlowingLiquidBlock.class, priority = 900)
public class FlowingLiquidBlockMixin {
    @WrapOperation(
            method = "onTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockMeta(III)I"
            ),
            require = 0
    )
    private int allowWaterSpringPropagation(World world, int x, int y, int z, Operation<Integer> original) {
        if (UniTweaks.BUGFIXES_CONFIG.springPropagationFix) {
            return world.getBlockMeta(x, y - 1, z);
        } else {
            return original.call(world, x, y, z);
        }
    }
}
