package net.danygames2014.unitweaks.mixin.tweaks.fov;

import net.danygames2014.unitweaks.tweaks.fov.FovData;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.BufferedReader;
import java.io.PrintWriter;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow protected abstract float parseFloat(String string);

    @Inject(method = {"setFloat"}, at = {@At(value = "HEAD")})
    public void setFloat(Option option, float value, CallbackInfo ci) {
        if (option == FovData.fovOption) {
            FovData.fovValue = value;
        }
    }

    @Inject(method = {"getFloat"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void getFloat(Option option, CallbackInfoReturnable<Float> cir) {
        if (option == FovData.fovOption) {
            cir.setReturnValue(FovData.fovValue);
        }
    }

    @Inject(method = {"method_1234"}, at = {@At(value = "HEAD")}, cancellable = true)
    public void getTranslatedValue(Option option, CallbackInfoReturnable<String> cir) {
        if (option == FovData.fovOption) {
            float value = FovData.fovValue;
            if (value == 0.0f) {
                cir.setReturnValue("FOV: Normal");
            } else if (value == 1.0f) {
                cir.setReturnValue("FOV: Quake Pro");
            } else {
                cir.setReturnValue("FOV: " + FovData.getRealFov());
            }
        }
    }

    @Inject(method={"load"}, at={@At(value="INVOKE", target="Ljava/lang/String;split(Ljava/lang/String;)[Ljava/lang/String;")}, locals=LocalCapture.CAPTURE_FAILHARD)
    private void load(CallbackInfo ci, BufferedReader bufferedReader, String string) {
        String[] stringArray = string.split(":");
        if (stringArray[0].equals("fov")) {
            FovData.fovValue = this.parseFloat(stringArray[1]);
        }
    }

    @Inject(method={"save"}, at={@At(value="INVOKE", target="Ljava/io/PrintWriter;close()V")}, locals= LocalCapture.CAPTURE_FAILHARD)
    private void saveOptions(CallbackInfo ci, PrintWriter printWriter) {
        printWriter.println("fov:" + FovData.fovValue);
    }

}
