package net.danygames2014.unitweaks.mixin.tweaks.ingameversiontext;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = InGameHud.class, priority = 900)
public class InGameHudMixin extends DrawContext {
    @ModifyConstant(method = "render", constant = @Constant(stringValue = "Minecraft Beta 1.7.3 ("), require = 0)
    public String changeVersionText(String constant) {
        if (UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.enableCustomVersionText) {
            return UniTweaks.USER_INTERFACE_CONFIG.versionTextConfig.customVersionText + " (";
        }
        return constant;
    }
}
