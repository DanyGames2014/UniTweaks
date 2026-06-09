package net.danygames2014.unitweaks.mixin.tweaks.milkablediver;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends EntityMixin {
    @Override
    protected void unitweaks$interact(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (this.world.isRemote) {
            return;
        }

        ItemStack hand = player.inventory.getSelectedItem();
        if (hand != null && hand.itemId == Item.BUCKET.id) {
            if (PlayerEntity.class.cast(this).name.equals("mine_diver")) {
                player.inventory.setStack(player.inventory.selectedSlot, new ItemStack(Item.MILK_BUCKET));
                cir.setReturnValue(true);
            }
        }
    }
}
