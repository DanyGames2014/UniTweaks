package net.danygames2014.unitweaks.mixin.tweaks.rightclickarmor;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (!UniTweaks.GAMEPLAY_CONFIG.rightClickEquipArmor) return;

        Item item = (Item) (Object) this;
        if (!(item instanceof ArmorItem armor)) return;

        // Get armor index
        int offset = 3;
        int armorTargetIndex = Math.abs(offset - armor.equipmentSlot);

        // Get current equipped armor in the slot
        ItemStack equippedArmor = user.inventory.armor[armorTargetIndex];

        // Copy single stack in to equipment slot
        ItemStack stackToEquip = stack.copy();
        stackToEquip.count = 1;

        user.inventory.armor[armorTargetIndex] = stackToEquip;
        stack.count--;

        // If player don't have any armor equipped in that slot
        if (equippedArmor == null) {
            cir.setReturnValue(stack);
            return;
        }

        // Fix swapping stacked armor
        if (stack.count > 0) {
            if (!user.inventory.addStack(equippedArmor)) {
                user.dropItem(equippedArmor);
            }
            cir.setReturnValue(stack);
        } else {
            cir.setReturnValue(equippedArmor);
        }
    }
}
