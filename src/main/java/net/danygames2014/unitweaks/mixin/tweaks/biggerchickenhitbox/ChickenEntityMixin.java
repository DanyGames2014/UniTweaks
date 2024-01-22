package net.danygames2014.unitweaks.mixin.tweaks.biggerchickenhitbox;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.passive.ChickenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ChickenEntity.class)
public class ChickenEntityMixin {
    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 0.3F))
    public float modifyWidth(float constant) {
        if (UniTweaks.TWEAKS_CONFIG.expandChickenHitbox) {
            return 0.4F;
        }
        return constant;
    }

    @ModifyConstant(method = "<init>", constant = @Constant(floatValue = 0.4F))
    public float modifyHeight(float constant) {
        if (UniTweaks.TWEAKS_CONFIG.expandChickenHitbox) {
            return 0.7F;
        }
        return constant;
    }
}
