package net.danygames2014.unitweaks.mixin.tweaks.bedtweaks;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.BedBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.SleepAttemptResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BedBlock.class)
public class BedBlockMixin {
    @Redirect(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;method_495(III)Lnet/minecraft/entity/player/SleepAttemptResult;"))
    public SleepAttemptResult useinject(PlayerEntity player, int x, int y, int z) {
        if (UniTweaks.TWEAKS_CONFIG.disableSleeping) {
            if (!player.world.isRemote) {
                player.method_490("Respawn Point Set");
                ((PlayerEntityAccessor) player).setRespawnPos(new Vec3i(MathHelper.floor(x), MathHelper.floor(y), MathHelper.floor(z)));
                return SleepAttemptResult.OK;
            }
        }
        return player.method_495(x, y, z);
    }
}
