package net.danygames2014.unitweaks.mixin;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UniTweaksMixinPlugin implements IMixinConfigPlugin {
    public static GlassYamlFile ui_config;

    @Override
    public void onLoad(String mixinPackage) {
        File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "unitweaks/userinterface.yml");

        ui_config = new GlassYamlFile();
        try {
            ui_config.load(file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null; // null = default behaviour
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null; // null = I don't wish to append any mixin
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Cloud Height Slider
        if (isDisabled(mixinClassName, "tweaks.cloudheight.WorldRendererMixin", ui_config, "videoSettingsConfig.cloudHeightSlider")) {
            return false;
        }

        // Clouds Toggle
        //if (isDisabled(mixinClassName, "tweaks.cloudstoggle.WorldRendererMixin", ui_config, "videoSettingsConfig.cloudsToggle")) {
        //    return false;
        //}

        // FPS Limit Slider
        if (isDisabled(mixinClassName, "tweaks.fpslimitslider.class_555Mixin", ui_config, "videoSettingsConfig.fpsLimitSlider")) {
            return false;
        }

        // GUI Scale Slider
        if (isDisabled(mixinClassName, "tweaks.guiscaleslider.ScreenScalerMixin", ui_config, "videoSettingsConfig.guiScaleSlider")) {
            return false;
        }

        // Render Distance
        if (isDisabled(mixinClassName, "tweaks.renderdistance.class555Mixin", ui_config, "videoSettingsConfig.renderDistanceSlider")) {
            return false;
        }
        if (isDisabled(mixinClassName, "tweaks.renderdistance.MinecraftMixin", ui_config, "videoSettingsConfig.renderDistanceSlider")) {
            return false;
        }
        if (isDisabled(mixinClassName, "tweaks.renderdistance.WorldRendererMixin", ui_config, "videoSettingsConfig.renderDistanceSlider")) {
            return false;
        }

        if (FabricLoader.getInstance().isModLoaded("stationapi")) {
            if(nonStationMixins.contains(mixinClassName)) {
                UniTweaks.LOGGER.info("StationAPI Detected. Skipping mixin " + mixinClassName);
                return false;       
            }
        }
        
        if (FabricLoader.getInstance().isModLoaded("nitch")) {
            if (mixinClassName.contains("net.danygames2014.unitweaks.mixin.bugfixes.torchbottomfix")) {
                UniTweaks.LOGGER.info("Nitch Detected. Skipping mixin " + mixinClassName);
                return false;
            }
        }

        return true;
    }
    
    public static ArrayList<String> nonStationMixins = new ArrayList<>(){{
        add("net.danygames2014.unitweaks.mixin.bugfixes.droppeditemfix.ItemRendererMixin");
        add("net.danygames2014.unitweaks.mixin.hooks.GameOptionsMixin");
        add("net.danygames2014.unitweaks.mixin.hooks.MinecraftMixin");
        add("net.danygames2014.unitweaks.mixin.tweaks.mipmap.TextureManagerMixin");
        add("net.danygames2014.unitweaks.mixin.tweaks.recipes.FurnaceBlockEntityMixin");
        add("net.danygames2014.unitweaks.mixin.tweaks.recipes.BlockMixin");
    }};

    public static boolean isDisabled(String mixinClassName, String mixinName, GlassYamlFile config, String configBool) {
        if (config.contains(configBool)) {
            return mixinClassName.equals("net.danygames2014.unitweaks.mixin." + mixinName) && !config.getBoolean(configBool);
        }
        return false;
    }
}
