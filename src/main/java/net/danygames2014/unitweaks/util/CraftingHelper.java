package net.danygames2014.unitweaks.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

import java.util.List;

public class CraftingHelper {

    public static void removeRecipe(Block block, int meta, boolean onlyRemoveFirst) {
        removeRecipe(Item.ITEMS[block.id], meta, onlyRemoveFirst);
    }

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

    public static void removeRecipe(Block block, boolean onlyRemoveFirst) {
        removeRecipe(Item.ITEMS[block.id], onlyRemoveFirst);
    }

    public static void removeRecipe(Item item, boolean onlyRemoveFirst) {
        removeRecipe(item, -1, onlyRemoveFirst);
    }

    public static void removeRecipe(Block block) {
        removeRecipe(Item.ITEMS[block.id]);
    }

    public static void removeRecipe(Item item) {
        removeRecipe(item, -1, false);
    }

    public static void addShapedRecipe(ItemStack output, Object... recipe) {
        if (FabricLoader.getInstance().isModLoaded("stationapi")) {
            CraftingRegistry.addShapedRecipe(output, recipe);
        } else {
            CraftingRecipeManager.INSTANCE.addShapedRecipe(output, recipe);
        }
    }

    public static void addShapelessRecipe(ItemStack output, Object... ingredients) {
        if (FabricLoader.getInstance().isModLoaded("stationapi")) {
            CraftingRegistry.addShapelessRecipe(output, ingredients);
        } else {
            CraftingRecipeManager.INSTANCE.addShapelessRecipe(output, ingredients);
        }
    }
}
