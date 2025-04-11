package net.danygames2014.unitweaks.mixin.tweaks.trapdoorplacement;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TrapdoorBlock.class, priority = 900)
public class TrapdoorBlockMixin extends Block {
    public TrapdoorBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "canPlaceAt", at = @At(value = "HEAD"), cancellable = true, require = 0)
    public void canPlaceAt(World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.allowTrapdoorsWithoutSupport) {
            cir.setReturnValue(super.canPlaceAt(world, x, y, z));
        }
    }

    @WrapOperation(method = "neighborUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;shouldSuffocate(III)Z"), require = 0)
    public boolean bamboozlingTheTrapdoor(World world, int x, int y, int z, Operation<Boolean> original) {
        if (UniTweaks.TWEAKS_CONFIG.allowTrapdoorsWithoutSupport) {
            return true;
        }
        return original.call(world, x, y, z);
    }
}
