package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;

@Mixin(SoundManager.class)
public class SoundManagerMixin {

    @Inject(method = "loadSound", at = @At(value = "HEAD"))
    public void soundLoaded(String file, File par2, CallbackInfo ci){
        System.out.println(file + " | " + par2);
    }
}
