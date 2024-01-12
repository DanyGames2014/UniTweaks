package net.danygames2014.unitweaks.recipe;

import net.danygames2014.unitweaks.Config;
import net.danygames2014.unitweaks.UniTweaks;
import net.glasslauncher.mods.api.gcapi.api.GCAPI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class RecipeListener {
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public void registerRecipes(RecipeRegisterEvent.Vanilla event){
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.type());



        if(UniTweaks.RECIPES_CONFIG.enableRecipes){
            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED){
                case CRAFTING_SHAPED -> {

                }

                case CRAFTING_SHAPELESS -> {
                    if(UniTweaks.RECIPES_CONFIG.modern.shapelessFlintAndSteel){
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FLINT_AND_STEEL), new ItemStack(Item.FLINT,1), new ItemStack(Item.IRON_INGOT,1));
                    }
                }

                case SMELTING -> {

                }
            }
        }
    }
}
