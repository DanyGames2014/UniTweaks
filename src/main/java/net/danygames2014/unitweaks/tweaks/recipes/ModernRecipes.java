package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;

public class ModernRecipes {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
                case CRAFTING_SHAPED -> {
                    // Slabs Craft 6 per Craft
                    if (UniTweaks.RECIPES_CONFIG.modern.sixSlabsPerCraft) {
                        CraftingHelper.removeRecipe(Block.SLAB.asItem());
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB.asItem(), 6, 0), "XXX", 'X', new ItemStack(Block.STONE.asItem(), 1));
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB.asItem(), 6, 1), "XXX", 'X', new ItemStack(Block.SANDSTONE.asItem(), 1));
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB.asItem(), 6, 2), "XXX", 'X', new ItemStack(Block.PLANKS.asItem(), 1));
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB.asItem(), 6, 3), "XXX", 'X', new ItemStack(Block.COBBLESTONE.asItem(), 1));
                    }

                    // Buttons crafted with 1 stone
                    if (UniTweaks.RECIPES_CONFIG.modern.oneStonePerButton) {
                        CraftingHelper.removeRecipe(Block.BUTTON.asItem(), true);
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.BUTTON, 1), "X", 'X', new ItemStack(Block.STONE, 1));
                    }

                    // Modern Fence recipe
                    if (UniTweaks.RECIPES_CONFIG.modern.modernFenceRecipe) {
                        CraftingHelper.removeRecipe(Block.FENCE.asItem(), true);
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.FENCE, 3), "PSP", "PSP", 'P', new ItemStack(Block.PLANKS, 1), 'S', new ItemStack(Item.STICK, 1));
                    }

                    // Craftable Snow layers (3 Snow Block -> 6 Layers)
                    if (UniTweaks.RECIPES_CONFIG.modern.snowLayerRecipe) {
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.SNOW, 6), "SSS", 'S', new ItemStack(Block.SNOW_BLOCK));
                    }

                    // Ladder craft 3 per craft
                    if (UniTweaks.RECIPES_CONFIG.modern.threeLadersPerCraft) {
                        CraftingHelper.removeRecipe(Block.LADDER.asItem(), true);
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.LADDER.asItem(), 3), "S S", "SSS", "S S", 'S', new ItemStack(Item.STICK));
                    }

                    // These will need configs to adjust stack sizes :
                    // No idea where to put the configs for that
                    // 3 Wood Door per craft
                    // 3 Iron door per craft
                    // 3 Signs per craft
                }

                case CRAFTING_SHAPELESS -> {
                    // Shapeless Flint and Steel Recipe
                    if (UniTweaks.RECIPES_CONFIG.modern.shapelessFlintAndSteel) {
                        CraftingHelper.removeRecipe(Item.FLINT_AND_STEEL, true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FLINT_AND_STEEL, 1), new ItemStack(Item.FLINT, 1), new ItemStack(Item.IRON_INGOT, 1));
                    }

                    // Shapeless Mushroom Stew
                    if (UniTweaks.RECIPES_CONFIG.modern.shapelessMushroomStew) {
                        CraftingHelper.removeRecipe(Item.MUSHROOM_STEW, true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.MUSHROOM_STEW, 1), new ItemStack(Item.BOWL, 1), new ItemStack(Block.BROWN_MUSHROOM.asItem(), 1), new ItemStack(Block.RED_MUSHROOM.asItem(), 1));
                    }

                    // Shapeless Chest Minecart
                    if (UniTweaks.RECIPES_CONFIG.modern.shapelessChestMinecart) {
                        CraftingHelper.removeRecipe(Item.CHEST_MINECART, true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.CHEST_MINECART, 1), new ItemStack(Item.MINECART, 1), new ItemStack(Block.CHEST.asItem(), 1));
                    }

                    // Shapeless Furnace Minecart
                    if (UniTweaks.RECIPES_CONFIG.modern.shapelessFurnaceMinecart) {
                        CraftingHelper.removeRecipe(Item.FURNACE_MINECART, true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.FURNACE_MINECART, 1), new ItemStack(Item.MINECART, 1), new ItemStack(Block.FURNACE.asItem(), 1));
                    }

                    // Shapeless Sticky Pistons
                    if (UniTweaks.RECIPES_CONFIG.modern.shapelessStickyPistons) {
                        CraftingHelper.removeRecipe(Block.STICKY_PISTON.asItem(), true);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Block.STICKY_PISTON.asItem(), 1), new ItemStack(Item.SLIMEBALL, 1), new ItemStack(Block.PISTON.asItem(), 1));
                    }

                    // Books require 1 leather and 3 paper
                    if (UniTweaks.RECIPES_CONFIG.modern.booksRequireLeather) {
                        CraftingHelper.removeRecipe(Item.BOOK);
                        CraftingRegistry.addShapelessRecipe(new ItemStack(Item.BOOK, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.LEATHER, 1));
                    }

                    // Wool redyeing
                    if (UniTweaks.RECIPES_CONFIG.modern.woolRedyeing) {
                        CraftingHelper.removeRecipe(Block.WOOL.asItem());
                        CraftingRegistry.addShapedRecipe(new ItemStack(Block.WOOL.asItem(), 1), "SS", "SS", 'S', new ItemStack(Item.STRING, 1));
                        for (int i = 0; i < 16; i++) {
                            CraftingRegistry.addShapelessRecipe(new ItemStack(Block.WOOL.asItem(), 1, i), new ItemStack(Block.WOOL.asItem(), 1, -1), new ItemStack(Item.DYE, 1, 15 - i));
                        }
                    }
                }

                case SMELTING -> {

                }
            }
        }
    }
}
