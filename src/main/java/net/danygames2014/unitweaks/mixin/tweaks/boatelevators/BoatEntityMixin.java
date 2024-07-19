package net.danygames2014.unitweaks.mixin.tweaks.boatelevators;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    public BoatEntityMixin(World world) {
        super(world);
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 1.0, ordinal = 1))
    private double betaTweaks_elevatorBoatCondition(double d) {
        if (UniTweaks.OLD_FEATURES_CONFIG.boatElevators) {
            return Double.MAX_VALUE;
        } else {
            return d;
        }
    }
}
