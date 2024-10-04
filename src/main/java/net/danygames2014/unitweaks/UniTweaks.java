package net.danygames2014.unitweaks;

import net.danygames2014.unitweaks.util.Config;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class UniTweaks {
    @Entrypoint.Logger
    public static final Logger logger = Null.get();

    @ConfigRoot(value = "general", visibleName = "General", index = 0)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();
    
    @ConfigRoot(value = "userinterface", visibleName = "User Interface", index = 1)
    public static final Config.UserInterfaceConfig USER_INTERFACE_CONFIG = new Config.UserInterfaceConfig();

    @ConfigRoot(value = "gameplay", visibleName = "Gameplay", index = 2)
    public static final Config.GameplayConfig GAMEPLAY_CONFIG = new Config.GameplayConfig();

    @ConfigRoot(value = "features", visibleName = "Features", index = 3)
    public static final Config.FeaturesConfig FEATURES_CONFIG = new Config.FeaturesConfig();

    @ConfigRoot(value = "tweaks", visibleName = "Tweaks", index = 4)
    public static final Config.TweaksConfig TWEAKS_CONFIG = new Config.TweaksConfig();

    @ConfigRoot(value = "bugfixes", visibleName = "Bugfixes", index = 5)
    public static final Config.BugfixesConfig BUGFIXES_CONFIG = new Config.BugfixesConfig();
    
    @ConfigRoot(value = "oldfeatures", visibleName = "Old Features", index = 6)
    public static final Config.OldFeaturesConfig OLD_FEATURES_CONFIG = new Config.OldFeaturesConfig();

    @ConfigRoot(value = "recipes", visibleName = "Recipes", index = 7)
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();
}
