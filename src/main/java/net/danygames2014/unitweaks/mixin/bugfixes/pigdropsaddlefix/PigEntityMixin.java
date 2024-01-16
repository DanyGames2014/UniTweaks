package net.danygames2014.unitweaks.mixin.bugfixes.pigdropsaddlefix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(PigEntity.class)
public abstract class PigEntityMixin extends AnimalEntity {
    @Shadow public abstract boolean method_1724();

    public PigEntityMixin(World arg) {
        super(arg);
    }

    @Override
    protected void method_933() {
        if(UniTweaks.BUGFIXES_CONFIG.pigSaddleDropFix){
            if(method_1724()){
                this.method_1339(Item.SADDLE.id, 1);
            }
        }
        super.method_933();
    }
}
