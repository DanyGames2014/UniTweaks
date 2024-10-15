package net.danygames2014.unitweaks.mixin.tweaks.quitbutton;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftApplet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftApplet.class)
public class MinecraftAppletMixin {
    @Shadow
    private Minecraft minecraft;

    @Inject(method = "init", at = @At(value = "TAIL"), remap = false)
    public void setIsAppletToFalse(CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.showQuitButton) {
            this.minecraft.isApplet = false;
        }
    }
}
