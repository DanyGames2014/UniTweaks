package net.danygames2014.unitweaks.mixin.bugfixes.armoriconsfix;

import net.danygames2014.unitweaks.mixininterface.UniTweaksArmorSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Slot.class)
public class SlotMixin implements UniTweaksArmorSlot {
    @Override
    public boolean uniTweaks$isArmorSlot() {
        return false;
    }
}
