package net.danygames2014.unitweaks.mixin;

import com.llamalad7.mixinextras.injector.WrapWithCondition;
import net.modificationstation.stationapi.api.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Registries.class)
public class RegistriesMixin {
    // This will no longer be required due to a calmilamsy W
    @WrapWithCondition(method = "bootstrap", at = @At(value = "INVOKE", target = "Lnet/modificationstation/stationapi/api/registry/Registries;freezeRegistries()V"), remap = false, require = 0)
    private static boolean fuckFreezing() {
        return false;
    }
}
