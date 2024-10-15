package net.danygames2014.unitweaks.mixin.tweaks.shiftplacing;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public class class_70Mixin {
    @Shadow
    public PlayerEntity player;

    @Redirect(method = "interactBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlockId(III)I"))
    public int shiftPlacing(World world, int x, int y, int z) {
        if (this.player.isSneaking() && !(this.player.getHand() == null) && UniTweaks.GAMEPLAY_CONFIG.shiftPlacing) {
            return 0;
        }
        return world.getBlockId(x, y, z);
    }
}
