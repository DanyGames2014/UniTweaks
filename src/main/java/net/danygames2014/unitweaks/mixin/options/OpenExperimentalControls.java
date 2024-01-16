package net.danygames2014.unitweaks.mixin.options;

import net.danygames2014.unitweaks.tweaks.controls.ControlsScreen;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class OpenExperimentalControls extends Screen {
    @Inject(method = "keyPressed", at = @At("HEAD"))
    public void injectKey(char character, int keyCode, CallbackInfo ci){
        if (keyCode == Keyboard.KEY_LCONTROL){
            this.minecraft.setScreen(new ControlsScreen(this, this.minecraft.options));
        }
    }
}
