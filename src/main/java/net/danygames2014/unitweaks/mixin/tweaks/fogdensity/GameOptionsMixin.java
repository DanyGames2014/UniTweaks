package net.danygames2014.unitweaks.mixin.tweaks.fogdensity;

import net.danygames2014.unitweaks.tweaks.fogdensity.FogDensityData;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.BufferedReader;
import java.io.PrintWriter;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow
    protected abstract float parseFloat(String string);

    @Inject(method = {"setFloat"}, at = {@At(value = "HEAD")})
    public void setFloat(Option option, float value, CallbackInfo ci) {
        if (option == FogDensityData.fogDensityOption) {
            FogDensityData.fogDensityValue = value;
        }
    }

    @Inject(method = {"getFloat"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void getFloat(Option option, CallbackInfoReturnable<Float> cir) {
        if (option == FogDensityData.fogDensityOption) {
            cir.setReturnValue(FogDensityData.fogDensityValue);
        }
    }

    @Inject(method = {"method_1234"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void getTranslatedValue(Option option, CallbackInfoReturnable<String> cir) {
        if (option == FogDensityData.fogDensityOption) {
            float value = FogDensityData.getDisplayValue();
            if (value == 0.0F) {
                cir.setReturnValue("Fog : Disabled");
            } else if (value == 1.0F) {
                cir.setReturnValue("Fog : Silent Hill");
            } else if (value == 0.5F) {
                cir.setReturnValue("Fog : Normal");
            } else {
                cir.setReturnValue("Fog: " + FogDensityData.getDisplayValue() * 2F + "x");
            }
        }
    }

    @Inject(method = {"load"}, at = {@At(value = "INVOKE", target = "Ljava/lang/String;split(Ljava/lang/String;)[Ljava/lang/String;")}, locals = LocalCapture.CAPTURE_FAILHARD)
    private void load(CallbackInfo ci, BufferedReader bufferedReader, String string) {
        String[] stringArray = string.split(":");
        if (stringArray[0].equals("fog_density")) {
            FogDensityData.fogDensityValue = this.parseFloat(stringArray[1]);
        }
    }

    @Inject(method = {"save"}, at = {@At(value = "INVOKE", target = "Ljava/io/PrintWriter;close()V")}, locals = LocalCapture.CAPTURE_FAILHARD)
    private void saveOptions(CallbackInfo ci, PrintWriter printWriter) {
        printWriter.println("fog_density:" + FogDensityData.fogDensityValue);
    }
}
