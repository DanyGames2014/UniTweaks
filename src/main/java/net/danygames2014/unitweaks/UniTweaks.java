package net.danygames2014.unitweaks;

import net.danygames2014.unitweaks.util.Config;
import net.glasslauncher.mods.api.gcapi.api.GConfig;

@SuppressWarnings("InstantiationOfUtilityClass")
public class UniTweaks {
    @GConfig(value = "general", visibleName = "General", primary = true)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();

    @GConfig(value = "gameplay", visibleName = "Gameplay")
    public static final Config.GameplayConfig GAMEPLAY_CONFIG = new Config.GameplayConfig();

    @GConfig(value = "backport", visibleName = "Backported Features")
    public static final Config.BackportConfig BACKPORT_CONFIG = new Config.BackportConfig();

    @GConfig(value = "oldfeatures", visibleName = "Old Features")
    public static final Config.OldFeaturesConfig OLD_FEATURES_CONFIG = new Config.OldFeaturesConfig();

    @GConfig(value = "bugfixes", visibleName = "Bugfixes")
    public static final Config.BugfixesConfig BUGFIXES_CONFIG = new Config.BugfixesConfig();

    @GConfig(value = "recipes", visibleName = "Recipes")
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();
}
