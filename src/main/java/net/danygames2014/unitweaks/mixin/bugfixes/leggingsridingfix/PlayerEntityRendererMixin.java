package net.danygames2014.unitweaks.mixin.bugfixes.leggingsridingfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin extends LivingEntityRenderer {
    @Shadow
    private BipedEntityModel field_296;

    public PlayerEntityRendererMixin(EntityModel arg, float f) {
        super(arg, f);
    }

    @Inject(method = "render(Lnet/minecraft/entity/player/PlayerEntity;DDDFF)V", at = @At("HEAD"))
    public void fixLeggings(PlayerEntity player, double e, double f, double g, float h, float par6, CallbackInfo ci) {
        if (UniTweaks.BUGFIXES_CONFIG.leggingsWhenRidingFix) {
            ItemStack stack = player.inventory.armor[1];
            if (stack != null) {
                if (stack.getItem() instanceof ArmorItem) {
                    this.field_296.riding = this.model.riding;
                }
            }
        }
    }
}
