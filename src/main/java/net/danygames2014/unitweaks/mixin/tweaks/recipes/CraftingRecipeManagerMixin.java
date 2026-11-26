package net.danygames2014.unitweaks.mixin.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.danygames2014.unitweaks.util.FarnNameTagCompat;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.CraftingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingRecipeManager.class)
public class CraftingRecipeManagerMixin {
    @Inject(method = "craft", at = @At(value = "HEAD"), cancellable = true)
    public void combineDurability(CraftingInventory inventory, CallbackInfoReturnable<ItemStack> cir) {
        if (UniTweaks.RECIPES_CONFIG.toolRepair || UniTweaks.RECIPES_CONFIG.armorRepair) {
            ItemStack stack1 = null;
            ItemStack stack2 = null;
            int craftingStacks = 0;

            for (ItemStack stack : inventory.stacks) {
                if (stack != null) {
                    craftingStacks++;

                    if (stack.getItem().isDamageable()) {
                        Item stackItem = stack.getItem();

                        if (stackItem instanceof ArmorItem) {
                            if (UniTweaks.RECIPES_CONFIG.armorRepair) {
                                if (stack1 != null && stack1.getItem() == stack.getItem()) {
                                    stack2 = stack;
                                } else {
                                    stack1 = stack;
                                }
                            }
                        } else {
                            if (UniTweaks.RECIPES_CONFIG.toolRepair) {
                                if (stack1 != null && stack1.getItem() == stack.getItem()) {
                                    stack2 = stack;
                                } else {
                                    stack1 = stack;
                                }
                            }
                        }
                    }
                }
            }

            if(stack1 != null && stack2 != null && craftingStacks == 2) {
                Item stackItem = stack1.getItem();

                int item1durability = stackItem.getMaxDamage() - stack1.getDamage();
                int item2durability = stackItem.getMaxDamage() - stack2.getDamage();
                int addedDurability = item1durability + item2durability + stackItem.getMaxDamage() * 10 / 100;
                int resultDurability = stackItem.getMaxDamage() - addedDurability;
                if (resultDurability < 0) {
                    resultDurability = 0;
                }

                ItemStack result = new ItemStack(stackItem, 1, resultDurability);

                // Farn's Nametags compat
                if (CraftingHelper.isFarnNameTagLoaded) {
                    FarnNameTagCompat.checkForCustomNametagAndAddToResult(stack1, stack2, result);
                }

                cir.setReturnValue(result);
            }
        }
    }
}
