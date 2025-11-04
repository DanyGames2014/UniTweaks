package net.danygames2014.unitweaks.mixin.tweaks.additionalproperties;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.util.ModOptions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerProperties;
import net.minecraft.world.ServerWorld;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow
    public ServerProperties properties;

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Ljava/util/logging/Logger;info(Ljava/lang/String;)V", ordinal = 2))
    public void addSpawnProtectionProp(CallbackInfoReturnable<Boolean> cir) {
        ModOptions.spawnProtection = this.properties.getProperty("spawn-protection", 16);
    }
    
    @WrapOperation(method = "loadWorld", at = @At(value = "FIELD",opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/world/ServerWorld;difficulty:I"))
    public void addDifficultyProp(ServerWorld world, int value, Operation<Void> original) {
        original.call(world, this.properties.getProperty("difficulty", 1));
    }
}
