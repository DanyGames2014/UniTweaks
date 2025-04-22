package net.danygames2014.unitweaks.mixin.tweaks.recipes;

import net.danygames2014.unitweaks.UniTweaks;
import net.danygames2014.unitweaks.tweaks.recipes.FuelLookup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceBlockEntity.class)
public class FurnaceBlockEntityMixin extends BlockEntity {
    @Inject(method = "getFuelTime", at = @At(value = "HEAD"), cancellable = true)
    public void injectFuelValues(ItemStack stack, CallbackInfoReturnable<Integer> cir){
        if(UniTweaks.RECIPES_CONFIG.furnaceFuels) {
            if (stack != null) {
                FuelLookup.FuelLookupEntry entry = FuelLookup.lookup.get(stack.getItem());

                if(entry != null){
                    if(entry.meta() == -1 || entry.meta() == stack.getDamage()) {
                        cir.setReturnValue(entry.fuelTime());
                    }
                }
            }
        }
    }
}
