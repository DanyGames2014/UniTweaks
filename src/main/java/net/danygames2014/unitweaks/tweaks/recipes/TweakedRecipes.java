package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class TweakedRecipes {
    @SuppressWarnings("DuplicateBranchesInSwitch")
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
                case CRAFTING_SHAPED -> {

                }

                case CRAFTING_SHAPELESS -> {
                    // Shapeless Jack o' Lantern
                    if (UniTweaks.RECIPES_CONFIG.tweaked.shapelessJackOLantern) {
                        CraftingHelper.removeRecipe(Block.JACK_O_LANTERN.asItem(), true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Block.JACK_O_LANTERN.asItem(), 1), new ItemStack(Block.PUMPKIN.asItem(), 1), new ItemStack(Block.TORCH.asItem(), 1));
                    }
                }

                case SMELTING -> {

                }
            }
        }
    }
}
