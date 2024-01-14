package net.danygames2014.unitweaks;

import net.danygames2014.unitweaks.util.Config;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

public class UniTweaks {
    @GConfig(value = "general", visibleName = "General", primary = true)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();

    @GConfig(value = "recipes", visibleName = "Recipes")
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();

    @GConfig(value = "bugfixes", visibleName = "Bugfixes")
    public static final Config.BugfixesConfig BUGFIXES_CONFIG = new Config.BugfixesConfig();

    @GConfig(value = "oldfeatures", visibleName = "Old Features")
    public static final Config.OldFeaturesConfig OLD_FEATURES_CONFIG = new Config.OldFeaturesConfig();

}
