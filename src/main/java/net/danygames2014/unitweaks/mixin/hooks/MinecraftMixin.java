package net.danygames2014.unitweaks.mixin.hooks;

import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyPressedListener;
import net.danygames2014.unitweaks.tweaks.rawinput.RawInputHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "tick",at = @At("RETURN"))
    public void tickEnd(CallbackInfo ci) {
        RawInputHandler.tick();
    }
    
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 2, remap = false))
    public void keyHook(CallbackInfo ci) {
        KeyPressedListener.keyPress();
    }
}
