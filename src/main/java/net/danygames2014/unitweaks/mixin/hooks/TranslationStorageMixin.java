package net.danygames2014.unitweaks.mixin.hooks;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.resource.language.TranslationStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.Properties;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @Shadow
    private Properties translations;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/Properties;load(Ljava/io/InputStream;)V", ordinal = 1, shift = At.Shift.AFTER))
    public void loadModMenuTranslations(CallbackInfo ci) {
        if (!FabricLoader.getInstance().isModLoaded("stationapi")) {
            try {
                translations.load(TranslationStorage.class.getResourceAsStream("/assets/unitweaks/stationapi/lang/en_US.lang"));
            } catch (IOException e) {
                System.err.println("Failed to load UniTweaks translations:");
            }
        }
    }
}
