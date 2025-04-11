package net.danygames2014.unitweaks.mixin.tweaks.cloudstoggle;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    private Minecraft client;

    @Inject(method = "renderClouds", at = @At(value = "HEAD"), cancellable = true)
    public void toggleClouds(float par1, CallbackInfo ci) {
        // If clouds are disabled, cancel their rendering
        if (!ModOptions.clouds && UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudsToggle) {
            // If hotbar rendering fix is enabled. apply the proper blend function
            if (UniTweaks.BUGFIXES_CONFIG.hotbarRenderingFix) {
                GL11.glBlendFunc(770, 771);
            }
            ci.cancel();
        }

        // Clouds are not disabled, apply hotbar rendering fix if enabled and running on Fast graphics
        if (UniTweaks.BUGFIXES_CONFIG.hotbarRenderingFix && !this.client.options.fancyGraphics) {
            GL11.glBlendFunc(770, 771);
        }
    }
}
