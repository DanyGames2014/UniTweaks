package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class ObtainableRecipes {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
                case CRAFTING_SHAPED -> {
                    // Craftable Cobwebs
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableCobwebs) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.COBWEB.asItem(), 1), "S S", " S ", "S S", 'S', new ItemStack(Item.STRING, 1));
                    }

                    // Craftable Coal Ore
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableCoalOre) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.COAL_ORE.asItem(), 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.COAL), 'S', new ItemStack(Block.STONE.asItem()));
                    }

                    // Craftable Iron Ore
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableIronOre) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.IRON_ORE.asItem(), 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.IRON_INGOT), 'S', new ItemStack(Block.STONE.asItem()));
                    }

                    // Craftable Coal Ore
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableGoldOre) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.GOLD_ORE.asItem(), 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.GOLD_INGOT), 'S', new ItemStack(Block.STONE.asItem()));
                    }

                    // Craftable Coal Ore
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableDiamondOre) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.DIAMOND_ORE.asItem(), 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.DIAMOND), 'S', new ItemStack(Block.STONE.asItem()));
                    }

                    // Craftable Coal Ore
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableLapisOre) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.LAPIS_ORE.asItem(), 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.DYE,1, 4), 'S', new ItemStack(Block.STONE.asItem()));
                    }
                }

                case CRAFTING_SHAPELESS -> {
                    // Craftable Grass Block
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableGrassBlocks) {
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Block.GRASS_BLOCK.asItem(), 1), new ItemStack(Block.DIRT.asItem(), 1), new ItemStack(Item.SEEDS, 1));
                    }

                    // Craftable Fire
                    if (UniTweaks.RECIPES_CONFIG.obtainable.craftableFire) {
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Block.FIRE.asItem(), 3), new ItemStack(Item.FLINT_AND_STEEL, 1));
                    }


                }

                case SMELTING -> {
                }
            }
        }
    }
}
