package net.danygames2014.unitweaks.mixin.tweaks.cloudheight;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @WrapOperation(method = "method_1552", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;method_1764()F"))
    public float changeCloudHeight(Dimension instance, Operation<Float> original) {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudHeightSlider){
            return ModOptions.getCloudHeight();
        }
        return original.call(instance);
    }

    @WrapOperation(method = "method_1556", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;method_1764()F"))
    public float ChangeFancyCloudHeight(Dimension instance, Operation<Float> original) {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudHeightSlider){
            return ModOptions.getCloudHeight();
        }
        return original.call(instance);
    }
}
