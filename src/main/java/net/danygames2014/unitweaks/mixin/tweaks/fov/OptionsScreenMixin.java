package net.danygames2014.unitweaks.mixin.tweaks.fov;

import net.danygames2014.unitweaks.tweaks.fov.FovData;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin {

    @Shadow
    private static Option[] field_2764;

    static {
        field_2764 = Arrays.copyOf(field_2764, field_2764.length + 1);
        OptionsScreenMixin.field_2764[OptionsScreenMixin.field_2764.length - 1] = FovData.fovOption;
    }
}
