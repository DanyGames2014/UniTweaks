package net.danygames2014.unitweaks.util;

import blue.endless.jankson.Comment;
import net.glasslauncher.mods.api.gcapi.api.*;

public class Config {
    public static class GeneralConfig {
        @ConfigName("Show Quit Button")
        @Comment("Shows Quit Button on the Main Menu")
        public Boolean showQuitButton = true;

        @ConfigName("Improved Controls Menu")
        public Boolean improvedControlsMenu = true;

        @ConfigName("Pause on Lost Focus")
        public Boolean pauseOnLostFocus = true;

        @ConfigName("Hide Achievement Toast")
        public Boolean hideAchievementToast = false;

        @ConfigName("Achievement Screen Back To Menu")
        @Comment("Done button will lead to Game Menu instead of unpausing")
        public Boolean achievementBackToMenu = true;

        @ConfigName("Enable Brightness Slider")
        @Comment("Requires a restart to take effect")
        public Boolean brightnessSlider = true;

        @ConfigCategory("Version Text")
        public VersionTextConfig versionTextConfig = new VersionTextConfig();

        @ConfigName("Disable F3 Entity ID Tags")
        public Boolean disableDebugEntityIdTags = true;

        @ConfigCategory("Main Menu Panorama")
        public PanoramaConfig panoramaConfig = new PanoramaConfig();

        @ConfigName("Autosave Interval (seconds)")
        @MaxLength(3600)
        public Integer autosaveInterval = 1;


        public static class PanoramaConfig {

            @ConfigName("Enable Panorama")
            public Boolean enablePanorma = true;

            @ConfigName("Blur Panorama")
            public Boolean blurBackground = true;

            @ConfigName("Panorama folder to use")
            @Comment("Default included : beta18, glacier")
            public String panoramaFolder = "glacier";
        }

        public static class VersionTextConfig {

            @ConfigName("Show Version Text Ingame")
            public Boolean showVersionTextIngame = true;

            @ConfigName("Unlicensed Copy")
            public Boolean unlicensedCopy = false;

            @ConfigName("Enable Custom Version Text")
            public Boolean enableCustomVersionText = false;

            @ConfigName("Custom Version Text")
            @Comment("Only has effect if custom version text is enabled")
            @MaxLength(64)
            public String customVersionText = "Minecraft Beta 1.7.3 (UniTweaks)";
        }
    }

    public static class GameplayConfig {
        @ConfigName("No Food Wastage")
        @Comment("Prevents you from eating when your health is full")
        public Boolean noFoodWastage = true;

        @ConfigName("Step Assist")
        @Comment("Allows you to step up one block")
        public Boolean stepAssist = false;

        @ConfigName("Pick Block from Inventory")
        public Boolean pickBlockFromInventory = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Shift Placing")
        @Comment("Ignores block actions allowing you to place blocks when crouching")
        public Boolean shiftPlacing = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Equip armor using right-click")
        public Boolean rightClickEquipArmor = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Fence Jump")
        @Comment("Only works if fence bounding box fix is enabled")
        public Boolean fenceJumping = false;
    }

    public static class FeaturesConfig {
        @ConfigCategory("Fast Leaf Decay")
        public FastLeafDecayConfig fastLeafDecay = new FastLeafDecayConfig();

        @ConfigCategory("Better Burning")
        public BetterBurningConfig betterBurning = new BetterBurningConfig();

        public static class FastLeafDecayConfig {
            @MultiplayerSynced
            @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
            @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
            @ConfigName("Enable Fast Leaf Decay")
            public Boolean enableFastLeafDecay = true;

            @ConfigName("Minimum Decay Time")
            @MaxLength(1200)
            public Integer minimumDecayTime = 10;

            @ConfigName("Maximum Decay Time")
            @MaxLength(1800)
            public Integer maximumDecayTime = 25;
        }

        public static class BetterBurningConfig {
            @MultiplayerSynced
            @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
            @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
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
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Allow placing sugar cane on sand")
        public Boolean sugarCaneOnSand = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Boats drop themselves when broken by a player")
        public Boolean boatsDropThemselves = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Fences placeable like normal")
        public Boolean fencesPlaceableLikeNormal = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Fences Connect to Blocks")
        public Boolean fencesConnectBlocks = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Bookshelves Drop 3 Books")
        public Boolean bookshelvesDropBooks = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Pressure Plates on Fences")
        public Boolean pressurePlatesOnFences = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Better Boat handling (rn just quicker)")
        public Boolean betterBoats = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Allow shears to harvest cobwebs and tall grass")
        public Boolean shearHarvesting = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Expand Chicken Hitbox")
        @Comment("Expands chicken hitbox to it's modern size")
        public Boolean expandChickenHitbox = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Don't Damage Flint And Steel on failed ignite")
        public Boolean modernFlintAndSteel = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Disable sleeping but retain respawning")
        public Boolean disableSleeping = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Disable spawning mobs when you sleep")
        public Boolean disableSleepSpawning = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Prevent Items Stopping Minecarts")
        public Boolean preventItemsStoppingMinecarts = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Allow igniting entities with flint and steel")
        public Boolean allowIgnitingEntities = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Allow placing trapdoors without support")
        public Boolean allowTrapdoorsWithoutSupport = true;

        @ConfigName("More Sounds")
        @Comment("Adds sounds for various things (tool breaking, sheep shearing, eating etc.)")
        public Boolean moreSounds = true;
    }

    public static class BugfixesConfig {
        @ConfigName("Bit Depth Fix")
        @Comment("Increases the buffer depth from 8 to 24 to fix graphical issues on AMD graphic cards")
        public Boolean bitDepthFix = true;

        @ConfigName("HiDPI Fix")
        @Comment("Fixes the canvas not adjusting properly on higher than 100% display scaling")
        public Boolean hiDpiFix = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Slime Split Fix")
        @Comment("Fixes slimes not splitting when their health is below zero after dying")
        public Boolean enableSlimeSplitFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Boat Dismount Fix")
        @Comment("Fixes sometimes falling through the boat when dismounting it")
        public Boolean boatDismountFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Stairs Drop Fix")
        @Comment("Stairs will drop themselves instead of the base block")
        public Boolean stairsDropFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Block Effectiveness Fix")
        @Comment("Fixes axes and pickaxes not being effective on various blocks")
        public Boolean blockEffectivenessFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Pig Saddle Drop Fix")
        @Comment("Fixes pigs not dropping saddle on death")
        public Boolean pigSaddleDropFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Fence Bounding Box Fix")
        @Comment("Fixes fence bounding box not reflecting the fence state")
        public Boolean fenceBoundingBoxFix = true;

        @ConfigName("Pick Block Fix")
        @Comment("Fixes some blocks not being pickable using Pick Block")
        public Boolean pickBlockFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Spring propagation fix")
        @Comment("Fixes water source blocks not forming when a block below is water")
        public Boolean springPropagationFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
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

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Fish Velocity Fix")
        @Comment("Fixes fish flying above the player head when caught")
        public Boolean fishVelocityFix = true;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Furnace Consume Bucket Fix")
        @Comment("Fixes furnace consuming lava bucket as fuel")
        public Boolean furnaceConsumeBucketFix = true;

        @ConfigName("Armor Icon Fix")
        public Boolean armorIconsFix = true;

        @ConfigName("Dropped Item Size Fix")
        public Boolean droppedItemSizeFix = true;
    }

    public static class OldFeaturesConfig {
        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Disable Dead Bush Generation")
        public Boolean disableDeadBushGeneration = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Disable Tall Grass Generation")
        public Boolean disableTallGrassGeneration = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Punch Sheep for Wool")
        public Boolean punchSheepForWool = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Punch TNT to Ignite")
        public Boolean punchTntToIgnite = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Hoe Grass for Seeds")
        public Boolean hoeGrassForSeeds = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Boat Elevators")
        public Boolean boatElevators = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Ladder Gaps")
        public Boolean ladderGaps = false;

        @MultiplayerSynced
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigName("Minecart Boosters")
        public Boolean minecartBoosters = false;
    }

    public static class RecipesConfig {
        @ConfigName("Enable Recipe Tweaks")
        @ValueOnNoGCAPIServer(booleanValue = TriBoolean.FALSE)
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
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
