package net.danygames2014.unitweaks.mixin.bugfixes.bowheldfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.UndeadEntityRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(UndeadEntityRenderer.class)
public abstract class SkeletonEntityRendererMixin extends EntityRenderer {
    @Inject(method = "renderMore", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;)V", shift = At.Shift.BEFORE))
    public void changeBowRendering(LivingEntity entity, float f, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.bowHeldFix) {
            ItemStack itemStack = entity.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof BowItem) {
                GL11.glRotatef(-5, 1, 0, 0);
                GL11.glTranslatef(0.2F, -0.5F, 0.2F);
            }
        }
    }
}
