package net.danygames2014.unitweaks.mixin.bugfixes.droppeditemfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.modificationstation.stationapi.impl.client.arsenic.renderer.render.ArsenicItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ArsenicItemRenderer.class, priority = 900)
public class ArsenicItemRendererMixin {
    @ModifyConstant(method = "renderVanilla", constant = @Constant(floatValue = 0.5F, ordinal = 0), require = 0)
    float fixDroppedItemSize(float constant) {
        if(UniTweaks.BUGFIXES_CONFIG.droppedItemSizeFix){
            return 0.25F;
        }
        return constant;
    }
}
