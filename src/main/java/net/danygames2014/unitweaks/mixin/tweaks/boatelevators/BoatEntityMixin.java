package net.danygames2014.unitweaks.mixin.tweaks.boatelevators;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {
    public BoatEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    public void elevateInWater(CallbackInfo ci) {
        if (UniTweaks.OLD_FEATURES_CONFIG.boatElevators) {
            if (this.isInFluid(Material.WATER)) {
                this.velocityY += 0.051F;
            }

            if (this.velocityY > 0.05F) {
                if (this.isInFluid(Material.WATER)) {
                    this.velocityY *= 1.01F;
                }
            }
        }
    }
}
