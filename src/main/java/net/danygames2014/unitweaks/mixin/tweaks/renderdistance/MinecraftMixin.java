package net.danygames2014.unitweaks.mixin.tweaks.renderdistance;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/GameOptions;setInt(Lnet/minecraft/client/option/Option;I)V"))
    public void changeRenderDistance(GameOptions instance, Option option, int value, Operation<Void> original) {
        if (option == Option.RENDER_DISTANCE) {
            ModOptions.cycleRenderDistance();
            return;
        }

        original.call(instance, option, value);
    }
}
