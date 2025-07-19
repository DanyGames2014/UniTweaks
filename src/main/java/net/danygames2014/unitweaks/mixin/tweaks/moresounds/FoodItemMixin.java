package net.danygames2014.unitweaks.mixin.tweaks.moresounds;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(FoodItem.class)
public class FoodItemMixin {
    @Unique
    private static final Random random = new Random();

    @Inject(method = "use", at = @At(value = "HEAD"))
    public void burpOnEat(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (UniTweaks.FEATURES_CONFIG.moreSounds) {
            world.playSound(user, "unitweaks:random.eat", 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            if (random.nextInt(0, 10) > 3) {
                world.playSound(user, "unitweaks:random.burp", 0.5F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }
}
