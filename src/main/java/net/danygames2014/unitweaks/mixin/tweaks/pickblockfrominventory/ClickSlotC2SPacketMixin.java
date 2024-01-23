package net.danygames2014.unitweaks.mixin.tweaks.pickblockfrominventory;

import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataOutputStream;

@Mixin(ClickSlotC2SPacket.class)
public class ClickSlotC2SPacketMixin {
    @Shadow public int slot;

    @Shadow public int button;

    @Shadow public boolean holdingShift;

    @Shadow public ItemStack stack;

    @Shadow public short actionType;

    @Shadow public int syncId;

    @Inject(method = "write", at = @At("HEAD"))
    private void debugPrint(DataOutputStream par1, CallbackInfo ci){
        System.out.println("Slot : " + this.slot + " | Button : " + this.button + " | Shift : " + this.holdingShift + " | Stack : " + this.stack + " | SyncId : + " + this.syncId);
    }
}
