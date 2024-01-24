package net.danygames2014.unitweaks.mixin.bugfixes.bowheldfix;

import net.danygames2014.unitweaks.UniTweaks;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BowItem.class)
public class BowItemMixin extends Item {
    public BowItemMixin(int id) {
        super(id);
    }

    @Override
    public boolean getIsHandheld() {
        if(UniTweaks.BUGFIXES_CONFIG.bowHeldFix){
            return true;
        }
        return super.getIsHandheld();
    }
}
