package net.danygames2014.unitweaks.mixin.tweaks.punchsheepforwool;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends Entity {
    @Shadow
    public abstract boolean isSheared();

    @Shadow
    public abstract void setSheared(boolean sheared);

    @Shadow
    public abstract int getColor();

    public SheepEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "damage", at = @At(value = "HEAD"))
    public void dropWoolOnHit(Entity attacker, int amount, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.OLD_FEATURES_CONFIG.punchSheepForWool) {
            if ((attacker instanceof PlayerEntity player) && !player.world.isRemote) {
                if (!isSheared()) {
                    this.setSheared(true);
                    int woolCount = random.nextInt(1, 4); // 1 - 3 wool
                    for (int j = 0; j < woolCount; j++) {
                        ItemEntity wool = this.dropItem(new ItemStack(Block.WOOL.id, 1, this.getColor()), 1.0F);
                        wool.velocityX += (random.nextFloat() - random.nextFloat()) * 0.1F;
                        wool.velocityY += random.nextFloat() * 0.05F;
                        wool.velocityZ += (random.nextFloat() - random.nextFloat()) * 0.1F;
                    }
                }
            }
        }
    }
}
