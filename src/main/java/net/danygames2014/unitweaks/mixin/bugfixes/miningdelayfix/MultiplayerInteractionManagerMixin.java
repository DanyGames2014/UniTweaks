package net.danygames2014.unitweaks.mixin.bugfixes.miningdelayfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MultiplayerInteractionManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MultiplayerInteractionManager.class)
public class MultiplayerInteractionManagerMixin extends InteractionManager {
    public MultiplayerInteractionManagerMixin(Minecraft minecraft) {
        super(minecraft);
    }

    @SuppressWarnings("SimplifiableConditionalExpression")
    @WrapOperation(method = "processBlockBreakingAction", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MultiplayerInteractionManager;breakingBlock:Z", opcode = Opcodes.GETFIELD, ordinal = 0))
    public boolean removeRandomDelay(MultiplayerInteractionManager instance, Operation<Boolean> original) {
        return UniTweaks.BUGFIXES_CONFIG.miningDelayFix ? true : original.call(instance);
    }
}
