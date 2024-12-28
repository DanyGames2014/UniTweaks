package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

@SuppressWarnings("unused")
public class TweakedRecipes {
    @EventListener(priority = ListenerPriority.HIGH)
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
                case CRAFTING_SHAPED -> {
                    // Custom Stairs per Craft
                    if (UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft != 4) {
                        CraftingHelper.removeRecipe(Block.WOODEN_STAIRS.asItem(), true);
                        CraftingHelper.removeRecipe(Block.COBBLESTONE_STAIRS.asItem(), true);
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.WOODEN_STAIRS, UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft), "X  ", "XX ", "XXX", 'X', new ItemStack(Block.PLANKS, 1));
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft), "X  ", "XX ", "XXX", 'X', new ItemStack(Block.COBBLESTONE, 1));
                    }
                    
                    // Custom Trapdoors per Craft
                    if(UniTweaks.RECIPES_CONFIG.tweaked.trapdoorsPerCraft != 2) {
                        CraftingHelper.removeRecipe(Block.TRAPDOOR.asItem(), true);
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.TRAPDOOR, UniTweaks.RECIPES_CONFIG.tweaked.trapdoorsPerCraft),"XXX", "XXX", 'X', new ItemStack(Block.PLANKS, 1));
                    }
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
