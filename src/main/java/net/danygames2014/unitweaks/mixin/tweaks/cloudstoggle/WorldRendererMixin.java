package net.danygames2014.unitweaks.mixin.tweaks.cloudstoggle;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "renderClouds", at = @At(value = "HEAD"), cancellable = true)
    public void toggleClouds(float par1, CallbackInfo ci) {
        if (!ModOptions.clouds && UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudsToggle) {
            GL11.glBlendFunc(770, 771);
            ci.cancel();
        }
    }
}
