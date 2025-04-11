package net.danygames2014.unitweaks.mixin.bugfixes.furnaceconsumebucketfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FuelRegistry.class)
public class FuelRegistryMixin {
    @WrapOperation(method = "getFuelTime", at = @At(value = "INVOKE", target = "Lit/unimi/dsi/fastutil/ints/Int2IntMap;getOrDefault(II)I"), remap = false)
    private static int miinneee(Int2IntMap instance, int key, int defaultValue, Operation<Integer> original) {
        try {
            return original.call(instance, key, defaultValue);
        } catch (Exception ignored) {

        }

        return defaultValue;
    }
}
