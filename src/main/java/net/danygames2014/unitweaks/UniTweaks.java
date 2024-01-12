package net.danygames2014.unitweaks;

import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class UniTweaks {
    @GConfig(value = "general", visibleName = "General", primary = true)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();

    @GConfig(value = "recipes", visibleName = "Recipes")
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();

}
