package net.danygames2014.unitweaks.mixin.tweaks.harvestablecobwebs;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SwordItem.class)
public class SwordItemMixin {
    @Inject(method = "postMine", at = @At("HEAD"))
    public void harvestCobweb(ItemStack stack, int blockId, int x, int y, int z, LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (UniTweaks.TWEAKS_CONFIG.harvestableCobwebs && !player.world.isRemote && blockId == Block.COBWEB.id) {
            ItemEntity itemEntity = new ItemEntity(player.world, x, y, z, new ItemStack(Item.STRING, 1));
            itemEntity.pickupDelay = 10;
            player.world.method_210(itemEntity);
        }
    }
}
