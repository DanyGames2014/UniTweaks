package net.danygames2014.unitweaks.mixin.tweaks.fogdensity;

import net.danygames2014.unitweaks.mixin.tweaks.fov.OptionsScreenMixin;
import net.danygames2014.unitweaks.tweaks.fogdensity.FogDensityData;
import net.danygames2014.unitweaks.tweaks.fov.FovData;
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
        field_2003 = Arrays.copyOf(field_2003, field_2003.length + 1);
        VideoOptionsScreenMixin.field_2003[VideoOptionsScreenMixin.field_2003.length - 1] = FogDensityData.fogDensityOption;
    }
}
