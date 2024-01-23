package net.danygames2014.unitweaks.mixin.tweaks.modernflintandsteel;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteel.class)
public class FlintAndSteelItemMixin {
    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIII)Z", shift = At.Shift.AFTER), cancellable = true)
    public void damageOnSuccesfulIgnite(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir){
        if(UniTweaks.TWEAKS_CONFIG.modernFlintAndSteel){
            stack.damage(1, user);
            cir.setReturnValue(true);
        }
    }

    @ModifyConstant(method = "useOnBlock", constant = @Constant(intValue = 1, ordinal = 1))
    public int disableVanillaDamage(int constant){
        if(UniTweaks.TWEAKS_CONFIG.modernFlintAndSteel){
            return 0;
        }
        return constant;
    }
}
