package net.danygames2014.unitweaks.mixin.tweaks.fasterboats;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    public BoatEntityMixin(World world) {
        super(world);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 0.2))
    public double increaseSpeedMultiplier(double constant) {
        if(UniTweaks.TWEAKS_CONFIG.betterBoats){
            return 0.6;
        }
        return constant;
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 0.4))
    public double increaseMaxSpeed(double constant) {
        if(UniTweaks.TWEAKS_CONFIG.betterBoats){
            return 0.8;
        }
        return constant;
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 0.15))
    public double increaseBreakingSpeedThreshold(double constant) {
        if(UniTweaks.TWEAKS_CONFIG.betterBoats){
            return 1;
        }
        return constant;
    }
}
