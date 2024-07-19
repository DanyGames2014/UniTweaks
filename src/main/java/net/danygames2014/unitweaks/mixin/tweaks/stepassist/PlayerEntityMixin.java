package net.danygames2014.unitweaks.mixin.tweaks.stepassist;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {

    public PlayerEntityMixin(World world) {
        super(world);
    }

    //    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;method_1341(DDDFF)V"))
    @Inject(method = "tick", at = @At(value = "HEAD"))
    private void changeStepHeight(CallbackInfo ci) {
        this.stepHeight = UniTweaks.GAMEPLAY_CONFIG.stepAssist && !this.isSneaking() ? 1.0F : 0.5F;
    }
}
