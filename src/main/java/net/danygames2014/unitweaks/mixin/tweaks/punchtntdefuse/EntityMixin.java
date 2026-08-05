package net.danygames2014.unitweaks.mixin.tweaks.punchtntdefuse;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
    private void damage(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {
        if (world.isRemote) return;

        Entity entity = (Entity) (Object) this;
        if (!(entity instanceof TntEntity tntEntity)) return;
        if (!UniTweaks.OLD_FEATURES_CONFIG.punchTntToDefuse) return;

        if (dead) return;
        if (!(damageSource instanceof PlayerEntity)) return;

        markDead();
        world.spawnEntity(new ItemEntity(this.world, tntEntity.x, tntEntity.y, tntEntity.z, new ItemStack(Block.TNT)));
        cir.setReturnValue(true);
    }
}
