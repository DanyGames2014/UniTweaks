package net.danygames2014.unitweaks.mixin;

import net.danygames2014.unitweaks.UniTweaks;
import net.glasslauncher.mods.api.gcapi.api.GCAPI;
import net.glasslauncher.mods.api.gcapi.impl.GCCore;
import net.modificationstation.stationapi.api.util.Identifier;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class UniTweaksMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        //GCAPI.reloadConfig(Identifier.of("unitweaks:general"));
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
        System.out.println(mixinClassName);

        // Cloud Height Slider
        if (isDisabled(mixinClassName, "tweaks.cloudheight.WorldRendererMixin", UniTweaks.GENERAL_CONFIG.cloudHeightSlider)) {
            return false;
        }

        // Clouds Toggle
        if(isDisabled(mixinClassName, "tweaks.cloudstoggle.WorldRendererMixin", UniTweaks.GENERAL_CONFIG.cloudsToggle)){
            return false;
        }

        // FPS Limit Slider
        if(isDisabled(mixinClassName, "tweaks.fpslimitslider.class_555Mixin", UniTweaks.GENERAL_CONFIG.fpsLimitSlider)){
            return false;
        }

        // GUI Scale Slider
        if(isDisabled(mixinClassName, "tweaks.guiscaleslider.ScreenScalerMixin", UniTweaks.GENERAL_CONFIG.guiScaleSlider)){
            return false;
        }

        // Render Distance
        if(isDisabled(mixinClassName, "tweaks.renderdistance.class555Mixin", UniTweaks.GENERAL_CONFIG.renderDistanceSlider)){
            return false;            
        }
        if(isDisabled(mixinClassName, "tweaks.renderdistance.MinecraftMixin", UniTweaks.GENERAL_CONFIG.renderDistanceSlider)){
            return false;
        }
        if(isDisabled(mixinClassName, "tweaks.renderdistance.WorldRendererMixin", UniTweaks.GENERAL_CONFIG.renderDistanceSlider)){
            return false;
        }

        return true;
    }

    public static boolean isDisabled(String mixinClassName, String mixinName, boolean configOption) {
        return mixinClassName.equals("net.danygames2014.unitweaks.mixin." + mixinName) && !configOption;
    }
}
