package net.danygames2014.unitweaks.mixin.tweaks.rightclickarmor;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorItem.class)
public class ArmorItemMixin extends Item {
    @Shadow
    @Final
    public int field_2083;

    public ArmorItemMixin(int id) {
        super(id);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if(UniTweaks.GAMEPLAY_CONFIG.rightClickEquipArmor){
            if (user.inventory.armor[Math.abs(this.field_2083 - 3)] == null) {
                user.inventory.armor[Math.abs(this.field_2083 - 3)] = stack.copy();
                stack.count = 0;
            } else {
                ItemStack temp = user.inventory.armor[Math.abs(this.field_2083 - 3)];
                user.inventory.armor[Math.abs(this.field_2083 - 3)] = stack.copy();
                return temp;
            }
        }
        return stack;
    }
}
