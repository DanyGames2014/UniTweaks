package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.client.resource.language.TranslationStorage;
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

    @Inject(method = "setFloat", at = @At(value = "HEAD"))
    public void setFloat(Option option, float value, CallbackInfo ci) {
        if (option == ModOptions.fovOption) {
            ModOptions.fov = value;
        }

        if (option == ModOptions.fogDensityOption) {
            ModOptions.fogDensity = value;
        }

        if (option == ModOptions.cloudHeightOption) {
            ModOptions.cloudHeight = value;
        }

        if (option == ModOptions.fpsLimitOption) {
            ModOptions.fpsLimit = value;
        }

        if (option == ModOptions.renderDistanceOption) {
            ModOptions.renderDistance = value;
        }

        if (option == ModOptions.brightnessOption){
            ModOptions.brightness = value;
            ModOptions.updateBrigthnessMultiplier();
        }
    }

    @Inject(method = "setInt", at = @At(value = "HEAD"))
    public void setBoolean(Option option, int value, CallbackInfo ci) {
        if (option == ModOptions.cloudsOption) {
            ModOptions.clouds = !ModOptions.clouds;
        }
    }


    @Inject(method = "getFloat", at = @At(value = "HEAD"), cancellable = true)
    public void getFloat(Option option, CallbackInfoReturnable<Float> cir) {
        if (option == ModOptions.fovOption) {
            cir.setReturnValue(ModOptions.fov);
        }

        if (option == ModOptions.fogDensityOption) {
            cir.setReturnValue(ModOptions.fogDensity);
        }

        if (option == ModOptions.cloudHeightOption) {
            cir.setReturnValue(ModOptions.cloudHeight);
        }

        if (option == ModOptions.fpsLimitOption) {
            cir.setReturnValue(ModOptions.fpsLimit);
        }

        if (option == ModOptions.renderDistanceOption) {
            cir.setReturnValue(ModOptions.renderDistance);
        }

        if (option == ModOptions.brightnessOption){
            cir.setReturnValue(ModOptions.brightness);
        }
    }

    @Inject(method = "getBoolean", at = @At(value = "HEAD"), cancellable = true)
    public void getBoolean(Option option, CallbackInfoReturnable<Boolean> cir) {
        if (option == ModOptions.cloudsOption) {
            cir.setReturnValue(ModOptions.clouds);
        }
    }


    @Inject(method = "method_1234", at = @At(value = "HEAD"), cancellable = true)
    public void getTranslatedValue(Option option, CallbackInfoReturnable<String> cir) {
        TranslationStorage translations = TranslationStorage.getInstance();

        if (option == ModOptions.fovOption) {
            float value = ModOptions.fov;
            if (value == 0.0f) {
                cir.setReturnValue("FOV: Normal");
            } else if (value == 1.0f) {
                cir.setReturnValue("FOV: Quake Pro");
            } else {
                cir.setReturnValue("FOV: " + ModOptions.getFovInDegrees());
            }
        }

        if (option == ModOptions.fogDensityOption) {
            float value = ModOptions.getFogDisplayValue();
            if (value == 0.0F) {
                cir.setReturnValue("Fog: Disabled");
            } else if (value == 1.0F) {
                cir.setReturnValue("Fog: Silent Hill");
            } else if (value == 0.5F) {
                cir.setReturnValue("Fog: Normal");
            } else {
                cir.setReturnValue("Fog: " + ModOptions.getFogDisplayValue() * 2F + "x");
            }
        }

        if (option == ModOptions.fpsLimitOption) {
            float value = ModOptions.getFpsLimitValue();
            if (value >= 300) {
                cir.setReturnValue(translations.get("options.unitweaks:fps_limit") + ": " + translations.get("options.unitweaks:fps_limit_max"));
            } else {
                cir.setReturnValue(translations.get("options.unitweaks:fps_limit") + ": " + value);
            }
        }

        if (option == ModOptions.cloudHeightOption) {
            String optionName = "Cloud Height: " + ModOptions.getCloudHeight();
            cir.setReturnValue(optionName);
        }

        if (option == ModOptions.cloudsOption) {
            String optionName = translations.get("options.unitweaks:clouds") + ": " + (ModOptions.clouds ? translations.get("options.on") : translations.get("options.off"));
            cir.setReturnValue(optionName);
        }

        if (option == ModOptions.renderDistanceOption) {
            String chunkValue;
            switch (ModOptions.getRenderDistanceChunks()) {
                case 2 -> chunkValue = "Tiny";
                case 4 -> chunkValue = "Short";
                case 8 -> chunkValue = "Normal";
                case 12 -> chunkValue = "Far";
                default -> chunkValue = ModOptions.getRenderDistanceChunks() + " " + translations.get("options.unitweaks:render_distance.chunks");
            }
            String optionName = translations.get("options.unitweaks:render_distance") + ": " + chunkValue;
            cir.setReturnValue(optionName);
        }
    }

    @Inject(method = "load", at = @At(value = "INVOKE", target = "Ljava/lang/String;split(Ljava/lang/String;)[Ljava/lang/String;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void load(CallbackInfo ci, BufferedReader bufferedReader, String string) {
        String[] stringArray = string.split(":");

        if (stringArray[0].equals("fov")) {
            ModOptions.fov = this.parseFloat(stringArray[1]);
        }

        if (stringArray[0].equals("fog_density")) {
            ModOptions.fogDensity = this.parseFloat(stringArray[1]);
        }

        if (stringArray[0].equals("clouds")) {
            ModOptions.clouds = stringArray[1].equals("true");
        }

        if (stringArray[0].equals("cloud_height")) {
            ModOptions.cloudHeight = this.parseFloat(stringArray[1]);
        }

        if (stringArray[0].equals("fps_limit")) {
            ModOptions.fpsLimit = this.parseFloat(stringArray[1]);
        }

        if (stringArray[0].equals("render_distance")) {
            ModOptions.renderDistance = this.parseFloat(stringArray[1]);
        }

        if(stringArray[0].equals("brightness")){
            ModOptions.brightness = this.parseFloat(stringArray[1]);
            ModOptions.updateBrigthnessMultiplier();
        }
    }

    @Inject(method = "save", at = @At(value = "INVOKE", target = "Ljava/io/PrintWriter;close()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void saveOptions(CallbackInfo ci, PrintWriter printWriter) {
        printWriter.println("fov:" + ModOptions.fov);
        printWriter.println("fog_density:" + ModOptions.fogDensity);
        printWriter.println("clouds:" + ModOptions.clouds);
        printWriter.println("cloud_height:" + ModOptions.cloudHeight);
        printWriter.println("fps_limit: " + ModOptions.fpsLimit);
        printWriter.println("render_distance: " + ModOptions.renderDistance);
        printWriter.println("brightness:" + ModOptions.brightness);
    }
}
