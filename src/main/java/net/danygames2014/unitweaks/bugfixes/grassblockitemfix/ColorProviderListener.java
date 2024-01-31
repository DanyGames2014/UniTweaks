package net.danygames2014.unitweaks.bugfixes.grassblockitemfix;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.class_287;
import net.modificationstation.stationapi.api.client.event.color.item.ItemColorsRegisterEvent;

public class ColorProviderListener {
    @EventListener
    public void registerColorProvider(ItemColorsRegisterEvent event) {
        event.itemColors.register(
                (item, damage) -> class_287.method_981(0.5F, 0.5F),
                Block.GRASS_BLOCK
        );
    }
}
