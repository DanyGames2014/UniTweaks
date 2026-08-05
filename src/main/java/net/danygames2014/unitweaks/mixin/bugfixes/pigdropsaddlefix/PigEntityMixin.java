package net.danygames2014.unitweaks.mixin.bugfixes.pigdropsaddlefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends LivingEntityMixin {

    @Override
    protected void dropItems(CallbackInfo ci) {
        PigEntity pigEntity = (PigEntity) (Object) this;

        if (!UniTweaks.BUGFIXES_CONFIG.pigSaddleDropFix) return;

        if (!pigEntity.isSaddled()) return;
        pigEntity.dropItem(Item.SADDLE.id, 1);
    }
}
