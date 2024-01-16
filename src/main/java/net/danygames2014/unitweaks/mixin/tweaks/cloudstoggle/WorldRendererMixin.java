package net.danygames2014.unitweaks.mixin.tweaks.cloudstoggle;

import net.danygames2014.unitweaks.tweaks.cloudstoggle.CloudToggleData;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "method_1552", at = @At(value = "HEAD"), cancellable = true)
    public void toggleClouds(float par1, CallbackInfo ci){
        if(!CloudToggleData.cloudsValue){
            ci.cancel();
        }
    }
}
