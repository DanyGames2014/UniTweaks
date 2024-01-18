package net.danygames2014.unitweaks.mixin.tweaks.fasterboats;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BoatEntity.class)
public class BoatEntityMixin {
    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 0.2))
    public double increaseSpeedMultiplier(double constant){
        return 10;
    }

    @ModifyConstant(method = "tick", constant = @Constant(doubleValue = 0.4))
    public double increaseMaxSpeed(double constant){
        return 2;
    }
}
