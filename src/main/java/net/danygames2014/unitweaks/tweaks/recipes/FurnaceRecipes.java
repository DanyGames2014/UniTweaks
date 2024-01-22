package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;

public class FurnaceRecipes {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes && UniTweaks.RECIPES_CONFIG.furnaceFuels) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            // These are technically modern recipes but i chose to not put them in the modern category
            if (type == RecipeRegisterEvent.Vanilla.SMELTING) {
                // 1200t - 60s
                FuelRegistry.addFuelItem(Item.BOAT, 1200);

                // 300t - 15s
                FuelRegistry.addFuelItem(Item.BOW, 300);
                FuelRegistry.addFuelItem(Item.FISHING_ROD, 300);
                FuelRegistry.addFuelItem(Block.LADDER.asItem(), 300);

                // 200t - 10s
                FuelRegistry.addFuelItem(Item.WOODEN_AXE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_HOE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_PICKAXE, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_SHOVEL, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_SWORD, 200);
                FuelRegistry.addFuelItem(Item.SIGN, 200);
                FuelRegistry.addFuelItem(Item.WOODEN_DOOR, 200);

                // 100t - 5s
                FuelRegistry.addFuelItem(Item.BOWL, 100);
            }
        }
    }
}
