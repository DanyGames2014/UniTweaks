package net.danygames2014.unitweaks.mixin.bugfixes.jitterfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.mine_diver.smoothbeta.client.render.RenderRegion;
import net.mine_diver.smoothbeta.client.render.SmoothWorldRenderer;
import net.mine_diver.smoothbeta.client.render.gl.GlUniform;
import net.mine_diver.smoothbeta.client.render.gl.VertexBuffer;
import net.mine_diver.smoothbeta.mixin.client.multidraw.ChunkRendererAccessor;
import net.minecraft.client.render.world.ChunkRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RenderRegion.class)
public abstract class RenderRegionMixin extends ChunkRenderer {
    @Shadow(remap = false) @Final private ChunkRendererAccessor _super;

    @Shadow @Final private List<VertexBuffer> buffers;
    @Shadow @Final private SmoothWorldRenderer stationWorldRenderer;
    @Unique
    private double fixedOffsetX;
    @Unique
    private double fixedOffsetY;
    @Unique
    private double fixedOffsetZ;

    @Inject(method = "init", at = @At(value = "HEAD"), remap = false)
    public void storeCorrectOffsets(int x, int y, int z, double offsetX, double offsetY, double offsetZ, CallbackInfo ci){
        this.fixedOffsetX = offsetX;
        this.fixedOffsetY = offsetY;
        this.fixedOffsetZ = offsetZ;
    }

    @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/mine_diver/smoothbeta/client/render/gl/GlUniform;set(FFF)V", remap = false))
    public void useDoubleOffsets(GlUniform instance, float x, float y, float z, Operation<Void> original, @Local GlUniform chunkOffset) {
        if (UniTweaks.BUGFIXES_CONFIG.farLandsJitterFix) {
            setChunkOffset(chunkOffset);
        } else {
            original.call(instance,x,y,z);
        }
    }

    @Unique
    public void setChunkOffset(GlUniform chunkOffset) {
        chunkOffset.set((float) (_super.smoothbeta_getX()  - this.fixedOffsetX), (float) (_super.smoothbeta_getY() - this.fixedOffsetY), (float) (_super.smoothbeta_getZ() - this.fixedOffsetZ));
    }
}
