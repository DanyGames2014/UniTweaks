package net.danygames2014.unitweaks.mixin.tweaks.fogdensity;

import net.danygames2014.unitweaks.tweaks.fogdensity.FogDensityData;
import net.danygames2014.unitweaks.tweaks.fov.FovData;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Option.class)
public abstract class OptionMixin {
    @Shadow
    @Final
    @Mutable
    private static Option[] field_1113;

    @Invoker(value="<init>")
    private static Option newOption(String internalName, int internalId, String translationKey, boolean slider, boolean toggle) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = 179, target = "Lnet/minecraft/client/option/Option;field_1113:[Lnet/minecraft/client/option/Option;", shift = At.Shift.AFTER))
    private static void addFogDensityOption(CallbackInfo ci){
        Option FOG_DENSITY;
        ArrayList<Option> options = new ArrayList<Option>(Arrays.asList(field_1113));
        Option last = options.get(options.size() - 1);
        FogDensityData.fogDensityOption = FOG_DENSITY = OptionMixin.newOption("FOG_DENSITY", last.ordinal(), "option.fog_density", true, false);
        options.add(FOG_DENSITY);
        field_1113 = options.toArray(new Option[0]);
    }
}
