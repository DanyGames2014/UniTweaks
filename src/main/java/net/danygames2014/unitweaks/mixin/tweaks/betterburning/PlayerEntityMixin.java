package net.danygames2014.unitweaks.mixin.tweaks.betterburning;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "damage(Lnet/minecraft/entity/Entity;I)Z", at = @At("HEAD"))
    public void setOnFireIfAttackerBurning(Entity attacker, int amount, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.FEATURES_CONFIG.betterBurning.enableBetterBurning && UniTweaks.FEATURES_CONFIG.betterBurning.burningEntitySpread && !this.world.isRemote) {
            if (!(attacker instanceof SkeletonEntity) && attacker != null) {
                if (this.random.nextInt(0, 100) < UniTweaks.FEATURES_CONFIG.betterBurning.burningEntitySpreadChance) {
                    if (attacker.fire > 0) {
                        this.fire = 100;
                    }
                }
            }
        }

    }
}
