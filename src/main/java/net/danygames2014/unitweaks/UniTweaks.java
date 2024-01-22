package net.danygames2014.unitweaks;

import net.danygames2014.unitweaks.util.Config;
import net.glasslauncher.mods.api.gcapi.api.GConfig;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class UniTweaks {
    @Entrypoint.Logger
    public static final Logger logger = Null.get();

    @GConfig(value = "general", visibleName = "General", primary = true)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();

    @GConfig(value = "gameplay", visibleName = "Gameplay")
    public static final Config.GameplayConfig GAMEPLAY_CONFIG = new Config.GameplayConfig();

    @GConfig(value = "tweaks", visibleName = "Tweaks")
    public static final Config.TweaksConfig TWEAKS_CONFIG = new Config.TweaksConfig();

    @GConfig(value = "oldfeatures", visibleName = "Old Features")
    public static final Config.OldFeaturesConfig OLD_FEATURES_CONFIG = new Config.OldFeaturesConfig();

    @GConfig(value = "bugfixes", visibleName = "Bugfixes")
    public static final Config.BugfixesConfig BUGFIXES_CONFIG = new Config.BugfixesConfig();

    @GConfig(value = "recipes", visibleName = "Recipes")
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();
}
