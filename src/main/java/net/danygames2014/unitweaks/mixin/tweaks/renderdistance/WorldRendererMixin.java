package net.danygames2014.unitweaks.mixin.tweaks.renderdistance;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.input.Mouse;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private int lastViewDistance;

    @Shadow
    private int chunkCountX;

    @Shadow
    private int chunkCountZ;

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/client/option/GameOptions;viewDistance:I"
            )
    )
    public int fixRebuildCheck(GameOptions instance, Operation<Integer> original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider) {
            // This prevents the the rebuilding when the user is still dragging the slider
            if (!Mouse.isButtonDown(0)) {
                return ModOptions.getRenderDistanceChunks();
            }
            return this.lastViewDistance;
        } else {
            return original.call(instance);
        }
    }

    @Inject(
            method = "reload",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;lastViewDistance:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void injectNewRenderDistance(CallbackInfo ci) {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider) {
            this.lastViewDistance = ModOptions.getRenderDistanceChunks();
        }
    }

    @Inject(
            method = "reload",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;chunkCountX:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void overrideHorizontalRenderDistnace(CallbackInfo ci) {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider) {
            this.chunkCountX = (ModOptions.getRenderDistanceChunks() * 2) + 1;
        }
    }

    @Inject(
            method = "reload",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;chunkCountZ:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void overrideDeepRenderDistnace(CallbackInfo ci) {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider) {
            this.chunkCountZ = (ModOptions.getRenderDistanceChunks() * 2) + 1;
        }
    }
}
