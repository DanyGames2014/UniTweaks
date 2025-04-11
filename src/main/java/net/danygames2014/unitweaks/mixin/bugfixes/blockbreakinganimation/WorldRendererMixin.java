package net.danygames2014.unitweaks.mixin.bugfixes.blockbreakinganimation;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(method = "renderMiningProgress", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Tessellator;translate(DDD)V", ordinal = 0))
    public void increaseBreakingAnimationZOffset(PlayerEntity hitResult, HitResult i, int itemStack, ItemStack f, float par5, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.breakingAnimationFix) {
            GL11.glScalef(0.95F, 0.95F, 0.95F);
        }
    }
}
