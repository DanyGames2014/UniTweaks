package net.danygames2014.unitweaks.mixin.bugfixes.vehicleloginlogoutfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.VehicleHelper;
import net.danygames2014.unitweaks.util.VehicleInterface;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerBaseMixin extends LivingEntity implements VehicleInterface {

    @Unique
    private static String NULL_AS_STRING = "null";

    @Unique
    public String _vehicleName = NULL_AS_STRING;

    @Unique
    public NbtCompound _vehicleTag = new NbtCompound();

    public PlayerBaseMixin(World arg) {
        super(arg);
    }

    @Override
    public String vehicle_getVehicleName() {
        return _vehicleName;
    }

    @Override
    public void vehicle_setVehicleName(String vehicleName) {
        _vehicleName = vehicleName;
    }

    @Override
    public NbtCompound vehicle_getVehicleTag() {
        return _vehicleTag;
    }

    @Override
    public void vehicle_setVehicleTag(NbtCompound vehicleTag) {
        _vehicleTag = vehicleTag;
    }

    @Redirect(
            method = "interact",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;method_1323(Lnet/minecraft/entity/player/PlayerEntity;)Z"
            )
    )
    public boolean interactWith(Entity instance, PlayerEntity playerBase) {
        boolean canInteract = instance.method_1323(playerBase);

        if (UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled && canInteract) {
            /** - Set vehicle data */
            _vehicleName = (instance.field_1594 != null) ?  EntityRegistry.getId(instance) : NULL_AS_STRING;
            if (!_vehicleName.equals(NULL_AS_STRING)) {
                instance.write(_vehicleTag);
            }
        }

        return  canInteract;
    }



    @Inject(method = "writeNbt", at = @At("HEAD"))
    private void betaTweaks_writeCustomDataToTag(NbtCompound tag, CallbackInfo info) {
        if (!UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Save vehicle data */
        _vehicleName = (this.field_1595 != null) ?  EntityRegistry.getId(this.field_1595) : NULL_AS_STRING;
        tag.putString("VehicleName", _vehicleName);
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            this.field_1595.write(_vehicleTag);
            tag.put("VehicleTag", _vehicleTag);
        }
    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    private void betaTweaks_readCustomDataFromTag(NbtCompound tag, CallbackInfo info) {
        if (!UniTweaks.BUGFIXES_CONFIG.boatLogoutLoginFixesEnabled) {
            return;
        }

        /** - Get vehicle data */
        _vehicleName = tag.getString("VehicleName");
        if (!_vehicleName.equals(NULL_AS_STRING)) {
            _vehicleTag = tag.getCompound("VehicleTag");
        }

        /** - Find saved vehicle if on single player */
        if (world.isRemote)   return;
        PlayerEntity singlePlayer = PlayerHelper.getPlayerFromGame();
        if (null == singlePlayer) return;
        if (null == _vehicleTag)  return;
        if (_vehicleName.equals(NULL_AS_STRING)) return;
        Entity vehicle = EntityRegistry.create(_vehicleName, world);
        if (null != vehicle) {
            VehicleHelper.isVehicleSaved = true;
            Entity savedVehicle = null;

            try {
                vehicle.read(_vehicleTag);
            } catch(Exception ex) {
                vehicle.method_1341(x, y, z, yaw, pitch);
                System.out.println("Failed to read vehicle data");
            }

            VehicleHelper.savedVehicleClass = vehicle.getClass();
            VehicleHelper.savedVehicleX = vehicle.x;
            VehicleHelper.savedVehicleY = vehicle.y;
            VehicleHelper.savedVehicleZ = vehicle.z;

            /** - Search for vehicle */
            for (int entityIndex = 0; entityIndex < world.field_198.size(); entityIndex++) {
                Entity entityToCheck = (Entity) world.field_198.get(entityIndex);

                if (  (entityToCheck.getClass().equals(VehicleHelper.savedVehicleClass))
                   && (1 > Math.abs(entityToCheck.x - vehicle.x))
                   && (1 > Math.abs(entityToCheck.y - vehicle.y))
                   && (1 > Math.abs(entityToCheck.z - vehicle.z))
                ) {
                    savedVehicle = entityToCheck;
                }
            }

            if (null != savedVehicle) {
                method_1376(savedVehicle);
                VehicleHelper.isVehicleSaved = false;
            }
        }
    }
}
