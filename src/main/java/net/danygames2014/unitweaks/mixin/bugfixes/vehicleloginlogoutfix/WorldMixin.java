package net.danygames2014.unitweaks.mixin.bugfixes.vehicleloginlogoutfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.VehicleHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(World.class)
public class WorldMixin {

    @Shadow public boolean isRemote;

    @Shadow public List field_198;

    @Inject(
            method = "method_210",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;method_219(Lnet/minecraft/entity/Entity;)V"
            )
    )
    public void spawnEntity(Entity arg, CallbackInfoReturnable<Boolean> cir) {
        if (  (UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled)
           && (VehicleHelper.isVehicleSaved)
           && (!this.isRemote)
        ) {
            /** - Find saved vehicle if on single player */
            for (int entityIndex = 0; entityIndex < this.field_198.size(); entityIndex++) {
                Entity entityToCheck = (Entity) this.field_198.get(entityIndex);

                if (  (entityToCheck.getClass().equals(VehicleHelper.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - VehicleHelper.savedVehicleX))
                   && (1 > Math.abs(entityToCheck.y - VehicleHelper.savedVehicleY))
                   && (1 > Math.abs(entityToCheck.z - VehicleHelper.savedVehicleZ))
                ) {
                    PlayerEntity player = PlayerHelper.getPlayerFromGame();
                    if (null != player) {
                        player.method_1376(entityToCheck);
                    }
                    VehicleHelper.isVehicleSaved = false;
                }
            }
        }
    }
}
