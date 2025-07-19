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
    private static Option[] VIDEO_OPTIONS;

    @Unique
    private static ArrayList<Option> addedOptions = new ArrayList<>();

    static {
        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fogDensitySlider) {
            addedOptions.add(ModOptions.fogDensityOption);
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudsToggle) {
            addedOptions.add(ModOptions.cloudsOption);
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.cloudHeightSlider) {
            addedOptions.add(ModOptions.cloudHeightOption);
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.brightnessSlider) {
            addedOptions.add(ModOptions.brightnessOption);
        }

        VIDEO_OPTIONS = Arrays.copyOf(VIDEO_OPTIONS, VIDEO_OPTIONS.length + addedOptions.size());
        for (int i = 0; i < addedOptions.size(); i++) {
            VideoOptionsScreenMixin.VIDEO_OPTIONS[VideoOptionsScreenMixin.VIDEO_OPTIONS.length - (addedOptions.size() - i)] = addedOptions.get(i);
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.fpsLimitSlider) {
            VideoOptionsScreenMixin.VIDEO_OPTIONS[3] = ModOptions.fpsLimitOption;
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.renderDistanceSlider) {
            VideoOptionsScreenMixin.VIDEO_OPTIONS[1] = ModOptions.renderDistanceOption;
        }

        if (UniTweaks.USER_INTERFACE_CONFIG.videoSettingsConfig.guiScaleSlider) {
            VideoOptionsScreenMixin.VIDEO_OPTIONS[6] = ModOptions.guiScaleOption;
        }
    }
}
