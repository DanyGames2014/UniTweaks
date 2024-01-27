package net.danygames2014.unitweaks.mixin.tweaks.ingameversiontext;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_594;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
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

@Mixin(class_594.class)
public class class_594Mixin extends DrawContext {
    @Shadow
    private Minecraft field_2571;

    @ModifyConstant(method = "method_1963", constant = @Constant(stringValue = "Minecraft Beta 1.7.3   Unlicensed Copy :("))
    public String modifyUnlicensedVersionText(String constant) {
        return getVersionText() + "   Unlicensed Copy :(";
    }

    @Unique
    public String getVersionText() {
        if (UniTweaks.GENERAL_CONFIG.versionTextConfig.enableCustomVersionText) {
            return UniTweaks.GENERAL_CONFIG.versionTextConfig.customVersionText;
        } else {
            return "Minecraft Beta 1.7.3";
        }
    }

    @Inject(method = "method_1963", at = @At(value = "HEAD"))
    public void renderVersionText(CallbackInfo ci) {
        if (UniTweaks.GENERAL_CONFIG.versionTextConfig.showVersionTextIngame) {
            if (!(this.field_2571.options.debugHud)) {
                boolean draw = false;
                int color = 16777215;

                if (this.field_2571.currentScreen == null) {
                    draw = true;
                } else if (this.field_2571.currentScreen instanceof GameMenuScreen || this.field_2571.currentScreen instanceof OptionsScreen || this.field_2571.currentScreen instanceof VideoOptionsScreen) {
                    draw = true;
                    color = Color.darkGray.getRGB();
                }

                if (draw) {
                    if (UniTweaks.GENERAL_CONFIG.versionTextConfig.unlicensedCopy) {
                        this.drawTextWithShadow(field_2571.textRenderer, getVersionText() + "   Unlicensed Copy :(", 2, 2, color);
                        this.drawTextWithShadow(field_2571.textRenderer, "(Or logged in from another location)", 2, 11, color);
                        this.drawTextWithShadow(field_2571.textRenderer, "Purchase at minecraft.net", 2, 20, color);
                    } else {
                        this.drawTextWithShadow(field_2571.textRenderer, getVersionText(), 2, 2, color);
                    }
                }
            }
        }
    }
}
