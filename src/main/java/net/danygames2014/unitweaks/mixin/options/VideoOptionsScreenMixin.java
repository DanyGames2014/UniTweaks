package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(VideoOptionsScreen.class)
public class VideoOptionsScreenMixin {
    @Shadow
    private static Option[] field_2003;

    static {
        field_2003 = Arrays.copyOf(field_2003, field_2003.length + 4);
        VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - 4] = ModOptions.brightnessOption;
        VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - 3] = ModOptions.fogDensityOption;
        VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - 2] = ModOptions.cloudsOption;
        VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - 1] = ModOptions.cloudHeightOption;
        VideoOptionsScreenMixin.field_2003[3] = ModOptions.fpsLimitOption;
        VideoOptionsScreenMixin.field_2003[1] = ModOptions.renderDistanceOption;
    }
}
