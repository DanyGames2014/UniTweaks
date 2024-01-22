package net.danygames2014.unitweaks.mixin.tweaks.cloudheight;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.dimension.Dimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Redirect(method = "method_1552", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;method_1764()F"))
    public float changeCloudHeight(Dimension dimension){
        return ModOptions.getCloudHeight();
    }

    @Redirect(method = "method_1556", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/dimension/Dimension;method_1764()F"))
    public float ChangeFancyCloudHeight(Dimension dimension){
        return ModOptions.getCloudHeight();
    }
}
