package net.danygames2014.unitweaks.mixin.tweaks.nofoodwastage;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FoodItem.class)
public class FoodItemMixin {
    @Inject(method = "use", at = @At(value = "HEAD"), cancellable = true)
    public void preventEatingWithFullHealth(ItemStack stack, World world, PlayerEntity player, CallbackInfoReturnable<ItemStack> cir){
        if(UniTweaks.GAMEPLAY_CONFIG.noFoodWastage && (player.health >= 20)){
            cir.setReturnValue(stack);
        }
    }
}
