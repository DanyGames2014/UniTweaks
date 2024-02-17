package net.danygames2014.unitweaks.mixin.tweaks.renderdistance;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.WorldRenderer;
import org.lwjgl.input.Mouse;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private int field_1782;

    @Shadow
    private int field_1810;

    @Shadow
    private int field_1812;

    @Redirect(
            method = "method_1548",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETFIELD,
                    target = "Lnet/minecraft/client/option/GameOptions;viewDistance:I"
            )
    )
    public int fixRebuildCheck(GameOptions instance) {
        // This prevents the the rebuilding when the user is still dragging the slider
        if (!Mouse.isButtonDown(0)) {
            return ModOptions.getRenderDistanceChunks();
        }
        return this.field_1782;
    }

    @Inject(
            method = "method_1537",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;field_1782:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void injectNewRenderDistance(CallbackInfo ci) {
        this.field_1782 = ModOptions.getRenderDistanceChunks();
    }

    @Inject(
            method = "method_1537",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;field_1810:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void overrideHorizontalRenderDistnace(CallbackInfo ci) {
        this.field_1810 = (ModOptions.getRenderDistanceChunks() * 2) + 1;
    }

    @Inject(
            method = "method_1537",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/client/render/WorldRenderer;field_1812:I",
                    shift = At.Shift.AFTER,
                    ordinal = 0
            )
    )
    public void overrideDeepRenderDistnace(CallbackInfo ci) {
        this.field_1812 = (ModOptions.getRenderDistanceChunks() * 2) + 1;
    }
}
