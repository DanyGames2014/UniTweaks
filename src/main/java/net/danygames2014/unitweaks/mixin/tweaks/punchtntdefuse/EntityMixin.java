package net.danygames2014.unitweaks.mixin.tweaks.punchtntdefuse;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow
    public World world;

    @Shadow
    public boolean dead;

    @Shadow
    public abstract void markDead();

    @Inject(method = "damage(Lnet/minecraft/entity/Entity;I)Z", at = @At("HEAD"), cancellable = true)
    protected void damage(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {}
}
