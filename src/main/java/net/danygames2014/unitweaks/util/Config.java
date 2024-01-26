package net.danygames2014.unitweaks.util;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.ConfigCategory;
import net.glasslauncher.mods.api.gcapi.api.ConfigName;
import net.glasslauncher.mods.api.gcapi.api.MaxLength;
import net.glasslauncher.mods.api.gcapi.api.MultiplayerSynced;

public class Config {
    public static class GeneralConfig {
        @ConfigName("Show Quit Button")
        @Comment("Shows Quit Button on the Main Menu")
        public Boolean showQuitButton = true;

        @ConfigName("Improved Controls Menu")
        public Boolean improvedControlsMenu = true;

        @ConfigName("Hide Achievement Toast")
        public Boolean hideAchievementToast = false;

        @ConfigName("Unlicensed Copy Text")
        public Boolean unlicensedCopy = false;

        @ConfigName("Disable F3 Entity ID Tags")
        public Boolean disableDebugEntityIdTags = true;

        @ConfigCategory("Main Menu Panorama")
        public PanoramaConfig panoramaConfig = new PanoramaConfig();

        public static class PanoramaConfig {

            @ConfigName("Enable Panorama")
            public Boolean enablePanorma = true;

            @ConfigName("Blur Panorama")
            public Boolean blurBackground = true;

            @ConfigName("Panorama folder to use")
            @Comment("Default included : beta18, glacier, glacier2")
            public String panoramaFolder = "glacier";
        }

    }

    public static class GameplayConfig {
        @ConfigName("No Food Wastage")
        @Comment("Prevents you from eating when your health is full")
        public Boolean noFoodWastage = true;

        @ConfigName("Step Assist")
        @Comment("Allows you to step up one block")
        public Boolean stepAssist = true;

        @ConfigName("Pick Block from Inventory")
        public Boolean pickBlockFromInventory = true;

        @MultiplayerSynced
        @ConfigName("Shift Placing")
        @Comment("Ignores block actions allowing you to place blocks when crouching")
        public Boolean shiftPlacing = true;

        @MultiplayerSynced
        @ConfigName("Equip armor using right-click")
        public Boolean rightClickEquipArmor = true;
    }

    public static class FeaturesConfig {
        @ConfigCategory("Fast Leaf Decay")
        public FastLeafDecayConfig fastLeafDecay = new FastLeafDecayConfig();

        @ConfigCategory("Better Burning")
        public BetterBurningConfig betterBurning = new BetterBurningConfig();

        public static class FastLeafDecayConfig {
            @MultiplayerSynced
            @ConfigName("Enable Fast Leaf Decay")
            public Boolean enableFastLeafDecay = true;

            @ConfigName("Minimum Decay Time")
            public Integer minimumDecayTime = 10;

            @ConfigName("Maximum Decay Time")
            public Integer maximumDecayTime = 25;
        }

        public static class BetterBurningConfig {
            @MultiplayerSynced
            @ConfigName("Enable Better Burning")
            @Comment("Master switch for all features here")
            public Boolean enableBetterBurning = true;

            @ConfigName("Skeletons on Fire shoot flaming arrows")
            public Boolean skeletonsBurningArrows = true;

            @ConfigName("Skeletons on Fire flaming arrow chance (0-100)")
            @MaxLength(100)
            public Integer skeletonBurningArrowChance = 70;

            @ConfigName("Burning arrows set entities on fire")
            public Boolean burningArrowsSetOnFire = true;

            @ConfigName("Burning entities spread fire to others")
            public Boolean burningEntitySpread = true;

            @ConfigName("Burning entities spread fire chance (0-100)")
            @MaxLength(100)
            public Integer burningEntitySpreadChance = 30;
        }
    }

    public static class TweaksConfig {
        @MultiplayerSynced
        @ConfigName("Allow placing sugar cane on sand")
        public Boolean sugarCaneOnSand = true;

        @MultiplayerSynced
        @ConfigName("Boats drop themselves when broken by a player")
        public Boolean boatsDropThemselves = true;

        @MultiplayerSynced
        @ConfigName("Fences placeable like normal")
        public Boolean fencesPlaceableLikeNormal = true;

        @MultiplayerSynced
        @ConfigName("Fences Connect to Blocks")
        public Boolean fencesConnectBlocks = true;

        @MultiplayerSynced
        @ConfigName("Bookshelves Drop 3 Books")
        public Boolean bookshelvesDropBooks = true;

        @MultiplayerSynced
        @ConfigName("Pressure Plates on Fences")
        public Boolean pressurePlatesOnFences = true;

        @MultiplayerSynced
        @ConfigName("Better Boat handling (rn just quicker)")
        public Boolean betterBoats = false;

        @MultiplayerSynced
        @ConfigName("Harvestable Cobwebs")
        public Boolean harvestableCobwebs = true;

        @MultiplayerSynced
        @ConfigName("Expand Chicken Hitbox")
        @Comment("Expands chicken hitbox to it's modern size")
        public Boolean expandChickenHitbox = true;

        @MultiplayerSynced
        @ConfigName("Don't Damage Flint And Steel on failed ignite")
        public Boolean modernFlintAndSteel = false;

        @MultiplayerSynced
        @ConfigName("Disable sleeping but retain respawning")
        public Boolean disableSleeping = false;

        @ConfigName("Disable spawning mobs when you sleep")
        public Boolean disableSleepSpawning = false;
    }

    public static class BugfixesConfig {
        @ConfigName("Bit Depth Fix")
        @Comment("Increases the buffer depth from 8 to 24 to fix graphical issues on AMD graphic cards")
        public Boolean bitDepthFix = true;

        @MultiplayerSynced
        @ConfigName("Slime Split Fix")
        @Comment("Fixes slimes not splitting when their health is below zero after dying")
        public Boolean enableSlimeSplitFix = true;

        @MultiplayerSynced
        @ConfigName("Boat Dismount Fix")
        @Comment("Fixes sometimes falling through the boat when dismounting it")
        public Boolean boatDismountFix = true;

        @MultiplayerSynced
        @ConfigName("Stairs Drop Fix")
        @Comment("Stairs will drop themselves instead of the base block")
        public Boolean stairsDropFix = true;

        @MultiplayerSynced
        @ConfigName("Block Effectiveness Fix")
        @Comment("Fixes axes and pickaxes not being effective on various blocks")
        public Boolean blockEffectivenessFix = true;

        @MultiplayerSynced
        @ConfigName("Pig Saddle Drop Fix")
        @Comment("Fixes pigs not dropping saddle on death")
        public Boolean pigSaddleDropFix = true;

        @MultiplayerSynced
        @ConfigName("Fence Bounding Box Fix")
        @Comment("Fixes fence bounding box not reflecting the fence state")
        public Boolean fenceBoundingBoxFix = true;

        @ConfigName("Pick Block Fix")
        @Comment("Fixes some blocks not being pickable using Pick Block")
        public Boolean pickBlockFix = true;

        @MultiplayerSynced
        @ConfigName("Spring propagation fix")
        @Comment("Fixes water source blocks not forming when a block below is water")
        public Boolean springPropagationFix = true;

        @MultiplayerSynced
        @ConfigName("Lava Without Source Fix")
        @Comment("Fixes lava not dissapearing without a source block")
        public Boolean lavaWithoutSourceFix = true;

        @ConfigName("Bow Held Fix")
        @Comment("Skeletons and Players now hold bows properly")
        public Boolean bowHeldFix = true;

        @ConfigName("Leggings When Riding Fix")
        public Boolean leggingsWhenRidingFix = true;

        @ConfigName("ItemStack Rendering Fix")
        @Comment("Fixes itemstacks being render below text in containers")
        public Boolean itemstackRenderingFix = true;

    }

    public static class OldFeaturesConfig {
        @MultiplayerSynced
        @ConfigName("Disable Dead Bush Generation")
        public Boolean disableDeadBushGeneration = false;

        @MultiplayerSynced
        @ConfigName("Disable Tall Grass Generation")
        public Boolean disableTallGrassGeneration = false;

        @MultiplayerSynced
        @ConfigName("Punch Sheep for Wool")
        public Boolean punchSheepForWool = false;

        @MultiplayerSynced
        @ConfigName("Punch TNT to Ignite")
        public Boolean punchTntToIgnite = false;

        @MultiplayerSynced
        @ConfigName("Hoe Grass for Seeds")
        public Boolean hoeGrassForSeeds = false;

        @ConfigName("Boat Elevators")
        public Boolean boatElevators = false;

        @ConfigName("Ladder Gaps")
        public Boolean ladderGaps = false;

        @ConfigName("Minecart Boosters")
        public Boolean minecartBoosters = true;
    }

    public static class RecipesConfig {
        @ConfigName("Enable Recipe Tweaks")
        @Comment("Acts as a master switch for all recipe tweaks")
        public Boolean enableRecipes = true;

        @ConfigName("Make more wooden items burnable")
        public Boolean furnaceFuels = true;

        @ConfigName("Allow tool repair in crafting grid")
        public Boolean toolRepair = true;

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
            public Boolean booksRequireLeather = false;
            @ConfigName("Wool Redyeing")
            public Boolean woolRedyeing = true;
            @ConfigName("6 Slabs per Craft")
            public Boolean sixSlabsPerCraft = false;
            @ConfigName("Button Requires 1 Stone")
            public Boolean oneStonePerButton = true;
            @ConfigName("Modern Fence Recipe (4 Planks,2 Sticks)")
            public Boolean modernFenceRecipe = false;
            @ConfigName("Snow Layer Recipe")
            public Boolean snowLayerRecipe = true;
            @ConfigName("3 Laders per Craft")
            public Boolean threeLadersPerCraft = false;
        }

        public static class TweakedRecipesConfig {
            @ConfigName("Shapeless Jack o' Lantern")
            public Boolean shapelessJackOLantern = true;
            @ConfigName("Stairs per Craft")
            public Integer stairsPerCraft = 4;
        }

        public static class ObtainableRecipesConfig {
            @ConfigName("Craftable Grass Blocks")
            public Boolean craftableGrassBlocks = false;
            @ConfigName("Craftable Cobwebs")
            public Boolean craftableCobwebs = false;
            @ConfigName("Craftable Fire")
            public Boolean craftableFire = false;
            @ConfigName("Craftable Coal Ore (8 Coal around a Stone)")
            public Boolean craftableCoalOre = false;
            @ConfigName("Craftable Iron Ore (8 Iron Ingots around a Stone)")
            public Boolean craftableIronOre = false;
            @ConfigName("Craftable Gold Ore (8 Gold Ingots around a Stone)")
            public Boolean craftableGoldOre = false;
            @ConfigName("Craftable Lapis Lazuli Ore (8 Lapis Lazuli around a Stone)")
            public Boolean craftableLapisOre = false;
            @ConfigName("Craftable Diamond Ore (8 Diamonds around a Stone)")
            public Boolean craftableDiamondOre = false;
        }
    }
}
