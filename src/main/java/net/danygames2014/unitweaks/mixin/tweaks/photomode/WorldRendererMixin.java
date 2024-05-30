package net.danygames2014.unitweaks.mixin.tweaks.photomode;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.tweaks.photomode.PhotoModeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    private Minecraft client;

    @ModifyExpressionValue(method = "method_1544", at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/GameOptions;thirdPerson:Z"))
    public boolean renderPlayerInPhotoMode(boolean original) {
        return original || this.client.currentScreen instanceof PhotoModeScreen;
    }

    @Inject(method = "method_1552", at = @At(value = "HEAD"), cancellable = true)
    public void disableCloudsInPhotoMode(float par1, CallbackInfo ci) {
        if (this.client.currentScreen instanceof PhotoModeScreen) {
            ci.cancel();
        }
    }
}
