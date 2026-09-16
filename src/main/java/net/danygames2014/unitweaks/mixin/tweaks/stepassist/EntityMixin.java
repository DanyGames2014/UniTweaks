package net.danygames2014.unitweaks.mixin.tweaks.stepassist;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract boolean isSneaking();

    @WrapOperation(method = "move", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;stepHeight:F"))
    public float injectChangeStepHeight(Entity instance, Operation<Float> original) {
        return original.call(instance);
    }
}
