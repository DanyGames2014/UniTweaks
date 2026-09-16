package net.danygames2014.unitweaks.mixin.bugfixes.armoriconsfix;

import net.danygames2014.unitweaks.mixininterface.UniTweaksArmorSlot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.class_277$1")
public class ArmorSlotMixin implements UniTweaksArmorSlot {
    @Override
    public boolean uniTweaks$isArmorSlot() {
        return true;
    }
}
