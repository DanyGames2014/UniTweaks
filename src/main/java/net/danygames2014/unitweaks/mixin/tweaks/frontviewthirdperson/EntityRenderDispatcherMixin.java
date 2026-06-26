package net.danygames2014.unitweaks.mixin.tweaks.frontviewthirdperson;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.frontviewthirdperson.FrontViewMode;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    @Shadow
    public GameOptions options;

    @Shadow
    public float yaw;

    @Shadow
    public float pitch;

    @Unique
    public boolean isFrontViewEnabled() {
        if (Minecraft.INSTANCE.options == null) {
            return false;
        }
        
        return ModOptions.frontView || (UniTweaks.USER_INTERFACE_CONFIG.frontViewThirdPerson == FrontViewMode.HIDE_HUD && (Minecraft.INSTANCE.options.thirdPerson && Minecraft.INSTANCE.options.hideHud));
    }
    
    @Inject(method = "init", at = @At(value = "TAIL"))
    private void adjustAngles(World world, TextureManager textureManager, TextRenderer textRenderer, LivingEntity livingEntity, GameOptions gameOptions, float scale, CallbackInfo ci) {
        if (this.options != null && isFrontViewEnabled()) {
            this.yaw += 180.0F;
            this.pitch = -this.pitch;
        }
    }
}
