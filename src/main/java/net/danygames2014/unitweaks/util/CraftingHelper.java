package net.danygames2014.unitweaks.util;

import net.minecraft.item.Item;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;

import java.util.List;

public class CraftingHelper {
    @SuppressWarnings({"SuspiciousListRemoveInLoop", "unchecked"})
    public static void removeRecipe(Item item, boolean onlyRemoveFirst){
        List<CraftingRecipe> recipes = CraftingRecipeManager.getInstance().getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            CraftingRecipe recipe = recipes.get(i);
            if(recipe.getOutput().itemId == item.id){
                recipes.remove(i);
                if(onlyRemoveFirst){
                    return;
                }
            }
        }
    }

    public static void removeRecipe(Item item){
        removeRecipe(item, false);
    }
}
