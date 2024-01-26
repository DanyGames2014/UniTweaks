package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.controls.ControlsScreen;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Shadow
    private static Option[] field_2764;

    @Shadow
    private GameOptions field_2763;

    static {
        field_2764 = Arrays.copyOf(field_2764, field_2764.length + 1);
        OptionsScreenMixin.field_2764[OptionsScreenMixin.field_2764.length - 1] = ModOptions.fovOption;
    }

    @Inject(method = "buttonClicked", at = @At("HEAD"), cancellable = true)
    public void openImprovedControls(ButtonWidget button, CallbackInfo ci) {
        if (UniTweaks.GENERAL_CONFIG.improvedControlsMenu) {
            if (button.id == 100) {
                this.minecraft.setScreen(new ControlsScreen(this, this.field_2763));
                ci.cancel();
            }
        }
    }
}
