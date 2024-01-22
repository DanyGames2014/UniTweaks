package net.danygames2014.unitweaks.mixin.tweaks.shiftplacing;

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
    @Shadow @Final protected Minecraft minecraft;

    @Redirect(method = "method_1713", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    public int shiftPlacing(World world, int x, int y, int z){
        if(this.minecraft.player.method_1373() && !(this.minecraft.player.getHand() == null) && UniTweaks.GAMEPLAY_CONFIG.shiftPlacing){
            return 0;
        }
        return world.getBlockId(x, y, z);
    }
}
