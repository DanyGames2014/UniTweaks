package net.danygames2014.unitweaks.mixin.bugfixes.pigdropsaddlefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "dropItems", at = @At("HEAD"))
    private void dropItems(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (!(livingEntity instanceof PigEntity pigEntity)) return;
        if (!UniTweaks.BUGFIXES_CONFIG.pigSaddleDropFix) return;

        if (!pigEntity.isSaddled()) return;
        pigEntity.dropItem(Item.SADDLE.id, 1);
    }
}
