package net.danygames2014.unitweaks.mixin.tweaks.punchtntdefuse;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends EntityMixin {

    @Override
    protected void damage(Entity damageSource, int amount, CallbackInfoReturnable<Boolean> cir) {
        if (world.isRemote) return;

        TntEntity tntEntity = (TntEntity) (Object) this;
        if (!UniTweaks.OLD_FEATURES_CONFIG.punchTntToDefuse) return;

        if (dead) return;
        if (!(damageSource instanceof PlayerEntity)) return;

        markDead();
        world.spawnEntity(new ItemEntity(this.world, tntEntity.x, tntEntity.y, tntEntity.z, new ItemStack(Block.TNT)));
        cir.setReturnValue(true);
    }
}
