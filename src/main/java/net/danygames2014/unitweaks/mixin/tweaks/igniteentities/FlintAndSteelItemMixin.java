package net.danygames2014.unitweaks.mixin.tweaks.igniteentities;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.FlintAndSteel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FlintAndSteel.class)
public class FlintAndSteelItemMixin extends Item {
    public FlintAndSteelItemMixin(int id) {
        super(id);
    }

    @Override
    public void useOnEntity(ItemStack stack, LivingEntity entity) {
        if(UniTweaks.TWEAKS_CONFIG.allowIgnitingEntities){
            entity.fire += 100;
            stack.damage(1, null);
        }
        super.useOnEntity(stack, entity);
    }
}
