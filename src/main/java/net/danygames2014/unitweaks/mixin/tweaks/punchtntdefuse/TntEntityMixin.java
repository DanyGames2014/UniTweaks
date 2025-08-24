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

@Mixin(TntEntity.class)
public abstract class TntEntityMixin extends Entity {
    public TntEntityMixin(World world) {
        super(world);
    }

    @Override
    public boolean damage(Entity damageSource, int amount) {
        if(!world.isRemote){
            if(UniTweaks.OLD_FEATURES_CONFIG.punchTntToDefuse && !dead){
                super.damage(damageSource, amount);
                if(damageSource instanceof PlayerEntity){
                    markDead();
                    world.spawnEntity(new ItemEntity(this.world, this.x, this.y, this.z, new ItemStack(Block.TNT)));
                    return true;
                }
            }
        }
        return false;
    }
}
