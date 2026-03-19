package net.danygames2014.unitweaks.event;

import net.danygames2014.unitweaks.util.CompatHelper;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.StationAPI;

@SuppressWarnings("UnstableApiUsage")
@EventPhases(StationAPI.INTERNAL_PHASE)
public class RegisterShiftPlacingCompatEvent extends Event {
    public void addToShiftPlacingBlacklist(Block block) {
        CompatHelper.addToShiftPlacingBlacklist(block);
    }
}
