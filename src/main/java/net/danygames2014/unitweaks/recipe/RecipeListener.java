package net.danygames2014.unitweaks.recipe;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeListener {
    @SuppressWarnings("DuplicateBranchesInSwitch")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event){
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);



        if(UniTweaks.RECIPES_CONFIG.enableRecipes){
            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED){
                case CRAFTING_SHAPED -> {

                }

                case CRAFTING_SHAPELESS -> {
                    if(UniTweaks.RECIPES_CONFIG.modern.shapelessFlintAndSteel){
                        CraftingHelper.removeRecipe(Item.FLINT_AND_STEEL);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FLINT_AND_STEEL), new ItemStack(Item.FLINT,1), new ItemStack(Item.IRON_INGOT,1));
                    }
                }

                case SMELTING -> {

                }
            }
        }
    }
}
