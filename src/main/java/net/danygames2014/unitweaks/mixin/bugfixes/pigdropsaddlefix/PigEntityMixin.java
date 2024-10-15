package net.danygames2014.unitweaks.mixin.bugfixes.pigdropsaddlefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity {
    @Shadow
    public abstract boolean isSaddled();

    public PigEntityMixin(World arg) {
        super(arg);
    }

    @Override
    protected void dropItems() {
        if (UniTweaks.BUGFIXES_CONFIG.pigSaddleDropFix) {
            if (isSaddled()) {
                this.dropItem(Item.SADDLE.id, 1);
            }
        }
        super.dropItems();
    }
}
