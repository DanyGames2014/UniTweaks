package net.danygames2014.unitweaks.mixin.tweaks.fogdensity;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.class_555;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_555.class)
public abstract class class555Mixin {
    @Shadow
    private float field_2350;

    @Inject(method = "method_1842", at = @At(value = "HEAD"))
    public void overrideFogDensity(int f, float par2, CallbackInfo ci) {
//        this.field_2350 = field_2350 * ModOptions.getFogMultiplier();
//        System.out.println(this.field_2350);
    }
}
