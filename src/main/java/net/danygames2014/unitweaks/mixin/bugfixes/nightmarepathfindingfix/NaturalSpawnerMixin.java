package net.danygames2014.unitweaks.mixin.bugfixes.nightmarepathfindingfix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.NaturalSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(NaturalSpawner.class)
public class NaturalSpawnerMixin {
    @WrapOperation(method = "spawnMonstersAndWakePlayers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;abs(D)D", ordinal = 0))
    private static double fixXOffset(double a, Operation<Double> original, @Local(ordinal = 0)PathNode var19, @Local(ordinal = 0)PlayerEntity var5) {
        if (!UniTweaks.BUGFIXES_CONFIG.nightmarePathfindingFix) {
            return original.call(a);
        }
        
        return Math.abs(((double)var19.x + 0.5D) - var5.x);
    }

    @WrapOperation(method = "spawnMonstersAndWakePlayers", at = @At(value = "INVOKE", target = "Ljava/lang/Math;abs(D)D", ordinal = 1))
    private static double fixZOffset(double a, Operation<Double> original, @Local(ordinal = 0)PathNode var19, @Local(ordinal = 0)PlayerEntity var5) {
        if (!UniTweaks.BUGFIXES_CONFIG.nightmarePathfindingFix) {
            return original.call(a);
        }

        return Math.abs(((double)var19.z + 0.5D) - var5.z);
    }
}
