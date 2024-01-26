package net.danygames2014.unitweaks.mixin.tweaks.hideachievementtoast;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_594;
import net.minecraft.client.gui.DrawContext;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_594.class)
public class class_594Mixin extends DrawContext {

    @Inject(method = "method_1963", at = @At(value = "FIELD", opcode = Opcodes.GETFIELD, target = "Lnet/minecraft/class_594;field_2576:Lnet/minecraft/achievement/Achievement;"), cancellable = true)
    public void disableAchievementToast(CallbackInfo ci) {
        if (UniTweaks.GENERAL_CONFIG.hideAchievementToast) {
            ci.cancel();
        }
    }
}
