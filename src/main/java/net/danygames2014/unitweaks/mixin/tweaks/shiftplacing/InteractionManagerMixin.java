package net.danygames2014.unitweaks.mixin.tweaks.shiftplacing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.client.InteractionManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InteractionManager.class)
public class InteractionManagerMixin {
    @Shadow
    @Final
    protected Minecraft minecraft;

    @WrapOperation(method = "interactBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    public int shiftPlacing(World world, int x, int y, int z, Operation<Integer> original) {
        if (this.minecraft.player.isSneaking() && !(this.minecraft.player.getHand() == null) && UniTweaks.GAMEPLAY_CONFIG.shiftPlacing) {
            return 0;
        }
        return original.call(world, x, y, z);
    }
}
