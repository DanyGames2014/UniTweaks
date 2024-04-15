package net.danygames2014.unitweaks.util;

import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Util;

public interface VehicleInterface {
    default String vehicle_getVehicleName() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicleName(String vehicleName) {
        Util.assertImpl();
    }

    default NbtCompound vehicle_getVehicleTag() {
        return Util.assertImpl();
    }

    default void vehicle_setVehicleTag(NbtCompound vehicleTag) {
        Util.assertImpl();
    }
}