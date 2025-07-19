package net.danygames2014.unitweaks.mixin.bugfixes.deathscreenscorefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.gui.screen.DeathScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(DeathScreen.class)
public class DeathScreenMixin {
    @ModifyConstant(method = "render", constant = @Constant(stringValue = "Score: &e"), require = 0)
    public String fixFormattingCharacter(String constant) {
        if (UniTweaks.BUGFIXES_CONFIG.deathScreenFormattingFix) {
            return constant.replace('&', '§');
        }
        return constant;
    }
}
