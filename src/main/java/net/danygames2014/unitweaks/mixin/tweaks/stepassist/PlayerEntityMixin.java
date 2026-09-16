package net.danygames2014.unitweaks.mixin.tweaks.stepassist;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends EntityMixin {
    @Override
    public float injectChangeStepHeight(Entity instance, Operation<Float> original) {
        Float stepHeight = original.call(instance);
        
        if (UniTweaks.FEATURES_CONFIG.stepAssist) {
            stepHeight += (this.isSneaking() ? 0.0F : 0.5F);
        }
        
        return stepHeight;
    }
}
