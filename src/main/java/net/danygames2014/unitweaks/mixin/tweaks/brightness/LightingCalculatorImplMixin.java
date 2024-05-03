package net.danygames2014.unitweaks.mixin.tweaks.brightness;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.block.Block;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.impl.client.arsenic.renderer.aocalc.LightingCalculatorImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LightingCalculatorImpl.class)
public class LightingCalculatorImplMixin {

    @WrapOperation(method = "light", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getLuminance(Lnet/minecraft/world/BlockView;III)F"))
    public float injectBrightness(Block instance, BlockView blockView, int x, int y, int z, Operation<Float> original){
        if(UniTweaks.GENERAL_CONFIG.brightnessSlider){
            return Math.max(original.call(instance, blockView, x, y, z), ModOptions.minimumBrightness);
        }
        return original.call(instance, blockView, x, y, z);
    }
}
