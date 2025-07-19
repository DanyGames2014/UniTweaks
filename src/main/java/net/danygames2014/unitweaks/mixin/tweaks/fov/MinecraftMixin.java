package net.danygames2014.unitweaks.mixin.tweaks.fov;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.danygames2014.unitweaks.util.ModOptions;
import net.danygames2014.unitweaks.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;scrollInHotbar(I)V"))
    public boolean disableScrollWhenZooming(PlayerInventory instance, int scroll) {
        if (Keyboard.isKeyDown(KeyBindingListener.zoom.code)) {
            ModOptions.addZoomFovOffset(-Util.clamp(scroll, -1, 1));
            return false;
        }
        return true;
    }
}
