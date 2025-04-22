package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.CraftingHelper;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;

public class RecipeListener {
    @EventListener(priority = ListenerPriority.HIGH)
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            switch (type != null ? type : RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED) {
                case CRAFTING_SHAPED -> {
                    registerShapedRecipes();
                }

                case CRAFTING_SHAPELESS -> {
                    registerShapelessRecipes();
                }

                case SMELTING -> {

                }
            }
        }
    }
    
    public static void registerShapedRecipes() {
        /* Modern Recipes */
        // Slabs Craft 6 per Craft
        if (UniTweaks.RECIPES_CONFIG.modern.sixSlabsPerCraft) {
            CraftingHelper.removeRecipe(Block.SLAB);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.SLAB, 6, 0), "XXX", 'X', new ItemStack(Block.STONE, 1));
            CraftingHelper.addShapedRecipe(new ItemStack(Block.SLAB, 6, 1), "XXX", 'X', new ItemStack(Block.SANDSTONE, 1));
            CraftingHelper.addShapedRecipe(new ItemStack(Block.SLAB, 6, 2), "XXX", 'X', new ItemStack(Block.PLANKS, 1));
            CraftingHelper.addShapedRecipe(new ItemStack(Block.SLAB, 6, 3), "XXX", 'X', new ItemStack(Block.COBBLESTONE, 1));
        }

        // Buttons crafted with 1 stone
        if (UniTweaks.RECIPES_CONFIG.modern.oneStonePerButton) {
            CraftingHelper.removeRecipe(Block.BUTTON, true);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.BUTTON, 1), "X", 'X', new ItemStack(Block.STONE, 1));
        }

        // Modern Fence recipe
        if (UniTweaks.RECIPES_CONFIG.modern.modernFenceRecipe) {
            CraftingHelper.removeRecipe(Block.FENCE, true);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.FENCE, 3), "PSP", "PSP", 'P', new ItemStack(Block.PLANKS, 1), 'S', new ItemStack(Item.STICK, 1));
        }

        // Craftable Snow layers (3 Snow Block -> 6 Layers)
        if (UniTweaks.RECIPES_CONFIG.modern.snowLayerRecipe) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.SNOW, 6), "SSS", 'S', new ItemStack(Block.SNOW_BLOCK));
        }

        // Ladder craft 3 per craft
        if (UniTweaks.RECIPES_CONFIG.modern.threeLadersPerCraft) {
            CraftingHelper.removeRecipe(Block.LADDER, true);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.LADDER, 3), "S S", "SSS", "S S", 'S', new ItemStack(Item.STICK));
        }

        /* Obtainable Recipes */
        // Craftable Cobwebs
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableCobwebs) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.COBWEB, 1), "S S", " S ", "S S", 'S', new ItemStack(Item.STRING, 1));
        }

        // Craftable Coal Ore
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableCoalOre) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.COAL_ORE, 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.COAL), 'S', new ItemStack(Block.STONE));
        }

        // Craftable Iron Ore
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableIronOre) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.IRON_ORE, 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.IRON_INGOT), 'S', new ItemStack(Block.STONE));
        }

        // Craftable Gold Ore
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableGoldOre) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.GOLD_ORE, 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.GOLD_INGOT), 'S', new ItemStack(Block.STONE));
        }

        // Craftable Diamond Ore
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableDiamondOre) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.DIAMOND_ORE, 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.DIAMOND), 'S', new ItemStack(Block.STONE));
        }

        // Craftable Lapis Ore
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableLapisOre) {
            CraftingHelper.addShapedRecipe(new ItemStack(Block.LAPIS_ORE, 1), "RRR", "RSR", "RRR", 'R', new ItemStack(Item.DYE, 1, 4), 'S', new ItemStack(Block.STONE));
        }
        
        /* Tweaked Recipes */
        // Custom Stairs per Craft
        if (UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft != 4) {
            CraftingHelper.removeRecipe(Block.WOODEN_STAIRS, true);
            CraftingHelper.removeRecipe(Block.COBBLESTONE_STAIRS, true);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.WOODEN_STAIRS, UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft), "X  ", "XX ", "XXX", 'X', new ItemStack(Block.PLANKS, 1));
            CraftingHelper.addShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, UniTweaks.RECIPES_CONFIG.tweaked.stairsPerCraft), "X  ", "XX ", "XXX", 'X', new ItemStack(Block.COBBLESTONE, 1));
        }

        // Custom Trapdoors per Craft
        if (UniTweaks.RECIPES_CONFIG.tweaked.trapdoorsPerCraft != 2) {
            CraftingHelper.removeRecipe(Block.TRAPDOOR, true);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.TRAPDOOR, UniTweaks.RECIPES_CONFIG.tweaked.trapdoorsPerCraft), "XXX", "XXX", 'X', new ItemStack(Block.PLANKS, 1));
        }
    }
    
    public static void registerShapelessRecipes() {
        /* Modern Recipes */
        // Shapeless Flint and Steel Recipe
        if (UniTweaks.RECIPES_CONFIG.modern.shapelessFlintAndSteel) {
            CraftingHelper.removeRecipe(Item.FLINT_AND_STEEL, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Item.FLINT_AND_STEEL, 1), new ItemStack(Item.FLINT, 1), new ItemStack(Item.IRON_INGOT, 1));
        }

        // Shapeless Mushroom Stew
        if (UniTweaks.RECIPES_CONFIG.modern.shapelessMushroomStew) {
            CraftingHelper.removeRecipe(Item.MUSHROOM_STEW, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Item.MUSHROOM_STEW, 1), new ItemStack(Item.BOWL, 1), new ItemStack(Block.BROWN_MUSHROOM, 1), new ItemStack(Block.RED_MUSHROOM, 1));
        }

        // Shapeless Chest Minecart
        if (UniTweaks.RECIPES_CONFIG.modern.shapelessChestMinecart) {
            CraftingHelper.removeRecipe(Item.CHEST_MINECART, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Item.CHEST_MINECART, 1), new ItemStack(Item.MINECART, 1), new ItemStack(Block.CHEST, 1));
        }

        // Shapeless Furnace Minecart
        if (UniTweaks.RECIPES_CONFIG.modern.shapelessFurnaceMinecart) {
            CraftingHelper.removeRecipe(Item.FURNACE_MINECART, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Item.FURNACE_MINECART, 1), new ItemStack(Item.MINECART, 1), new ItemStack(Block.FURNACE, 1));
        }

        // Shapeless Sticky Pistons
        if (UniTweaks.RECIPES_CONFIG.modern.shapelessStickyPistons) {
            CraftingHelper.removeRecipe(Block.STICKY_PISTON, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Block.STICKY_PISTON, 1), new ItemStack(Item.SLIMEBALL, 1), new ItemStack(Block.PISTON, 1));
        }

        // Books require 1 leather and 3 paper
        if (UniTweaks.RECIPES_CONFIG.modern.booksRequireLeather) {
            CraftingHelper.removeRecipe(Item.BOOK);
            CraftingHelper.addShapelessRecipe(new ItemStack(Item.BOOK, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.PAPER, 1), new ItemStack(Item.LEATHER, 1));
        }

        // Wool redyeing
        if (UniTweaks.RECIPES_CONFIG.modern.woolRedyeing) {
            CraftingHelper.removeRecipe(Block.WOOL);
            CraftingHelper.addShapedRecipe(new ItemStack(Block.WOOL, 1), "SS", "SS", 'S', new ItemStack(Item.STRING, 1));
            for (int i = 0; i < 16; i++) {
                CraftingHelper.addShapelessRecipe(new ItemStack(Block.WOOL, 1, i), new ItemStack(Block.WOOL, 1, -1), new ItemStack(Item.DYE, 1, 15 - i));
            }
        }
        
        /* Obtainable Recipes */
        // Craftable Grass Block
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableGrassBlocks) {
            CraftingHelper.addShapelessRecipe(new ItemStack(Block.GRASS_BLOCK, 1), new ItemStack(Block.DIRT, 1), new ItemStack(Item.SEEDS, 1));
        }

        // Craftable Fire
        if (UniTweaks.RECIPES_CONFIG.obtainable.craftableFire) {
            CraftingHelper.addShapelessRecipe(new ItemStack(Block.FIRE, 3), new ItemStack(Item.FLINT_AND_STEEL, 1));
        }
        
        /* Tweaked Recipes */
        // Shapeless Jack o' Lantern
        if (UniTweaks.RECIPES_CONFIG.tweaked.shapelessJackOLantern) {
            CraftingHelper.removeRecipe(Block.JACK_O_LANTERN, true);
            CraftingHelper.addShapelessRecipe(new ItemStack(Block.JACK_O_LANTERN, 1), new ItemStack(Block.PUMPKIN, 1), new ItemStack(Block.TORCH, 1));
        }
    }
}
