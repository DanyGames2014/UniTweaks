package net.danygames2014.unitweaks.mixin.tweaks.disablef3entityids;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(method = "method_821", at = @At("HEAD"), cancellable = true)
    public void disableDebugEntityIds(LivingEntity entity, double x, double y, double z, CallbackInfo ci){
        if(UniTweaks.GENERAL_CONFIG.disableDebugEntityIdTags){
            ci.cancel();
        }
    }
}
