package net.danygames2014.unitweaks.mixin.tweaks.additionalproperties;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @ModifyExpressionValue(method = "handlePlayerAction", at = @At(value = "CONSTANT", args = "intValue=16"))
    public int overrideSpawnProtection(int original) {
        return ModOptions.spawnProtection;
    }

    @ModifyExpressionValue(method = "onPlayerInteractBlock", at = @At(value = "CONSTANT", args = "intValue=16"))
    public int overrideSpawnProtection2(int original) {
        return ModOptions.spawnProtection;
    }
}
