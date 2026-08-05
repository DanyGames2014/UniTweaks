package net.danygames2014.unitweaks.mixin.tweaks.armoricons;

import net.danygames2014.unitweaks.interfaces.ArmorSlotDuck;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.apache.commons.lang3.Range;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Unique
    private static final Range<Integer> ARMOR_SLOTS = Range.between(36, 39);

    @Inject(method = "addSlot", at = @At("HEAD"))
    private void markArmorSlot(Slot slot, CallbackInfo ci) {
        if (!(slot instanceof SlotInventoryAccessor accessedSlot)) return;
        if (!(accessedSlot.getInventory() instanceof PlayerInventory)) return;

        if (!ARMOR_SLOTS.contains(accessedSlot.getIndex())) return;
        ((ArmorSlotDuck) slot).uniTweaks$setArmorSlot(true);
    }
}
