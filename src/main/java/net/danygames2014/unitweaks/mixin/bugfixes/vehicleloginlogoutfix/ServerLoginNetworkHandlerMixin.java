package net.danygames2014.unitweaks.mixin.bugfixes.vehicleloginlogoutfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.SERVER)
@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandlerMixin {

    @Redirect(
            method = "accept",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ServerPlayerEntity;method_317()V"
            )
    )
    public void annoyanceFix_completeLoadVehicle(ServerPlayerEntity instance) {
        instance.method_317();

        if (UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled) {
            /** - Spawn saved vehicle if on multiplayer */
            String vehicleName = instance.vehicle_getVehicleName();
            if (!vehicleName.equals("null")) {
                NbtCompound vehicleTag = instance.vehicle_getVehicleTag();
                if (vehicleTag != null) {
                    Entity vehicle = EntityRegistry.create(vehicleName, instance.world);
                    if (null != vehicle) {
                        try {
                            vehicle.read(vehicleTag);
                        } catch(Exception ex) {
                            vehicle.method_1341(instance.x, instance.y, instance.z, instance.yaw, instance.pitch);
                            System.out.println("Failed to read vehicle data");
                        }
                        instance.world.method_210(vehicle);
                        instance.method_1376(vehicle);
                    }
                }
            }
        }
    }

}
