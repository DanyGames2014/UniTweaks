package net.danygames2014.unitweaks.mixin.tweaks.armoricons;

import net.danygames2014.unitweaks.interfaces.ArmorSlotDuck;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Slot.class)
public class SlotMixin implements ArmorSlotDuck {

    @Unique
    private boolean isArmorSlot = false;

    @Override
    public boolean uniTweaks$isArmorSlot() {
        return this.isArmorSlot;
    }

    @Override
    public void uniTweaks$setArmorSlot(boolean isArmorSlot) {
        this.isArmorSlot = isArmorSlot;
    }
}
