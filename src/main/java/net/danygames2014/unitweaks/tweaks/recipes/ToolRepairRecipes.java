package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.*;
import net.modificationstation.stationapi.api.event.container.slot.ItemUsedInCraftingEvent;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class ToolRepairRecipes {
    @EventListener
    public void combineDurability(ItemUsedInCraftingEvent event) {
        if ((event.itemUsed != null) && event.itemUsed.isDamageable() && (event.itemCrafted != null) && event.itemCrafted.isDamageable()) {
            int maximumDurability = event.itemCrafted.getMaxDamage();

            // First item
            if (event.itemCrafted.getDamage() == maximumDurability) {
                event.itemCrafted.setDamage(event.itemCrafted.getDamage() - (event.itemUsed.getMaxDamage() - event.itemUsed.getDamage()));
                event.itemCrafted.setDamage(event.itemCrafted.getDamage() - (int) Math.floor((double) maximumDurability / 20));
            }
            event.itemCrafted.setDamage(event.itemCrafted.getDamage() - (event.itemUsed.getMaxDamage() - event.itemUsed.getDamage()));

            event.itemCrafted.setDamage(Math.max(event.itemCrafted.getDamage(), 0));
        }
    }

    @EventListener
    public void registerToolRecipes(RecipeRegisterEvent event) {
        // Check if recipes are enabled
        if (UniTweaks.RECIPES_CONFIG.enableRecipes && UniTweaks.RECIPES_CONFIG.toolRepair) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);
            // Add the recipes as shapeless
            if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS) {
                // Loop through items
                for (var item : Item.ITEMS) {
                    // If the item is a tool, add a shapeless recipe to it
                    if (item instanceof ToolItem || item instanceof SwordItem || item instanceof ArmorItem || item instanceof FishingRodItem || item instanceof ShearsItem || item instanceof FlintAndSteel) {
//                        System.out.println(item.getTranslatedName());
                        CraftingRegistry.addShapelessRecipe(
                                new ItemStack(item.id, 1, item.getMaxDamage()),
                                new ItemStack(item.id, 1, -1),
                                new ItemStack(item.id, 1, -1));
                    }
                }
            }
        }
    }
}
