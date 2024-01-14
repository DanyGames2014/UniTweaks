package net.danygames2014.unitweaks.mixin.tweaks.fogdensity;

import net.danygames2014.unitweaks.tweaks.fogdensity.FogDensityData;
import net.minecraft.class_555;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_555.class)
public class class555Mixin {
    @Shadow private float field_2350;

    @Shadow private Minecraft field_2349;

    @Inject(method = "method_1842", at = @At(value = "HEAD"))
    public void overriceFogDensity(int f, float par2, CallbackInfo ci){
        this.field_2350 = (256 >> this.field_2349.options.viewDistance) * FogDensityData.getFogMultiplier();
    }
}
