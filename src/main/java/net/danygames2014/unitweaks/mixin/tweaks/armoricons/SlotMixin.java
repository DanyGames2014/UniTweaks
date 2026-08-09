package net.danygames2014.unitweaks.mixin.tweaks.armoricons;

import net.danygames2014.unitweaks.interfaces.ArmorSlotDuck;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Slot.class)
public class SlotMixin implements ArmorSlotDuck {

    @Override
    public boolean uniTweaks$isArmorSlot() {
        return false;
    }
}
