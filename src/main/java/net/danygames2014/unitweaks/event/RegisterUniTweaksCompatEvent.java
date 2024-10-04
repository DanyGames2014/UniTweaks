package net.danygames2014.unitweaks.event;

import net.danygames2014.unitweaks.util.CompatHelper;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.modificationstation.stationapi.api.StationAPI;

import java.util.function.BiFunction;

@SuppressWarnings("UnstableApiUsage")
@EventPhases(StationAPI.INTERNAL_PHASE)
public class RegisterUniTweaksCompatEvent extends Event {
    public void registerFovCompat(BiFunction<Float, Float, Float> fovFunction) {
        CompatHelper.registerFovCompat(fovFunction);
    }
}
