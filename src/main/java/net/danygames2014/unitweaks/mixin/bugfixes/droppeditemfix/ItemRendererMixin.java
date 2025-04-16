package net.danygames2014.unitweaks.mixin.bugfixes.droppeditemfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = ItemRenderer.class, priority = 1100)
public class ItemRendererMixin {
    @ModifyConstant(method = "render(Lnet/minecraft/entity/ItemEntity;DDDFF)V", constant = @Constant(floatValue = 0.5F, ordinal = 0), require = 0)
    float fixDroppedItemSize(float constant) {
        if (UniTweaks.BUGFIXES_CONFIG.droppedItemSizeFix) {
            return 0.25F;
        }
        return constant;
    }
}
