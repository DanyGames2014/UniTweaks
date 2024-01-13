package net.danygames2014.unitweaks.util;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;

public class Config {
    public static class GeneralConfig {
        @ConfigName(value = "General Config Value")
        public Integer test2 = 0;
    }

    public static class RecipesConfig {
        @ConfigName("Enable Recipe Tweaks")
        @Comment("Acts as a master switch for all recipe tweaks")
        public Boolean enableRecipes = true;

        @ConfigCategory("Modern")
        @Comment("Options here require restart to take effect")
        public ModernRecipesConfig modern = new ModernRecipesConfig();

        @ConfigCategory("Tweaked")
        @Comment("Options here require restart to take effect")
        public TweakedRecipesConfig tweaked = new TweakedRecipesConfig();

        public static class ModernRecipesConfig {
            @ConfigName("Shapeless Flint and Steel")
            public Boolean shapelessFlintAndSteel = true;
            @ConfigName("Shapeless Mushroom Stew")
            public Boolean shapelessMushroomStew = true;
            @ConfigName("Shapeless Chest Minecart")
            public Boolean shapelessChestMinecart = true;
            @ConfigName("Shapeless Furnace Minecart")
            public Boolean shapelessFurnaceMinecart = true;
            @ConfigName("Shapeless Sticky Pistons")
            public Boolean shapelessStickyPistons = true;
            @ConfigName("Books Require Leather")
            public Boolean booksRequireLeather = true;
            @ConfigName("Wool Redyeing")
            public Boolean woolRedyeing = true;
            @ConfigName("6 Slabs per Craft")
            public Boolean sixSlabsPerCraft = true;
            @ConfigName("Button Requires 1 Stone")
            public Boolean oneStonePerButton = true;
            @ConfigName("Modern Fence Recipe (4 Planks,2 Sticks)")
            public Boolean modernFenceRecipe = true;
            @ConfigName("Snow Layer Recipe")
            public Boolean snowLayerRecipe = true;
            @ConfigName("3 Laders per Craft")
            public Boolean threeLadersPerCraft = true;
        }

        public static class TweakedRecipesConfig {
            @ConfigName("Shapeless Jack o' Lantern")
            public Boolean shapelessJackOLantern = true;
        }
    }
}
