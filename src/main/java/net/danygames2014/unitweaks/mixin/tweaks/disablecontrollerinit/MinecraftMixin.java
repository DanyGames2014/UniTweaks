package net.danygames2014.unitweaks.mixin.tweaks.disablecontrollerinit;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapWithCondition(method = "init", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Controllers;create()V"))
    public boolean disableControllerInit() {
        return !UniTweaks.GENERAL_CONFIG.disableControllerInit;
    }
}
