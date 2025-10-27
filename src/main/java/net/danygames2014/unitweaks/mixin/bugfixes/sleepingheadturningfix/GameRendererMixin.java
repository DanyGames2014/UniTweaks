package net.danygames2014.unitweaks.mixin.bugfixes.sleepingheadturningfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.BedBlock;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @WrapOperation(method = "onFrameUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;changeLookDirection(FF)V"))
    public void disableTurning(ClientPlayerEntity instance, float cursorDeltaX, float cursorDeltaY, Operation<Void> original) {
        if (!instance.isSleeping() || !UniTweaks.BUGFIXES_CONFIG.sleepingCameraRotationFix) {
            original.call(instance, cursorDeltaX, cursorDeltaY);
            return;
        }

        instance.pitch = 0;
        instance.yaw = getSleepingRotation(instance);
        original.call(instance, 0.0F, 0.0F);
    }

    @Unique
    public float getSleepingRotation(ClientPlayerEntity player) {
        if (player.sleepingPos != null) {
            int bedMeta = player.world.getBlockMeta(player.sleepingPos.x, player.sleepingPos.y, player.sleepingPos.z);
            int bedDirection = BedBlock.getDirection(bedMeta);
            switch (bedDirection) {
                case 0:
                    return 180.0F;
                case 1:
                    return 270.0F;
                case 2:
                    return 0.0F;
                case 3:
                    return 90.0F;
            }
        }

        return 0.0F;
    }
}
