package net.danygames2014.unitweaks.mixin.tweaks.frontviewthirdperson;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.frontviewthirdperson.FrontViewMode;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow private Minecraft client;

    @WrapOperation(method = "applyCameraTransform", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/LivingEntity;pitch:F", ordinal = 1))
    public float aVoid(LivingEntity instance, Operation<Float> original) {
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.DISABLED) {
            return original.call(instance);
        }
        
        if (ModOptions.frontView || (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.HIDE_HUD && (client.options.thirdPerson && client.options.hideHud))) {
            return original.call(instance) + 180F;
        }
        
        return original.call(instance);
    }

    @WrapOperation(method = "applyCameraTransform", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glRotatef(FFFF)V", ordinal = 6, remap = false))
    public void aVoid(float angle, float x, float y, float z, Operation<Void> original) {
        original.call(angle, x, y, z);
        if (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.DISABLED) {
            return;
        }

        if (ModOptions.frontView || (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.HIDE_HUD && (client.options.thirdPerson && client.options.hideHud))) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
        }
    }
}
