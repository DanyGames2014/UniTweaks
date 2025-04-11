package net.danygames2014.unitweaks.mixin.bugfixes.armoriconsfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.class_277$1")
public class ArmorSlotMixin extends Slot {
    public ArmorSlotMixin(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getBackgroundTextureId() {
        if (UniTweaks.BUGFIXES_CONFIG.armorIconsFix) {
            return 7355608;
        } else {
            return super.getBackgroundTextureId();
        }
    }
}
