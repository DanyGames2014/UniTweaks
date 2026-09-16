package net.danygames2014.unitweaks.mixin.bugfixes.armoriconsfix;

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
