package net.danygames2014.unitweaks.util;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;

public class Config {
    public static class GeneralConfig {
        @ConfigName(value = "Show Quit Button")
        public Boolean showQuitButton = true;
    }

    public static class GameplayConfig {
        @ConfigName("No Food Wastage")
        @Comment("Prevents you from eating when your health is full")
        public Boolean noFoodWastage = true;
    }

    public static class BugfixesConfig {
        @ConfigName("Bit Depth Fix")
        @Comment("Increases the buffer depth from 8 to 24 to fix graphical issues on AMD graphic cards")
        public Boolean bitDepthFix = true;
        @ConfigName("Slime Split Fix")
        @Comment("Fixes slimes not splitting when their health is below zero after dying")
        public Boolean enableSlimeSplitFix = true;
    }

    public static class OldFeaturesConfig {
        @ConfigName("Disable Dead Bush Generation")
        public Boolean disableDeadBushGeneration = true;

        @ConfigName("Disable Tall Grass Generation")
        public Boolean disableTallGrassGeneration = true;
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

        @ConfigCategory("Obtainable")
        @Comment("Options here require restart to take effect")
        public ObtainableRecipesConfig obtainable = new ObtainableRecipesConfig();

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
            @ConfigName("Stairs per Craft")
            public Integer stairsPerCraft = 4;
        }

        public static class ObtainableRecipesConfig {
            @ConfigName("Craftable Grass Blocks")
            public Boolean craftableGrassBlocks = true;
            @ConfigName("Craftable Cobwebs")
            public Boolean craftableCobwebs = true;
            @ConfigName("Craftable Fire")
            public Boolean craftableFire = true;
            @ConfigName("Craftable Coal Ore (8 Coal around a Stone)")
            public Boolean craftableCoalOre = true;
            @ConfigName("Craftable Iron Ore (8 Iron Ingots around a Stone)")
            public Boolean craftableIronOre = true;
            @ConfigName("Craftable Gold Ore (8 Gold Ingots around a Stone)")
            public Boolean craftableGoldOre = true;
            @ConfigName("Craftable Lapis Lazuli Ore (8 Lapis Lazuli around a Stone)")
            public Boolean craftableLapisOre = true;
            @ConfigName("Craftable Diamond Ore (8 Diamonds around a Stone)")
            public Boolean craftableDiamondOre = true;
        }
    }
}
