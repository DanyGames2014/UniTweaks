package net.danygames2014.unitweaks.mixin.tweaks.boatsdropthemselves;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {

    public BoatEntityMixin(World world) {
        super(world);
    }

    @Redirect(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;method_1325(IIF)Lnet/minecraft/entity/ItemEntity;", ordinal = 0))
    public ItemEntity changeItemId(BoatEntity instance, int id, int count, float offset){
        if(UniTweaks.TWEAKS_CONFIG.boatsDropThemselves){
            return this.method_1325(Item.BOAT.id, 1, offset);
        }else{
            return this.method_1325(id, count, offset);
        }
    }

    @ModifyConstant(method = "damage", constant = @Constant(intValue = 3))
    public int changePrimaryDrop(int constant){
        if(UniTweaks.TWEAKS_CONFIG.boatsDropThemselves){
            constant = 1;
        }
        return constant;
    }

    @ModifyConstant(method = "damage", constant = @Constant(intValue = 2))
    public int disableSecondaryDrop(int constant){
        if(UniTweaks.TWEAKS_CONFIG.boatsDropThemselves){
            constant = 0;
        }
        return constant;
    }
}
