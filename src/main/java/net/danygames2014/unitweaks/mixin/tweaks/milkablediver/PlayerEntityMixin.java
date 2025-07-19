package net.danygames2014.unitweaks.mixin.tweaks.milkablediver;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin extends LivingEntity {
    public PlayerEntityMixin(World world) {
        super(world);
    }

    @Override
    public boolean interact(PlayerEntity player) {
        if(this.world.isRemote) {
            return false;
        }
        
        ItemStack hand = player.inventory.getSelectedItem();
        if (hand != null && hand.itemId == Item.BUCKET.id) {
            if (PlayerEntity.class.cast(this).name.equals("mine_diver")){
                player.inventory.setStack(player.inventory.selectedSlot, new ItemStack(Item.MILK_BUCKET));
                return true;
            }
        }
        
        return false;
    }
}
