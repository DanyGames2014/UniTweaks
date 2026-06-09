package net.danygames2014.unitweaks.mixin.tweaks.milkablediver;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    public World world;

    @SuppressWarnings("CancellableInjectionUsage")
    @Inject(method = "interact", at = @At(value = "HEAD"), cancellable = true)
    protected void unitweaks$interact(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        
    }
}
