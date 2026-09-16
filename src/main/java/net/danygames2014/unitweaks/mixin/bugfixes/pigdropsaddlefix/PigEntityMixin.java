package net.danygames2014.unitweaks.mixin.bugfixes.pigdropsaddlefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends LivingEntityMixin {
    public PigEntityMixin(World world) {
        super(world);
    }

    @Shadow
    public abstract boolean isSaddled();

    @Override
    protected void dropItems(CallbackInfo ci) {
        if (!UniTweaks.BUGFIXES_CONFIG.pigSaddleDropFix) {
            return;
        }
        
        if (!this.isSaddled()) {
            return;
        }
        
        this.dropItem(Item.SADDLE.id, 1);
    }
}
