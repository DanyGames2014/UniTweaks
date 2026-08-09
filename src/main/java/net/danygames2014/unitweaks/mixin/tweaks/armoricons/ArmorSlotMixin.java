package net.danygames2014.unitweaks.mixin.tweaks.armoricons;

import net.danygames2014.unitweaks.interfaces.ArmorSlotDuck;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "net.minecraft.class_277$1")
public class ArmorSlotMixin implements ArmorSlotDuck {

    @Override
    public boolean uniTweaks$isArmorSlot() {
        return true;
    }
}
