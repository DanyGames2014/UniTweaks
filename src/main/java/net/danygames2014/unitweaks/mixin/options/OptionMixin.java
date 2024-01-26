package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.util.ModOptions;
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

    @Invoker(value = "<init>")
    private static Option newOption(String internalName, int internalId, String translationKey, boolean slider, boolean toggle) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", opcode = 179, target = "Lnet/minecraft/client/option/Option;field_1113:[Lnet/minecraft/client/option/Option;", shift = At.Shift.AFTER))
    private static void addOptions(CallbackInfo ci) {
        ArrayList<Option> options = new ArrayList<Option>(Arrays.asList(field_1113));
        Option last = options.get(options.size() - 1);

        Option FOV;
        ModOptions.fovOption = FOV = OptionMixin.newOption("FOV", last.ordinal(), "options.fov", true, false);
        options.add(FOV);

        Option FOG_DENSITY;
        ModOptions.fogDensityOption = FOG_DENSITY = OptionMixin.newOption("FOG_DENSITY", last.ordinal() + 1, "options.fog_density", true, false);
        options.add(FOG_DENSITY);

        Option CLOUDS;
        ModOptions.cloudsOption = CLOUDS = OptionMixin.newOption("CLOUDS", last.ordinal() + 2, "options.clouds", false, true);
        options.add(CLOUDS);

        Option CLOUD_HEIGHT;
        ModOptions.cloudHeightOption = CLOUD_HEIGHT = OptionMixin.newOption("CLOUD_HEIGHT", last.ordinal() + 3, "options.cloud_height", true, false);
        options.add(CLOUD_HEIGHT);

        field_1113 = options.toArray(new Option[0]);
    }
}
