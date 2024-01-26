package net.danygames2014.unitweaks.mixin.tweaks.unlicensedcopy;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.class_594;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_594.class)
public class class_594Mixin extends DrawContext {
    @Inject(method = "method_1963", at = @At(value = "HEAD"))
    public void failSessionCheck(CallbackInfo ci){
        if(UniTweaks.GENERAL_CONFIG.unlicensedCopy){
            Minecraft.failedSessionCheckTime = 1L;
        }
    }
}
