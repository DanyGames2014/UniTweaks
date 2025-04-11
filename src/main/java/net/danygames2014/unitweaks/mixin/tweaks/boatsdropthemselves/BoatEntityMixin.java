package net.danygames2014.unitweaks.mixin.tweaks.boatsdropthemselves;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
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

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin extends Entity {

    public BoatEntityMixin(World world) {
        super(world);
    }

    @WrapOperation(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/BoatEntity;dropItem(IIF)Lnet/minecraft/entity/ItemEntity;", ordinal = 0), require = 0)
    public ItemEntity changeItemId(BoatEntity instance, int id, int count, float offset, Operation<ItemEntity> original) {
        if (UniTweaks.TWEAKS_CONFIG.boatsDropThemselves) {
            return this.dropItem(Item.BOAT.id, 1, offset);
        } else {
            return original.call(instance, id, count, offset);
        }
    }

    @ModifyConstant(method = "damage", constant = @Constant(intValue = 3), require = 0)
    public int changePrimaryDrop(int constant) {
        if (UniTweaks.TWEAKS_CONFIG.boatsDropThemselves) {
            constant = 1;
        }
        return constant;
    }

    @ModifyConstant(method = "damage", constant = @Constant(intValue = 2), require = 0)
    public int disableSecondaryDrop(int constant) {
        if (UniTweaks.TWEAKS_CONFIG.boatsDropThemselves) {
            constant = 0;
        }
        return constant;
    }
}
