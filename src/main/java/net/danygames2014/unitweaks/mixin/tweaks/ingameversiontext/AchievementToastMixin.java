package net.danygames2014.unitweaks.mixin.tweaks.ingameversiontext;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.toast.AchievementToast;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(AchievementToast.class)
public class AchievementToastMixin extends DrawContext {
    @Shadow
    private Minecraft client;

    @ModifyConstant(method = "tick", constant = @Constant(stringValue = "Minecraft Beta 1.7.3   Unlicensed Copy :("))
    public String modifyUnlicensedVersionText(String constant) {
        return getVersionText() + "   Unlicensed Copy :(";
    }

    @Unique
    public String getVersionText() {
        if (UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.enableCustomVersionText) {
            return UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.customVersionText;
        } else {
            return "Minecraft Beta 1.7.3";
        }
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void renderVersionText(CallbackInfo ci) {
        if (UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.showVersionTextIngame) {
            if (!(this.client.options.debugHud)) {
                boolean draw = false;
                int color = 16777215;

                if (this.client.currentScreen == null) {
                    draw = true;
                } else if (this.client.currentScreen instanceof GameMenuScreen || this.client.currentScreen instanceof OptionsScreen || this.client.currentScreen instanceof VideoOptionsScreen) {
                    draw = true;
                    color = Color.darkGray.getRGB();
                }

                if (draw) {
                    if (UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.unlicensedCopy) {
                        this.drawTextWithShadow(client.textRenderer, getVersionText() + "   Unlicensed Copy :(", 2, 2, color);
                        this.drawTextWithShadow(client.textRenderer, "(Or logged in from another location)", 2, 11, color);
                        this.drawTextWithShadow(client.textRenderer, "Purchase at minecraft.net", 2, 20, color);
                    } else {
                        this.drawTextWithShadow(client.textRenderer, getVersionText(), 2, 2, color);
                    }
                }
            }
        }
    }
}
