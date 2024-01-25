package net.danygames2014.unitweaks.mixin.tweaks.hoegrassforseeds;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoeItem.class)
public class HoeItemMixin extends Item {
    public HoeItemMixin(int id) {
        super(id);
    }

    @Inject(method = "useOnBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlock(IIII)Z"))
    public void dropSeeds(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir){
        if(!world.isRemote){
            if(UniTweaks.OLD_FEATURES_CONFIG.hoeGrassForSeeds){
                if(Block.GRASS.getDroppedItemId(0, world.field_214) != -1){
                    ItemEntity seeds = new ItemEntity(world, x, y+1, z, new ItemStack(Item.SEEDS,1));
                    seeds.velocityY = 0.2f;
                    seeds.pickupDelay = 7; // Slightly shorter than normal
                    world.method_210(seeds);
                }
            }
        }
    }
}
