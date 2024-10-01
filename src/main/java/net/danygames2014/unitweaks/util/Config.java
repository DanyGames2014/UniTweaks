package net.danygames2014.unitweaks.util;

import net.glasslauncher.mods.gcapi3.api.ConfigCategory;
import net.glasslauncher.mods.gcapi3.api.ConfigEntry;
import net.glasslauncher.mods.gcapi3.api.TriBoolean;
import net.glasslauncher.mods.gcapi3.api.ValueOnVanillaServer;

import javax.swing.*;

public class Config {
    public static class GeneralConfig {
        @ConfigEntry(name = "Show Quit Button", description = "Shows Quit Button on the Main Menu")
        public Boolean showQuitButton = true;

        @ConfigEntry(name = "Improved Controls Menu")
        public Boolean improvedControlsMenu = true;

        @ConfigEntry(name = "Pause on Lost Focus")
        public Boolean pauseOnLostFocus = true;

        @ConfigEntry(name = "Hide Achievement Toast")
        public Boolean hideAchievementToast = false;

        @ConfigEntry(name = "Achievement Screen Back To Menu", description = "Done button will lead to Game Menu instead of unpausing")
        public Boolean achievementBackToMenu = true;

        @ConfigEntry(name = "Enable Brightness Slider", description = "Requires a restart to take effect")
        public Boolean brightnessSlider = true;

        @ConfigCategory(name = "Version Text")
        public VersionTextConfig versionTextConfig = new VersionTextConfig();

        @ConfigEntry(name = "Disable F3 Entity ID Tags")
        public Boolean disableDebugEntityIdTags = true;

        @ConfigCategory(name = "Main Menu Panorama")
        public PanoramaConfig panoramaConfig = new PanoramaConfig();

        @ConfigEntry(name = "Autosave Interval (seconds)", maxLength = 3600)
        public Integer autosaveInterval = 1;
        
        @ConfigEntry(name = "TCP NoDelay")
        public Boolean tcpNoDelay = true;
        
        @ConfigEntry(name = "Raw Input")
        public Boolean rawInput = false;
        
        @ConfigEntry(name = "Cloud Height Slider", description = "Requires a restart to take effect")
        public Boolean cloudHeightSlider = true;
        
        @ConfigEntry(name = "Clouds Toggle", description = "Requires a restart to take effect")
        public Boolean cloudsToggle = true;
        
        @ConfigEntry(name = "Fog Density Slider", description = "Requires a restart to take effect")
        public Boolean fogDensitySlider = true;
        
        @ConfigEntry(name = "GUI Scale Slider", description = "Requires a restart to take effect")
        public Boolean guiScaleSlider = true;
        
        @ConfigEntry(name = "FPS Limit Slider", description = "Requires a restart to take effect")
        public Boolean fpsLimitSlider = true;
        
        @ConfigEntry(name = "Render Distance Slider", description = "Requires a restart to take effect")
        public Boolean renderDistanceSlider = true;


        public static class PanoramaConfig {

            @ConfigEntry(name = "Enable Panorama")
            public Boolean enablePanorma = true;

            @ConfigEntry(name = "Blur Panorama")
            public Boolean blurBackground = true;

            @ConfigEntry(name = "Panorama folder to use", description = "Default included : beta18, glacier")
            public String panoramaFolder = "glacier";
        }

        public static class VersionTextConfig {

            @ConfigEntry(name = "Show Version Text Ingame")
            public Boolean showVersionTextIngame = true;

            @ConfigEntry(name = "Unlicensed Copy")
            public Boolean unlicensedCopy = false;

            @ConfigEntry(name = "Enable Custom Version Text")
            public Boolean enableCustomVersionText = false;

            @ConfigEntry(name = "Custom Version Text", maxLength = 64, description = "Only has effect if custom version text is enabled")
            public String customVersionText = "Minecraft Beta 1.7.3 (UniTweaks)";
        }
    }

    public static class GameplayConfig {
        @ConfigEntry(name = "No Food Wastage", description = "Prevents you from eating when your health is full")
        public Boolean noFoodWastage = true;

        @ConfigEntry(name = "Step Assist", description = "Allows you to step up one block")
        public Boolean stepAssist = false;

        @ConfigEntry(name = "Pick Block from Inventory")
        public Boolean pickBlockFromInventory = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(
                name = "Shift Placing", 
                description = "Ignores block actions allowing you to place blocks when crouching",
                multiplayerSynced = true
        )
        public Boolean shiftPlacing = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Equip armor using right-click", multiplayerSynced = true)
        public Boolean rightClickEquipArmor = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Fence Jump", description = "Only works if fence bounding box fix is enabled", multiplayerSynced = true)
        public Boolean fenceJumping = false;
    }

    public static class FeaturesConfig {
        @ConfigCategory(name = "Fast Leaf Decay")
        public FastLeafDecayConfig fastLeafDecay = new FastLeafDecayConfig();

        @ConfigCategory(name = "Better Burning")
        public BetterBurningConfig betterBurning = new BetterBurningConfig();

        public static class FastLeafDecayConfig {
            @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
            @ConfigEntry(name = "Enable Fast Leaf Decay", multiplayerSynced = true)
            public Boolean enableFastLeafDecay = true;

            @ConfigEntry(name = "Minimum Decay Time", maxLength = 1200)
            public Integer minimumDecayTime = 10;

            @ConfigEntry(name = "Maximum Decay Time", maxLength = 1200)
            public Integer maximumDecayTime = 25;
        }

        public static class BetterBurningConfig {
            @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
            @ConfigEntry(name = "Enable Better Burning", description = "Master switch for all features here", multiplayerSynced = true)
            public Boolean enableBetterBurning = true;

            @ConfigEntry(name = "Skeletons on Fire shoot flaming arrows")
            public Boolean skeletonsBurningArrows = true;

            @ConfigEntry(name = "Skeletons on Fire flaming arrow chance (0-100)", maxLength = 100)
            public Integer skeletonBurningArrowChance = 70;

            @ConfigEntry(name = "Burning arrows set entities on fire")
            public Boolean burningArrowsSetOnFire = true;

            @ConfigEntry(name = "Burning entities spread fire to others")
            public Boolean burningEntitySpread = true;

            @ConfigEntry(name = "Burning entities spread fire chance (0-100)", maxLength = 100)
            public Integer burningEntitySpreadChance = 30;
        }
    }

    public static class TweaksConfig {
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Allow placing sugar cane on sand", multiplayerSynced = true)
        public Boolean sugarCaneOnSand = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Boats drop themselves when broken by a player", multiplayerSynced = true)
        public Boolean boatsDropThemselves = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Fences placeable like normal", multiplayerSynced = true)
        public Boolean fencesPlaceableLikeNormal = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Fences Connect to Blocks", multiplayerSynced = true)
        public Boolean fencesConnectBlocks = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Bookshelves Drop 3 Books", multiplayerSynced = true)
        public Boolean bookshelvesDropBooks = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Pressure Plates on Fences", multiplayerSynced = true)
        public Boolean pressurePlatesOnFences = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Better Boat handling (rn just quicker)", multiplayerSynced = true)
        public Boolean betterBoats = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Allow shears to harvest cobwebs and tall grass", multiplayerSynced = true)
        public Boolean shearHarvesting = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Expand Chicken Hitbox", description = "Expands chicken hitbox to it's modern size", multiplayerSynced = true)
        public Boolean expandChickenHitbox = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Don't Damage Flint And Steel on failed ignite", multiplayerSynced = true)
        public Boolean modernFlintAndSteel = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Disable sleeping but retain respawning", multiplayerSynced = true)
        public Boolean disableSleeping = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Disable spawning mobs when you sleep", multiplayerSynced = true)
        public Boolean disableSleepSpawning = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Prevent Items Stopping Minecarts", multiplayerSynced = true)
        public Boolean preventItemsStoppingMinecarts = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Allow igniting entities with flint and steel", multiplayerSynced = true)
        public Boolean allowIgnitingEntities = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Allow placing trapdoors without support", multiplayerSynced = true)
        public Boolean allowTrapdoorsWithoutSupport = true;

        @ConfigEntry(name = "More Sounds", description = "Adds sounds for various things (tool breaking, sheep shearing, eating etc.)")
        public Boolean moreSounds = true;
    }

    public static class BugfixesConfig {
        @ConfigEntry(name = "Bit Depth Fix", description = "Increases the buffer depth from 8 to 24 to fix graphical issues on AMD graphic cards")
        public Boolean bitDepthFix = true;

        @ConfigEntry(name = "HiDPI Fix", description = "Fixes the canvas not adjusting properly on higher than 100% display scaling")
        public Boolean hiDpiFix = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Slime Split Fix", description = "Fixes slimes not splitting when their health is below zero after dying", multiplayerSynced = true)
        public Boolean enableSlimeSplitFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Boat Dismount Fix", description = "Fixes sometimes falling through the boat when dismounting it", multiplayerSynced = true)
        public Boolean boatDismountFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Stairs Drop Fix", description = "Stairs will drop themselves instead of the base block", multiplayerSynced = true)
        public Boolean stairsDropFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Block Effectiveness Fix", description = "Fixes axes and pickaxes not being effective on various blocks", multiplayerSynced = true)
        public Boolean blockEffectivenessFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Pig Saddle Drop Fix", description = "Fixes pigs not dropping saddle on death", multiplayerSynced = true)
        public Boolean pigSaddleDropFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Fence Bounding Box Fix", description = "Fixes fence bounding box not reflecting the fence state", multiplayerSynced = true)
        public Boolean fenceBoundingBoxFix = true;

        @ConfigEntry(name = "Pick Block Fix", description = "Fixes some blocks not being pickable using Pick Block")
        public Boolean pickBlockFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Spring propagation fix", description = "Fixes water source blocks not forming when a block below is water", multiplayerSynced = true)
        public Boolean springPropagationFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Lava Without Source Fix", description = "Fixes lava not dissapearing without a source block", multiplayerSynced = true)
        public Boolean lavaWithoutSourceFix = true;

        @ConfigEntry(name = "Bow Held Fix", description = "Skeletons and Players now hold bows properly")
        public Boolean bowHeldFix = true;

        @ConfigEntry(name = "Leggings When Riding Fix")
        public Boolean leggingsWhenRidingFix = true;

        @ConfigEntry(name = "ItemStack Rendering Fix", description = "Fixes itemstacks being render below text in containers")
        public Boolean itemstackRenderingFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Fish Velocity Fix", description = "Fixes fish flying above the player head when caught", multiplayerSynced = true)
        public Boolean fishVelocityFix = true;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Furnace Consume Bucket Fix", description = "Fixes furnace consuming lava bucket as fuel", multiplayerSynced = true)
        public Boolean furnaceConsumeBucketFix = true;

        @ConfigEntry(name = "Armor Icon Fix")
        public Boolean armorIconsFix = true;

        @ConfigEntry(name = "Dropped Item Size Fix")
        public Boolean droppedItemSizeFix = true;
        
        @ConfigEntry(name = "Breaking Animation Fix", description = "Fixes the breaking animation not rendering on bottom face")
        public Boolean breakingAnimationFix = true;
        
        @ConfigEntry(name = "Death Screen Formatting Fix")
        public Boolean deathScreenFormattingFix = true;
    }

    public static class OldFeaturesConfig {
        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Disable Dead Bush Generation", multiplayerSynced = true)
        public Boolean disableDeadBushGeneration = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Disable Tall Grass Generation", multiplayerSynced = true)
        public Boolean disableTallGrassGeneration = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Punch Sheep for Wool", multiplayerSynced = true)
        public Boolean punchSheepForWool = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Punch TNT to Ignite", multiplayerSynced = true)
        public Boolean punchTntToIgnite = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Hoe Grass for Seeds", multiplayerSynced = true)
        public Boolean hoeGrassForSeeds = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Boat Elevators", multiplayerSynced = true)
        public Boolean boatElevators = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Ladder Gaps", multiplayerSynced = true)
        public Boolean ladderGaps = false;

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Minecart Boosters", multiplayerSynced = true)
        public Boolean minecartBoosters = false;
    }

    public static class RecipesConfig {

        @ValueOnVanillaServer(booleanValue = TriBoolean.FALSE)
        @ConfigEntry(name = "Enable Recipe Tweaks", description = "Acts as a master switch for all recipe tweaks", multiplayerSynced = true)
        public Boolean enableRecipes = true;
        
        @ConfigEntry(name = "Make more wooden items burnable")
        public Boolean furnaceFuels = true;

        @ConfigEntry(name = "Allow tool repair in crafting grid")
        public Boolean toolRepair = true;

        @ConfigCategory(name = "Modern", description = "Options here require restart to take effect")
        public ModernRecipesConfig modern = new ModernRecipesConfig();

        @ConfigCategory(name = "Tweaked", description = "Options here require restart to take effect")
        public TweakedRecipesConfig tweaked = new TweakedRecipesConfig();

        @ConfigCategory(name = "Obtainable", description = "Options here require restart to take effect")
        public ObtainableRecipesConfig obtainable = new ObtainableRecipesConfig();


        public static class ModernRecipesConfig {
            @ConfigEntry(name = "Shapeless Flint and Steel")
            public Boolean shapelessFlintAndSteel = true;
            @ConfigEntry(name = "Shapeless Mushroom Stew")
            public Boolean shapelessMushroomStew = true;
            @ConfigEntry(name = "Shapeless Chest Minecart")
            public Boolean shapelessChestMinecart = true;
            @ConfigEntry(name = "Shapeless Furnace Minecart")
            public Boolean shapelessFurnaceMinecart = true;
            @ConfigEntry(name = "Shapeless Sticky Pistons")
            public Boolean shapelessStickyPistons = true;
            @ConfigEntry(name = "Books Require Leather")
            public Boolean booksRequireLeather = false;
            @ConfigEntry(name = "Wool Redyeing")
            public Boolean woolRedyeing = true;
            @ConfigEntry(name = "6 Slabs per Craft")
            public Boolean sixSlabsPerCraft = false;
            @ConfigEntry(name = "Button Requires 1 Stone")
            public Boolean oneStonePerButton = true;
            @ConfigEntry(name = "Modern Fence Recipe (4 Planks,2 Sticks)")
            public Boolean modernFenceRecipe = false;
            @ConfigEntry(name = "Snow Layer Recipe")
            public Boolean snowLayerRecipe = true;
            @ConfigEntry(name = "3 Laders per Craft")
            public Boolean threeLadersPerCraft = false;
        }

        public static class TweakedRecipesConfig {
            @ConfigEntry(name = "Shapeless Jack o' Lantern")
            public Boolean shapelessJackOLantern = true;
            @ConfigEntry(name = "Stairs per Craft")
            public Integer stairsPerCraft = 4;
        }

        public static class ObtainableRecipesConfig {
            @ConfigEntry(name = "Craftable Grass Blocks")
            public Boolean craftableGrassBlocks = false;
            @ConfigEntry(name = "Craftable Cobwebs")
            public Boolean craftableCobwebs = false;
            @ConfigEntry(name = "Craftable Fire")
            public Boolean craftableFire = false;
            @ConfigEntry(name = "Craftable Coal Ore (8 Coal around a Stone)")
            public Boolean craftableCoalOre = false;
            @ConfigEntry(name = "Craftable Iron Ore (8 Iron Ingots around a Stone)")
            public Boolean craftableIronOre = false;
            @ConfigEntry(name = "Craftable Gold Ore (8 Gold Ingots around a Stone)")
            public Boolean craftableGoldOre = false;
            @ConfigEntry(name = "Craftable Lapis Lazuli Ore (8 Lapis Lazuli around a Stone)")
            public Boolean craftableLapisOre = false;
            @ConfigEntry(name = "Craftable Diamond Ore (8 Diamonds around a Stone)")
            public Boolean craftableDiamondOre = false;
        }
    }
}
