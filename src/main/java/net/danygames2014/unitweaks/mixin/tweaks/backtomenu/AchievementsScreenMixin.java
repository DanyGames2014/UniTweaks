package net.danygames2014.unitweaks.mixin.tweaks.backtomenu;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.achievement.AchievementsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AchievementsScreen.class)
public class AchievementsScreenMixin extends Screen {
    @Inject(method = "buttonClicked", at = @At(value = "HEAD"), cancellable = true)
    public void clik(ButtonWidget button, CallbackInfo ci){
        if(button.id == 1){
            this.minecraft.setScreen(new GameMenuScreen());
            ci.cancel();
        }
    }
}
