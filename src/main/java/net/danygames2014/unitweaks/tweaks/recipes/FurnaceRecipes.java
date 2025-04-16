package net.danygames2014.unitweaks.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;

import java.util.Map;

public class FurnaceRecipes {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        if (UniTweaks.RECIPES_CONFIG.enableRecipes && UniTweaks.RECIPES_CONFIG.furnaceFuels) {
            RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

            if (type == RecipeRegisterEvent.Vanilla.SMELTING) {
                for (Map.Entry<Item, FuelLookup.FuelLookupEntry> entry : FuelLookup.lookup.entrySet()){
                    FuelRegistry.addFuelItem(entry.getKey(), entry.getValue().meta(), entry.getValue().fuelTime());
                }
            }
        }
    }
}
