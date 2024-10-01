package net.danygames2014.unitweaks.mixin;

import net.danygames2014.unitweaks.UniTweaks;
import net.fabricmc.loader.api.FabricLoader;
import net.glasslauncher.mods.gcapi3.impl.GlassYamlFile;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UniTweaksMixinPlugin implements IMixinConfigPlugin {
    public static GlassYamlFile config;
    
    @Override
    public void onLoad(String mixinPackage) {
        File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "unitweaks/general.yml");
        
        config = new GlassYamlFile();
        try {
            config.load(file);
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

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // Cloud Height Slider
        if (isDisabled(mixinClassName, "tweaks.cloudheight.WorldRendererMixin", "cloudHeightSlider")) {
            return false;
        }

        // Clouds Toggle
        if(isDisabled(mixinClassName, "tweaks.cloudstoggle.WorldRendererMixin", "cloudsToggle")){
            return false;
        }

        // FPS Limit Slider
        if(isDisabled(mixinClassName, "tweaks.fpslimitslider.class_555Mixin", "fpsLimitSlider")){
            return false;
        }

        // GUI Scale Slider
        if(isDisabled(mixinClassName, "tweaks.guiscaleslider.ScreenScalerMixin", "guiScaleSlider")){
            return false;
        }

        // Render Distance
        if(isDisabled(mixinClassName, "tweaks.renderdistance.class555Mixin", "renderDistanceSlider")){
            return false;            
        }
        if(isDisabled(mixinClassName, "tweaks.renderdistance.MinecraftMixin", "renderDistanceSlider")){
            return false;
        }
        if(isDisabled(mixinClassName, "tweaks.renderdistance.WorldRendererMixin", "renderDistanceSlider")){
            return false;
        }

        return true;
    }

    public static boolean isDisabled(String mixinClassName, String mixinName, String configBool) {
        if(config.contains(configBool)){
            return mixinClassName.equals("net.danygames2014.unitweaks.mixin." + mixinName) && !config.getBoolean(configBool);
        }
        return true;        
    }
}
