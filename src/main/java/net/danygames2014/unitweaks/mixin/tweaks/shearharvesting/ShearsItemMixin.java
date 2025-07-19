package net.danygames2014.unitweaks.mixin.tweaks.shearharvesting;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    @Inject(method = "postMine", at = @At("HEAD"))
    public void harvestCobwebAndGrass(ItemStack stack, int blockId, int x, int y, int z, LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.shearHarvesting && !player.world.isRemote) {
            ItemEntity itemEntity = null;

            if (blockId == Block.COBWEB.id) {
                itemEntity = new ItemEntity(player.world, x, y, z, new ItemStack(Block.COBWEB, 1));
            } else if (blockId == Block.GRASS.id) {
                itemEntity = new ItemEntity(player.world, x, y, z, new ItemStack(Block.GRASS, 1, 1));
            }

            if (itemEntity != null) {
                itemEntity.pickupDelay = 10;
                player.world.spawnEntity(itemEntity);
            }
        }
    }
}
