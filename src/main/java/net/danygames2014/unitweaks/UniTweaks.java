package net.danygames2014.unitweaks;

import net.danygames2014.unitweaks.util.Config;
import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class UniTweaks {
    @Entrypoint.Logger
    public static final Logger logger = Null.get();

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @ConfigRoot(value = "general", visibleName = "General", index = 0)
    public static final Config.GeneralConfig GENERAL_CONFIG = new Config.GeneralConfig();

    @ConfigRoot(value = "gameplay", visibleName = "Gameplay", index = 1)
    public static final Config.GameplayConfig GAMEPLAY_CONFIG = new Config.GameplayConfig();

    @ConfigRoot(value = "features", visibleName = "Features", index = 2)
    public static final Config.FeaturesConfig FEATURES_CONFIG = new Config.FeaturesConfig();

    @ConfigRoot(value = "tweaks", visibleName = "Tweaks", index = 3)
    public static final Config.TweaksConfig TWEAKS_CONFIG = new Config.TweaksConfig();

    @ConfigRoot(value = "bugfixes", visibleName = "Bugfixes", index = 4)
    public static final Config.BugfixesConfig BUGFIXES_CONFIG = new Config.BugfixesConfig();
    
    @ConfigRoot(value = "oldfeatures", visibleName = "Old Features", index = 5)
    public static final Config.OldFeaturesConfig OLD_FEATURES_CONFIG = new Config.OldFeaturesConfig();

    @ConfigRoot(value = "recipes", visibleName = "Recipes", index = 6)
    public static final Config.RecipesConfig RECIPES_CONFIG = new Config.RecipesConfig();

    // These will eventually be completely redone, probably with the gcapi merge into StAPI

    // General, Gameplay, Tweaks - These have to be redone, there isnt a clear theme to any of these
    // categories, currently thinking about User Interface, Tweaks, QoL etc. categories

    // Features - This was supposed to be extra features that actually add a game mechanic

    // Bugfixes - This will stay but might need to create sub-categories and evaluate what is bugfix and what is tweak
    // Old Features - This can stay, the category is small enough and descriptive enough
    // Recipes - This can probably stay
}
