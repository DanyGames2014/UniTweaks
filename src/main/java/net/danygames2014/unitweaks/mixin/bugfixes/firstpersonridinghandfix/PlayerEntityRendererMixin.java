package net.danygames2014.unitweaks.mixin.bugfixes.firstpersonridinghandfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {

    @Shadow
    private BipedEntityModel bipedModel;

    @Inject(
            method = "renderHand",
            at = @At("TAIL")
    )
    private void fixFirstPersonHand(CallbackInfo ci) {
        if (!UniTweaks.BUGFIXES_CONFIG.firstPersonRidingHandFix) return;
        bipedModel.riding = false; // Fixes MC-1349
    }
}
