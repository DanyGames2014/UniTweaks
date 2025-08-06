package net.danygames2014.unitweaks.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.util.ModConflictScreen;
import net.danygames2014.unitweaks.util.ModConflicts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow
    public abstract void setScreen(Screen screen);

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screen/Screen;)V", ordinal = 1))
    public void aaa(Minecraft instance, Screen screen, Operation<Void> original) {
        ModConflicts.detect();
        if (!ModConflicts.detectedConflicts.isEmpty()) {
            this.setScreen(new ModConflictScreen());
        } else {
            original.call(instance, screen);
        }
    }
}
