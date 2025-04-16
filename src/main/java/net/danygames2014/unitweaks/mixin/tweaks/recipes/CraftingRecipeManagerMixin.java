package net.danygames2014.unitweaks.mixin.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingRecipeManager.class)
public class CraftingRecipeManagerMixin {
    @Inject(method = "craft", at = @At(value = "HEAD"), cancellable = true)
    public void combineDurability(CraftingInventory inventory, CallbackInfoReturnable<ItemStack> cir) {
        if (UniTweaks.RECIPES_CONFIG.toolRepair) {
            ItemStack stack1 = null;
            ItemStack stack2 = null;

            for (ItemStack stack : inventory.stacks) {
                if (stack != null && stack.getItem().isDamageable()) {
                    if (stack1 != null && stack1.getItem() == stack.getItem()) {
                        stack2 = stack;
                    } else {
                        stack1 = stack;
                    }
                }
            }
            
            if(stack1 != null && stack2 != null) {
                Item item = stack1.getItem();
                
                int item1durability = item.getMaxDamage() - stack1.getDamage();
                int item2durability = item.getMaxDamage() - stack2.getDamage();
                int addedDurability = item1durability + item2durability + item.getMaxDamage() * 10 / 100;
                int resultDurability = item.getMaxDamage() - addedDurability;
                if (resultDurability < 0) {
                    resultDurability = 0;
                }
                
                cir.setReturnValue(new ItemStack(item, 1, resultDurability));
            }
        }
    }
}
