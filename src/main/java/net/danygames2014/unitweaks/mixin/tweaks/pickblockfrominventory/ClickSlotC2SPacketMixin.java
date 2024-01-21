package net.danygames2014.unitweaks.mixin.tweaks.pickblockfrominventory;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClickSlotC2SPacket.class)
public class ClickSlotC2SPacketMixin {
//    @Inject(method = "<init>(IIIZLnet/minecraft/item/ItemStack;S)V", at = @At("HEAD"))
//    private void debugPrint(int syncId, int slot, int button, boolean holdingShift, ItemStack stack, short actionType, CallbackInfo ci){
//        System.out.println("Slot : " + slot + " | Button : " + button + " | Shift : " + holdingShift + " | Stack : " + stack + " | ActionType : + " + actionType);
//    }
}
