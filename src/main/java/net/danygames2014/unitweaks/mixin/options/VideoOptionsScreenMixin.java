package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {
    @Shadow
    private static Option[] field_2003;

    @Unique
    private static ArrayList<Option> addedOptions = new ArrayList<>();

    static {
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fogDensitySlider){
            addedOptions.add(ModOptions.fogDensityOption);
        }
        
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudsToggle){
            addedOptions.add(ModOptions.cloudsOption);
        }
        
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudHeightSlider){
            addedOptions.add(ModOptions.cloudHeightOption);
        }
        
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.brightnessSlider){
            addedOptions.add(ModOptions.brightnessOption);
        }

        field_2003 = Arrays.copyOf(field_2003, field_2003.length + addedOptions.size());
        for (int i = 0; i < addedOptions.size(); i++) {
            VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - (addedOptions.size() - i)] = addedOptions.get(i);
        }

        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider){
            VideoOptionsScreenMixin.field_2003[3] = ModOptions.fpsLimitOption;
        }
        
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider){
            VideoOptionsScreenMixin.field_2003[1] = ModOptions.renderDistanceOption;
        }
        
        if(UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.guiScaleSlider){
            VideoOptionsScreenMixin.field_2003[6] = ModOptions.guiScaleOption;
        }
    }
}
