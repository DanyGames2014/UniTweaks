package net.danygames2014.unitweaks.util;

import net.minecraft.item.Item;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;

import java.util.List;

public class CraftingHelper {
    @SuppressWarnings({"unchecked"})
    public static void removeRecipe(Item item, int meta, boolean onlyRemoveFirst) {
        List<CraftingRecipe> recipes = CraftingRecipeManager.getInstance().getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            CraftingRecipe recipe = recipes.get(i);
            if (recipe.getOutput().itemId == item.id) {
                //noinspection SimplifiableConditionalExpression
                if ((meta == -1) ? true : (recipe.getOutput().getDamage() == meta)) {
                    recipes.remove(i);
                    i--;
                    if (onlyRemoveFirst) {
                        return;
                    }
                }
            }
        }
    }

    public static void removeRecipe(Item item, boolean onlyRemoveFirst) {
        removeRecipe(item, -1, onlyRemoveFirst);
    }

    public static void removeRecipe(Item item) {
        removeRecipe(item, -1, false);
    }
}
