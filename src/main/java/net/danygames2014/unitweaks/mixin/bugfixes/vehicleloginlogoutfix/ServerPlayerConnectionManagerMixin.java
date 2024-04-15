package net.danygames2014.unitweaks.mixin.bugfixes.vehicleloginlogoutfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_166;
import net.minecraft.class_168;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_166.class)
public abstract class ServerPlayerConnectionManagerMixin {

    @Redirect(
            method = "method_575",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/class_168;method_609(Lnet/minecraft/entity/player/PlayerEntity;)V"
            )
    )
    public void updateDimension(class_168 instance, PlayerEntity playerBase) {
        instance.method_609(playerBase);

        if (UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled) {
            /** - Remove vehicle on logout */
            if (null != playerBase.field_1595) {
                playerBase.world.method_231(playerBase.field_1595);
            }
        }
    }
}
