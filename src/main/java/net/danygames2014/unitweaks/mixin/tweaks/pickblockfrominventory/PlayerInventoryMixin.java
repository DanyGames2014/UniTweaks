package net.danygames2014.unitweaks.mixin.tweaks.pickblockfrominventory;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Shadow
    public PlayerEntity player;

    @Shadow
    public ItemStack[] main;

    @Shadow
    public int selectedSlot;

    @Environment(EnvType.CLIENT)
    @Inject(method = "method_691", at = @At("HEAD"), cancellable = true)
    public void setSelectedItem(int itemId, boolean bl, CallbackInfo ci) {
        if (UniTweaks.GAMEPLAY_CONFIG.pickBlockFromInventory) {
            int slotWithItemIndex = -1;
            int hotbarSlotIndex;

            // Find the item
            for (int i = 0; i < main.length; i++) {
                if (main[i] != null && main[i].itemId == itemId) {
                    slotWithItemIndex = i;
                    break;
                }
            }

            // Item not found, no point in continuing
            if (slotWithItemIndex == -1) {
                ci.cancel();
                return;
            }

            if (slotWithItemIndex < 9) { // The item is in hotbar, select the slot
                selectedSlot = slotWithItemIndex;
            } else { // Item is not in in hotbar
                // Find a hotbar slot to put the item into
                if (player.inventory.getSelectedItem() == null) {
                    // Selected hotbar slot is empty, put it there
                    hotbarSlotIndex = this.selectedSlot;
                } else {
                    // Selected htobar slot isn't empty, try to find a free one
                    hotbarSlotIndex = getEmptyHotbarSlot();
                }

                // Handle putting the item in hand
                if (!player.world.isRemote) { // SINGLEPLAYER
                    if (hotbarSlotIndex != -1) { // hotbarSlot isn't -1, therefore a free hotbar slot has been found
                        selectedSlot = hotbarSlotIndex;
                        main[hotbarSlotIndex] = main[slotWithItemIndex];
                        main[slotWithItemIndex] = null;
                    } else { // hotbarSlot = -1 means that no hotbar slot is free
                        ItemStack tempItem = player.getHand();
                        main[selectedSlot] = main[slotWithItemIndex];
                        main[slotWithItemIndex] = tempItem;
                    }
                } else { // MULTIPLAYER

                }
            }
        }
    }

    @Unique
    public int getEmptyHotbarSlot() {
        // 0-8 in main inventory is the hotbar
        for (int i = 0; i < 9; i++) {
            if (main[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
