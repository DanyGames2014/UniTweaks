package net.danygames2014.unitweaks.mixin.tweaks.fov;

import net.danygames2014.unitweaks.tweaks.fov.FovData;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.*;
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
    private static void addFovOption(CallbackInfo ci){
        Option FOV;
        ArrayList<Option> options = new ArrayList<Option>(Arrays.asList(field_1113));
        Option last = (Option) options.get(options.size() - 1);
        FovData.fovOption = FOV = OptionMixin.newOption("FOV", last.ordinal(), "option.fov", true, false);
        options.add(FOV);
        field_1113 = options.toArray(new Option[0]);
    }
}
