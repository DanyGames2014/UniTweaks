package net.danygames2014.unitweaks.mixin.bugfixes.jitterfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.world.ChunkRenderer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkRenderer.class)
public class ChunkRendererMixin {
    @Shadow
    private int x;
    @Shadow
    private int y;
    @Shadow
    private int z;

    @Unique
    private double fixedOffsetX;
    @Unique
    private double fixedOffsetY;
    @Unique
    private double fixedOffsetZ;

    @Inject(method = "init", at = @At(value = "HEAD"))
    public void storeCorrectOffsets(int x, int y, int z, double offsetX, double offsetY, double offsetZ, CallbackInfo ci) {
        this.fixedOffsetX = offsetX;
        this.fixedOffsetY = offsetY;
        this.fixedOffsetZ = offsetZ;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V", remap = false))
    public void useDoubleOffsets(float x, float y, float z, Operation<Void> original) {
        if (UniTweaks.BUGFIXES_CONFIG.farLandsJitterFix) {
            GL11.glTranslated(this.x - fixedOffsetX, this.y - fixedOffsetY, this.z - fixedOffsetZ);
        } else {
            original.call(x, y, z);
        }
    }
}
