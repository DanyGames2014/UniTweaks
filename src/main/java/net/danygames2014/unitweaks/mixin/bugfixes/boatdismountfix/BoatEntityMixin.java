package net.danygames2014.unitweaks.mixin.bugfixes.boatdismountfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;setVehicle(Lnet/minecraft/entity/Entity;)V", shift = At.Shift.AFTER))
    public void fixPositionOnDismount(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.BUGFIXES_CONFIG.boatDismountFix) {
            if (player.passenger == null) {
                player.setPosition(player.x, player.y + 0.01, player.z);
            }
        }
    }

}
