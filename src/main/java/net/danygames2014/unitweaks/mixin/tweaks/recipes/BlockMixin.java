package net.danygames2014.unitweaks.mixin.tweaks.recipes;

import net.danygames2014.unitweaks.tweaks.recipes.RecipeListener;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/stat/Stats;initializeItemStats()V", shift = At.Shift.AFTER))
    private static void injectRecipes(CallbackInfo ci){
        RecipeListener.registerShapedRecipes();
        RecipeListener.registerShapelessRecipes();
    }
}
